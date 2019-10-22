package com.games.gazeus.domain.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopDTO {
	@Id
	private long id;
	private String namePlayer;
	private String nameGame;
	private long totalPoints;

	public TopDTO(long id, String namePlayer, String nameGame, long totalPoints) {

		this.id = id;
		this.namePlayer = namePlayer;
		this.nameGame = nameGame;
		this.totalPoints = totalPoints;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}

	public String getNameGame() {
		return nameGame;
	}

	public void setNameGame(String nameGame) {
		this.nameGame = nameGame;
	}

	public long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(long totalPoints) {
		this.totalPoints = totalPoints;
	}

}
