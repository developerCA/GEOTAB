app.factory("prefacturarFactory", ["Restangular", function (Restangular) {
	var service = Restangular.service("/prefactura");
	
	return{
		prefacturar: function(){
			return Restangular.allUrl("prefactura/noPrefacturados").getList();
		},
		detalles: function(item){
			return Restangular.allUrl("prefactura/detallesPorPedido").getList({id:item});
		},
		totales: function(item){
			return Restangular.allUrl("prefactura/totalesPorPedido").customPUT(item);
		},
		ordenpedido: function(item){
			return Restangular.allUrl("prefactura/pedido").customPUT(item);
		},
		bodegas: function(){
			return Restangular.allUrl("prefactura/bodegas").getList();
		},
		sucursalesbodegas: function(item){
			return Restangular.allUrl("prefactura/sucursalBodega").getList({codigoBodega:item});
		},
		verificarHomologacion: function(item){
			return Restangular.allUrl("prefactura/detallesPorPedido").getList({dto:item});
		},
		verificarHomologacionOrdenPedido: function(item){
			return Restangular.allUrl("prefactura/verificarHomologacion").customPUT(item);
		}
	}
}])