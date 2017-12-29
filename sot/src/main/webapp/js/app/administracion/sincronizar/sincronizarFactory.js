app.factory("sincronizarFactory", ["Restangular",
	function (Restangular) {

	var service = Restangular.service("ruta");

	return{
		list: function() {
			return service.getList();
        },
/*        create: function(item){
            return service.post(item);
        },
        update:function(item){
        	return item.put();
        },
*/	}
}]);