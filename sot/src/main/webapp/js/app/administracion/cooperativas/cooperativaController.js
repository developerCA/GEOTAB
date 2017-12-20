app
.controller(
            "cooperativaCtrl",
            [
                "$scope",
                "toaster",
                "cooperativaFactory",   
                "$timeout",  
                "asignacioPermisoResource",
                "perfilesFactory",
                
                function($scope, toaster, cooperativaFactory, $timeout, asignacioPermisoResource, perfilesFactory) {
                     
                    $scope.perfiles = {}; 
                    $scope.perfilesProducto = {}; 
                    $scope.itemPerfil = {};
  
                    treedata_avm = [ {
                            "label" : "Cargando...",
                            data : {
                                    description : "Procesando Perfiles..."
                            },
                            id : 3
                    } ]

                    treedata_avm_2 = [ {
                            "label" : "Cargando Perfiles por Producto...",
                            data : {
                                    description : "Procesando elementos perfil..."
                            },
                            id : 3
                    } ]                    
                 
                    $scope.my_data = treedata_avm;
                    $scope.my_data_2 = treedata_avm_2;                

                    $scope.nuevo = false;
                    $scope.perfil = false; 

                    $scope.init = function() { 
                        $scope.traerItems();
                    };  
                     
                    $scope.clear = function() {
                        $scope.perfiles = {}; 
                        $scope.perfilesProducto = {};                         
                    }
 
                    $scope.traerItems = function() { 
                        cooperativaFactory.list().then(function(r){
                        	console.log(r);
                            $scope.items = r;
                        });
                    }                                                        
   
                    $scope.cargarPerfilProducto = function(idProducto) {
                        asignacioPermisoResource.traerPerfiles(idProducto).then(
                        function(request) {    
                            $scope.perfilesProducto = request;
                            $scope.cargarPerfiles(); 
                        });                           
                    }
                     
                    $scope.cargarPerfiles = function() {  
                        perfilesFactory.list().then(function(request) {
                            
                                 
                                angular.forEach($scope.perfilesProducto, function(perfilProducto) {
                                    angular.forEach(request, function(perfil, key) {
                                        if (perfilProducto.perfil.id == perfil.id && perfilProducto.empresa.id == $scope.item.id && perfilProducto.estado == true )
                                            request.splice(key, 1);       
                                    });     
                                });   
                                
                                $scope.perfiles = request;
                        });                         
                    }                     
                     
                    $scope.nuevoItem = function() {
                        $scope.nuevo = true;
                    };

                    $scope.cancelarItem = function() {

                        $scope.nuevo = false;
                        $scope.editar = false;
                        $scope.perfil = false; 
                        $scope.item={descripcionProducto:null,nombreProducto:null};

                    };

                    $scope.item={descripcionProducto:null,nombreProducto:null};
                    
                    $scope.selectPerfil = function(itemPerfil){ 
                        $scope.unselectAll();                        
                        $scope.itemPerfil = itemPerfil;
                        $scope.itemPerfil.selected = true; 
                    };           
                        
                    $scope.selectPerfilProducto = function(itemPerfilProducto){
                        $scope.unselectAll();
                        $scope.itemPerfilProducto = itemPerfilProducto; 
                        $scope.itemPerfilProducto.selected = true;   
                    };                      
                    
                    $scope.agregarPerfilProducto = function() {  
                         var item = {empresa : $scope.item, perfil : $scope.itemPerfil}; 

                        angular.forEach($scope.perfilesProducto, function(perfilProducto) {
                            angular.forEach($scope.perfiles, function(perfil, key) {
                                if (perfilProducto.perfil.id == perfil.id && perfilProducto.empresa.id == $scope.item.id && !perfilProducto.estado)
                                    item.id = perfilEmpresa.id; 
                            });        
                        });   
                           
                        cooperativaFactory.agregarPerfilProducto(item).then( 
                            function(request) {  
                                    if (request.estado == true) { 
                                        toaster.pop("info", "Asignación Perfil Producto", "Registro guardado satisfactoriamente");
                                        $scope.asignarPerfil($scope.item);
                                    } else
                                        toaster.pop("error", "Asignación Perfil Producto", r.mensaje);
                            });
                    };                         

                    $scope.guardarItem = function() {
                    	cooperativaFactory
                            .create($scope.item)
                            .then(function(r) {

                                $scope.status = !r.estado;

                                if (r.estado == true) {
                                        toaster.pop("info","Productos","Registro guardado satisfactoriamente");
                                        $scope.traerItems();
                                        $scope.item={descripcionProducto:null,nombreProducto:null};
                                        $scope.nuevo = false;
                                } else {

                                        toaster.pop("error",
                                                        "Productos",
                                                        r.mensaje);

                                }

                            });

                    };

                    $scope.actualizarItem = function() {
                    	cooperativaFactory
                            .update($scope.item)
                            .then(function(r) { 

                                $scope.status = !r.estado;

                                if (r.estado == true) {
                                    toaster
                                        .pop(
                                            "success",
                                            "Productos",
                                            "Registro actualizado satisfactoriamente");

                                    $scope.traerItems();
                                    $scope.cancelarItem();

                                } else {

                                    toaster.pop("error",
                                                "Productos",
                                                r.mensaje);

                                }

                            });
                    };
                    
                    $scope.removerPerfilProducto = function() {
                            
                    	cooperativaFactory.removerPerfilProducto($scope.itemPerfilProducto).then( 
                            function(request) {  
                                    if (request.estado == true) { 
                                        toaster.pop("info", "Remover Perfil Producto", "Registro removido satisfactoriamente");
                                        $scope.asignarPerfil($scope.item);  
                                    } else
                                        toaster.pop("error", "Eliminación Perfil Producto", r.mensaje);
                            });
                    };                    


                    $scope.desactivarItem = function(item) {

                    	cooperativaFactory
                            .desactivar(item)
                            .then(function(r) {

                                $scope.status = !r.estado;

                                if (r.estado == true) {
                                    toaster
                                        .pop(
                                            "success",
                                            "Productos",
                                            "Registro actualizado satisfactoriamente");
                                    $scope.traerItems();


                                } else {

                                        toaster.pop("error",
                                                        "Productos",
                                                        r.mensaje);

                                }

                            });
                    };

                    $scope.editarItem = function(item) {
                        $scope.item = item;
                        $scope.nuevo = true;
                        $scope.editar = true; 
                        $scope.perfil = false;
                    }

                    $scope.asignarPerfil = function(item) {
                        $scope.unselectAll();
                        
                        $scope.item = item;
                        $scope.perfil = true;
                        $scope.nuevo = true;
                        $scope.editar = true;  
                          
                        $scope.clear();
                        $scope.cargarPerfilProducto(item.id);
                    } 
                    
                    $scope.unselectAll  = function() {
                        angular.forEach($scope.perfiles, function(itemPerfil) {
                          itemPerfil.selected = false;
                        });
                            
                        angular.forEach($scope.perfilesProducto, function(itemPerfilProducto) {
                          itemPerfilProducto.selected = false;
                        });                          
                    }
                    

                } ]);
