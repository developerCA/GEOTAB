
app.factory("migracionFactory", [ "Restangular", function(Restangular) {

	var service = Restangular.service("/migracion");

	return {

	
		migraClientes : function() {
			return Restangular.allUrl("migracion/clientes").customPUT();
		},
		migraContactos : function() {
			return Restangular.allUrl("migracion/contactos").customPUT();
		},
		
		migraProductos : function() {
			return Restangular.allUrl("migracion/productos").customPUT();
		},
		migraProductosRenovacion : function() {
			return Restangular.allUrl("migracion/productosRenovacion").customPUT();
		},
		
		
		
		
	}
} ]);