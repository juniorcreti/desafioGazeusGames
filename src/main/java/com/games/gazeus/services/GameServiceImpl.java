package com.games.gazeus.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.games.gazeus.domain.Game;
import com.games.gazeus.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	GameRepository gameRepository;
	@PersistenceContext
EntityManager em;
	@Override
	public Game cadastrarGame(Game game) {

		return gameRepository.save(game);
	}

	@Override
	public List<Game> listarAllGame() {

		return gameRepository.findAll();
	}

	@Override
	public Optional<Game> findByNameAndStatus(String name, String status) {

		return gameRepository.findByNameAndStatus(name, status);
	}
	
	public Game buscaGame(String name, String status) {
	 CriteriaBuilder cb = em.getCriteriaBuilder();
     CriteriaQuery<Game> cq = cb.createQuery(Game.class);

     Root<Game> book = cq.from(Game.class);
     Predicate authorNamePredicate = cb.equal(book.get("name"), name);
     Predicate titlePredicate = cb.equal(book.get("status"), status);
     cq.where(authorNamePredicate, titlePredicate);

     TypedQuery<Game> query = em.createQuery(cq);
     return query.getResultList().get(0);
}

}
