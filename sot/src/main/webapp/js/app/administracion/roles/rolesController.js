app
		.controller(
				"rolesCtrl",
				[
						"$scope",
						"toaster",
						"rolesFactory",
						"$timeout",
						function($scope, toaster, rolesFactory, $timeout) {

							$scope.nuevo = false;

							$scope.traerItems = function() {

								rolesFactory.list().then(function(r) {
									
									//$timeout(function() {
										$scope.items = r;
										
									//}, 3000);
									
								});

							};

							$scope.nuevoItem = function() {

								$scope.nuevo = true;

							};

							$scope.cancelarItem = function() {

								$scope.nuevo = false;
								$scope.editar = false;
								$scope.item = {
									nombreRol : null,
									descripcionRol : null
								};

							};

							$scope.item = {
								nombreRol : null,
								descripcionRol : null
							};

							$scope.guardarItem = function() {

								rolesFactory
										.create($scope.item)
										.then(
												function(r) {

													$scope.status = !r.estado;

													if (r.estado == true) {
														toaster
																.pop(
																		"info",
																		"Roles",
																		"Registro guardado satisfactoriamente");
														$scope.traerItems();
														$scope.item = {
															nombreRol : null,
															descripcionRol : null
														};
														$scope.nuevo = false;
													} else {

														toaster.pop("error",
																"Roles",
																r.mensaje);

													}

												});

							};

							$scope.actualizarItem = function() {

								rolesFactory
										.update($scope.item)
										.then(
												function(r) {

													$scope.status = !r.estado;

													if (r.estado == true) {
														toaster
																.pop(
																		"success",
																		"Roles",
																		"Registro actualizado satisfactoriamente");
														$scope.traerItems();
														$scope.cancelarItem();

													} else {

														toaster.pop("error",
																"Roles",
																r.mensaje);

													}

												});
							};

							$scope.desactivarItem = function(item) {

								rolesFactory
										.desactivar(item)
										.then(
												function(r) {

													$scope.status = !r.estado;

													if (r.estado == true) {
														toaster
																.pop(
																		"success",
																		"Roles",
																		"Registro actualizado satisfactoriamente");
														$scope.traerItems();

													} else {

														toaster.pop("error",
																"Roles",
																r.mensaje);

													}

												});
							};

							$scope.editarItem = function(item) {

								$scope.item = item;
								$scope.nuevo = true;
								$scope.editar = true;

							}

						} ]);
