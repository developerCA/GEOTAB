app.controller("suscripcionesCtrl",["$scope", "suscripcionesFactory", "notaPedidoFactory",  "suscripcionFactory", "modalService", "commonService", "toaster", "$timeout","$modal",
	function($scope, suscripcionesFactory, notaPedidoFactory, suscripcionFactory, modalService, commonService, toaster, $timeout,$modal) {

		$scope.nuevo = false;
		$scope.operacion={titulo:null,color:null};
		
		$scope.ver = false;
			    		
		$scope.selectOperacion = function(index){
			$scope.selected1=false;
			$scope.selected2=false;
			$scope.selected3=false;
			$scope.selected4=false;
			
			 
			switch(index) {
		    case 1:
		    	$scope.selected1=true;
		    	$scope.operacion.titulo="Aprobación de Orden de Trabajo";
		    	$scope.operacion.color="primary";
		    	$scope.nuevo = false;
		    	
		        break;
		    case 2:
		    	$scope.selected2=true;
		    	$scope.operacion.titulo="Generación de Accesos";
		    	$scope.operacion.color="success";
		        break;
		    case 3:
		    	$scope.selected3=true;
		    	$scope.operacion.titulo="Consulta de Ordenes de Trabajo";
		    	$scope.operacion.color="warning";
		        break;
		    case 4:
		    	$scope.selected4=true;
		    	$scope.operacion.titulo="Renovaciones";
		    	$scope.operacion.color="danger";
		        break;		        
		  	} 
			
		 
			   
		 };
		 
		 $scope.init=function(){
			 notaPedidoFactory.usuario().then(function(r) {
					$scope.usuario=r.cliente;
				
			});
		 }
		
		$scope.toDate = function(fecha){
			if(!(fecha==null)){
				return new Date(fecha).toLocaleDateString();
			}
		};
		
		$scope.traerNotasPedido = function() {
			
			notaPedidoFactory.listAprobadas().then(function(r) {
					$scope.notasPedido = r;	
					console.log(r);
			});
		};
		
		$scope.aprobarSuscripcion=function(item){
			
			notaPedidoFactory.traerSuscripciones(item.id).then(function(r) {
				$timeout(function(){
					$scope.dto=r;
					$scope.nuevo = true;
				});
				
			});
		}
		
		$scope.guardarSuscripcion=function(){	
			console.log($scope.dto);
			notaPedidoFactory.aprobarSuscripcion($scope.dto).then(function(r) {
				$timeout(function(){
					
					if (r.estado == true) {
						toaster
								.pop(
										"info",
										"Orden de Trabajo",
										"Se aprobó correctamente la orden de trabajo");
						$scope.traerNotasPedido();
						$scope.nuevo = false;
					} else {
						$scope.msg = "Error: "
								+ r.mensaje;
						toaster.pop("error",
								"Orden de Trabajo",
								r.mensaje);

					}
					
				
					
				});
				
			});
			
		}
		
		$scope.revisarNotaPedido = function (size) {
		
		      var modalInstance = $modal.open({
		        templateUrl: 'tpl/app/administracion/generasuscripcion/modal.html',
		        controller: 'ModalInstanceCtrl',
		        size: size,
		        resolve: {
		          items: function () {
		            return $scope.items;
		          }
		        }
		      });

		      modalInstance.result.then(function (selectedItem) {
		        $scope.selected = selectedItem;
		        
		        $scope.registrarRevisionNotaPedido($scope.selected);
		        
		      }, function () {
		        console.log('Modal dismissed at: ' + new Date());
		      });
		};
		
       $scope.registrarRevisionNotaPedido=function(text){
			
			var item=$scope.dto.notaPedido;
			item.observacion=text;
			
			notaPedidoFactory.revisaNotaPedido(item).then(function(r) {
				
				$timeout(function(){
					$scope.nuevo = false;
					
					if (r.estado == true) {
						$scope.traerNotasPedido();
						
						$scope.msg = "Se marco la orden de trabajo como revisada ";
						toaster.pop("success", $scope.msg);
					} else {
						$scope.msg = "Error: "
								+ r.mensaje;
						toaster.pop("error", $scope.msg);
					}
		
					
				});
			
			});
			
		}
		
		
		$scope.crearEnlace=function(suscripcion,size){
			
			   var modalInstance = $modal.open({
			        templateUrl: 'tpl/app/administracion/generasuscripcion/enlace.html',
			        controller: 'ModalInstanceCtrlEnlace',
			        size: size,
			        resolve: {
			        suscripcion: function () {
			            return suscripcion;
			          }
			        }
			      });

			      modalInstance.result.then(function (selectedItem) {
			       
			    	  suscripcion.enlace=selectedItem;
			    	  suscripcion.enlaceP=selectedItem;
				    	  
			    	
			    	  
			    	
			        
			      }, function () {
			        console.log('Modal dismissed at: ' + new Date());
			      });
			
		};
		
		
		
	
		
		$scope.cancelarItem=function(){
			$scope.nuevo = false;
		}
		
				
			
				
		$scope.eliminarItem=function(item){
				
			notaPedidoFactory.eliminarSuscripcion(item).then(function(r) {
				
				$timeout(function(){
					$scope.nuevo = false;
					
					if (r.estado == true) {
						$scope.traerNotasPedido();
						
						$scope.msg = "Eliminó satisfactoriamente";
						toaster.pop("success", $scope.msg);
					} else {
						$scope.msg = "Error: "
								+ r.mensaje;
						toaster.pop("error", $scope.msg);
					}
		
					
				});
			
			});
		};
		
		$scope.listarClientes=function(){
			
			
			suscripcionesFactory.listClientes().then(function(r) {
				$timeout(function(){
					$scope.clientes = r;
				});
				
			});
		}
		
		 
		$scope.selectCliente=function(dto){
			angular.forEach($scope.clientes, function(cliente) {
				cliente.selected = false;
			});
			$scope.cliente = dto;
			$scope.cliente.selected = true;
			$scope.muestraSuscripciones=false;    
			suscripcionesFactory.listSuscripciones($scope.cliente.cliente.id).then(function(r) {
				$timeout(function(){
					$scope.suscripciones = r;
					$scope.muestraSuscripciones=true;
					$scope.muestraAccesos=false;
				});
			});
		};
		
		$scope.buscarAccesos=function(item){
			$scope.suscripcion=item;
			
			suscripcionesFactory.listAccesos(item.id).then(function(r) {
				$timeout(function(){
					$scope.accesos = r;
					$scope.muestraAccesos=true;
					
					
					
				});
			});
		};
		
		$scope.pnlVisible=true;
		
		$scope.togglePanel=function(status){
			
			if(status==undefined){
				$scope.pnlVisible=!$scope.pnlVisible;
				return;
			}else{
				$scope.pnlVisible = status;					
			}
			
		}
		
		$scope.visualizarTipo = function(acceso){
			$scope.ver = true;
		}
		
		$scope.procesarAccesos=function(){
			
			suscripcionesFactory.aprobarAccesos($scope.accesos).then(function(r) {
				$timeout(function(){
					if (r.estado == true) {
						$scope.buscarAccesos($scope.suscripcion);
						toaster
								.pop(
										"info",
										"Suscripciones",
										"Se procesaron correctamente los accesos");
					
					} else {
						$scope.msg = "Error: "
								+ r.mensaje;
						toaster.pop("error",
								"Suscripciones",
								r.mensaje);

					}
					
			    });
			});	
			
		};
		
		$scope.guardarRango = function(acceso){
			suscripcionesFactory.guardarAccesoPorRango(acceso).then(function(r){
				if(r.estado){
					toaster.pop("success", "Gestión Accesos", r.mensaje);
				}else{
					toaster.pop("error", "Gestión Accesos", r.mensaje);
				}
			})
		} 
		
		$scope.desactivarAccesos=function(){
			suscripcionesFactory.desactivarAccesos($scope.suscripcion.id).then(function(r){
				if(r.estado){
					toaster.pop("success", "Gestión Accesos", r.mensaje);
				}else{
					toaster.pop("error", "Gestión Accesos", r.mensaje);
				}
			})
		}

		$scope.cargarDatos=function(){			
			alert("En construccion");
		}
		
		$scope.activarAccesosTmp=function(){
			//$scope.seleccionarAccesos();
			
			suscripcionesFactory.aprobarAccesosTmp($scope.accesos).then(function(r) {
				$timeout(function(){
					if (r.estado == true) {
						$scope.buscarAccesos($scope.suscripcion);
						toaster
								.pop(
										"success",
										"Suscripciones",
										"Se activaron temporalmente los accesos");
					
					} else {
						
						toaster.pop("error",
								"Suscripciones",
								r.mensaje);

					}
					
			    });
			});	
			
			
		}
		
		$scope.resetarAccesos=function(acceso){
			
			suscripcionesFactory.resetearAccesos(acceso).then(function(r) {
				$timeout(function(){
					if (r.estado == true) {
						$scope.buscarAccesos($scope.suscripcion);
						toaster
								.pop(
										"success",
										"Suscripciones",
										"Se resetearon los accesos seleccionados");
					
					} else {
						
						toaster.pop("error",
								"Suscripciones",
								r.mensaje);

					}
					
			    });
			});	
			
			
		}
		
		$scope.agregarObsequio=function(){
			
			suscripcionesFactory.generarAccesoGratis($scope.suscripcion.id).then(function(r) {
				$timeout(function(){
					$scope.accesos.push(r);
			    });
			});	
			
		}
		
		$scope.accesosSeleccionados=[];
		

		
		$scope.seleccionarAccesos=function(){
			$scope.accesosSeleccionados=[];
			for(i=0;i<$scope.accesos.length;i++){
				if ($scope.accesos[i].check==true){
					$scope.accesosSeleccionados.push($scope.accesos[i]);
				}
			}
			
			
				
		}
		$scope.ac={checkTodos:false};
		
		$scope.seleccionarTodos=function(){
				
				
			if ($scope.ac.checkTodos==true){
				for(i=0;i<$scope.accesos.length;i++){
					$scope.accesos[i].ckeck=true;
				}		
			}else{
				for(i=0;i<$scope.accesos.length;i++){
					var acceso=$scope.accesos[i];
					$scope.accesos[i].ckeck=false;
				}	
			}
		}
		
		$scope.cerrarAccesos=function(){
			
			$scope.muestraAccesos=false;
		}
		
		$scope.ejecutar = function(){
			   var modalInstance = $modal.open({
			        templateUrl: 'tpl/app/administracion/suscripciones/propagar.html',
			        controller: 'ModalInstanceCtrlPropagar',
			        size: 'lg',
			        resolve: {
				        renovacion: function () {
				            return $scope.dto;
				        }
			        }
			   });
			   
			   modalInstance.result.then(function (selectedItem) {
				   $scope.dto = selectedItem;
			   })
		}		
				
} ]);


app.controller('ModalInstanceCtrlEnlace', ['$scope', '$modalInstance','clienteFactory','profesionResource','paisFactory','provinciaFactory','ciudadFactory','notaPedidoFactory','toaster','$timeout','suscripcion',  function($scope, $modalInstance,clienteFactory,profesionResource,paisFactory,provinciaFactory,ciudadFactory,notaPedidoFactory,toaster,$timeout,suscripcion) {
	$scope.suscripcion = suscripcion;
	
	$scope.buscaEnlances=function(){
		
		$scope.traerProfesiones();
		$scope.traerPaises();
		$scope.traerProvincias();
		$scope.traerCiudades();
		
		clienteFactory.listEnlaces($scope.suscripcion.clienteP.id).then(function(r) {
			$timeout(function(){
				
				$scope.items = r;
			
			});
			
		});
		
	}
	

	$scope.cancelar=function(){
		
		  $scope.editing = false;
		  $scope.visualizar = false;
		  $scope.item={};
		
	}
	 $scope.selectItem = function(item){    

		 
	  angular.forEach($scope.items, function(item) {
	      item.selected = false;
	    
	    });
		 
		    $scope.editing = false;
		    
		    $scope.visualizar = true;
		    
		    $scope.item = item;
		  
		    
		    $scope.item.selected = true;
		  };
	

     $scope.createItem = function(){
    	 $scope.visualizar = true;
    	 $scope.editing = true;
    	 $scope.item={};
	  };
	  
	  $scope.editarItem=function(){
		  $scope.editing = true;
		  
	  }
		  
	 $scope.doneEditing=function(){
		$scope.item.clienteE=$scope.suscripcion.clienteP;
	   
	    
	    notaPedidoFactory.crearEnlace($scope.item).then(function(r) {
			$timeout(function(){
				
				if (r.estado == true) {
					$scope.buscaEnlances();
					$scope.editing = false;
					$scope.msg = "Enlace creado satisfactoriamente";
					toaster.pop("success", $scope.msg);
				} else {
					$scope.msg = "Error: "
							+ r.mensaje;
					toaster.pop("error", $scope.msg);
				}
			
			});
			
		});
		 
	 }  
	 
    $scope.ok = function () {
    	
      $modalInstance.close($scope.item);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
    

    $scope.traerProfesiones = function(){
		profesionResource.list().then(function(r){
			$scope.profesiones = r;
		})			
	}
	
    $scope.traerPaises = function(){		 
		paisFactory.list().then(function(r){
			$scope.paises = r;
		})
		
	}
	
	$scope.traerProvincias = function(){
		provinciaFactory.list().then(function(r){
			$scope.provincias = r;
		})
		
	}
	
	$scope.traerCiudades = function(){	 
		ciudadFactory.list().then(function(r){
			$scope.ciudades = r;
		})	
	}
    
  }]); 

app.controller('ModalInstanceCtrlPropagar', ['$scope', '$modalInstance', 'renovacion', 'clienteFactory', 'toaster','$timeout',  function($scope, $modalInstance, renovacion, clienteFactory, toaster, $timeout) {
	
	$scope.objeto = renovacion;
	$scope.selected;
	
	$scope.init = function(){
		$scope.traerLista();
	}
	
	$scope.buscarEnlace = function(id){
		for(e=0;e<$scope.enlaces.length;e++){
			if(id == $scope.enlaces[e].id){
				return $scope.enlaces[e];
			}
		}
	}
	
	$scope.traerLista = function(){
		clienteFactory.listEnlaces($scope.objeto.suscripciones[0].clienteP.id).then(function(r){
			$scope.enlaces = r;
		})
	}
	
	$scope.propagar = function(){
		$scope.contacto = $scope.buscarEnlace($scope.selected);
	}
	
	$scope.aceptar = function() {
		for(i=0; i<$scope.objeto.suscripciones.length; i++){
		if($scope.objeto.suscripciones[i].enlaceP == null){
			if($scope.objeto.suscripciones[i].check != false ){
				$scope.objeto.suscripciones[i].enlaceP = $scope.contacto;
			}
		}
	}
		
		$modalInstance.close($scope.objeto);
	}
	
	$scope.cambiar = function(item){
    	if(item.check == null){
    		item.check = false;
    	}else{
    		item.check = true;
    	}
	}
	
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}])

app.controller('ModalInstanceCtrl', ['$scope', '$modalInstance',  function($scope, $modalInstance) {
  
    $scope.ok = function () {
    	
      $modalInstance.close($scope.htmlVariable);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])
  ; 