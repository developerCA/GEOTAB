app.controller("productoCtrl",["$scope", "productoFactory", "$timeout","categoriasResource","toaster","$modal","modalService",
	function($scope, productoFactory, $timeout,categoriasResource,toaster,$modal,modalService) {

	$scope.obj={clienteFinal:null};
		
		$scope.cargaModalFinal = function (size) {
		      var modalInstance = $modal.open({
		        templateUrl: 'tpl/app/administracion/notasPedido/buscarCliente.html',
		        controller: 'ModalInstanceCtrlCliente',
		        size: size
		      });

		      modalInstance.result.then(function (selectedItem) {
		        $scope.selectedCliente = selectedItem;
		        
		        $scope.obj.clienteFinal=$scope.selectedCliente;
		        $scope.traerProductos();
		        
		      }, function () {
		        console.log('Modal dismissed at: ' + new Date());
		      });
		};
		

		
		$scope.nuevo = false;
		
		$scope.init=function(){
			
			
			
			$scope.traerTipoProductos();
			
		};
		
		$scope.traerCategorias=function(){
			
			productoFactory.listCategorias($scope.obj.clienteFinal.id).then(function(r) {
				$scope.categorias = r;
				
			});
			
			
		};
		
		$scope.traerProductos = function() {

			productoFactory.listProductos($scope.obj.clienteFinal.id).then(function(r) {
				$scope.productos = r;
				$scope.traerCategorias();
				
			});
			
		};
		$scope.traerTipoProductos = function() {

			productoFactory.listTipoProductos().then(function(r) {
				$scope.tipoProductos = r;
			});
			
		};
		
		$scope.guardarItem=function(){
			
			$scope.producto.cliente=$scope.obj.clienteFinal;
	      

			productoFactory.crear($scope.producto).then(function(r) {
					
							
						$scope.status = !r.estado;
		
						if (r.estado == true) {
							//$scope.traerProductosRenovacion();
							$scope.edicion = false;
							$scope.nuevo = false;
							$scope.traerProductos();
							
							toaster
							.pop(
									"success",
									"Equipo",
									"Registro guardado satisfactoriamente");
						} else {
							
									toaster.pop("error",
											"Equipo",
											r.mensaje);
						}	
				});
		};
		
		$scope.modalOptions = {
				headerText : 'Equipo',
				bodyText : '',
				type:'danger'
		};
		
		$scope.eliminarItem=function(item){
			
			$scope.producto = item;
			
			$scope.modalOptions.bodyText = 'Desea eliminar el equipo?';
			$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
		
			modalService.showModal({}, $scope.modalOptions).then(function(r){
				productoFactory.eliminar($scope.producto).then(function(r) {
					$scope.traerProductos();
					$scope.nuevo = false;
					
					$scope.status = !r.estado;

					if (r.estado == true) {
						//$scope.traerProductosRenovacion();
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.traerProductos();
						
						toaster
						.pop(
								"success",
								"Equipo",
								"Registro actualizado satisfactoriamente");
					} else {
						toaster.pop("error",
								"Equipo",
								r.mensaje);
					}

				});

			});	
			
		};
		
		$scope.editarItem=function(item){
			
			$scope.producto = item;			
			$scope.nuevo = true;
			$scope.editar = true;
			
							
		};
		
		$scope.actualizarItem=function(){
			
			productoFactory.update($scope.producto).then(function(r) {
				
					
					$scope.status = !r.estado;

					if (r.estado == true) {
						
						$scope.traerProductos();
						$scope.nuevo = false;
						$scope.edicion = false;
						
						toaster
						.pop(
								"success",
								"Equipo",
								"Registro actualizado satisfactoriamente");
					} else {
						toaster.pop("error",
								"Equipo",
								r.mensaje);
					}				

				});
		};
		$scope.nuevoItem = function(){
			
			if ($scope.obj.clienteFinal==null){
				toaster
				.pop(
						"error",
						"Equipo",
						"Seleccione un cliente");
				
				return;
			}
			$scope.producto = null;
			$scope.nuevo = true;
			$scope.editar = false;
			
		};
		
		$scope.cargaDimensiones=function(){
			var modalInstance = $modal.open({
		        templateUrl: 'tpl/app/administracion/productos/modalDimension.html',
		        controller: 'ModalInstanceCtrlAddDimension',
		        size: 'lg'
		        
		      });

		      modalInstance.result.then(function () {
		    	  $scope.traerTipoProductos();
		      }, function () {
		    
		        $scope.traerTipoProductos();
		      });
			
		};
		
		$scope.cancelarItem=function(){
			$scope.nuevo = false;
		};
		
		
	 
	    

	    
		
	
} ]);

app.controller('ModalInstanceCtrlCliente', ['$scope', '$modalInstance','$timeout','clienteFactory','$modal',  function($scope, $modalInstance,$timeout,clienteFactory,$modal) {
	
	/*======INIT========*/
	$scope.init = function(){
	};
	/*======INIT========*/
	
	$scope.datosCliente = {
		cedula: "",
		nombres: "",
		apellidos: ""
	};
	
	
	$scope.buscar = function(){
		clienteFactory.listPorAtributos($scope.datosCliente.cedula, $scope.datosCliente.nombres, $scope.datosCliente.apellidos).then(function(r){
			$scope.clientes = r;
		})
	};
	
	$scope.agregar=function(){
		var modalInstanceAdd = $modal.open({
	        templateUrl: 'tpl/app/administracion/clientes/modal.html',
	        controller: 'ModalInstanceCtrlClientesAdd',
	        size: 'lg'
	      });

		modalInstanceAdd.result.then(function (selectedItem) {
	       // $scope.selectedCliente = selectedItem;
	        
	        //$scope.item.cliente=$scope.selectedCliente;
	        
	      }, function () {
	        console.log('Modal dismissed at: ' + new Date());
	      });
		
	};
	
	
    $scope.ok = function (item) {
    	$scope.cliente=item;
    	
      $modalInstance.close($scope.cliente);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]); 

app.controller('ModalInstanceCtrlAddDimension', ['$scope', '$modalInstance','$timeout','productoFactory','toaster',  function($scope, $modalInstance,$timeout,productoFactory,toaster) {
	
	$scope.nuevo = false;
	
	$scope.init=function(){
		$scope.traerDimensiones();
	};
	
	$scope.traerDimensiones = function() {

		productoFactory.listTipoProductos().then(function(r) {
			$scope.dimensiones = r;
			
		});
		
	};

	
	$scope.guardarItem=function(){
		
		productoFactory.crearTipo($scope.tipo).then(function(r) {
				
					$scope.status = !r.estado;
	
					if (r.estado == true) {
						
						$scope.edicion = false;
						$scope.nuevo = false;
						$scope.traerDimensiones();
						
						toaster
						.pop(
								"success",
								"Dimensiones",
								"Registro guardado satisfactoriamente");
					} else {
						
								toaster.pop("error",
										"Dimensiones",
										r.mensaje);
					}	
			});
	};

	$scope.editarItem=function(item){
		
		$scope.tipo = item;			
		$scope.nuevo = true;
		$scope.editar = true;
						
	};
	
	$scope.actualizarItem=function(){
		
		productoFactory.crearTipo($scope.tipo).then(function(r) {
			
				
				$scope.status = !r.estado;

				if (r.estado == true) {
					
					$scope.traerDimensiones();
					$scope.nuevo = false;
					$scope.edicion = false;
					
					toaster
					.pop(
							"success",
							"Dimensiones",
							"Registro actualizado satisfactoriamente");
				} else {
					toaster.pop("error",
							"Dimensiones",
							r.mensaje);
				}				

			});
	};
	$scope.nuevoItem = function(){
		$scope.tipo = null;
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

app.controller('ModalInstanceCtrlClientesAdd', ['$scope', '$modalInstance','$timeout', function($scope, $modalInstance,$timeout) {
	
	
	
	
    $scope.ok = function (item) {
    	//$scope.cliente=item;
    	
      $modalInstance.close("producto");
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
    
    
  }]); 




