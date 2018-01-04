app.factory("usuariosFactory" , ["Restangular" , function (Restangular, $http) {
	
    var service = Restangular.service("/admUsuario"); 
  
    return{        
        list: function() { 
            return service.getList();   
        },  
        listPorProducto:function(idProducto){
            return Restangular.allUrl("admUsuario/porProducto").getList({idProducto:idProducto});
        }, 	  
        listPorPerfilProducto:function(idPerfil,idEmpresa){ 
            return Restangular.allUrl("admUsuario/porPerfilProducto").getList({idPerfil:idPerfil,idEmpresa:idEmpresa});
        },        
        generarPassword:function(){         
            return Restangular.allUrl("admUsuario/generarPassword").customPUT();
        },    
        cambiarPassword:function(item){         
            return Restangular.allUrl("admUsuario/cambiarPassword").customPUT(null,null,{clave:item});
        },    
        
        create: function(item){ 
            return service.post(item); 
        }, 
        editar:function(item){
            return Restangular.allUrl("admUsuario/editar").customPUT(item);
        }
    } 
}]); 
