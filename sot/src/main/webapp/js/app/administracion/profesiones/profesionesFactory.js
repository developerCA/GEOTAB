app.factory("profesionResource", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/profesion");
	
	return{
		
		 list: function() {
             return service.getList();
         },
        
         create: function(item){
             return service.post(item);
         },
         update:function(item){
        	 return item.put();
         }
//         remove: function(item){
//        	 return item.remove();        	 
//         },
//         clone:function(item){
//        	 return Restangular.copy(item);
//         },
//         customPUT:function(item){
//        	 return Restangular.allUrl("administracion/perfiles/desactivar").customPUT(item);
//         }
		 
	}
	
}]);