app.factory("tableroFactory", ["Restangular",
	function (Restangular) {

	var service = Restangular.service("ruta");

	return{
		list: function() {
			return service.getList();
        },

        tableroGrupos:function(items){
       	 return Restangular.allUrl("geotab/tableroGrupos").customPOST(items);
        },

        tableroDispositivos:function(items){
      	 return Restangular.allUrl("geotab/tableroDispositivos").customPOST(items);
        },
        
        
/*
        create: function(item){
            return service.post(item);
        },
        update:function(item){
        	return item.put();
        },
*/	}
}]);