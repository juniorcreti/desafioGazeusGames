var app = angular.module("listaPlayers", [ "ngMessages" ]);
angular.module("listaPlayers").controller(
		"listaPlayersCtrl",
		function($scope, $http) {
			$scope.gamesViews = [ {
				nome : "Buraco"
			}, {
				nome : "Domino"
			}, {
				nome : "Truco"
			} ];
			$scope.loading = 0;
			$scope.app = "Lista de TOP Players";
			$scope.games = [];
			$scope.playerslist = [];
			$scope.Match = {};
			langKey = $scope.gamesView;
			
			var carregarplayers = function() {
				$scope.showLoader = true;
				$http.get("http://localhost:8050/api/player/").success(
						function(playerslist) {
							$scope.playerslist = playerslist;
							$scope.showLoader = false;
						}).error(function(playerslist, status) {
					$scope.message = "Aconteceu um problema: " + playerslist;
				});
			};

			$scope.update = function(gameName) {

				$scope.showLoader = true;

				$http.get("http://localhost:8050/api/" + gameName + "/top?n=0")
						.success(function(data) {

							$scope.data = data;
							delete $scope.MatchI;
							$scope.gamesView = ""
							$scope.contatoForm.$setPristine();

							$scope.showLoader = false;
						}).error(
								function(data, status) {
									$scope.message = "Aconteceu um problema: "
											+ data;
								});
			}

			

			var carregarTopPlayers = function() {
				$scope.showLoader = true;
				$http.get("http://localhost:8050/api/Truco/top?n=0").success(
						function(data) {
							$scope.data = data;
							$scope.showLoader = false;
						}).error(function(data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
			};

			var carregarGames = function() {
				$scope.showLoader = true;
				$http.get("http://localhost:8050/api/games/").success(
						function(games) {
							$scope.games = games;
							$scope.showLoader = false;
						}).error(function(games, status) {
					$scope.message = "Aconteceu um problema: " + games;
				});
			};

			$scope.adicionarMatch = function(MatchI) {
				$scope.showLoader = true;
				$scope.contatoForm.$setPristine();
				
				var game = MatchI.game.name;
				$http.post(
						"http://localhost:8050/api/" + MatchI.game.name
								+ "/match/player/" + MatchI.player.id
								+ "/?status=" + MatchI.game.status, MatchI)
						.success(function(player) {
						
							delete $scope.MatchI;
							$scope.contatoForm.$setPristine();
							
							$scope.update(game);

							$scope.showLoader = false;
						});
			};
			carregarplayers();
			carregarGames();
			carregarTopPlayers();

		});