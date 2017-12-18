app.factory("activacionesFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/activacion");
	
	return {
		activadasTemporal: function(){
			return Restangular.allUrl("activacion/activas/temporal").getList();
		},
		clientesSuscripcionesAprobadas: function(){
			return Restangular.allUrl("activacion/clientesPorSuscripcionesActivas").getList();
		},
		activarsuscripcion: function(item){
			return Restangular.allUrl("activacion/activar/suscripcion").customPUT(item);
		}
	}
}])	