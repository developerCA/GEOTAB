app.controller("renovacionesPendientesCtrl",["$scope", "renovacionesPendientesFactory", "modalService", "commonService", "toaster", "$timeout", "$modal",
	function($scope, renovacionesPendientesFactory, modalService, commonService, toaster, $timeout, $modal) {
	
	$scope.init = function(){
	
		$scope.traerRenovacionesPendientes();
	}
	
	$scope.nuevo = false;
	$scope.res = 0;
	
	$scope.traerRenovacionesPendientes = function(){
		renovacionesPendientesFactory.procesadas().then(function(r){
			$scope.renovacionesPendientesList = r;
		})
	}
	
	$scope.decimales = function(numero){
		return parseFloat(Math.round(numero * 100) / 100).toFixed(2);
	}
	
	
	$scope.traerSuscripcionesGeneradas = function(item){
		$scope.renovacionPendiente = item;
		renovacionesPendientesFactory.generarSuscripciones($scope.renovacionPendiente).then(function(r){
			$scope.suscripcionesGeneradas = r;
			console.log(r);
			$scope.nuevo = true;
			$scope.renovacionesAprobadas($scope.renovacionPendiente);
		})		
	}
		
	$scope.cancelarItem = function(){
		$scope.nuevo = false;
	}
	
	$scope.verificarEnlaces = function(){
		var res = true;
		
		for (var i = 0; i < $scope.suscripcionesGeneradas.suscripciones.length; i++) {
			if($scope.suscripcionesGeneradas.suscripciones[i].enlaceP == null){
				res = false;
			}			
		}
		return res;
	}
	
	
	$scope.subtotal = function(item, item1){
		if(item1 != null){
			return item * item1;
		}
		return item;
	}
	
	$scope.totalProductos = function(){
		var resultado = 0;
		for(j = 0; j < $scope.suscripcionesGeneradas.detalleNotaPedido.length; j++){
			resultado += $scope.suscripcionesGeneradas.detalleNotaPedido[j].valorReal;
		}
//		alert(resultado);
		return resultado;
	}
	
	$scope.aprobarRenovaciones = function(){
		if($scope.verificarEnlaces()){
			$scope.suscripcionesGeneradas.renovacion = $scope.renovacionPendiente;
			renovacionesPendientesFactory.guardaRenovaciones($scope.suscripcionesGeneradas).then(function(r){
				$scope.traerRenovacionesPendientes();
				$timeout(function(){
					
					if (r.estado == true) {
						toaster
								.pop(
										"info",
										"Aprobar Renovaciones",
										"Se generarom correctamente las renovaciones");
						$scope.nuevo = false;
					} else {
						$scope.msg = "Error: "
								+ r.mensaje;
						toaster.pop("error",
								"Aprobar Renovaciones",
								r.mensaje);
	
					}
					
				});
			})
		}else{
			toaster.pop("error",
					"Aprobar Renovaciones",
					"Es necesario ingresar los enlaces");
		}
	}
	
	$scope.renovacionesAprobadas = function(item){
		renovacionesPendientesFactory.traerRenovacionesAprobadas(item).then(function(r){
			$scope.renovacionAprobada = r;
			
			console.log($scope.renovacionAprobada);
		})
	}
}])