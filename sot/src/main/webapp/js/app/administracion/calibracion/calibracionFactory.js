app.factory("calibracionFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/calibracion");
	
	return{
         list: function() {
        	 return Restangular.allUrl("calibracion/instrumentos").getList();
         },

         listDispositivos: function() {
        	 return Restangular.allUrl("geotab/listar/dispositivos").getList();
         },

         listPorInstrumento: function(item) {
        	 return Restangular.allUrl("calibracion/calibracionesPorInstrumento").getList({calibracion:item.id});
         },
         
         crearItem:function(item){
        	 return Restangular.allUrl("calibracion/crear").customPUT(item);
         },
         crearCalibracion:function(item){
        	 return Restangular.allUrl("calibracion/calibracion").customPUT(item);
         },
         
         update:function(item){
        	 return Restangular.allUrl("calibracion/actualizar").customPUT(item);
         },
         updateVerificacion:function(item){
        	 return Restangular.allUrl("calibracion/calibracionActualizar").customPUT(item);
         },
         
         eliminar:function(item){
        	 return Restangular.allUrl("calibracion/eliminar").customPUT(item);
         },
         listCalibraciones: function() {
         	
        	 return Restangular.allUrl("calibracion/calibracionesInstrumento").getList();
         }
       
        
	}
	
}]);