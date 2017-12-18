app.controller("sucursalCtrl", [ "$scope", "sucursalesFactory", "regionResource","$timeout","toaster",
		function($scope, sucursalesFactory, regionResource,$timeout,toaster) {


			$scope.nuevo = false;
			
			$scope.init = function(){
			
				$scope.traerItems();
				$scope.traerRegiones();
			}
			
			
			$scope.traerItems = function() {

				sucursalesFactory.list().then(function(r) {
					$scope.items = r;
				});
				

			}; 
			

			$scope.traerRegiones = function() {
				
				regionResource.list().then(function(r) {
					$scope.regiones = r;
				});

			};

			$scope.nuevoItem = function() {
				$scope.item = null;
				$scope.nuevo = true;
				$scope.editar = false;
			}

			$scope.cancelarItem = function() {
				$scope.nuevo = false;
			}

			$scope.guardarItem = function() {

					sucursalesFactory.create($scope.item).then(function(r) {

						$scope.traerItems();
						$scope.nuevo = false;

						$scope.status = !r.estado;

						if (r.estado == true) {
							$scope.edicion = false;
							$scope.nuevo = false;
							$scope.traerItems();
							
							toaster
							.pop(
									"success",
									"Sucursales",
									"Registro guardado satisfactoriamente");
						} else {
							
							toaster.pop("error",
									"Sucursales",
									r.mensaje);
						}
					});
			};

			$scope.editarItem = function(item) {
				$scope.item = item;
				$scope.nuevo = true;
				$scope.editar = true;

			};

			$scope.actualizarItem = function(item) {
				sucursalesFactory.update($scope.item).then(function(r) {
					$scope.traerItems();
					$scope.nuevo = false;

					$scope.status = !r.estado;

					if (r.estado == true) {
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.traerItems();
						toaster.pop(
								"success",
								"Sucursales",
								"Registro actualizado satisfactoriamente");
					} else {
						toaster.pop("error",
								"Sucursales",
								r.mensaje);
					}

				});
			};

			$scope.eliminarItem = function(item) {
				sucursalesFactory.desactivar(item).then(function(r) {
					$scope.traerItems();
					$scope.nuevo = false;

					$scope.status = !r.estado;

					if (r.estado == true) {
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.traerItems();
						toaster.pop(
								"success",
								"Sucursales",
								"Registro actualizado satisfactoriamente");
				
					} else {
						toaster.pop("error",
								"Sucursales",
								r.mensaje);
					}

				});
			};

		} ]);
