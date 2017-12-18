app.factory("notaPedidoFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/notaPedido");
	
	return{
		
		 list: function() {
             return service.getList();
         },
         
         listRegistradas: function() {
             return Restangular.allUrl("notaPedido/registradas").getList();
         },   
         listDataInicial: function() {
             return Restangular.allUrl("notaPedido/datainicial").getList();
         },  
         listOrdenes: function() {
             return Restangular.allUrl("notaPedido/ordenes").getList();
         },
         listTicks: function() {
             return Restangular.allUrl("notaPedido/ticks").getList();
         }, 
         listValueTicksOrdenes: function() {
             return Restangular.allUrl("notaPedido/valueticks1").getList();
         }, 
         listValueTicksEquipos: function() {
             return Restangular.allUrl("notaPedido/valueticksEquipos").getList();
         }, 
         listValueTicksServicios: function() {
             return Restangular.allUrl("notaPedido/valueticksServicios").getList();
         }, 
         listAprobadas: function() {
             return Restangular.allUrl("notaPedido/aprobadas").getList();
         }, 
         listTareas: function() {
             return Restangular.allUrl("notaPedido/tareas").getList();
         }, 
         
         generaSuscripcion: function(item) {
             return Restangular.allUrl("notaPedido/suscripcion").customPUT(item);
         },  
         
         generaUrlReporte: function(item) {
             return Restangular.allUrl("notaPedido/reporte").customPUT(item);
         }, 
         generaUrlReporteSinCosto: function(item) {
             return Restangular.allUrl("notaPedido/reporteSinCosto").customPUT(item);
         }, 
         
         usuario: function() {
             return Restangular.allUrl("notaPedido/usuario").customPUT();
         },  
         traerSuscripciones: function(item) {
        	
             return Restangular.allUrl("notaPedido/suscripcion/aprobadas").customPUT(item);
         },  
         guardarSuscripcion: function(item) {
             return Restangular.allUrl("notaPedido/suscripcion/crear").customPOST(item);
         },  
         aprobarSuscripcion: function(item) {
             return Restangular.allUrl("notaPedido/suscripcion/aprobar").customPOST(item);
         },  
         
         crearEnlace: function(item) {
             return Restangular.allUrl("notaPedido/suscripcion/crearenlace").customPOST(item);
         },  
         crearOrdenInterna: function() {
             return Restangular.allUrl("notaPedido/ordenInterna").customPUT();
         },  
         
         asigarTecnicos: function(item) {
             
        	 console.log(item);
        	 return Restangular.allUrl("notaPedido/responsables").customPUT(item);
         },  
         
         revisaNotaPedido: function(item) {
             return Restangular.allUrl("notaPedido/revision").customPUT(item);
         },  
         reversaNotaPedido: function(item) {
             return Restangular.allUrl("notaPedido/reversion").customPUT(item);
         },  
         create: function(item){
             return service.post(item);
         },
         update:function(item){
        	 return Restangular.allUrl("notaPedido/actualizar").customPUT(item);
         },   
         clone:function(item){
        	 return Restangular.copy(item);
         },
         eliminar:function(item){
        	 return Restangular.allUrl("notaPedido/eliminar").customPUT(item);
         },
         eliminarSuscripcion:function(item){
        	 return Restangular.allUrl("notaPedido/eliminar/suscripcion").customPUT(item);
         },
         
         eliminarDetalle:function(item){
        	 return Restangular.allUrl("notaPedido/eliminarDetalle").customPUT(item);
         },
         cancelar:function(item){
        	 return Restangular.allUrl("notaPedido/cancelar").customPUT(item);
         },
         aprobar:function(item){
        	 return Restangular.allUrl("notaPedido/aprobar").customPUT(item);
         },         
         listAprobadas:function(item){
        	 return Restangular.allUrl("notaPedido/aprobadas").getList();
         },
         calcularCostos:function(item){
        	
        	 return Restangular.allUrl("notaPedido/calcular").customPUT(item);
         },
         buscar:function(item){
        	 return Restangular.allUrl("notaPedido/buscar").customPUT(item); 
         }
	}	
}]);
