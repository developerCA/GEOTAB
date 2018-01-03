'use strict';

app.controller("dispositivosCtrl", ["$scope", "$filter", "dispositivosFactory",
    function($scope, $filter, dispositivosFactory) {

	$scope.cargado = false;
    $scope.dispositivos = [];
    $scope.total = 0;

    $scope.init = function() {
    	$scope.total = 10;
        $scope.traerdispositivos();
    }

    $scope.$watch('dispositivos', function() {
    	console.log("DISPOSITIVOS", $scope.dispositivos);
	});

    $scope.traerdispositivos = function() {
        api.call("Get", {
            typeName: "Device"
        }, function(result) {
        	//console.log($scope);
        	//console.log(result);
            if (result !== undefined && result.length > 0) {
            	$scope.dispositivos = result;
            	$scope.total = result.length;
            	$scope.cargado = true;
            	console.log($scope.dispositivos);
            } else {
                alert("No se pudo obtener los dispositivos");
            }
        }, function(error) {
            alert(error);
        });
    };
}]); 
