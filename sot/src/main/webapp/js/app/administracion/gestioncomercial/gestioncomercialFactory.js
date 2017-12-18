app.factory("gestionFactory", ["Restangular", function (Restangular) {
	var service = Restangular.service("/gestion");
	return {
		usuario: function(){
			return Restangular.allUrl("gestion/usuario").customPUT();
		},
		gestion: function(item){
			return Restangular.allUrl("gestion/porGestion").customPUT(item);
		},
		detalle: function(fecha,telerenovador){
			return Restangular.allUrl("gestion/porDetalle").getList({idFechaRenovacion:fecha.id,idTelerenovador:telerenovador.id});
		},
		eliminar: function(item){
			return Restangular.allUrl("gestion/eliminarSuscripcion").customPUT(item);
		},
		renovados: function(item){
			return Restangular.allUrl("gestion/guardarRenovacion").customPUT(item);
		},
		cancelado: function(item){
			return Restangular.allUrl("gestion/cancelarGestionDistribucion").customPUT(item);
		},
		canceladoIndividual: function(item){
			return Restangular.allUrl("gestion/cancelarGestionDistribucionIndividual").customPUT(item);
		},
		
		motivo: function(item){
			console.log(item);
			return Restangular.allUrl("gestion/motivoAvisoRenovado").customPUT(item);
		},
		productosConAccesos: function(item){
			return Restangular.allUrl("gestion/productos/conAccesos").getList({codigoSuscripcion:item});
		},
		accesosPorSuscripcion: function(item){
			return Restangular.allUrl("gestion/accesos/porSuscripcion").getList({idSuscripcion:item});
		},
		renovacionAccesos: function(item){
			return Restangular.allUrl("gestion/mapearAccesos").customPUT(item);
		},
		agregarAccesos: function(item){
			return Restangular.allUrl("gestion/accesosAgregados").customPUT(item);
		},
		suscripcionPorId: function(item){
			return Restangular.allUrl("gestion/suscripcion").customPUT(item);
		},
		accesosRenovacionPorIdSuscripcion: function(item){
			return Restangular.allUrl("gestion/accesosRenovacionPorSuscripcionId").getList({idSuscripcion:item});
		},
		
		agregarProductosNuevos: function(item){
			return Restangular.allUrl("gestion/nuevasRenovaciones").customPUT(item);
		},
		calcular: function(item){
		
			return Restangular.allUrl("gestion/calcular").customPUT(item);
		},
		
		generarNotaPedido: function(item){
			return Restangular.allUrl("gestion/generarNotaPedido").customPUT(item);
		}
		
		
		
	}
}]);