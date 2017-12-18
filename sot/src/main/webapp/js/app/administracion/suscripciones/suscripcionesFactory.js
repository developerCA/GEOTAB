app.factory("suscripcionesFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/suscripcion");
	
	return{
		
		 listClientes: function() {
             return service.getList();
         },
         listSuscripciones: function(item) {
        	  return Restangular.allUrl("suscripcion/cliente").getList({idCliente:item});
         },
         listAccesos: function(item) {
       	  return Restangular.allUrl("suscripcion/accesos").getList({idSuscripcion:item});
         },
         aprobarAccesos:function(item){
        	 return Restangular.allUrl("suscripcion/generar/accesos").customPUT(item);
         },
         consultaOt:function(item){
        	 return Restangular.allUrl("suscripcion/ots").customPUT(item);
         },
         consultaOtServicios:function(item){
        	 return Restangular.allUrl("suscripcion/otsServicios").customPUT(item);
         },
         consultaOtTecnicos:function(item){
        	 return Restangular.allUrl("suscripcion/otsTecnicos").customPUT(item);
         },
         
         guardarSeguimiento:function(item){
        	 return Restangular.allUrl("suscripcion/seguimiento").customPUT(item);
         },
         listSeguimientos: function(item) {
          	  return Restangular.allUrl("suscripcion/traerSeguimiento").getList({idTareaDetallePedido:item});
         },
         
         
         aprobarAccesosTmp:function(item){
        	 return Restangular.allUrl("suscripcion/temporal/accesos").customPUT(item);
         },
         resetearAccesos:function(item){
        	 return Restangular.allUrl("suscripcion/resetear/accesos").customPUT(item);
         },
         generarAccesoGratis:function(item){
        	 return Restangular.allUrl("suscripcion/accesos/gratis").customPUT(item);
         },
         aprobadasPorCliente:function(item){
        	 return Restangular.allUrl("suscripcion/aprobadas/cliente").getList({clienteId:item});
         },
         guardarAccesoPorRango:function(item){
        	 return Restangular.allUrl("suscripcion/accesos/rango").customPUT(item);
         },
         desactivarAccesos:function(item){
        	 return Restangular.allUrl("suscripcion/desactivar/accesos").customPUT(item);
         }
        
	}	
}]);
