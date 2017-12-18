app.factory("clienteFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/cliente");
	
	return{
		
		 list: function() {
             return service.getList();
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
        	 return Restangular.allUrl("cliente/eliminar").customPUT(item);
         },
         listPorAtributos:function(item1, item2, item3){
        	 return Restangular.allUrl("cliente/porAtributos").getList({identificacion:item1, nombres:item2, apellidos:item3});
         }, 
         
         listEnlaces:function(item){
        	 return Restangular.allUrl("cliente/enlace").getList({idCliente:item});
         }, 
         
         listClientesKoynor : function() {
 			return Restangular.allUrl("cliente/listaClientesKohinor").getList();
 		}
         
	}	
}]);