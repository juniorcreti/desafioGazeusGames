<table>
	<th> Lista de Players
<img src="https://user-images.githubusercontent.com/4553131/67336746-3e8ee980-f4fc-11e9-864e-02893f86e982.png" width="1400" height="50">
	</th><
# Gazeus Games TOP Players

API REST desenvolvida em Java com Spring Boot e JPA - Postgres para cadastro de partifdas dos games da Gazeus
Também será possível cadastrar a partida quantas vezes quiser pois somos viciados em GAMES!!!!.


## Pré-requisitos

- Java 
- Maven 
- Postgres
- AngularJS

## Documentação API

- [GET]

--- /api/games/Retorna uma lista de Games
  
  [
  {
    "id": 20,
    "name": "Truco",
    "status": "VICTORY",
    "points": 200
  },
  {
    "id": 21,
    "name": "Truco",
    "status": "DEFEAT",
    "points": -50
  },
  {
    "id": 22,
    "name": "Truco",
    "status": "DRAW",
    "points": 100
  },
  {
    "id": 23,
    "name": "Buraco",
    "status": "VICTORY",
    "points": 100
  },
  {
    "id": 24,
    "name": "Buraco",
    "status": "DEFEAT",
    "points": -20
  },
  {
    "id": 25,
    "name": "Buraco",
    "status": "DRAW",
    "points": 50
  },
  {
    "id": 26,
    "name": "Domino",
    "status": "VICTORY",
    "points": 150
  },
  {
    "id": 27,
    "name": "Domino",
    "status": "DEFEAT",
    "points": -30
  },
  {
    "id": 28,
    "name": "Domino",
    "status": "DRAW",
    "points": 75
  }
]
- [POST]
--- /api/{game}/match/player/{playerId}/ Cadastra Partidas do game Informado
{
  "data": {
    "id": 282,
    "game": {
      "id": 20,
      "name": "Truco",
      "status": "VICTORY",
      "points": 200
    },
    "player": {
      "id": 17,
      "name": "Rodrigo Neves"
    },
    "total": 0
  },
  "erros": null
}
- [POST]  
--- /api/{game}/player/{id} Retorna a posição players no TOP do game informado
{
  "data": 3,
  "erros": null
}
  
- [GET]
--- /api/{game}/topLista o Top players do game informado
 {
  "data": [
    {
      "id": 18,
      "namePlayer": "Carlos Estigarribia",
      "nameGame": "Truco",
      "totalPoints": 1900
    },
    {
      "id": 17,
      "namePlayer": "Rodrigo Neves",
      "nameGame": "Truco",
      "totalPoints": 1600
    },
    {
      "id": 16,
      "namePlayer": "Rodrigo Mello",
      "nameGame": "Truco",
      "totalPoints": 1000
    },
    {
      "id": 19,
      "namePlayer": "Jose Costa Flor Junior",
      "nameGame": "Truco",
      "totalPoints": 1000
    },
    {
      "id": 14,
      "namePlayer": "Paula Marcolino",
      "nameGame": "Truco",
      "totalPoints": 600
    },
    {
      "id": 15,
      "namePlayer": "Joao Felipe Ghizi Assad",
      "nameGame": "Truco",
      "totalPoints": 150
    }
  ],
  "erros": null
}
- [GET]
--- /api/player/listarPlayer Lista Players cadastrados
[
  {
    "id": 14,
    "name": "Paula Marcolino"
  },
  {
    "id": 15,
    "name": "Joao Felipe Ghizi Assad"
  },
  {
    "id": 16,
    "name": "Rodrigo Mello"
  },
  {
    "id": 17,
    "name": "Rodrigo Neves"
  },
  {
    "id": 18,
    "name": "Carlos Estigarribia"
  },
  {
    "id": 19,
    "name": "Jose Costa Flor Junior"
  }
]
- [POST]
--- /api/player/ cadastraPlayer
Teste na API e Documentação http://localhost:8050/swagger-ui.html. 

Criação do Front End com Angular JS para teste http://localhost:8050/

Neste frontend foram manipulados alguns objetos do AngularJS, com objetivo de desmostrar meu conhecimento na linguagem.



<table>
	<th> Lista de Players
<img src="https://user-images.githubusercontent.com/4553131/67335884-caa01180-f4fa-11e9-919b-5d7f427c78af.png" width="451" height="300">
	</th><th >
 Lista Top Players 
<img src="https://user-images.githubusercontent.com/4553131/67336281-72b5da80-f4fb-11e9-8460-e5e82848c48c.png" width="451" height="300">
</th><th>
 Lista Top Players com seleção de players
<img src="https://user-images.githubusercontent.com/4553131/67336341-8cefb880-f4fb-11e9-97d8-83982dbcc86a.png" width="411" height="300">
</th>
<th>
 Lista Top Playes com seleção de Games
<img src="https://user-images.githubusercontent.com/4553131/67336374-9da02e80-f4fb-11e9-8946-d87bd70b3b6a.png" width="411" height="300">
</th>
