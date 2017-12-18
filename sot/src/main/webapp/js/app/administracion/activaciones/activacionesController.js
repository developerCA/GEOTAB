app.controller("activacionesCtrl", ["$scope", "activacionesFactory", "suscripcionesFactory", "toaster", "$timeout", "$modal", function($scope, activacionesFactory, suscripcionesFactory, toaster, $timeout, $modal){
	
	$scope.init = function(){
		$scope.traerSuscripcionesActivasTemporales();
		$scope.traerClientes();
	}
	
	$scope.traerSuscripcionesActivasTemporales = function(){
		activacionesFactory.activadasTemporal().then(function(r){
			$scope.activadasTemporal = r;
			console.log(r);
		})
	}
	
	$scope.modificar = false;
	
	$scope.traerClientes = function(){
		activacionesFactory.clientesSuscripcionesAprobadas().then(function(r){
			$scope.clientes = r;
		})
	}
	
	$scope.selectCliente = function(item){
		angular.forEach($scope.clientes, function(cliente) {
			cliente.selected = false;
		});
		$scope.cliente = item;
		$scope.cliente.selected = true;
		$scope.muestraSuscripciones=false;
		$scope.suscripcionesAprobadasPorCliente($scope.cliente.id);
	}
	
	$scope.suscripcionesAprobadasPorCliente = function(item){
		suscripcionesFactory.aprobadasPorCliente(item).then(function(r) {
			$timeout(function(){
				$scope.suscripciones = r;
				$scope.muestraSuscripciones=true;
				$scope.muestraAccesos=false;
			});
		});		
	}
	
	$scope.activar = function(item){
	   var modalInstance = $modal.open({
	        templateUrl: 'tpl/app/administracion/activacion/activarModal.html',
	        controller: 'ModalInstanceCtrlActivar',
	        size: 'lg',
	        resolve: {
		        activacion: function () {
		            return item;
		        }
	        }
	   });
	   
	   modalInstance.result.then(function (selectedItem) {
		   $scope.activarSuscripcion = selectedItem;
		   activacionesFactory.activarsuscripcion($scope.activarSuscripcion.id).then(function(r){
			   if(r.estado){
				   $scope.traerClientes();
				   $scope.suscripcionesAprobadasPorCliente($scope.cliente.id);
				   toaster.pop("success", "Activar Suscripción", r.mensaje);
			   }else{
				   toaster.pop("error", "Activar Suscripción", r.mensaje);
			   }
		   })
	   })
		
	}
}])

app.controller('ModalInstanceCtrlActivar', ['$scope', '$modalInstance', 'activacion', 'toaster','$timeout',  function($scope, $modalInstance, activacion, toaster, $timeout) {

	$scope.suscripcion = activacion;
	
	$scope.activar = function(){
		
		$modalInstance.close($scope.suscripcion);
	}
	
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };	
}])