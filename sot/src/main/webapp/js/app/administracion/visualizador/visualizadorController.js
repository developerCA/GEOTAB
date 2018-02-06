app.controller("visualizadorCtrl", ["$scope", "$filter", "$rootScope","$sce",
    function($scope, $filter, $rootScope,$sce) {
        $scope.filter = '';  

        $scope.visualizarPDF=function(){
        	//console.log('ssssssssssssssssssss');
        	$scope.archivo=$rootScope.nombreArchivo;
        	//console.log($scope.archivo);
        	$scope.pdf = {
    			src: $scope.archivo
			};
			//console.log($scope.pdf);
			//$window.open($scope.pdf.src);
        }

}]); 
