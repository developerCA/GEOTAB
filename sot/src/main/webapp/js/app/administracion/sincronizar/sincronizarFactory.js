app.factory("sincronizarFactory", ["Restangular",
	function (Restangular) {

	var service = Restangular.service("ruta");

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