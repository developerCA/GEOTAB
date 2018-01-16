app.controller("tableroCtrl",
		[
				"$scope",
				"$filter",
				"$interval",
				"tableroFactory",
				"sincronizarFactory",
				function($scope, $filter, $interval, tableroFactory,
						sincronizarFactory) {
					$scope.tableros = null;
					$scope.beginningOfDay = new Date();
					$scope.endOfDay = new Date();
					$scope.list = [ "bB9", "b21", "b23", "b82" ];

					$scope.tableros = [];
					$scope.mostrar = false;
					$scope.ultimo = null;
					$scope.p = 0;
					$scope.data = [];

					$scope.init = function() {
						// $scope.beginningOfDay.setHours(0, 0, 0, 0);
						// $scope.endOfDay.setHours(23, 59, 59, 59);
						// $scope.traertablero();

						console.log("inicia proed");
						$scope.iniciaCron();
					}

					$scope.bandera = 100;

					var stop;
					$scope.iniciaCron = function() {

						if (angular.isDefined(stop))
							return;

						stop = $interval(function() {
							if ($scope.bandera == 100) {

								sincronizarFactory
										.sincronizarFechasLozalizaciones()
										.then(function(r) {
											console.log(r);
											var fechaProceso=r.objeto;
								
											if (r.estado){
												
												api.call("Get", {
												          typeName: "LogRecord",
														   search: {
														   fromDate: fechaProceso.fechaInicio,
														   toDate: fechaProceso.fechaFinal
														            }
												}, function(resultDatos) {
												
												 var dtoDatos={fechaDispositivo:fechaProceso,listaDatos:resultDatos};
												
													sincronizarFactory.sincronizarLozalizaciones(
															dtoDatos
										    			).then(function(r) {


										    		});
												
												   
												}, function(e) {
												    console.error("Failed:", e);
												});
												
												
											}
										});

							} else {
								$scope.pararCron();
							}
						}, 20000);
					};

					// 200000
					$scope.pararCron = function() {
						if (angular.isDefined(stop)) {
							$interval.cancel(stop);
							stop = undefined;
						}
					};

					$scope.$watch("tableros", function() {
						$scope.data = $scope.tableros;
						console.log($scope.data);
					});

					$scope.traertablero = function() {
						// $scope.tableros = [];
						$scope.p = 0;
						for (var i = 0; i < $scope.list.length; i++) {
							api.call("Get", {
								typeName : "LogRecord",
								search : {
									deviceSearch : {
										id : $scope.list[i]
									},
									fromDate : $scope.beginningOfDay
											.toISOString(),
									toDate : $scope.endOfDay.toISOString()
								}
							}, function(result) {
								if ($scope.tableros.length == 4) {
									$scope.tableros = [];
								}
								$scope.p++;
								var ii;
								if (result !== undefined) {
									ii = result.length - 1;
									// console.log(result);
									$scope.tableros.push(result[ii]);
									// if ($scope.tableros.length == 4) {
									// console.log($scope.tableros);
									// $scope.mostrar = true;
									// }

								} else {
									alert("Could not retrieve trip");
								}
							}, function(error) {
								alert(error);
							});
						}
					};
				} ]);
