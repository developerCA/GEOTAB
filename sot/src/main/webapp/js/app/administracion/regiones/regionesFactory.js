app.factory("regionResource", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/region");
	
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
        	 return Restangular.allUrl("region/desactivar").customPUT(item);
         }         
         
	 
	}
	
}]);