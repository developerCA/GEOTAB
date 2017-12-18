app.factory("subCategoriaFactory", ["Restangular", function (Restangular) {
	
	var service = Restangular.service("/subcategoria");
	
	return{
	    list: function() {
             return service.getList();
        }

	}
	
}])