app.factory("consultaFactory", ["Restangular", function (Restangular) {
	var service = Restangular.service("/consulta");
	
	return{
		suscripcionesPorSuscripcionInicial : function(item){
			return Restangular.allUrl("consulta/suscripcion/inicial").getList({suscripcionInicial:item});
		},
		notaPedidoPorId : function(item){
			return Restangular.allUrl("consulta/notaPedido/id").customPUT(item);
		},
		vendedorPorId : function(item){
			return Restangular.allUrl("consulta/vendedor/id").customPUT(item);
		},
		sucursalPorId : function(item){
			return Restangular.allUrl("consulta/sucursal/id").customPUT(item);
		},
		renovadorPorId : function(item){
			return Restangular.allUrl("consulta/renovador/id").customPUT(item);
		},
		productoPorId : function(item){
			return Restangular.allUrl("consulta/producto/id").customPUT(item);
		},
		contactoPorId : function(item){
			return Restangular.allUrl("consulta/contacto/id").customPUT(item);
		}
		
	}
}])