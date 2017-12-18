app.controller("distribucionCtrl",["$scope", "distribucionFactory", "fechasFactory", "subCategoriaFactory","toaster", "$timeout",
    function($scope, distribucionFactory, fechasFactory, subCategoriaFactory,toaster, $timeout) {
	
	// Initialization
	
	$scope.fecha=null;
	$scope.linea=null;
	$scope.telerenovador=null;
	
	$scope.init = function(){
		$scope.traerFechas();
		$scope.traerTelerenovadores();
		$scope.traerLineas();
	}	
	
	$scope.all = {check:false};	
	
	
	$scope.traerFechas = function(){
		fechasFactory.fechasProcesadas().then(function(r){
			$scope.fechas = r;
		})
	}

	$scope.traerLineas=function(){
		subCategoriaFactory.list().then(function(r){
			
			$scope.lineas=r;
		})
		
	}
	$scope.traerTelerenovadores = function(){
		distribucionFactory.telerenovadores().then(function(r){
			$scope.telerenovadores = r;
			console.log(r);
		})
	}
	
	$scope.buscarSuscripciones=function(){
		
		if ($scope.fecha==null){
			toaster.pop("warning","Distribución","Seleccione la fecha de distribución de cartera");
			return;
		}
		if ($scope.linea==null){
			toaster.pop("warning","Distribución","Seleccione la línea de producto");
			return;
		}
		
		
		distribucionFactory.distribucionObj($scope.fecha.id,$scope.linea.id).then(function(r){
			
			$scope.distribuciones = r;
		})
		
	}
	
	$scope.resetear=function(){
		$scope.distribuciones={};
	}
	
	
	$scope.seleccionarTodos = function(){
		
		if($scope.distribuciones != null){
			
			for(i=0; i < $scope.distribuciones.length; i++){
				$scope.distribuciones[i].check = $scope.all.check;
			}
			
		}else{
			$scope.all.check = false;
			toaster.pop("error","Distribución","No existen elementos que seleccionar...");
		}
	}
	
	$scope.asignar = function(){
		
		var asignaciones=[];
		var cont=0;
		
		if ($scope.telerenovador!=null){
			for(i=0; i < $scope.distribuciones.length; i++){
				if ($scope.distribuciones[i].check){
					cont++;
					$scope.distribuciones[i].telerenovador=$scope.telerenovador;
					asignaciones.push($scope.distribuciones[i]);
				}
			}
			
			if (cont>0){
				distribucionFactory.asigna(asignaciones).then(function(r){
				
					$scope.buscarSuscripciones();
					toaster.pop("success","Distribución","La asignación se ejecutó exitosamente...");
				})	
				
				
			}else{
				toaster.pop("warning","Distribución de Cartera","No ha seleccionado ninguna suscripción para poder asignar");
			}
			
	
		}else{
			
			toaster.pop("warning","Distribución de Cartera","Seleccione el Telerenovador ");
		}
		
		
	}
	
	
	
	
}]); 