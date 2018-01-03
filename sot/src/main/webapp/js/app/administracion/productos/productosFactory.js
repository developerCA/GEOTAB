app.factory("productoFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/producto");
	
	return{
        
		
		 list: function() {
             return service.getList();
         },
         listProductos: function(idCliente) {
        	 return Restangular.allUrl("producto/listado").getList({idCliente:idCliente});
         },
         listCategorias: function() {
        	 return Restangular.allUrl("producto/categorias").getList();
         },
         create: function(item){
             return service.post(item);
         },
         eliminar:function(item){
        	 return Restangular.allUrl("producto/eliminar").customPUT(item);
         },
         crear:function(item){
        	 return Restangular.allUrl("producto/crear").customPUT(item);
         },
         crearTipo:function(item){
        	 return Restangular.allUrl("producto/crearTipo").customPUT(item);
         },
         crearServicio:function(item){
        	 return Restangular.allUrl("producto/crearServicio").customPUT(item);
         },
         eliminarServicio:function(item){
        	 return Restangular.allUrl("producto/eliminarServicio").customPUT(item);
         },
         
         update:function(item){
        	 return Restangular.allUrl("producto/actualizar").customPUT(item);
         },
         listTipoProductos: function() {
        	 return Restangular.allUrl("producto/tipoProducto").getList();
         },
         listTareas: function() {
             return Restangular.allUrl("producto/tareas").getList();
         }, 
	}
}]);