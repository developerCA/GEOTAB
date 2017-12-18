app.controller('contactCtrl', ['$scope', "clienteFactory", "paisFactory", "provinciaFactory", "ciudadFactory", "profesionResource", "tipoClienteFactory", "regionResource", "sucursalesFactory", "modalService", "$timeout", "$filter", 
                               function($scope, clienteFactory, paisFactory, provinciaFactory, ciudadFactory, profesionResource, tipoClienteFactory, regionResource, sucursalesFactory, modalService, $timeout, $filter) {
	
	$scope.ver = false;
	$scope.clientes = null;
	
	$scope.init = function(){
		$scope.traerClientes();
		$scope.traerPaises();
		$scope.traerProvincias();
		$scope.traerCiudades();
		$scope.traerProfesiones();
		$scope.traerTiposCliente();
		$scope.traerRegiones();
		$scope.traerSucursales();
		
		
	}
	
	/*======CONSTANTES========*/
	$scope.constantes = {
			confirmaCreacion : "<h4><i class='fa fa-question-circle'></i> Confirma actualizar registro cliente</h4>",
			confirmaEliminacion : "<h4><i class='fa fa-question-circle'></i> Confirma eliminar registro cliente</h4>",
	}
	
	/*======MODAL========*/
	$scope.modalOptions = {
			headerText : 'Confirmar modificación',
			bodyText : '',
			type:'success'
	};
	

	$scope.traerClientes = function(){		 
		clienteFactory.list().then(function(r){
				$scope.clientes = r;
				if($scope.clientes.length > 0){
					$scope.cliente = $filter('orderBy')($scope.clientes, 'nombres')[0];
					$scope.cliente.selected = true;
					$scope.ver = true;				
				}
		})		
	}	
	
	$scope.traerCodigoKohinor = function() {
		clienteFactory.listClientesKoynor().then(function(r) {
			$scope.listClientesKoynor = r;
		});
		
	};
	
	
	$scope.traerPaises = function(){		 
		paisFactory.list().then(function(r){
			$scope.paises = r;
		})
		
	}
	
	$scope.traerProvincias = function(){
		provinciaFactory.list().then(function(r){
			$scope.provincias = r;
		})
		
	}
	
	$scope.traerCiudades = function(){	 
		ciudadFactory.list().then(function(r){
			$scope.ciudades = r;
		})	
	}
	
	$scope.traerProfesiones = function(){
		profesionResource.list().then(function(r){
			$scope.profesiones = r;
		})			
	}
	
	$scope.traerTiposCliente = function(){		 
		tipoClienteFactory.list().then(function(r){
			$scope.tiposCliente = r;
		})			
		
	}
	
	$scope.traerRegiones = function(){
		regionResource.list().then(function(r){
			$scope.regiones =  r;
		})				
	}

	$scope.traerSucursales = function(){
		sucursalesFactory.list().then(function(r){
			$scope.sucursales = r;
		})				
	}
	
  $scope.filter = '';

  $scope.selectItem = function(item){    
    
	  angular.forEach($scope.clientes, function(item) {
	      item.selected = false;
	      item.editing = false;
	      $scope.ver = true;
	  });
    
    
    $scope.cliente = item
    $scope.myImage='';
    $scope.myCroppedImage='';
    console.log($scope.cliente.logo_data);
   
    $scope.myCroppedImage=$scope.cliente.logo_data==null ? '' : $scope.cliente.logo_data;
    console.log($scope.myCroppedImage);
    
    $scope.cliente.selected = true;
  };

  $scope.nombres="Nombres";
  $scope.apellidos="Apellidos";
  $scope.nacimiento="Fecha Nacimiento";
  
  $scope.cambiarTipo=function(){
	  

	if ($scope.cliente.tipoCliente.id==1){
		$scope.nombres="Nombres";
		$scope.apellidos="Apellidos";
		 $scope.nacimiento="Fecha Nacimiento";
		  		
	}else{
		$scope.nombres="Empresa";
		$scope.apellidos="Nombre Comercial";
		$scope.nacimiento="Fecha Apertura";
		  
	}
	
  };
  $scope.deleteItem = function(item){
		$scope.modalOptions.bodyText = $scope.constantes.confirmaEliminacion;
		$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
		
		modalService.showModal({}, $scope.modalOptions).then(function(r){
			clienteFactory.eliminar(item).then(function(r){
				$scope.traerClientes();
				$scope.ver = true;
			})
		});
	    if($scope.item) $scope.item.selected = true;
		$scope.ver = false;  					
  };

  $scope.createItem = function(){
	  var item = {};
	  
	  $scope.cliente = item;
	  $scope.selectItem($scope.cliente);
	  $scope.cliente.editing = true;
  };

  $scope.editItem = function(item){
    if(item && item.selected){
    	item.editing = true;
    }
  };
  
  $scope.ejecutar = function(item){
	  alert(item);
  }
  
  $scope.cancelar = function(){
	  $scope.ver = false;
  }

  $scope.doneEditing = function(item){
	$scope.modalOptions.bodyText = $scope.constantes.confirmaCreacion;
	$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
	
	modalService.showModal({}, $scope.modalOptions).then(function(r){
		if($scope.cliente.id != null){
			 $scope.cliente.logoTmp=$scope.myCroppedImage;
			clienteFactory.update($scope.cliente).then(function(r){
				$scope.traerClientes();
			}); 	  		
		} else{
			  $scope.cliente.logoTmp=$scope.myCroppedImage;
			clienteFactory.create($scope.cliente).then(function(r){
				$scope.traerClientes();
			}); 	  		
		} 
	    item.editing = false;
	    $scope.ver = false;
	});  
	
  };
  
  
  $scope.myImage='';
  $scope.myCroppedImage='';
  $scope.cropType="circle";

 
  var handleFileSelect=function(evt) {
    var file=evt.currentTarget.files[0];
    
    
    var reader = new FileReader();
    reader.onload = function (evt) {
      $scope.$apply(function($scope){
        $scope.myImage=evt.target.result;
      });
    };
    reader.readAsDataURL(file);
  };
  angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);
  
  

}]);
