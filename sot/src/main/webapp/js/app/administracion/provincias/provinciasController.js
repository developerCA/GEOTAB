app.controller("provinciaCtrl",["$scope", "provinciaFactory", "$timeout",
	function($scope, provinciaFactory, $timeout) {

		$scope.nuevo = false;
		
		$scope.init = function(){
			$scope.traerProvincias();
		}
		
		$scope.traerProvincias = function() {
			$scope.provincias = provinciaFactory.list();
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

			provinciaFactory.create($scope.item).then(function(r) {
					
						$scope.traerItems();
						$scope.nuevo = false;
						
						$scope.status = !r.estado;
		
						if (r.estado == true) {
							$scope.edicion = false;
							$scope.nuevo = false;
							$scope.traerItems();
							$scope.msg = "Guardó satisfactoriamente";
						} else {
							$scope.msg = "Error: "
									+ r.mensaje;
						}	
				});
		};
		
		$scope.editarItem=function(item){	
			$scope.item = item;
			$scope.nuevo = true;
			$scope.editar = true;
		};
			
		$scope.actualizarItem=function(item){
			provinciaFactory.update($scope.item).then(function(r) {
					$scope.traerItems();
					$scope.nuevo = false;
					
					$scope.status = !r.estado;

					if (r.estado == true) {
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.traerItems();
						$scope.msg = "Guardó satisfactoriamente";
					} else {
						$scope.msg = "Error: "
								+ r.mensaje;
					}				

				});
		}
		
		$scope.eliminarItem=function(item){
			provinciaFactory.eliminar(item).then(function(r) {
				$scope.traerItems();
				$scope.nuevo = false;
				
				$scope.status = !r.estado;

				if (r.estado == true) {
					$scope.edicion = false;
					$scope.nuevo = false;
					$scope.traerItems();
					$scope.msg = "Eliminó satisfactoriamente";
				} else {
					$scope.msg = "Error: "
							+ r.mensaje;
				}

			});
		};
		
} ]);
