app.controller("tableroCtrl", ["$scope", "$filter", "tableroFactory",
    function($scope, $filter, tableroFactory) {
        $scope.filter = '';  

        $scope.init = function() { 
            //$scope.listProductos();
            $scope.traertablero();
        }

        $scope.traertablero = function() {
        	$scope.tablero = [];
        	tableroFactory.list().then(function(r) {
                $scope.tablero = r;
            })   
        };
}]); 
