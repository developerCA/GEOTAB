'use strict';
app.controller('userInfo', [ '$scope', '$http', "$timeout", "commonService",
             function($scope, $http, $timeout, commonService) {
 
                    $scope.usr = null;
 
                    $http.get('api/users/info').success(function(data) {
 
                           $scope.usr = data;
                           $scope.username = data.username;
                           commonService.setVar("grants", data.permisos);
                           commonService.setVar("nombres", data.nombresCompletos);
                           commonService.setVar("data", data);
                                 
 
 
 
                    });
             } ]);
app.controller('getGrants', [ '$scope', 'commonService', '$timeout','notaPedidoFactory','$http','usuariosFactory','$window','modalService',
                              function($scope, commonService, $timeout,notaPedidoFactory,$http,usuariosFactory,$window,modalService) {
       //timeout
       $timeout(function(){
    	   
             $scope.grants = commonService.getVar("grants");
             $scope.logo = commonService.getVar("logo");
             $scope.nombres = commonService.getVar("nombres");
             $scope.email = commonService.getVar("email");
             $scope.data = commonService.getVar("data");
             
       });
       
      
       
       $scope.cargarReset=function(){
    	   
    	   $http.get('api/users/info').success(function(data) {
    		   $scope.data=data; 
    		  
    	   });
    	 
    	
    	   
       };
       
       $scope.cambiar=function(){
    	   
    	   $scope.modalOptions = {
   				headerText : 'Cambio de Clave',
   				bodyText : 'Cambio de clave exitoso',
   				type:'success'
   		   };
    	   
    	  
			$scope.modalOptions.templateUrl="tpl/modals/accept.html";
		
		
			
    	  
    	   usuariosFactory.cambiarPassword($scope.password).then(function(r){
              if (r.estado==true){
            		modalService.showModal({}, $scope.modalOptions).then(function(r){
            			
            			 $window.location.href = $scope.app.versionApp+ '/logout';
        			});	
            	 
              }else{
            	  
            	  
              }
       	   });
    	   
       };
       
      
       
      
       
       $scope.toDate = function(fecha){
			if(!(fecha==null)){
				return new Date(fecha).toLocaleDateString();
			}
		};
       
       
       $scope.init=function(){
    	   
    	  
         
       };
      
} ]);
