app.factory("rolesFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/rol");
	
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
        	 return Restangular.allUrl("rol/desactivar").customPUT(item);
         }  

		 
	}
	
}]);