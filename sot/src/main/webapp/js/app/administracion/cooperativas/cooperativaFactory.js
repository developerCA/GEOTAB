app.factory("cooperativaFactory", ["Restangular", function (Restangular) {
	
    var service = Restangular.service("/empresa");

    return{

        list: function() {
            return service.getList();
        },

        create: function(item){
            return service.post(item);
        }, 
 
        agregarPerfilProducto: function(item){ 
            return Restangular.allUrl("empresa/agregarPerfilProducto").customPUT(item);
        }, 

        removerPerfilProducto: function(item){ 
            return Restangular.allUrl("empresa/removerPerfilProducto").customPUT(item);
        },

        update:function(item){
                return item.put();
        },

        desactivar:function(item){
                return Restangular.allUrl("empresa/desactivar").customPUT(item);
        }

    }
	
}]);