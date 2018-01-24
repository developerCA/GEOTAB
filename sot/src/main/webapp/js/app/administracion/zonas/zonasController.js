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
    		$scope.zonaz = [];
    		for (var i = 0; i < $scope.zonas.length; i++) {
    			if ($scope.zonas[i].zonaEnlace == null) $scope.zonas[i].zonaEnlace = "";
    			$scope.zonaz.push({
    				codigo: $scope.zonas[i].codigo,
    				nombre: $scope.zonas[i].nombre
				});
			}
    	    $scope.model = {
	            contacts: $scope.zonas,
	            selected: {}
	        };
    		//$scope.zonaz = $scope.zonas;
    		//console.log($scope.zonaz);
    	});
    }
/*
    $scope.$watch('zonas', function() {
    	console.log("DISPOSITIVOS", $scope.zonas);
	});
*/

    $scope.getTemplate = function(contact) {
        if (contact.codigo === $scope.model.selected.codigo) return 'edit';
        else return 'display';
    };

    $scope.editContact = function(index) {
        $scope.model.selected = angular.copy($scope.model.contacts[index]);
    };

    $scope.saveContact = function(index) {
        //console.log("Saving contact");
        $scope.model.contacts[index] = angular.copy($scope.model.selected);
        zonasFactory.guardar(
    		$scope.model.selected
		);
        $scope.reset();
    };

    $scope.reset = function() {
        $scope.model.selected = {};
    };
}]); 
