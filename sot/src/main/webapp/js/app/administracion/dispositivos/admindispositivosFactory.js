app.factory("admindispositivosFactory", ["Restangular",
	function (Restangular) {

	var service = Restangular.service("geotab");

	return{
        cargarEmpresas: function() {
        	var url = "geotab/listar/empresas";
        	return Restangular.allUrl(url).customGET();
        },

        cargarRutas: function(empresa) {
        	var url = "geotab/rutas/";
    			
        	return Restangular.allUrl(url).getList({codigoEmpresa:empresa});
        },

        cargarDispositivos: function(ruta) {
        	var url = "geotab/listar/dispositivosRuta";

        	return Restangular.allUrl(url).getList({ruta:ruta});
        },

        eliminar: function(items) {
       	 	return Restangular.allUrl("geotab/eliminarDispositivo").customPOST(items);
        },
	}
}]);