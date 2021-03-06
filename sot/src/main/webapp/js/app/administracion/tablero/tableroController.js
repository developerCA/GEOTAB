app.controller("tableroCtrl",
		[
				"$scope",
				"$filter",
				"$interval",
				"toaster",
				"tableroFactory",
				"sincronizarFactory",
				"_","$modal","$rootScope",
				function($scope, $filter, $interval, toaster,tableroFactory,
						sincronizarFactory,_,$modal,$rootScope) {
					$scope.tableros = null;
					

					$scope.tableros = [];
					$scope.mostrar = false;
					$scope.ultimo = null;
					
					$scope.data = [];

					$scope.init = function() {
						$scope.iniciaCronTablero();
					}
					
					$scope.columnas=[{nombre:"Hora"},{nombre:"Diferencia"}];
					$scope.bandera = 100;

				
					var stopTablero;
					$scope.stopTablero = function() {
						if (angular.isDefined(stopTablero)) {
							$interval.cancel(stopTablero);
							stopTablero = undefined;
						}
					};
					
					$scope.imprimir=function(tablero){
						console.log(tablero);
						

						var reporte={vuelta:tablero.numeroVuelta,codigoDispositivo:tablero.codigoDispositivo};
						

						$rootScope.nombreArchivo=reporte.nombre;
						sincronizarFactory.generarReporte(reporte).then(function(resp) {
							if (resp.estado){
								var reporte=resp.objeto;
								$rootScope.nombreArchivo=reporte.nombre;
								console.log($rootScope.nombreArchivo);
								var ventana=$modal.open({
									templateUrl:'visualizador.html',
									controller:'visualizadorCtrl',
									size:'lg',
								})
							}
						});

					};

					$scope.iniciaCronTablero = function() {

						if (angular.isDefined(stopTablero))
							return;

						stopTablero = $interval(function() {
							if ($scope.bandera == 100) {
								console.log("Actualizar tablero")

								$scope.traertablero();
							} else {
								$scope.stopTablero();
							}
						}, 16000);
					};

					$scope.traertablero = function() {
						sincronizarFactory.sincronizarTablero().then(function(resp) {
							if (resp.objeto.zonas == []) {
								return;
							}

							$scope.tabla=resp.objeto;
							$scope.zonas=$scope.tabla.zonas;
							$scope.dispositivos=$scope.tabla.dispositivos;
							//console.log($scope.tabla);
						});
					};
					
					$scope.editarLocalizacion=function(vLocalizacion){
						vLocalizacion.horaProgramada=vLocalizacion.horaProgramadaTmp;

						sincronizarFactory.sincronizarHoraProgramada(vLocalizacion).then(function(resp) {
							vLocalizacion.muestra=false;
							vLocalizacion.horaProgramadaTmp=null;
							toaster
							.pop("success", "Hora Programada",
									"Se registro correctamente la hora programada, muy pronto se actualizará el tablero!");


						},function() {
							toaster
							.pop("error", "Hora Programada",
									"Es posible que el formato este incorrecto!");

						    
						}
						);
						
						
						
						
						
					};
					$scope.cancelaLocalizacion=function(vLocalizacion){
						vLocalizacion.muestra=false;
						vLocalizacion.horaProgramadaTmp=vLocalizacion.horaProgramada;
					}
					
					
					
					
				} ]);
