package com.games.gazeus.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Service;
import com.games.gazeus.domain.dto.TopDTO;

@Service
public class TopDTOService {

	@PersistenceContext
	private EntityManager em;

	public List<TopDTO> listarTop(String game, int n) {
		TypedQuery<TopDTO> q = em.createQuery(
				"SELECT new TopDTO( p.id as id, p.name as nameplayer , g.name as namegame, sum(g.points) as totalpoints) "
						+ "FROM Match topdto INNER JOIN topdto.game g INNER JOIN topdto.player p WHERE g.name='" + game
						+ "' group by p.id , p.name, g.name ORDER BY totalpoints DESC ",
				TopDTO.class);
		q.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
		if(n!=0) {q.setMaxResults(n);}
		List<TopDTO> lista = q.getResultList();
		return lista;
	}
}
