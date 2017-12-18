app
		.controller(
				"profesionCtrl",
				[
						"$scope",
						"toaster",
						"profesionResource",
						"$timeout",
						function($scope, toaster, profesionResource, $timeout) {

							$scope.nuevo = false;

							$scope.traerItems = function() {

								profesionResource.list().then(function(r) {
									$scope.items = r;
								});

							};

							$scope.nuevoItem = function() {

								$scope.item = null;
								$scope.nuevo = true;
								$scope.editar = false;

							};

							$scope.cancelarItem = function() {

								$scope.nuevo = false;
							};

							$scope.guardarItem = function() {

								profesionResource
										.create($scope.item)
										.then(
												function(r) {

													$scope.status = !r.estado;

													if (r.estado == true) {
														toaster
																.pop(
																		"success",
																		"Profesiones",
																		"Registro guardado satisfactoriamente");
														$scope.traerItems();
														$scope.nuevo = false;
													} else {
														
														toaster.pop("error",
																"Profesiones",
																r.mensaje);

													}

												});

							};

							$scope.actualizarItem = function() {

								profesionResource
										.update($scope.item)
										.then(
												function(r) {

													$scope.status = !r.estado;

													if (r.estado == true) {
														toaster
																.pop(
																		"success",
																		"Profesiones",
																		"Registro actualizado satisfactoriamente");
														$scope.traerItems();
														$scope.nuevo = false;
													} else {
														
														toaster.pop("error",
																"Profesiones",
																r.mensaje);

													}

												});
							}

							$scope.editarItem = function(item) {

								$scope.item = item;
								$scope.nuevo = true;
								$scope.editar = true;

							}

						} ]);
