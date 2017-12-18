app.factory("detalleNotaPedidoFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/detalleNotaPedido");
	
	return{
		
		 list: function() {
             return service.getList().$object;
         },   
         create: function(item){
             return service.post(item);
         },
         update:function(item){
        	 return item.put();
         },
         clone:function(item){
        	 return Restangular.copy(item);
         },
         eliminar:function(item){
        	 return Restangular.allUrl("detalleNotaPedido/eliminar").customPUT(item);
         },
         traerObjeto:function(item){
        	 return Restangular.allUrl("detalleNotaPedido/traerObjeto").customPUT();
         },
         
         listPorNotaPedidoId:function(item){
        	return Restangular.allUrl("detalleNotaPedido/porNotaPedidoId").getList({id:item});
         },
	}	
}]);