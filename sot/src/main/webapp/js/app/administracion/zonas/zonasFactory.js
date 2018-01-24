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
        	var url = "geotab/rutas/";
    			
        	return Restangular.allUrl(url).getList({codigoEmpresa:empresa});
        },

        cargarZonas: function(ruta) {
        	var url = "geotab/zonas/";

        	return Restangular.allUrl(url).getList({codigoRuta:ruta});
        },

        guardar: function(item){
            return Restangular.allUrl("geotab/actualizarZona/").customPOST(item);
        },
/*
        update:function(item){
        	return item.put();
        },
*/	}
}]);