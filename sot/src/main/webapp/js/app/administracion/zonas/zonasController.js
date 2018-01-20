'use strict';

app.controller("zonasCtrl", ["$scope", "$filter", "zonasFactory",
    function($scope, $filter, zonasFactory) {

	$scope.cargado = false;
    $scope.zonas = [];
    $scope.total = 0;

    $scope.init = function() {
    	$scope.total = 10;
        $scope.traerzonas();
    }

    $scope.$watch('zonas', function() {
    	console.log("DISPOSITIVOS", $scope.zonas);
	});

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
