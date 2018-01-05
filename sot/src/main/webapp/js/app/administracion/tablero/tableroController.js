app.controller("tableroCtrl", ["$scope", "$filter", "tableroFactory",
    function($scope, $filter, tableroFactory) {
    $scope.tableros = null;  
	$scope.beginningOfDay = new Date();
	$scope.endOfDay = new Date();
	$scope.list = ["bB9","b21","b23","b82"];
	
	$scope.tableros = [];
	$scope.mostrar = false;
	$scope.ultimo = null;
	$scope.p = 0;
	$scope.data = [];

    $scope.init = function() { 
		$scope.beginningOfDay.setHours(0, 0, 0, 0);
		$scope.endOfDay.setHours(23, 59, 59, 59);
        $scope.traertablero();
    }
    
    $scope.$watch("tableros",function() {
       $scope.data=$scope.tableros;
       console.log($scope.data);
    });
    
    $scope.traertablero = function() {
    	//$scope.tableros = [];
    	$scope.p = 0;
    	for (var i = 0; i < $scope.list.length; i++) {
			api.call("Get", {
                typeName: "LogRecord",
                search: {
                    deviceSearch: {
                        id: $scope.list[i]
                    },
                    fromDate: $scope.beginningOfDay.toISOString(),
                    toDate: $scope.endOfDay.toISOString()
                }
            }, function(result) {
            	if ($scope.tableros.length==4) {
            		$scope.tableros=[];
            	}
            	$scope.p++;
            	var ii;
                if (result !== undefined) {
                    ii = result.length - 1;
                    //console.log(result);
                    $scope.tableros.push(result[ii]);
                    //if ($scope.tableros.length == 4) {
                    //	console.log($scope.tableros);
                    //	$scope.mostrar = true;
                    //}
                   
                } else {
                    alert("Could not retrieve trip");
                }
            }, function(error) {
                alert(error);
            });
		}
    };
}]); 
