app.factory("zonasFactory", ["Restangular",
	function (Restangular) {

	var service = Restangular.service("geotab");

	return{
		list: function() {
			return service.getList();
        },

        sincronizarGrupos:function(items){
       	 return Restangular.allUrl("geotab/sincronizar").customPOST(items);
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