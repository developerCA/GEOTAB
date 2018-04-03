'use strict';

app.controller("admindispositivosCtrl", ["$scope", "$filter", "admindispositivosFactory",
    function($scope, $filter, admindispositivosFactory) {

	$scope.cargado = false;
    $scope.dispositivos = [];

    $scope.init = function() {
    	admindispositivosFactory.cargarEmpresas().then(function(rest) {
    		$scope.empresas = rest.splice(1);
    	});
    }

    $scope.cargarRutas = function() {
    	admindispositivosFactory.cargarRutas(
			$scope.empresa
		).then(function(rest) {
    		$scope.rutas = rest;
    	});
    }
/*
    $scope.$watch('dispositivos', function() {
    	console.log("DISPOSITIVOS", $scope.dispositivos);
	});
*/
    $scope.cargarDispositivos = function() {
    	$scope.dispositivos = [];
    	admindispositivosFactory.cargarDispositivos(
			$scope.ruta
		).then(function(rest) {
    		$scope.dispositivos = rest;
    	});
    }

    $scope.eliminar = function(index) {
    	admindispositivosFactory.eliminar(
    		$scope.dispositivos[index]
		).then(function(rest) {
			$scope.cargarDispositivos();
    	});
    };
}]); 
