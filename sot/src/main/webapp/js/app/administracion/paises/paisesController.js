app
		.controller(
				"usuariosCtrl",
				[
						"$scope",
						"$http",
						"usuariosResource",
						"perfilesResource",
						"usuariorolResource",
						"personaProveedorResource",
						"modalService",
						"$log",
						"$timeout",
						function($scope, $http, usuariosResource,
								perfilesResource, usuariorolResource,personaProveedorResource,
								modalService, $log, $timeout) {

							$scope.items = [];
							$scope.actual = null;
							$scope.respuesta = null;
							$scope.dto = null;
							$scope.edicion = false;
							$scope.actualizacion = false;

							$scope.nuevo = false;
							$scope.status = false;
							$scope.msg = "";
							$scope.usuario;

							$scope.rolusuario = [];
							$scope.roles = [];
							$scope.usuariorol;

							$scope.traerItems = function() {

								$scope.items = usuariosResource.list();
								$scope.perfiles = perfilesResource.list();

								$scope.status = false;
								$scope.msg = "";
								//$scope.personas=personaProveedorResource.customListaPersonasMiEmpresaNoAsigUsuario();
								//$scope.personas2=personaProveedorResource.customListaPersonasMiEmpresa();
							};
							$scope.nuevoItem = function() {
								$scope.usuario=null;
								$scope.confirm_password = null;
								$scope.actual = null;
								$scope.edicion = false;
								$scope.nuevo = true;
								$scope.personas=personaProveedorResource.customListaPersonasMiEmpresaNoAsigUsuario();
								
							};
							$scope.guardarItem = function() {

								usuariosResource.create($scope.usuario).then(
										function(r) {
											$scope.dto = null;
											$scope.status = !r.estado;

											if (r.estado == true) {
												$scope.edicion = false;
												$scope.nuevo = false;
												$scope.traerItems();

											} else {
												$scope.msg = "Error: "
														+ r.mensaje;
											}

										});

							};

							$scope.cancelarItem = function() {
								$scope.edicion = false;
								$scope.actualizacion = false;
								$scope.nuevo = false;
								$scope.status = false;
								$scope.resetclave = false;
								$scope.rolSet = false;
								$scope.msg = "";
								$scope.traerItems();
							};
							$scope.editarItem = function(actual) {
								$scope.personas=personaProveedorResource.customListaPersonasMiEmpresaNoAsigUsuarioEdita(actual);
								
								$timeout(function() {
								
									$scope.dto = usuariosResource.clone($scope.actual);
							});
								
								//$scope.personasel=personasResource.clone(actual.persona);
								//console.log($scope.personasel);
								
								//$scope.personas.push(personasResource.clone(actual.persona));
								
								
								//$scope.personas[$scope.personas.length-1].id=actual.persona.id;//$scope.dto.persona.id;
								$scope.actual = actual;
								//$scope.dto = usuariosResource.clone($scope.actual);
								//$scope.dto=actual;
								//alert($scope.dto.persona.id);
								$scope.actualizacion = true;
								$scope.nuevo = false;
								/*console.log(actual.persona);
								console.log(personasResource.clone(actual.persona));*/
								
							};
							$scope.resetItem = function(actual) {

								$scope.actual = actual;
								$scope.dto = usuariosResource
										.clone($scope.actual);
								$scope.resetclave = true;
								$scope.rolSet = false;
								$scope.nuevo = false;

							};
							$scope.rolUsuario = function(actual) {

								$scope.actual = actual;
								$scope.listarolusuario = usuariorolResource
										.customListRolUsuario($scope.actual);
								$scope.listaroles = usuariorolResource
										.customListRol($scope.actual);

								$scope.listUsuarioRol = usuariorolResource
										.customListUsuarioRol($scope.actual);
								// $scope.listarolusuarioInicial=$scope.listarolusuario.slice(0);
								$scope.resetclave = false;
								$scope.rolSet = true;
								$scope.nuevo = false;

							};

							$scope.agregarRol = function() {
								if ($scope.roles.length > 0) {
									// añade el rol seleccionado al usuario
									for (j = 0; j < $scope.roles.length; j++) {
										for (i = 0; i < $scope.listaroles.length; i++) {
											if ($scope.listaroles[i]['id'] == $scope.roles[j]) {
												$scope.listarolusuario[$scope.listarolusuario.length] = $scope.listaroles
														.splice(i, 1)[0];
												// $scope.listarolusuario[$scope.listarolusuario.length]=
												// $scope.listaroles[i];
											}
										}
									}
								}
							};

							$scope.quitarRol = function() {
								if ($scope.rolusuario.length > 0) {
									// quita el rol seleccionado al usuario
									for (j = 0; j < $scope.rolusuario.length; j++) {
										for (i = 0; i < $scope.listarolusuario.length; i++) {
											if ($scope.listarolusuario[i]['id'] == $scope.rolusuario[j]) {
												$scope.listaroles[$scope.listaroles.length] = $scope.listarolusuario
														.splice(i, 1)[0];
											}
										}
									}
								}
							};

							$scope.actualizarRolUsuario = function() {
								if ($scope.listarolusuario.length == 0) {
									alert('No ha seleccionado roles');
									return null;
								}

								// valida q se hayan hecho cambios
								if (arraysIdentical($scope.listUsuarioRol,
										$scope.listarolusuario)) {
									alert('No ha realizado cambios');
									return null;
								}
								/*
								 * //elimina los roles del usuario for (x in
								 * $scope.listUsuarioRol) {
								 * usuariorolResource.remove(
								 * $scope.listUsuarioRol[x]).then( function() {
								 * console.log("TODO ok");
								 * 
								 * }); }
								 */
								
								// desactiva los roles del usuario
								for (i = 0; i < $scope.listUsuarioRol.length; i++) {
									usuariorolResource
									.customPUT($scope.listUsuarioRol[i])
									.then(
											function(r) {
												$timeout(function() {
													
													if (r.estado == true) {
	
														
													} else {
														$scope.msg = "Error: "
																+ r.mensaje;
													}
												});
	
											});
								}
								// ingresar los roles seleccionados
								for (i = 0; i < $scope.listarolusuario.length; i++) {
									$scope.usuariorol = {
										rol : $scope.listarolusuario[i],
										usuario : $scope.actual
									};

										usuariorolResource
											.create($scope.usuariorol)
											.then(
													function(r) {
														$scope.usuariorol = null;
														$scope.status = !r.estado;
														if (!r.estado) {
															$scope.msg = "Error: "
																	+ r.mensaje;
														}
													});
								}

								$scope.resetclave = false;
								$scope.rolSet = false;
								$scope.nuevo = false;

								$scope.traerItems();
							}

							function arraysIdentical(a, b) {
								var i = a.length;
								if (i != b.length)
									return false;
								var aux;
								for (j = 0; j < a.length; j++) {
									aux = false;
									for (i = 0; i < b.length; i++) {
										if (b[i]['id'] == a[j]['id']) {
											aux = true;
											break;
										}
									}
									if (aux == false) {
										return false;
									}
								}
								return true;
							}

							$scope.actualizarItem = function() {

								/*console.log($scope.personas);
								console.log($scope.personas2);*/
								usuariosResource
										.update($scope.dto)
										.then(
												function(r) {
													$scope.status = !r.estado;

													if (r.estado == true) {
														$scope.actualizacion = false;
														$scope.nuevo = false;
														$scope.traerItems();
														$scope.msg = "Actualizó satisfactoriamente";
													} else {
														$scope.msg = "Error: "
																+ r.mensaje;
													}
												});
								$scope.edicion = false;
								$scope.nuevo = false;

							};
							$scope.actualizarResetItem = function() {
								// console.log(item)

								$scope.dto.password = $scope.password;

								var modalOptions = {
									headerText : 'Usuarios',
									bodyText : 'Confirma actualizar la clave  del usuario?'
								};

								modalService
										.showModal({}, modalOptions)
										.then(
												function(result) {
													usuariosResource
															.customRESET(
																	$scope.dto)
															.then(
																	function(r) {
																		$timeout(function() {
																			$scope.status = !r.estado;

																			if (r.estado == true) {

																				$scope
																						.traerItems();
																				$scope.resetclave = false;
																				$scope.password = null;
																				$scope.confirm_password = null;

																			} else {
																				$scope.msg = "Error: "
																						+ r.mensaje;
																			}
																		});

																	});
												});

							};

							$scope.eliminarItem = function(item) {
								// console.log(item)

								var modalOptions = {
									headerText : 'Usuarios',
									bodyText : 'Confirma eliminar el usuario?'
								};

								modalService
										.showModal({}, modalOptions)
										.then(
												function(result) {
													usuariosResource
															.customDEL(item)
															.then(
																	function(r) {
																		$timeout(function() {
																			$scope.status = !r.estado;

																			if (r.estado == true) {

																				$scope
																						.traerItems();

																			} else {
																				$scope.msg = "Error: "
																						+ r.mensaje;
																			}
																		});

																	});
												});

							};

							$scope.checkItem = function(item) {
								// console.log(item)

								var modalOptions = {};
								if (item.catalogo.siglaCatalogo == "ESTACT") {
									modalOptions = {
										headerText : 'Usuario',
										bodyText : 'Confirma desactivar el usuario seleccionado?'
									};

								} else {
									modalOptions = {
										headerText : 'Usuario',
										bodyText : 'Confirma activar el usuario seleccionado?'
									};

								}

								modalService
										.showModal({}, modalOptions)
										.then(
												function(result) {
													usuariosResource
															.customPUT(item)
															.then(
																	function(r) {
																		$timeout(function() {
																			$scope.status = !r.estado;

																			if (r.estado == true) {

																				$scope
																						.traerItems();

																			} else {
																				$scope.msg = "Error: "
																						+ r.mensaje;
																			}
																		});

																	});
												});

							};

						} ]);
