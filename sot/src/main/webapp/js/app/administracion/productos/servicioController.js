app.controller('servicioCtrl', ["$scope", "$timeout","productoFactory","toaster","modalService", 
                                function($scope, $timeout,productoFactory,toaster,modalService) {
	
	$scope.nuevo = false;
	
	$scope.init=function(){
		
		$scope.manejarProducto();
	};
	
	$scope.modalOptions = {
			headerText : 'Servicio',
			bodyText : '',
			type:'danger'
	};
		
	$scope.manejarProducto=function(){
		
		
		productoFactory.listTareas().then(function(r) {				
			$scope.servicios=r;
			console.log(r);
			
		});
	
	}

	
	$scope.guardarItem=function(){
		
		productoFactory.crearServicio($scope.servicio).then(function(r) {
				
					$scope.status = !r.estado;
	
					if (r.estado == true) {
						
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.manejarProducto();
						
						toaster
						.pop(
								"success",
								"Servicio",
								"Registro guardado satisfactoriamente");
					} else {
						
								toaster.pop("error",
										"Servicio",
										r.mensaje);
					}	
			});
	};

	$scope.editarItem=function(item){
		
		$scope.servicio = item;			
		$scope.nuevo = true;
		$scope.editar = true;
						
	};
	
	$scope.actualizarItem=function(){
		
		productoFactory.crearServicio($scope.servicio).then(function(r) {
			
				
				$scope.status = !r.estado;

				if (r.estado == true) {
					
					$scope.manejarProducto();
					$scope.nuevo = false;
					$scope.edicion = false;
					
					toaster
					.pop(
							"success",
							"Servicio",
							"Registro actualizado satisfactoriamente");
				} else {
					toaster.pop("error",
							"Servicio",
							r.mensaje);
				}				

			});
	};

	$scope.eliminarItem=function(item){
		
		$scope.servicio=item;
		$scope.modalOptions.bodyText = 'Desea eliminar el servicio ? ';
		$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
	
		modalService.showModal({}, $scope.modalOptions).then(function(r){
		productoFactory.eliminarServicio($scope.servicio).then(function(r) {
			
				
				$scope.status = !r.estado;

				if (r.estado == true) {
					
					$scope.manejarProducto();
					$scope.nuevo = false;
					$scope.edicion = false;
					
					toaster
					.pop(
							"success",
							"Servicio",
							"Registro eliminado satisfactoriamente");
				} else {
					toaster.pop("error",
							"Servicio",
							r.mensaje);
				}				

			});
			
		});	
	};

	$scope.nuevoItem = function(){
		$scope.servicio = null;
		$scope.nuevo = true;
		$scope.editar = false;
		
	};
	
	$scope.cancelarItem=function(){
		$scope.nuevo = false;
	};
	
	
    $scope.ok = function (item) {
    	//$scope.cliente=item;
    	
      $modalInstance.close("cliente");
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
    
    
  }]); 

