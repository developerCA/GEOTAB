app.factory("ciudadFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/ciudad");
	
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
        	 return Restangular.allUrl("ciudad/eliminar").customPUT(item);
         },         
	}	
}]);