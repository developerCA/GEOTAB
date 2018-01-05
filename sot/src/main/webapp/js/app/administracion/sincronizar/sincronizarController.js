app.controller("sincronizarCtrl", ["$scope", "$filter", "sincronizarFactory",
    function($scope, $filter, sincronizarFactory) {
        $scope.filter = '';  

        $scope.init = function() { 
            //$scope.listProductos();
            $scope.traersincronizar();
        }

        $scope.traersincronizar = function() {
        	$scope.sincronizar = [];
        	sincronizarFactory.list().then(function(r) {
                $scope.sincronizar = r;
            })   
        };

    	$scope.sincronizarDispositivos = function(){
    		//console.log("cargar Dispositivos");
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
                alert(error);
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
