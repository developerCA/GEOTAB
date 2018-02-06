app.controller("visualizadorCtrl", ["$scope", "$filter", "$rootScope","$window",
    function($scope, $filter, $rootScope,$window) {
        $scope.filter = '';  

        $scope.visualizarPDF=function(){
        	console.log('ssssssssssssssssssss');
        	$scope.archivo=$rootScope.nombreArchivo;
        	//console.log($scope.archivo);
        	$scope.pdf = {src: 'files/'+$scope.archivo};
			console.log($scope.pdf);
			//$window.open($scope.pdf.src);
        }
      
}]); 
