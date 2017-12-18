app.controller("regionCtrl",["$scope", "regionResource","$timeout","toaster",
	function($scope, regionResource, $timeout,toaster) {

		$scope.nuevo = false;
		
		$scope.traerItems = function() {

			regionResource.list().then(function(r) {
				$scope.items = r;
			});
		
			

		};
		
		$scope.nuevoItem = function(){
			$scope.item = null;
			$scope.nuevo = true;
			$scope.editar = false;
		}
		
		$scope.cancelarItem=function(){
			$scope.nuevo = false;
		}
		
		$scope.guardarItem=function(){

				regionResource.create($scope.item).then(function(r) {
					
						$scope.traerItems();
						$scope.nuevo = false;
						
						$scope.status = !r.estado;
		
						if (r.estado == true) {
							
							toaster
							.pop(
									"success",
									"Regiones",
									"Registro guardado satisfactoriamente");
							
							$scope.edicion = false;
							$scope.nuevo = false;
							$scope.traerItems();
							
						} else {
							
							toaster.pop("error",
									"Regiones",
									r.mensaje);
							
						}	
				});
		};
		
		$scope.editarItem=function(item){	
			$scope.item = item;
			$scope.nuevo = true;
			$scope.editar = true;

		};
			
		$scope.actualizarItem=function(item){
				regionResource.update($scope.item).then(function(r) {
					$scope.traerItems();
					$scope.nuevo = false;
					
					$scope.status = !r.estado;

					if (r.estado == true) {
						
						toaster
						.pop(
								"success",
								"Regiones",
								"Registro actualizado satisfactoriamente");
						
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.traerItems();
						
					} else {
						toaster.pop("error",
								"Regiones",
								r.mensaje);
					}				

				});

		}
		
		$scope.eliminarItem=function(item){
			regionResource.desactivar(item).then(function(r) {
				$scope.traerItems();
				$scope.nuevo = false;
				
				$scope.status = !r.estado;

				if (r.estado == true) {
					
					toaster.pop(
							"success",
							"Regiones",
							"Registro actualizado satisfactoriamente");
					
					$scope.edicion = false;
					$scope.nuevo = false;
					$scope.traerItems();
				} else {
					toaster.pop("error",
							"Regiones",
							r.mensaje);
				}

			});
		};

} ]);
