app.factory("vendedorFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/vendedor");
	
	return{
		
		 list: function() {
             return service.getList();
         },
         create: function(item){
             return service.post(item);
         }, 
 		listVendedor: function() {
 			return Restangular.allUrl("vendedor/lista").getList();
 		},
 		
 		listSucursale: function() {
 			return Restangular.allUrl("vendedor/listaSucursales").getList();
 		},
         update : function(item) {
 			return Restangular.allUrl("vendedor/actualizar").customPUT(item);
 		},
         desactivar:function(item){
        	 return Restangular.allUrl("vendedor/eliminar").customPUT(item);
         } ,
         listVendedorKoynor : function() {
			return Restangular.allUrl("vendedor/listaVendedor").getList();
		}        
         
//         remove: function(item){
//        	 return item.remove();        	 
//         },
//         clone:function(item){
//        	 return Restangular.copy(item);
//         },
		 
	}
	
}]);