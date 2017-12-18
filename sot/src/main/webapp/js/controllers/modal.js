// tab controller
app.controller('modalController', ['$scope', '$modal', function($scope, $modal) {

	$scope.template = "<p>Entra!!!</p>";
	$scope.buttons = [];
	$scope.instance = null;
	
	$scope.open = function(){
		alert("open");
		$scope.instance = $modal.open({
			  template:$template,
			  size:"lg"
		  });
		
	};
	$scope.close = function(){
		
		$scope.instance.close();
		
	}
	
}]);