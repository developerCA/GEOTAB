app
		.controller(
				"suscripcionCtrl",
				[
						"$scope",
						"notaPedidoFactory",
						"suscripcionFactory",
						"modalService",
						"commonService",
						"toaster",
						"$timeout",
						"$modal",
						function($scope, notaPedidoFactory, suscripcionFactory,
								modalService, commonService, toaster, $timeout,
								$modal) {

							$scope.nuevo = false;

							$scope.init = function() {
								$scope.traerNotasPedido();

							}

							$scope.toDate = function(fecha) {
								if (!(fecha == null)) {
									return new Date(fecha).toLocaleDateString();
								}
							};

							$scope.traerNotasPedido = function() {
								notaPedidoFactory.listRegistradas().then(
										function(r) {
											$scope.notasPedido = r;
										});

							};

							$scope.generarSuscripcion = function(item) {

								notaPedidoFactory.generaSuscripcion(item.id)
										.then(function(r) {
											$timeout(function() {
												//console.log(r);
												$scope.dto = r;
												$scope.nuevo = true;

											});

										});
							}

							$scope.guardarSuscripcion = function() {

								if (!$scope.validarOrdenCliente()) {
									toaster
											.pop("error", "Orden de Trabajo",
													"Es necesario cargar la orden de trabajo del cliente");
									return;
								}

								if ($scope.validarResponsables()) {

									notaPedidoFactory
											.guardarSuscripcion($scope.dto)
											.then(
													function(r) {
														$timeout(function() {
															$scope.dto = r;

															if (r.estado == true) {
																toaster
																		.pop(
																				"info",
																				"Orden de Trabajo",
																				"La orden de trabjo se gestionó existosamente");
																$scope
																		.traerNotasPedido();
																$scope.nuevo = false;
															} else {
																$scope.msg = "Error: "
																		+ r.mensaje;
																toaster
																		.pop(
																				"danger",
																				"Orden de Trabajo",
																				r.mensaje);

															}

														});

													});
								} else {
									toaster
											.pop(
													"error",
													"Orden de Trabajo",
													"Es necesario ingresar al menos un responsable para cada servicio ejecutado en la orden de trabajo");
								}

							}

							$scope.mirarEstadoResponsable = function(someValue) {

								if (someValue == null) {
									return "btn btn-default btn-sm";

								}

								else {
									return "btn btn-success btn-sm";
								}

							}

							$scope.validarOrdenCliente = function() {
								var val = true;
								console.log($scope.dto);
								
								if (null == $scope.dto.notaPedido.archivo) {
									val = false;
								}
								return val;
							};

							$scope.validarResponsables = function() {

								for (var i = 0; i <= $scope.dto.detalleServicio.length - 1; i++) {
									var servicio = $scope.dto.detalleServicio[i];
									// console.log(servicio);

									for (var j = 0; j <= servicio.listaTareas.length - 1; j++) {
										var tarea = servicio.listaTareas[j];
										// console.log(tarea);
										if (tarea.responsable == null) {
											return false;
										}

									}
								}

								return true;
							};
							$scope.revisarNotaPedido = function(size) {
								var modalInstance = $modal
										.open({
											templateUrl : 'tpl/app/administracion/generasuscripcion/modal.html',
											controller : 'ModalInstanceCtrl',
											size : size,
											resolve : {
												items : function() {
													return $scope.items;
												}
											}
										});

								modalInstance.result
										.then(
												function(selectedItem) {
													$scope.selected = selectedItem;

													$scope
															.registrarRevisionNotaPedido($scope.selected);

												},
												function() {
													console
															.log('Modal dismissed at: '
																	+ new Date());
												});
							};

							$scope.registrarRevisionNotaPedido = function(text) {

								var item = $scope.dto.notaPedido;
								item.observacion = text;

								notaPedidoFactory
										.revisaNotaPedido(item)
										.then(
												function(r) {

													$timeout(function() {
														$scope.nuevo = false;

														if (r.estado == true) {
															$scope
																	.traerNotasPedido();

															$scope.msg = "Se marco la orden de trabajo como revisada ";
															toaster.pop(
																	"success",
																	$scope.msg);
														} else {
															$scope.msg = "Error: "
																	+ r.mensaje;
															toaster.pop(
																	"error",
																	$scope.msg);
														}

													});

												});

							}

							$scope.crearArchivo = function(tarea, index) {

								var modalInstance = $modal
										.open({
											templateUrl : 'tpl/app/administracion/suscripciones/modalCarga.html',
											controller : 'ModalInstanceCargaDatosCtrl',
											size : 'lg',
											resolve : {
												tareaOpt : function() {
													return tarea;
												}
											}
										});

								modalInstance.result.then(function(tareaOpt) {

									tarea.archivoTarea = tareaOpt.archivoTarea;
									tarea.archivoReal = tareaOpt.archivoReal;

								})

							};

							$scope.ejecutar = function(r) {
								$scope.dtoPropagar = {
									dto : $scope.dto,
									tarea : r
								};

								var modalInstance = $modal
										.open({
											templateUrl : 'tpl/app/administracion/suscripciones/propagar.html',
											controller : 'ModalInstanceCtrlPropagar',
											size : 'lg',
											resolve : {
												dtoPropagar : function() {
													return $scope.dtoPropagar;
												}
											}
										});

								modalInstance.result
										.then(function(selectedItem) {
											console.log(selectedItem);

											// $scope.dto = selectedItem;
										})
							};

							$scope.tecnicos = function(t) {

								$scope.tareaSelecionada = t;

								var modalInstance = $modal
										.open({
											templateUrl : 'tpl/app/administracion/generasuscripcion/tecnicos.html',
											controller : 'ModalInstanceCtrlTecnicos',
											size : 'md',
											resolve : {
												tarea : function() {
													return $scope.tareaSelecionada;
												}
											}
										});

								modalInstance.result.then(function(tarea) {

									console.log(tarea);

								})

							};

							$scope.cancelarItem = function() {
								$scope.nuevo = false;
							}

							$scope.eliminarItem = function(item) {

								notaPedidoFactory
										.eliminar(item)
										.then(
												function(r) {

													$timeout(function() {
														$scope.nuevo = false;

														if (r.estado == true) {
															$scope
																	.traerNotasPedido();

															$scope.msg = "Eliminó satisfactoriamente";
															toaster.pop(
																	"success",
																	$scope.msg);
														} else {
															$scope.msg = "Error: "
																	+ r.mensaje;
															toaster.pop(
																	"error",
																	$scope.msg);
														}

													});

												});
							};

						} ]);

app.controller('ModalInstanceCtrl', [ '$scope', '$modalInstance',
		function($scope, $modalInstance) {

			$scope.ok = function() {

				$modalInstance.close($scope.htmlVariable);
			};

			$scope.cancel = function() {
				$modalInstance.dismiss('cancel');
			};
		} ]);

app.controller('ModalInstanceCtrlEnlace', [
		'$scope',
		'$modalInstance',
		'clienteFactory',
		'profesionResource',
		'paisFactory',
		'provinciaFactory',
		'ciudadFactory',
		'notaPedidoFactory',
		'toaster',
		'$timeout',
		'suscripcion',
		function($scope, $modalInstance, clienteFactory, profesionResource,
				paisFactory, provinciaFactory, ciudadFactory,
				notaPedidoFactory, toaster, $timeout, suscripcion) {
			$scope.suscripcion = suscripcion;

			$scope.buscaEnlances = function() {

				$scope.traerProfesiones();
				$scope.traerPaises();
				$scope.traerProvincias();
				$scope.traerCiudades();

				clienteFactory.listEnlaces($scope.suscripcion.clienteP.id)
						.then(function(r) {
							$timeout(function() {

								$scope.items = r;

							});

						});

			}

			$scope.cancelar = function() {

				$scope.editing = false;
				$scope.visualizar = false;
				$scope.item = {};

			}
			$scope.selectItem = function(item) {

				angular.forEach($scope.items, function(item) {
					item.selected = false;

				});

				$scope.editing = false;

				$scope.visualizar = true;

				$scope.item = item;

				$scope.item.selected = true;
			};

			$scope.createItem = function() {
				$scope.visualizar = true;
				$scope.editing = true;
				$scope.item = {};
			};

			$scope.editarItem = function() {
				$scope.editing = true;

			}

			$scope.doneEditing = function() {
				$scope.item.clienteE = $scope.suscripcion.clienteP;

				notaPedidoFactory.crearEnlace($scope.item).then(function(r) {
					$timeout(function() {

						if (r.estado == true) {
							$scope.buscaEnlances();
							$scope.editing = false;
							$scope.msg = "Enlace creado satisfactoriamente";
							toaster.pop("success", $scope.msg);
						} else {
							$scope.msg = "Error: " + r.mensaje;
							toaster.pop("error", $scope.msg);
						}

					});

				});

			}

			$scope.ok = function() {

				$modalInstance.close($scope.item);
			};

			$scope.cancel = function() {
				$modalInstance.dismiss('cancel');
			};

			$scope.traerProfesiones = function() {
				profesionResource.list().then(function(r) {
					$scope.profesiones = r;
				})
			}

			$scope.traerPaises = function() {
				paisFactory.list().then(function(r) {
					$scope.paises = r;
				})

			}

			$scope.traerProvincias = function() {
				provinciaFactory.list().then(function(r) {
					$scope.provincias = r;
				})

			}

			$scope.traerCiudades = function() {
				ciudadFactory.list().then(function(r) {
					$scope.ciudades = r;
				})
			}

		} ]);

app.controller('ModalInstanceCargaDatosCtrl',
		[
				'$scope',
				'$modalInstance',
				'FileUploader',
				'toaster',
				'$timeout',
				'$rootScope',
				'tareaOpt',
				function($scope, $modalInstance, FileUploader, toaster,
						$timeout, $rootScope, tareaOpt) {

					var uploader = $scope.uploader = new FileUploader({
						url : $rootScope.versionApp
								+ '/api/upload/cargarAccesos'
					});

					$scope.lista = [];

					// FILTERS

					uploader.filters
							.push({
								name : 'customFilter',
								fn : function(item /* {File|FileLikeObject} */,
										options) {
									return this.queue.length < 10;
								}
							});

					// CALLBACKS

					uploader.onWhenAddingFileFailed = function(
							item /* {File|FileLikeObject} */, filter, options) {
						// console.info('onWhenAddingFileFailed', item, filter,
						// options);
					};
					uploader.onAfterAddingFile = function(fileItem) {
						// console.info('onAfterAddingFile', fileItem);
					};
					uploader.onAfterAddingAll = function(addedFileItems) {
						// console.info('onAfterAddingAll', addedFileItems);
					};
					uploader.onBeforeUploadItem = function(item) {
						var formData = {
							id : tareaOpt.id
						};
						console.log(formData);
						if (!(formData == null)) {
							item.formData.push(formData);
						}
						console.info('onBeforeUploadItem', item);
					};
					uploader.onProgressItem = function(fileItem, progress) {
						try {
							// console.info('onProgressItem', fileItem,
							// progress);
						} catch (ex) {
							alert(ex);
						}
					};
					uploader.onProgressAll = function(progress) {
						try {
							// console.info('onProgressAll', progress);
						} catch (ex) {
							alert(ex);
						}
					};
					uploader.onSuccessItem = function(fileItem, response,
							status, headers) {
						try {
							$modalInstance.close(response.tarea);
						} catch (ex) {
							alert(ex);
						}
					};
					uploader.onErrorItem = function(fileItem, response, status,
							headers) {
						try {
							// console.info('onErrorItem', fileItem, response,
							// status, headers);
						} catch (ex) {
							alert(ex);
						}
					};
					uploader.onCancelItem = function(fileItem, response,
							status, headers) {
						try {
							// console.info('onCancelItem', fileItem, response,
							// status, headers);
						} catch (ex) {
							alert(ex);
						}
					};
					uploader.onCompleteItem = function(fileItem, response,
							status, headers) {
						// console.info('onCompleteItem', fileItem, response,
						// status, headers);
					};
					uploader.onCompleteAll = function() {
						// console.info('onCompleteAll');
					};

					// console.info('uploader', uploader);

					$scope.cerrar = function() {
						$modalInstance.dismiss('cancel');
					};

				} ])

app
		.controller(
				'ModalInstanceCtrlPropagar',
				[
						'$scope',
						'$modalInstance',
						'dtoPropagar',
						'toaster',
						'$timeout',
						function($scope, $modalInstance, dtoPropagar, toaster,
								$timeout) {

							$scope.selected;

							$scope.init = function() {
								$scope.objeto = dtoPropagar;
								console.log($scope.objeto);
							}

							$scope.buscarEnlace = function(id) {
								for (e = 0; e < $scope.enlaces.length; e++) {
									if (id == $scope.enlaces[e].id) {
										return $scope.enlaces[e];
									}
								}
							}

							$scope.traerLista = function() {
								clienteFactory
										.listEnlaces(
												$scope.objeto.suscripciones[0].clienteP.id)
										.then(function(r) {
											$scope.enlaces = r;
										})
							}

							$scope.propagar = function() {
								$scope.contacto = $scope
										.buscarEnlace($scope.selected);
							}

							$scope.aceptar = function() {

								for (i = 0; i < $scope.objeto.dto.detalleServicio.length; i++) {
									var objProducto = $scope.objeto.dto.detalleServicio[i];
									for (j = 0; j < objProducto.listaTareas.length; j++) {
										var objTarea = objProducto.listaTareas[j];

										if (objTarea.check == true) {
											console.log(objTarea);
											objTarea.archivoReal = $scope.objeto.tarea.archivoReal;
											objTarea.archivoTarea = $scope.objeto.tarea.archivoTarea;
										}

									}
								}

								$modalInstance.close($scope.objeto);
							}

							$scope.cambiar = function(item) {

							}

							$scope.cancel = function() {
								$modalInstance.dismiss('cancel');
							};
						} ])

app.controller('ModalInstanceCtrlTecnicos', [
		'$scope',
		'$modalInstance',
		'vendedorFactory',
		'notaPedidoFactory',
		'tarea',
		'toaster',
		'$timeout',
		function($scope, $modalInstance, vendedorFactory, notaPedidoFactory,
				tarea, toaster, $timeout) {

			$scope.asistente = null;

			$scope.init = function() {
				$scope.objeto = tarea;

				$scope.traerVendedores();
			}
			$scope.resetResponsable = function() {
				$scope.objeto.responsable = null;
			};

			$scope.resetAsistente = function() {
				$scope.objeto.asistente = null;
			};

			$scope.aceptar = function() {

				notaPedidoFactory.asigarTecnicos($scope.objeto).then(
						function(r) {
							$timeout(function() {
								$modalInstance.close($scope.objeto);

							});

						});

			};

			$scope.traerVendedores = function() {
				vendedorFactory.listVendedor().then(function(r) {
					$scope.vendedores = r;

				});
			}

			$scope.cancel = function() {
				$modalInstance.dismiss('cancel');
			};
		} ])
