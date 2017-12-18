app
		.controller(
				"perfilesCtrl",
				[
						"$scope",
						"toaster",
						"perfilesFactory",
						"$timeout",
						function($scope, toaster, perfilesFactory, $timeout) {

							$scope.nuevo = false;

							$scope.traerItems = function() {

								perfilesFactory.list().then(function(r) {
									$scope.items = r;
								});

							};

							$scope.nuevoItem = function() {

								$scope.nuevo = true;

							};

							$scope.cancelarItem = function() {

								$scope.nuevo = false;
								$scope.editar = false;
								$scope.item = {
									descripcionPerfil : null,
									nombrePerfil : null
								};
								

							};

							$scope.item = {
								descripcionPerfil : null,
								nombrePerfil : null
							};

							$scope.guardarItem = function() {

								perfilesFactory
										.create($scope.item)
										.then(
												function(r) {

													$scope.status = !r.estado;

													if (r.estado == true) {
														toaster
																.pop(
																		"info",
																		"Perfiles",
																		"Registro guardado satisfactoriamente");
														$scope.traerItems();
														$scope.item = {
															descripcionPerfil : null,
															nombrePerfil : null
														};
														$scope.nuevo = false;
													} else {
														toaster
														.pop(
																"error",
																"Perfiles",
																r.mensaje);

													}

												});

							};

							$scope.actualizarItem = function() {

								perfilesFactory
										.update($scope.item)
										.then(
												function(r) {

													$scope.status = !r.estado;

													if (r.estado == true) {
														toaster
																.pop(
																		"success",
																		"Perfiles",
																		"Registro actualizado satisfactoriamente");
														$scope.traerItems();
														$scope.cancelarItem();

													} else {
														toaster.pop("error",
																"Perfiles",
																r.mensaje);

													}

												});
							};

							$scope.desactivarItem = function(item) {

								perfilesFactory
										.desactivar(item)
										.then(
												function(r) {

													$scope.status = !r.estado;

													if (r.estado == true) {
														toaster
																.pop(
																		"success",
																		"Perfiles",
																		"Registro actualizado satisfactoriamente");
														$scope.traerItems();

													} else {
														toaster
														.pop(
																"error",
																"Perfiles",
																r.mensaje);
														
													}

												});
							};
							
							$scope.closeAlert=function(){
								
								$scope.status=false;
							}
							

							$scope.editarItem = function(item) {

								$scope.item = item;
								$scope.nuevo = true;
								$scope.editar = true;

							}

						} ]);
