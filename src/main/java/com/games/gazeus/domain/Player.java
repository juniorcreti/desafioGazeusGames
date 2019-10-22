package com.games.gazeus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;



	public Player(String name) {
		super();
		this.name = name;
	}

	public Player() {
		super();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@NotEmpty(message = "O nome do Player é obrigatório")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
