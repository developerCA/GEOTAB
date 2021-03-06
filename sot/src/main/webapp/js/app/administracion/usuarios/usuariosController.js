app.controller("usuariosCtrl", [ "$scope", "$filter", "cooperativaFactory", "usuariosFactory", "regionResource",
                                "sucursalesFactory",  "toaster","clienteFactory","perfilesFactory","rutasFactory",
 
    function($scope, $filter, cooperativaFactory, usuariosFactory, regionResource, sucursalesFactory, toaster,clienteFactory,perfilesFactory,rutasFactory) {

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
             
        }

      

        $scope.traerRutas=function(){
        	rutasFactory.listaRutas($scope.idProducto).then(function(r) {
                $scope.rutas = r;
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

        $scope.findUsuariosPorPerfil = function(idPerfil,idEmpresa) { 
        	
            $scope.emptyUsers();
            
          
            
            usuariosFactory.listPorPerfilProducto(idPerfil,idEmpresa).then(function(request) {  
                $scope.usuarios = request; 
                console.log($scope.usuarios);
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
            $scope.empresa=item;
            $scope.nombreProductoSelected = item.nombre; 

            angular.forEach($scope.productos, function(item) {
                    item.selected = false;
            }); 
            
            $scope.producto = item;  
            $scope.producto.selected = true; 
            $scope.cargarPerfiles();
            $scope.traerRutas();
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
 
        $scope.cargarPerfiles = function() {
            $scope.emptyUsers();  
         
            perfilesFactory.list().then(function(request) {   
            	console.log(request);
                $scope.perfiles = request;    
                $scope.perfilList = $scope.perfiles[0];    
                	
                if ($scope.perfilList!=undefined)
                $scope.cargarUsuarios();
                //$scope.perfilListUser = $scope.perfilList;
                    
            });  
        }              

        $scope.cargarUsuarios = function() {  
        	
            $scope.findUsuariosPorPerfil($scope.perfilList.id,$scope.idProducto);
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
            
            console.log($scope.perfilListUser);
            adminUser.perfil = $scope.perfilListUser;
            adminUser.empresa = $scope.empresa;
            adminUser.password = $scope.password;  
            delete $scope.perfilListUser.usuarios;            
            //$scope.perfilListUser.usuario = item; 
            
            console.log(adminUser);
            return adminUser;
        }        
            
        $scope.clickGuardar = function(item){   
                   
  
            var user = $scope.buildUser(item);
        
            console.log(user);
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
