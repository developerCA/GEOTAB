app.controller("tableroCtrl",
		[
				"$scope",
				"$filter",
				"$interval",
				"tableroFactory",
				"sincronizarFactory",
				"_",
				function($scope, $filter, $interval, tableroFactory,
						sincronizarFactory,_) {
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
						sincronizarFactory.sincronizarTablero().then(function(resp) {
						
							$scope.tablero=resp.objeto;
							console.log($scope.tablero);
							$scope.zonas=$scope.tablero.zonas;
							
						    /*$scope.zonas= _.groupBy($scope.tablero,function(data){
						    	return data.codigoZona,data.zona;
						    });*/
						    
						    
							console.log($scope.zonas);

						});
					}
					
					
					
					
				} ]);
