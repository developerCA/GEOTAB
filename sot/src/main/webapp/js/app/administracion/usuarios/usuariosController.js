app.controller("usuariosCtrl", [ "$scope", "$filter", "cooperativaFactory", "usuariosFactory", "regionResource",
                                "sucursalesFactory", "asignacioPermisoResource", "toaster","clienteFactory",
 
    function($scope, $filter, cooperativaFactory, usuariosFactory, regionResource, sucursalesFactory, asignacioPermisoResource, toaster,clienteFactory) {

        $scope.filter = '';  
        $scope.productos = null; 
        $scope.perfiles = null;   
        $scope.regiones = null;  
        $scope.sucursales = null;  
        $scope.usuarios = null; 
        $scope.idProducto = null;
        $scope.colorProducto = null;
        $scope.nombreProductoSelected = null;
        $scope.password = null;
    
        $scope.init = function(){ 
            $scope.listProductos();
            $scope.traerClientes();
        } 
        
        $scope.traerClientes=function(){
        	clienteFactory.list().then(function(r) {
                $scope.clientes = r;
               
            })   
        	
        };
 
        $scope.listProductos = function(){	
        	cooperativaFactory.list().then(function(request) {
                $scope.productos = request;
                $scope.idProducto = $scope.productos[0].id;
            })    
        }    
         
        $scope.listRegiones = function(){ 
            regionResource.list().then(function(request) { 
                $scope.regiones = request;      
                       
                if ($scope.regiones.length > 0){   
                    $scope.regionList = $scope.regiones[0]; 
                    $scope.listSucursalesByRegion(0);
                }    
            })               
        }             
             
        $scope.listSucursalesByRegion = function(idSucursal){
            sucursalesFactory.listPorRegion($scope.regionList.id).then(function(request) {
                $scope.sucursales = request;     

                if ($scope.sucursales.length > 0) 
                    $scope.sucursalList = idSucursal > 0 ? {"id": idSucursal} : $scope.sucursales[0];  
            })            
        }             

        $scope.findUsuariosPorPerfilProducto = function(idPerfilProducto) { 
            $scope.emptyUsers();
            usuariosFactory.listPorPerfilProducto(idPerfilProducto).then(function(request) {  
                $scope.usuarios = request; 
               // console.log($scope.usuarios);
                if ($scope.usuarios.length > 0)  
                	$scope.usuario = $filter('orderBy')($scope.usuarios,'first')[0];
                else
                    $scope.emptyUsers();
            })                              
        }

        $scope.emptyUsers = function() {  
            $scope.usuarios = {}; 
            $scope.item = {}; 
            $scope.password = null; 
        }

        $scope.selectProducto = function(item) {
        	
        	console.log(item);
          
            $scope.emptyUsers();
            $scope.idProducto = item.id;
            $scope.nombreProductoSelected = item.nombre; 

            angular.forEach($scope.productos, function(item) {
                    item.selected = false;
            }); 
            
            $scope.producto = item;  
            $scope.producto.selected = true; 
            $scope.cargarPerfiles($scope.idProducto);
        };

        $scope.selectUser = function(item){  
            angular.forEach($scope.usuarios, function(item) {
              item.selected = false;
              item.editing = false;
            });    
                 
            if($scope.colorProducto == 'primary'){  
                $scope.regionList = {"id": item.region};
                $scope.listSucursalesByRegion(item.sucursal)
            }                 
               
            $scope.item = item;
            $scope.item.selected = true; 
            $scope.perfilListUser = $scope.perfilList;  
        };                   

        $scope.clickEditUser = function(item){
            $scope.password = " ";          
            item.creating = false;   
            
            if(item && item.selected)
                item.editing = true;
        };         

        $scope.clickCancel = function(item){
            item.editing = false;
            //$scope.cargarPerfiles($scope.perfilList.producto.id);            
        };    
 
        $scope.cargarPerfiles = function(idProducto) {
            $scope.emptyUsers();         
            asignacioPermisoResource.traerPerfiles(idProducto).then(
            function(request) {    
                $scope.perfiles = request;    
                $scope.perfilList = $scope.perfiles[0];    
                //console.log($scope.perfilList);
                if ($scope.perfilList!=undefined)
                $scope.cargarUsuarios( $scope.perfiles[0].id );
                $scope.perfilListUser = $scope.perfilList;
                    
            });  
        }              

        $scope.cargarUsuarios = function() {  
        	
            $scope.findUsuariosPorPerfilProducto($scope.perfilList.id);
        };                             
             
        $scope.clickCreateUser = function(){     
            $scope.usuario = {};   
            var item = {"estadoUsuario": true};
            //console.log($scope.perfilList);
            //$scope.perfilList.usuarios.push(item);   
            $scope.selectItem(item);
            $scope.item.editing = true;
            $scope.item.creating = true; 
            
            //$scope.regionList = $scope.regiones[0]; 
            //$scope.listSucursalesByRegion(0);
        };       
 
        $scope.selectItem = function(item){    
            angular.forEach($scope.usuarios, function(item) {
              item.selected = false;
              item.editing = false;
            });

            $scope.item = item;
            $scope.item.selected = true;
        };        
        
        $scope.crearUsuario = function(item){
        	
            usuariosFactory.create(item).then( 
                function(request) { 
                    if (request.estado == true) {
                            toaster.pop("info","Usuario","Registro guardado satisfactoriamente");
                            $scope.nuevo = false; 
                            $scope.item.creating=false;
                            $scope.item.editing=false;
                             
                    } else { 
                            toaster.pop("error", "Asignación", request.mensaje);
                            
                    }                 
                });            
        }
        
        $scope.editarUsuario = function(item){ 
            usuariosFactory.editar(item).then(  
                function(request) { 
                    if (request.estado == true) {
                            toaster .pop("success", "Usuario", "Registro actualizado satisfactoriamente");
                            //$scope.cargarPerfiles($scope.perfilList.producto.id);
                           // $scope.selectItem(item.usuario);
                            $scope.item.creating=false;
                            $scope.item.editing=false;
                    } else { 
                            toaster.pop("error", "Asignación", request.mensaje);
                            //$scope.cargarPerfiles($scope.perfilList.producto.id);
                           
                    }                
                });            
        }         
        
        $scope.buildUser = function(item){ 
            var adminUser = {}; 
            adminUser.usuario = item;  
            
          
            adminUser.perfil = $scope.perfilListUser.perfil;
            adminUser.empresa = $scope.perfilList.empresa;
            adminUser.password = $scope.password;  
            delete $scope.perfilListUser.usuarios;            
            $scope.perfilListUser.usuario = item; 
            
            return adminUser;
        }        
            
        $scope.clickGuardar = function(item){   
                   
  
            var user = $scope.buildUser(item);
        
            console.log($scope.item);
            if($scope.item.creating)
                $scope.crearUsuario(user);
            else //Edit 
                $scope.editarUsuario(user);
               
            $scope.item.editing = true;
            $scope.item.creating = true; 
        };           
           
        $scope.clickGenerarPassword = function(item){
            $scope.password  = null; 
            usuariosFactory.generarPassword().then(    
                function(request) {    
                    if (request.estado == true) { 
                        $scope.password = request.objeto;
                    } else {       
                       
                        alert (request.mensaje);
                    }                  
                });               
        };         
 }]); 
