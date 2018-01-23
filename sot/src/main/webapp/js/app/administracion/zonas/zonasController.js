'use strict';

app.controller("zonasCtrl", ["$scope", "$filter", "zonasFactory",
    function($scope, $filter, zonasFactory) {

    $scope.empresas = [];
    $scope.empresa = "";
    $scope.rutas = [];
    $scope.ruta = "";
    $scope.zonas = [];

    $scope.init = function() {
    	zonasFactory.cargarEmpresas().then(function(rest) {
    		//console.log(rest);
    		$scope.empresas = rest.splice(1);
    	});
    }

    $scope.cargarRutas = function() {
    	zonasFactory.cargarRutas(
			$scope.empresa
		).then(function(rest) {
    		//console.log(rest);
    		$scope.rutas = rest;//.splice(1);
    	});
    }

    $scope.cargarZonas = function() {
    	zonasFactory.cargarZonas(
			$scope.ruta
		).then(function(rest) {
    		//console.log(rest);
    		$scope.zonas = rest;//.splice(1);
    	});
    }
/*
    $scope.$watch('zonas', function() {
    	console.log("DISPOSITIVOS", $scope.zonas);
	});
*/
    $scope.traerzonas = function() {
        api.call("Get", {
            typeName: "Device"
        }, function(result) {
        	//console.log($scope);
        	//console.log(result);
            if (result !== undefined && result.length > 0) {
            	$scope.zonas = result;
            	$scope.total = result.length;
            	$scope.cargado = true;
            	console.log($scope.zonas);
            } else {
                alert("No se pudo obtener los zonas");
            }
        }, function(error) {
            alert(error);
        });
    };
}]); 
