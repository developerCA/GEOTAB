app.controller("sincronizarCtrl", ["$scope", "$filter", "sincronizarFactory",
    function($scope, $filter, sincronizarFactory) {
        $scope.filter = '';  

      

    	$scope.sincronizarDispositivos = function(){
    		console.log(api);
            api.call("Get", {
                typeName: "Device"
            }, function(result) {
    		    console.log(result);
    		    sincronizarFactory.sincronizarDispositivos(
    				result
    			).then(function(r) {
    				console.log(r);
    			});

           }, function(error) {
              //  alert(error);
            });
    	};

    	$scope.sincronizarGrupos = function(){
    		api.call("Get", {
    		    "typeName": "Group"
    		}, function(result) {
    		    console.log(result);
    		    sincronizarFactory.sincronizarGrupos(
    				result
    			).then(function(r) {
    				console.log(r);
    			});
    		}, function(e) {
    		    console.error("Failed:", e);
    		});
    	}
    	
    	$scope.sincronizaZonas=function(){
    		
    		api.call("Get", {
    		    "typeName": "Zone"
    		}, function(resultZonas) {
    		    console.log(resultZonas);
    		    
    		   
    		    api.call("Get", {
        		    "typeName": "ZoneType"
        		}, function(resultType) {
        		    
        			console.log(resultType);
        			
        		    resultType.splice(0,1);
        		    resultType.splice(0,1);
        		    resultType.splice(0,1);
        		    resultType.splice(0,1);
        		    
        		    
        		    var zonas=[];
        		    var dtoZonas={tipoZonaGeotabDto:null,zonaGeotabDtos:null};
        		    
        		    for (var i =0;i<=resultZonas.length-1;i++){
        		    	
        		    	
        		    	
        		    	
        		    	if (resultZonas[i].zoneTypes[0]!="ZoneTypeHomeId" && 
        		    			resultZonas[i].zoneTypes[0]!="ZoneTypeCustomerId" &&
        		    			resultZonas[i].zoneTypes[0]!="ZoneTypeOfficeId" &&
        		    			resultZonas[i].zoneTypes[0]!="ZoneTypeAddressLookupId" ){
        		    		zonas.push(resultZonas[i]);
        		    	}else{
        		    		  console.log(resultZonas[i]);
        		    	}
        		    }
        		  
        		    console.log(zonas);
        		    
        		    dtoZonas.tipoZonaGeotabDto=resultType;
        		    dtoZonas.zonaGeotabDtos=zonas;
        		  
        		    sincronizarFactory.sincronizarZonas(
        		    		dtoZonas
            			).then(function(r) {
            				console.log(r);
            		});
        		   
        		}, function(e) {
        		    console.error("Failed:", e);
        		});
    		    
    		}, function(e) {
    		    console.error("Failed:", e);
    		});
    		
    	}
/*
        $scope.listProductos = function(){	
        	cooperativaFactory.list().then(function(request) {
                $scope.productos = request;
                $scope.idProducto = $scope.productos[0].id;
            })    
        }    

        $scope.selectProducto = function(item) {
        	
        	console.log(item);
          
            $scope.emptyUsers();
            $scope.idProducto = item.id;
            $scope.nombreProductoSelected = item.nombre; 

            angular.forEach($scope.productos, function(item) {
                    item.selected = false;
            }); 
            
            $scope.producto = item;  
            $scope.producto.selected = true; 
            $scope.cargarPerfiles($scope.idProducto);
        };
*/
}]); 
