app.factory("renovacionesPendientesFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/renovacionesPendientes");
	
	return{
		procesadas: function(){
			return Restangular.allUrl("renovacionesPendientes/procesadas").getList();
		},
		generarSuscripciones: function(item){
			return Restangular.allUrl("renovacionesPendientes/generarSuscripciones").customPUT(item);
		},
		guardaRenovaciones: function(item){
			return Restangular.allUrl("renovacionesPendientes/persistirRenovaciones").customPUT(item);
		},
		traerDetalleRenovaciones: function(item){
			return Restangular.allUrl("renovacionesPendientes/renovacionDetalleLista").getList({r:item});
		},
		traerRenovacionesAprobadas: function(item){
			return Restangular.allUrl("renovacionesPendientes/aprobadas").customPUT(item);
		},
//		guardarAccesosPendientes: function(item){
//			return Restangular.allUrl("renovacionesPendientes/guardarAccesosTemporales").customPUT(item);
//		}
	}
	
}])