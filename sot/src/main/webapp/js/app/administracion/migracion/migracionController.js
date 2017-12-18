app.controller("migracionCtrl",["$scope", "migracionFactory","$timeout","toaster",
	function($scope, migracionFactory, $timeout, toaster) {
	

	
	$scope.migrarClientes = function() {
		
		migracionFactory.migraClientes().then(function(r) {
			console.log(r);
			
		});
		
	};
	
	$scope.migrarContactos = function() {
		
		migracionFactory.migraContactos().then(function(r) {
			console.log(r);
			
		});
		
	};
	
    $scope.migrarProductos = function() {
		
		migracionFactory.migraProductos().then(function(r) {
			console.log(r);
			
		});
		
	};
	
	$scope.migrarProductosRenovacion=function(){
		
		migracionFactory.migraProductosRenovacion().then(function(r) {
			console.log(r);
			
		});
	}
	


	
	
}]);
