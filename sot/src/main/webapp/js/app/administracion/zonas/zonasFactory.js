app.factory("zonasFactory", ["Restangular",
	function (Restangular) {

	var service = Restangular.service("geotab");

	return{
		list: function() {
			return service.getList();
        },

        cargarEmpresas: function() {
        	var url = "geotab/listar/empresas";
        	return Restangular.allUrl(url).customGET();
        },

        cargarRutas: function(empresa) {
        	var url = "geotab/rutas/"
    			+ empresa;
        	return Restangular.allUrl(url).customGET();
        },

        cargarZonas: function(ruta) {
        	var url = "geotab/zonas/"
    			+ ruta;
        	return Restangular.allUrl(url).customGET();
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