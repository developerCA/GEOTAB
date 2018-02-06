app.factory("sincronizarFactory", ["Restangular",
	function (Restangular) {

	var service = Restangular.service("ruta");

	return{
		list: function() {
			return service.getList();
        },

        sincronizarGrupos:function(items){
       	 return Restangular.allUrl("geotab/sincronizarGrupos").customPOST(items);
        },
        
        sincronizarFechasLozalizaciones:function(){
          	 return Restangular.allUrl("geotab/fechasDispositivos").customPOST();
        },
        
        sincronizarLozalizaciones:function(item){
         	 return Restangular.allUrl("geotab/procesarDatos").customPOST(item);
       },
       
       generarReporte:function(item){
       	 return Restangular.allUrl("geotab/reporte").customPOST(item);
       },

       generarReporteGet:function(item){
       	 return Restangular.allUrl("geotab/reporte/" + item.vuelta + "/" + item.codigoDispositivo).customGET();
       },
    
       sincronizarTablero:function(){
         	 return Restangular.allUrl("geotab/procesarTablero").customPOST();
       },
       
       sincronizarHoraProgramada:function(item){
       	 return Restangular.allUrl("geotab/horaProgramada").customPOST(item);
       },
     
        sincronizarDispositivos:function(items){
      	 return Restangular.allUrl("geotab/sincronizarDispositivos").customPOST(items);
        },
        
        sincronizarZonas:function(itemDto){
        	
         	 return Restangular.allUrl("geotab/sincronizarZonas").customPOST(itemDto);
        },
        
      
	}
}]);