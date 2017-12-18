app.controller("etiquetadoCtrl", ["$scope", "etiquetadoFactory", "fechasFactory", "$timeout", function($scope, etiquetadoFactory, fechasFactory, $timeout){
	
	
	$scope.init = function(){
		$scope.traerRangosFechas();
		$scope.traerSubcategorias();
	}
	
	$scope.filtros = {};
	
	$scope.rangoSelecionado = null;
	
	$scope.mostrar = false;
	
	$scope.traerUrl = function(){
		etiquetadoFactory.urlparametros($scope.filtros).then(function(r){
			$scope.cadena = r;
			console.log(r);
			$scope.mostrar = true;
		})
	}
	
	$scope.elegida = function(){

	}
	
	$scope.traerRangosFechas = function(){
		fechasFactory.fechasProcesadas().then(function(r){
			$scope.fechas = r;
		})
	}
	
	$scope.traerSubcategorias = function(){
		etiquetadoFactory.subcategoria().then(function(r){
			$scope.subcategorias = r;
		})
	}
	
	$scope.generar = function(){
		$scope.traerUrl();
		console.log($scope.traerUrl());
	}
	
}]);