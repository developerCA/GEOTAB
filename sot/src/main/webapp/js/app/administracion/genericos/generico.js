app.controller('modalReport', ['$scope', '$modalInstance','$timeout','toaster','modalService','$sce','url',  function($scope, $modalInstance,$timeout,toaster,modalService,$sce,url) {
	
	
	$scope.init=function(){
	
		
		$scope.url = $sce.trustAsResourceUrl(url);
		
		
	};
	
	$scope.modalOptions = {
			headerText : 'Servicio',
			bodyText : '',
			type:'danger'
	};
		
	

	
	
	
	$scope.cancelarItem=function(){
		$scope.nuevo = false;
	};
	
	
    $scope.ok = function (item) {
    	//$scope.cliente=item;
    	
      $modalInstance.close("cliente");
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
    
    
  }]); 
