app.controller("categoriaCtrl",["$scope", "categoriasResource","$timeout","toaster","modalService",
	function($scope, categoriasResource, $timeout,toaster,modalService) {

		$scope.nuevo = false;
		
		$scope.traerItems = function() {

			categoriasResource.list().then(function(r) {
				$scope.items = r;
			});
		
			

		};
		
		$scope.nuevoItem = function(){
			$scope.item = null;
			$scope.nuevo = true;
			$scope.edit = true
			$scope.editar = false;
		}
		
		$scope.cancelarItem=function(){
			$scope.nuevo = false;
		}
		
		$scope.guardarItem=function(){

				categoriasResource.create($scope.item).then(function(r) {
					
						$scope.traerItems();
						$scope.nuevo = false;
						
						$scope.status = !r.estado;
		
						if (r.estado == true) {
							
							toaster
							.pop(
									"success",
									"Tipo de equipos",
									"Registro guardado satisfactoriamente");
							
							$scope.edicion = false;
							$scope.nuevo = false;
							$scope.traerItems();
							
						} else {
							
							toaster.pop("error",
									"Tipo de equipos",
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
				categoriasResource.update($scope.item).then(function(r) {
					$scope.traerItems();
					$scope.nuevo = false;
					
					$scope.status = !r.estado;

					if (r.estado == true) {
						
						toaster
						.pop(
								"success",
								"Tipo de equipos",
								"Registro actualizado satisfactoriamente");
						
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.traerItems();
						
					} else {
						toaster.pop("error",
								"Tipo de equipos",
								r.mensaje);
					}				

				});

		}
		
		$scope.modalOptions = {
				headerText : 'Tipo de Equipo',
				bodyText : '',
				type:'danger'
		};
		
		$scope.eliminarItem=function(item){
			
			$scope.modalOptions.bodyText = "Desea eliminar el tipo de equipo ?";
			$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
			
			modalService.showModal({}, $scope.modalOptions).then(function(r){	
				categoriasResource.desactivar(item).then(function(r) {
					$scope.traerItems();
					$scope.nuevo = false;
					
					$scope.status = !r.estado;

					if (r.estado == true) {
						
						toaster.pop(
								"success",
								"Tipo de equipos",
								"Registro eliminado satisfactoriamente");
						
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.traerItems();
					} else {
						toaster.pop("error",
								"Tipo de equipos",
								r.mensaje);
					}

				});
		
			});
		};
		
		$scope.agregarTareaItem=function(item){
			$scope.nuevo = true;
			
			$scope.tipo=item;
			categoriasResource.listServicio($scope.tipo).then(function(r) {
				$scope.servicios=r;
			});
			
		};
		
		$scope.guardarServicioItem=function(){
			
			categoriasResource.guardarServicios($scope.servicios).then(function(r) {
				console.log(r);
				
				if (r.estado==true){
					$scope.servicios=r.objeto;
					toaster.pop(
							"success",
							"Servicios",
							"Costo de los servicios registrados correctamente");
				
				}
			});
		};
		
		$scope.cerrarItem=function(){
			$scope.nuevo = false;
			
		};
		
		$scope.agregarTarea=function(){
			//$scope.nuevo = false;
			$scope.edit = true;
			
		};

} ]);
