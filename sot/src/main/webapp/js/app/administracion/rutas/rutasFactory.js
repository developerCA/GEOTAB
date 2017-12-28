app.factory("rutasFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/geotab");
	
	return{
        
		
		
         listaRutas: function(codigoEmpresa) {
        	 return Restangular.allUrl("geotab/rutas").getList({codigoEmpresa:codigoEmpresa});
         },
                 
        
	}
	
}]);