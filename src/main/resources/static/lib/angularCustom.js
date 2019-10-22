var app = angular.module("listaPlayers", [ "ngMessages" ]);
angular.module("listaPlayers").controller(
		"listaPlayersCtrl",
		function($scope, $http) {
			var app = angular.module("listaPlayers", [ "ngMessages" ]);
			$scope.loading = 0;
			$scope.app = "Lista de Players";
			$scope.players = [];
			$scope.planetaI = {};

			var carregarplayers = function() {
				$scope.showLoader = true;
				$http.get("http://localhost:8050/api/player/").success(
						function(players) {
							$scope.players = players;
							$scope.showLoader = false;
						}).error(function(players, status) {
					$scope.message = "Aconteceu um problema: " + players;
				});
			};

			$scope.adicionarPlayer = function(player) {
				$scope.showLoader = true;
				console.log(player)
				$http.post("http://localhost:8050/api/player/", player)
						.success(function(player) {
							delete $scope.playerI;
							$scope.contatoForm.$setPristine();
							carregarplayers();
							$scope.showLoader = false;
						});
			};

			carregarplayers();

		});