app.factory("etiquetadoFactory", [ "Restangular", function(Restangular) {

	var service = Restangular.service("/etiquetado");

	return {

		urlparametros : function(item) {
			return Restangular.allUrl("etiquetado/url").customPUT(item);
		},
		subcategoria : function() {
			return Restangular.allUrl("etiquetado/subcategorias").getList();
		}

	}

} ])