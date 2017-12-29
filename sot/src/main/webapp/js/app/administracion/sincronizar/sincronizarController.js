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
