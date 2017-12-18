app
		.controller(
				"portadaCtrl",
				[
						"$scope",
						"portadaFactory",
						"toaster",
						"$timeout",
						"$modal",
						function($scope, portadaFactory, toaster, $timeout,
								$modal) {

							$scope.init = function() {
							
								$scope.splineOpciones = {
									colors : '"' + $scope.app.color.info + '"',
									ticks : [

									[ 1, 'Aan' ], [ 2, 'Feb' ], [ 3, 'Mar' ],
											[ 4, 'Apr' ], [ 5, 'May' ],
											[ 6, 'Jun' ], [ 7, 'Jul' ],
											[ 8, 'Aug' ], [ 9, 'Sep' ],
											[ 10, 'Oct' ], [ 11, 'Nov' ],
											[ 12, 'Dec' ]

									]
								};

								$scope.cantSuscripciones();
								$scope.cantNotaPedido();
								$scope.cantFacturasPorEnviar();
								$scope.cantNotaPedidoEnviadas();
								$scope.traerProcesos();
								$scope.suscripciones = [];
								$scope.ventasA = [];
								$scope.traerLosMasVendidos();

								$scope.anios = [ {
									id : 1,
									nombre : "2010",
									value : 2010
								}, {
									id : 2,
									nombre : "2011",
									value : 2011
								}, {
									id : 3,
									nombre : "2012",
									value : 2012
								}, {
									id : 4,
									nombre : "2013",
									value : 2013
								}, {
									id : 5,
									nombre : "2014",
									value : 2014
								}, {
									id : 6,
									nombre : "2015",
									value : 2015
								}, {
									id : 7,
									nombre : "2016",
									value : 2016
								}, {
									id : 8,
									nombre : "2017",
									value : 2017
								}, {
									id : 9,
									nombre : "2018",
									value : 2018
								}, {
									id : 10,
									nombre : "2019",
									value : 2019
								}, {
									id : 11,
									nombre : "2020",
									value : 2020
								} ];

								$scope.anioSeleccionado = $scope.anios[5];
								$scope.ventasAnuales();
								
							}

							$scope.cantSuscripciones = function() {

								portadaFactory.cantSuscripciones().then(
										function(r) {

											$scope.cantSuscrip = r;

											// console.log($scope.cantSuscrip);
										})
							};

							$scope.cantNotaPedido = function() {

								portadaFactory.cantNotaPedido().then(
										function(r) {

											$scope.cantNotPedido = r;
										})
							};

							$scope.cantFacturasPorEnviar = function() {

								portadaFactory.cantFacturasPorEnviar().then(
										function(r) {

											$scope.cantfacturasEnviar = r;
										})
							};
							$scope.cantNotaPedidoEnviadas = function() {

								portadaFactory.cantNotaPedidoEnviadas().then(
										function(r) {

											$scope.cantNotaPedidoEnviada = r;
										})
							};

							$scope.traerProcesos = function() {

								portadaFactory
										.traerProcesos()
										.then(
												function(r) {
													var fechas = new Array();
													var suscripcionesProcesada = new Array();
													var suscripcionesDesactivadas = new Array();
													var suscripcionesRenovadas = new Array();
													var totalPorFecha = new Array();

													$scope.procesos = r;

													for (var i = 0; i < $scope.procesos.length; i++) {
														var pos = buscarItem(
																fechas,
																$scope.procesos[i].fecha);
														if (pos >= 0) {

															switch ($scope.procesos[i].tipo) {
															case "Procesadas":
																suscripcionesProcesada[pos] = $scope.procesos[i].cantidad

																break;
															case "Renovadas":
																suscripcionesRenovadas[pos] = $scope.procesos[i].cantidad
																break;
															case "Desactivadas":
																suscripcionesDesactivadas[pos] = $scope.procesos[i].cantidad
																break;
															}

														} else {
															fechas[i] = $scope.procesos[i].fecha;
															switch ($scope.procesos[i].tipo) {
															case "Procesadas":
																suscripcionesProcesada[i] = $scope.procesos[i].cantidad
																suscripcionesRenovadas[i] = 0;
																suscripcionesDesactivadas[i] = 0;

																break;
															case "Renovadas":
																suscripcionesRenovadas[i] = $scope.procesos[i].cantidad
																suscripcionesProcesada[i] = 0;
																suscripcionesDesactivadas[i] = 0;
																break;
															case "Desactivadas":
																suscripcionesDesactivadas[i] = $scope.procesos[i].cantidad
																suscripcionesProcesada[i] = 0;
																suscripcionesRenovadas[i] = 0;

																break;

															}
														}

													}

													for (var i = 0; i < fechas.length; i++) {
														totalPorFecha[i] = (suscripcionesProcesada[i]
																+ suscripcionesRenovadas[i] + suscripcionesDesactivadas[i])

													}
													$scope.fechas = fechas;
													$scope.suscripcionesProcesada = suscripcionesProcesada;
													$scope.suscripcionesRenovadas = suscripcionesRenovadas;
													$scope.suscripcionesDesactivadas = suscripcionesDesactivadas;
													$scope.totalPorFecha = totalPorFecha;

												})
							};

							function buscarItem(lista, valor) {
								var ind, pos;
								for (ind = 0; ind < lista.length; ind++) {
									if (lista[ind] == valor)
										break;
								}
								pos = (ind < lista.length) ? ind : -1;
								return (pos);
							}

							$scope.traerLosMasVendidos = function() {
								$scope.suscripciones = [];
								portadaFactory
										.traerLosMasVendidos()
										.then(
												function(r) {

													$scope.mv = r;

													for (var i = 0; i < $scope.mv.length; i++) {
														$scope.suscripciones[i] = {
															label : String($scope.mv[i].nombre),
															data : $scope.mv[i].cantidad
														};

													}
												})

							};

							$scope.ventasAnuales = function() {
								portadaFactory.ventasAnuales(
												$scope.anioSeleccionado.value)
										.then(
												function(r) {
													var mesMostrar = new Array();
													var costoMostrar = new Array();
													$scope.anuales = r;
													$scope.ventasA = [];
													if (($scope.anuales.objeto.length > 0)
															|| ($scope.anuales.objeto.length != null)) {
														for (var i = 0; i < $scope.anuales.objeto.length; i++) {
															var mes = ($scope.anuales.objeto[i].fechaInicio)
																	.split("-");
															var pos = buscarItem(
																	mesMostrar,
																	mes[1]);
															if (pos >= 0) {
																costoMostrar[pos] = (costoMostrar[pos] + $scope.anuales.objeto[i].costo);
															} else {
																mesMostrar[i] = mes[1];
																costoMostrar[i] = $scope.anuales.objeto[i].costo;
															}
														}
														var meses = [ "01",
																"02", "03",
																"04", "05",
																"06", "07",
																"08", "09",
																"10", "11",
																"12" ]
														for (var i = 0; i < meses.length; i++) {
															var pos = buscarItem(
																	mesMostrar,
																	meses[i]);
															if (pos >= 0) {
																$scope.ventasA[i] = [
																		mesMostrar[pos],
																		costoMostrar[pos] ];
															} else {
																$scope.ventasA[i] = [
																		meses[i],
																		0 ];
															}
														}

													}

												})
							};

						} ])