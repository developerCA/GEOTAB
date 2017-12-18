app.controller("avisosRenovacionCtrl", ["$scope", "avisosFactory", "sucursalesFactory","toaster", "$timeout", function($scope, avisosFactory,sucursalesFactory, toaster, $timeout){
	$scope.init = function(){
		$scope.traerFechaRegistrada();
		$scope.traerSucursal();
	}
	
	$scope.avisos = {};	
	$scope.proceso=false;
	
	$scope.traerFechaRegistrada = function(){
		avisosFactory.avisofechaProceso().then(function(r){
		
			if(r.estado == true){
				$scope.fechaRegistrada = r.objeto;
				if ($scope.fechaRegistrada!=null){
					$scope.mostrar = $scope.fechaRegistrada.fechaInicio + " - " + $scope.fechaRegistrada.fechaFin;
					$scope.traerSuscripcionesPorFechas();		
				}
			
			}else{
				toaster.pop("error", r.mensaje);
			}
	
			
		})
		
	}
	
	$scope.traerSucursal=function(){
		sucursalesFactory.traerSucursal().then(function(r){
			
			$scope.sucursal=r;
		})
		
	};
	
	$scope.traerSuscripcionesPorFechas = function(){
		avisosFactory.fechas($scope.fechaRegistrada).then(function(r){
			//console.log(r);
			$scope.lineas=r;
			
		})
	}
	

		
	$scope.procesar = function() {
		
		avisosFactory.procesar($scope.lineas).then(function(r){
			if(r.estado == true){
				
				toaster.pop("success", "Se generaron satisfactoriamente los avisos");
				$scope.fechaRegistrada=null;
				$scope.proceso=true;
			}else{
				toaster.pop("error", "Un error se ha sucitado durante el procesamiento");
			}
		})
	}
	
	
	
	
}]);