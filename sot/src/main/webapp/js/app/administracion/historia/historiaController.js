app.controller("historiaCtrl",
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

					$scope.hoy = new Date();
					$scope.tableros = null;
					$scope.tableros = [];
					$scope.mostrar = false;
					$scope.ultimo = null;
					$scope.buscarFiltro = null;
					$scope.data = [];

					$scope.init = function() {
					}
					
					$scope.columnas=[{nombre:"Hora"},{nombre:"Diferencia"}];
					
					$scope.imprimir=function(tablero){
						console.log(tablero);
						
						//$scope.cargaPdf = "http://localhost:8080/api/geotab/reporte/" + tablero.numeroVuelta + "/" + tablero.codigoDispositivo;

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


					$scope.traertablero = function() {
						if ($scope.dt==null) {
							return;
						}
						sincronizarFactory.sincronizarTableroHistorico(
								$scope.dt
						).then(function(resp) {
							$scope.tabla=resp.objeto;
							$scope.zonas=$scope.tabla.zonas;
							$scope.dispositivos=$scope.tabla.dispositivos;
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

					 $scope.today = function() {
					      $scope.dt = null;
					    };
					    $scope.today();

					    $scope.clear = function () {
					      $scope.dt = null;
					    };

					    // Disable weekend selection
					    $scope.disabled = function(date, mode) {
					      return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
					    };

					    $scope.toggleMin = function() {
					      $scope.minDate = $scope.minDate ? null : new Date();
					    };
					    $scope.toggleMin();

					    $scope.open = function($event) {
					      $event.preventDefault();
					      $event.stopPropagation();

					      $scope.opened = true;
					    };

					    $scope.dateOptions = {
					      formatYear: 'yy',
					      startingDay: 1,
					      class: 'datepicker'
					    };

					    $scope.initDate = new Date('2016-15-20');
					    $scope.formats = ['dd-MMMM-yyyy', 'dd/MM/yyyy', 'dd.MM.yyyy', 'shortDate'];
					    $scope.format = $scope.formats[1];
				} ]);
