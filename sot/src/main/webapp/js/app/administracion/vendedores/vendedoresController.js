app.controller("vendedorCtrl",["$scope", "vendedorFactory","$timeout","toaster","modalService",
	function($scope, vendedorFactory, $timeout,toaster,modalService) {

$scope.nuevo = false;
	
	$scope.init = function(){
		$scope.traerItems();
		
	}
	
	$scope.modalOptions = {
			headerText : 'Responsable',
			bodyText : '',
			type:'danger'
	};
	
	
	$scope.traerItems = function() {
		vendedorFactory.listVendedor().then(function(r){
			$scope.vendedores = r;
		});
			
	};
	$scope.nuevoItem=function(){
		$scope.item = null;
		$scope.nuevo = true;
	}
	
	$scope.cancelarItem=function(){
		$scope.nuevo = false;
		$scope.editar=false;
		$scope.item={codigo:null};
	}
	
	$scope.guardarItem=function(){
		
			vendedorFactory.create($scope.item).then(function(r) {
				
					$scope.traerItems();
					$scope.nuevo = false;
					
					$scope.status = !r.estado;
	
					if(r.estado){
						toaster
						.pop(
								"success",
								"Responsable",
								"Registro guardado satisfactoriamente");
						
					}else{
						toaster
						.pop(
								"error",
								"vendedor",
								r.mensaje);
						
					}			

			});

	};
		
	$scope.editarItem=function(item){	
		$scope.item = item;
		$scope.nuevo = true;
		$scope.editar=true;
		console.log(item);

	};
	
	$scope.actualizarItem=function(){
		
		
		vendedorFactory.update($scope.item).then(function(r) {
			
			
			$scope.traerItems();
			$scope.nuevo = false;
		
			
			if(r.estado){
				toaster
				.pop(
						"success",
						"Responsable",
						"Registro guardado satisfactoriamente");
				
			}else{
				toaster
				.pop(
						"error",
						"Responsable",
						"Un error se ha sucitado al guardar el registro...");
				
			}			

		});
  };

	$scope.eliminarItem=function(item){
		
		$scope.modalOptions.bodyText = 'Desea eliminar al Responsable ?';
		$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
	
		modalService.showModal({}, $scope.modalOptions).then(function(r){
			vendedorFactory.desactivar(item).then(function(r) {
				$scope.traerItems();
				$scope.nuevo = false;
				
				if(r.estado){
					toaster
					.pop(
							"success",
							"Responsable",
							"Registro eliminado satisfactoriamente");
					
				}else{
					toaster
					.pop(
							"error",
							"Responsable",
							"Un error se ha sucitado al guardar el registro...");
					
				}			


			});
	});	
		
	};

	
} ]);


