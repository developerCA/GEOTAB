app.factory("paisFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/paises");
	
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
//         customPUT:function(item){
//        	 return Restangular.allUrl("administracion/usuarios/desactivar").customPUT(item);
//         },
//         customDEL:function(item){
//        	 return Restangular.allUrl("administracion/usuarios/eliminar").customPUT(item);
//         },
//         customRESET:function(item){
//        	 return Restangular.allUrl("administracion/usuarios/reset").customPUT(item);
//         },
         
		 
       
		 
	}
	
}]);