app.controller('tipoClienteCtrl', ['$scope', "tipoCliente", "$timeout", 
                               function($scope, tipoCliente, $timeout, $filter) {
	
	$scope.init = function(){

	}
	
	$scope.traerProfesiones = function(){
		$scope.profesiones = profesionResource.list();
	}
	

  $scope.selectItem = function(item){    
    angular.forEach($scope.items, function(item) {
      item.selected = false;
      item.editing = false;
    });
    $scope.item = item;
    $scope.item.selected = true;
    console.log(item);
  };

  $scope.deleteItem = function(item){
    $scope.items.splice($scope.items.indexOf(item), 1);
    $scope.item = $filter('orderBy')($scope.items, 'first')[0];
    if($scope.item) $scope.item.selected = true;
  };

  $scope.createItem = function(){
	  var item = {};
//    $scope.items.push(item);
//    $scope.selectItem($scope.item);
	  $scope.item = item;
	  $scope.selectItem($scope.item);
	$scope.items.editing = true;
  };

  $scope.editItem = function(item){
    if(item && item.selected){
    	item.editing = true;
    }
  };

  $scope.doneEditing = function(item){
	if($scope.item.id != null){
		alert("UPDATE");
		clienteFactory.update($scope.item).then(function(r){
			$scope.traerClientes();
		}); 	  		
	} else{
		alert("CREATE");
		clienteFactory.create($scope.item).then(function(r){
			$scope.traerClientes();
		}); 	  		
	} 
    item.editing = false;    
  };

}]);