app.controller("visualizadorCtrl", ["$scope", "$filter", "$rootScope",
    function($scope, $filter, $rootScope) {

        $scope.visualizarPDF=function(){
        	$scope.archivo=$rootScope.nombreArchivo;
        	//console.log($scope.archivo);
        	$scope.pdf = {
    			src: $scope.archivo
			};
        }
    	
    	$scope.cancelar=function(){
    		this.$close();
    	};

}]); 
