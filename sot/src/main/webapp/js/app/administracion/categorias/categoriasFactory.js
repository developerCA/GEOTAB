app.factory("categoriasResource", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/categoria");
	
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
         desactivar:function(item){
        	 return Restangular.allUrl("categoria/desactivar").customPUT(item);
         },
         guardarServicios:function(item){
        	 return Restangular.allUrl("categoria/guardarServicios").customPUT(item);
         },
         listServicio:function(item){
        	 return Restangular.allUrl("categoria/servicios").getList({idTipo:item.id});
         }  
         
	 
	}
	
}]);