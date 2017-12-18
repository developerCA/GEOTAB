app.factory("distribucionFactory", ["Restangular", function (Restangular) {
	var service = Restangular.service("/distribucion");
	
	return {
		distribucionObj: function(fechaId,categoriaId){
		
			return Restangular.allUrl("distribucion/porFechaRenovacion").getList({codigoFechaRenovacion:fechaId,codigoSubcategoria:categoriaId});
		},
		telerenovadores: function(){
			return Restangular.allUrl("distribucion/telerenovadores").getList();
		},
		asigna: function(item){
			return Restangular.allUrl("distribucion/asignar").customPUT(item);
		},
	}
}]);