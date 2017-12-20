'use strict';
app.controller('userInfo', [ '$scope', '$http', "$timeout", "commonService",
             function($scope, $http, $timeout, commonService) {
 
                    $scope.usr = null;
 
                    $http.get('api/users/info').success(function(data) {
 
                           $scope.usr = data;
                           $scope.username = data.username;
                           commonService.setVar("grants", data.permisos);
                           commonService.setVar("logo", data.cliente.logo_data);
                           commonService.setVar("nombres", data.nombresCompletos);
                           commonService.setVar("email", data.cliente.email);
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
       
       $scope.ordenes=function(){
    	   
    	   
    	   notaPedidoFactory.listOrdenes().then(function(r){
      		 $scope.ordenes=r;
    	     console.log($scope.ordenes);
    	   });
    	   
       };
       
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
       
        $scope.ticksOrdenes=function(){
    	   
    	   
    	   notaPedidoFactory.listTicks().then(function(r){
            $scope.ticks=r;
    	    $scope.tockOrdenesValue();
    	   });
    	   
    	   
    	   
       };
       
       $scope.tockOrdenesValue=function(){
    	   
    	   notaPedidoFactory.listValueTicksOrdenes().then(function(r){
      		console.log(r);
      		 $scope.d = r;
    	   });
    	   
    	   notaPedidoFactory.listValueTicksEquipos().then(function(r){
         		console.log(r);
         		 $scope.d2 = r;
       	   });
    	   
    	   notaPedidoFactory.listValueTicksServicios().then(function(r){
        		console.log(r);
        		 $scope.d3 = r;
      	   });
    	   
    	   
    	   
    	   
    	   
       };
       
       $scope.toDate = function(fecha){
			if(!(fecha==null)){
				return new Date(fecha).toLocaleDateString();
			}
		};
       
       
       $scope.init=function(){
    	   
    	   //$scope.d = [ [1,6.5],[2,6.5]];
    	   $scope.d2 = [ [5,7.5],[6,7],[7,6.8],[8,7],[9,7.2],[10,7],[11,6.8],[12,7] ];
    	   //console.log($scope.xaxis);
    	   
    	   $scope.ordenes();
    	   $scope.ticksOrdenes();
    	   notaPedidoFactory.listDataInicial().then(function(r){
   			if (r.length>0){
   				$scope.total=r[0][0];
   				$scope.poraprobar=r[0][1];
   				$scope.cerradas=r[0][2];
   				$scope.abiertas=r[0][3];
   				$scope.equipos=r[0][4];
   				$scope.servicios=r[0][5];
   				
   				$scope.porcerradas=Math.round( ($scope.cerradas * 100 ) / $scope.total);
   				$scope.porabiertas=Math.round( ($scope.abiertas * 100 ) / $scope.total);
   				$scope.porfaltante=100-$scope.porcerradas;

   				$scope.data1=[$scope.porcerradas,$scope.porfaltante];
   			}
   		   });

    	   console.log(api);
           api.call("GetCountOf", {
               typeName: "Device"
           }, function (result) {
               if (result) {
            	   $scope.totalVehiculos = result;
               }
           }, function (error) {
               alert(error);
           });

           api.call("GetCountOf", {
               typeName: "User"
           }, function (result) {
               if (result) {
            	   $scope.totalVehiculos = result;
               }
           }, function (error) {
               alert(error);
           });

    	   //$scope.totalVehiculos = 345;
    	   //$scope.totalUsuarios = 21;
       };
      
} ]);
