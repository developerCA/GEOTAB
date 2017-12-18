app.controller('consultaCtrl', ['$scope', "consultaFactory", "clienteFactory","notaPedidoFactory","vendedorFactory","suscripcionesFactory", "modalService", "$timeout", "$filter","toaster","$modal", 
                               function($scope, consultaFactory,clienteFactory,notaPedidoFactory, vendedorFactory,suscripcionesFactory, modalService, $timeout, $filter,toaster,$modal) {
	$scope.init = function(){
	
		$scope.muestraHistorial = false;
		$scope.muestraNotaPedido = false;
		$scope.muestraVendedor = false;
		$scope.muestraSucursal = false;
		$scope.muestraProducto = false;
		$scope.muestraContacto = false;
		$scope.buscar();
		
	};
	
	$scope.initGeneral = function(){
		
	
		$scope.muestraContacto = false;
		$scope.item.tipo=4;
		$scope.buscar();
		$scope.traerVendedores();
	};
	
	$scope.initResultados = function(){
		$scope.buscar();
		$scope.aside = {
				  "title": "Consulta de Resultados",
				  "content": "Hello Aside<br />This is a multiline message!"
		};
		
	};
	
	
	
	$scope.toDate = function(fecha){
		if(!(fecha==null)){
			return new Date(fecha).toLocaleDateString();
		}
	};
	
	$scope.aside = {
			  "title": "Consulta de Ordenes",
			  "content": "xx"
	};
	
	$scope.asideServicios = {
			  "title": "Consulta de Servicios de Equipos",
			  "content": "xx"
	};
	
	$scope.asideTecnicos = {
			  "title": "Consulta de Experiencia por Técnico",
			  "content": "xx"
	};
	
	$scope.buscar = function(){
		clienteFactory.listPorAtributos("", "", "").then(function(r){
			$scope.clientes = r;
			
		})
	};
	
	$scope.item={fechaDesde:null,fechaHasta:null,tipo:2,equipo:'',servicio:'',serie:'',oet:'',cliente:null,tecnico:null,oit:'',reporte:''};

	$scope.buscarOT=function(){
		
		suscripcionesFactory.consultaOt($scope.item).then(function(r) {
			
			$scope.lista=r.objeto;
			
			$scope.muestraContacto = false;
		});
		
	};
	
	
	
    $scope.buscarOTEmpresa=function(){
	  
		suscripcionesFactory.consultaOt($scope.item).then(function(r) {
			$scope.lista=r.objeto;
			$scope.muestraContacto = false;
			
		});
		
	};

	$scope.buscarOTEmpresaServicio=function(){
	
		suscripcionesFactory.consultaOtServicios($scope.item).then(function(r) {
			$scope.muestraContacto = false;
			$scope.crearListaServicios(r.objeto);
		});
		
	};
	
	$scope.buscarOTEmpresaTecnico=function(){
		
		$scope.item.tipo=1;
		suscripcionesFactory.consultaOtTecnicos($scope.item).then(function(r) {
			$scope.lista= r.objeto;
		});
		
	};
	
	$scope.buscarOTFEmpresaServicio=function(){
	   	$scope.item.tipo=6;
	 
		suscripcionesFactory.consultaOtServicios($scope.item).then(function(r) {
			
			$scope.muestraContacto = false;
			$scope.crearListaServicios(r.objeto);
			
			
		});
	};
	
	$scope.buscarOTFEmpresaTecnico=function(){
	   	$scope.item.tipo=6;
	  
		suscripcionesFactory.consultaOtTecnicos($scope.item).then(function(r) {
			$scope.lista= r.objeto;
			
		});
	};
	
	$scope.servicios=[];
	
	$scope.crearListaServicios=function(objeto){
		
		$scope.servicios=[];
		objeto.forEach(function(nota) {
			
			nota.detalleServicios.forEach(function(detalle) {
				$scope.servicios.push(detalle);
			});
			
		    
		});
		
	
	};
	
	$scope.resetCliente=function(){
	 $scope.item.cliente=null;	
		
	};
	$scope.resetTecnico=function(){
		 $scope.item.tecnico=null;	
			
	};
	
	
	

	$scope.traerVendedores = function() {
		vendedorFactory.listVendedor().then(function(r){
			$scope.vendedores = r;
			
		});
	}
	
    $scope.buscarOTF=function(){
    	$scope.item.tipo=3;
    
		suscripcionesFactory.consultaOt($scope.item).then(function(r) {
			$scope.lista=r.objeto;
			$scope.muestraContacto = false;
			
			
		});
		
	};
	
	  $scope.buscarOTFEmpresa=function(){
		 
	    	$scope.item.tipo=6;
	    
			suscripcionesFactory.consultaOt($scope.item).then(function(r) {
				$scope.lista=r.objeto;
				$scope.muestraContacto = false;
			
			});
			
		};
		
		$scope.initSeguimiento=function(){
			$scope.buscarOTFEmpresaSeguimiento();
			
		};
		
		$scope.crearSeguimiento=function(t){
			$scope.tarea=t;
			
			var modalInstanceAdd = $modal.open({
		        templateUrl: 'tpl/app/administracion/seguimiento/modalSeguimiento.html',
		        controller: 'ModalInstanceCtrlAddSeguimiento',
		        size: 'lg',
		        resolve: {
		        	tarea: function () {
			            return $scope.tarea;
			          }
			     }

		      });

			modalInstanceAdd.result.then(function (selectedItem) {
		      
		        
		      }, function () {
		    	
		    	  $scope.verOrden($scope.seleccion);
		        console.log('Modal dismissed at: ' + new Date());
		      });
			
			
			
		};
		 $scope.buscarOTFEmpresaSeguimiento=function(){
		    	$scope.item.tipo=7;
		    
				suscripcionesFactory.consultaOt($scope.item).then(function(r) {
				    				
					$scope.lista=r.objeto;
					
					
					$scope.muestraContacto = false;
				
				});
				
			};
		$scope.modalOptions = {
				headerText : 'Orden de Trabajo',
				bodyText : '',
				type:'danger'
		};
		
		$scope.reversarOrden=function(item){
			$scope.modalOptions.bodyText = "Esta seguro de reversar la orden de trabajo?";
			$scope.modalOptions.templateUrl="tpl/modals/modalConfirm.html";
		
		
			modalService.showModal({}, $scope.modalOptions).then(function(r){
				notaPedidoFactory.reversaNotaPedido(item.notaPedido).then(function(r) {
					
					$timeout(function(){
						$scope.nuevo = false;
						
						if (r.estado == true) {
							
							item.notaPedido.idCatalogo.nombre="Reversado";
							item.notaPedido.idCatalogo.sigla="REVNOT";
							
							$scope.msg = "Se marco la orden de trabajo como reversada ";
							toaster.pop("success", $scope.msg);
						} else {
							$scope.msg = "Error: "
									+ r.mensaje;
							toaster.pop("error", $scope.msg);
						}
			
						
					});
				
				});

			});	
			
		};
	
	$scope.exportar=function(){
		
		window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#tbl').html()));

	}
	$scope.listarClientes=function(){
		suscripcionesFactory.listClientes().then(function(r) {
			$timeout(function(){
				$scope.clientes = r;
			});
			
		});
	}
	
	$scope.verOrden=function(item){
		
		$scope.seleccion=item;
		
		$scope.id=Math.random().toString(36).slice(2);
		
		$scope.muestraContacto=true;
		notaPedidoFactory.traerSuscripciones(item.notaPedido.id).then(function(r) {
			$timeout(function(){
				$scope.dto=r;
			   
				$scope.nuevo = true;
			});
			
		});
		
	};
	
   $scope.imprimirOrden=function(item){
		
		
		notaPedidoFactory.generaUrlReporte(item.notaPedido.id).then(function(r) {
			
			
				$scope.url=r.url;
				var modalInstanceAdd = $modal.open({
			        templateUrl: 'tpl/app/administracion/informes/reporte.html',
			        controller: 'modalReport',
			        size: 'lg',
			        resolve: {
			        	url: function () {
				            return $scope.url;
				          }
				     }

			      });

		  	      modalInstanceAdd.result.then(function () {
			       }, function () {
			        console.log('Modal dismissed at: ' + new Date());
			      });
			
		});
		
	};
	
    $scope.imprimirOrdenSinCosto=function(item){
		
		
		notaPedidoFactory.generaUrlReporteSinCosto(item.notaPedido.id).then(function(r) {
			
			
				$scope.url=r.url;
				var modalInstanceAdd = $modal.open({
			        templateUrl: 'tpl/app/administracion/informes/reporte.html',
			        controller: 'modalReport',
			        size: 'lg',
			        resolve: {
			        	url: function () {
				            return $scope.url;
				          }
				     }

			      });

		  	      modalInstanceAdd.result.then(function () {
			       }, function () {
			        console.log('Modal dismissed at: ' + new Date());
			      });
			
		});
		
	};
	
	$scope.cerrarItem=function(){
		
		$scope.muestraContacto=false;
	};
	
	$scope.traerSuscripcionesPorSuscripcionInicial = function(item){
		consultaFactory.suscripcionesPorSuscripcionInicial(item).then(function(r){
			$scope.suscripcionesIniciales = r;
		})
	}
	
	$scope.traerNotaPedidoPorId = function(item, s){
		consultaFactory.notaPedidoPorId(item).then(function(r){
			$scope.notaPedido = r;
			$scope.muestraNotaPedido = true;
			if(s.tipoOperacionP.id == 2){
				$scope.traerRenovadorNoTabla($scope.notaPedido.objeto.vendedor.id);
			}
			
		})
	}
	
	$scope.cerrarHistorial = function(){
		$scope.muestraHistorial = false;
	}
	
	$scope.renovacionesHistorial = function(item){
		$scope.traerSuscripcionesPorSuscripcionInicial(item);
		$scope.muestraHistorial= true;
	}
	
	$scope.mostrarNotaPedido = function(item, suscripcion){
		$scope.suscripcionElegida = suscripcion;
		$scope.traerNotaPedidoPorId(item, suscripcion)
	}
	
	$scope.cerrarNotaPedido = function(){
		$scope.muestraNotaPedido = false;
	}
	
	$scope.pnlVisible=true;
	
	$scope.togglePanel=function(status){
		
		if(status==undefined){
			$scope.pnlVisible=!$scope.pnlVisible;
			return;
		}else{
			$scope.pnlVisible = status;					
		}
		
	}
	
	$scope.traerVendedorPorId = function(item){
		consultaFactory.vendedorPorId(item).then(function(r){
			$scope.vendedor = r;
			$scope.muestraVendedor = true;
		})
	}
	
	$scope.cerrarVendedor = function(){
		$scope.muestraVendedor = false;
	}
	
	$scope.traerRenovadorNoTabla = function(item){
		consultaFactory.renovadorPorId(item).then(function(r){
			$scope.renovador = r;
		})		
	}
	
	$scope.traerRenovadorPorId = function(item){
		consultaFactory.renovadorPorId(item).then(function(r){
			$scope.renovador = r;
			$scope.muestraRenovador = true;
		})
	}
	
	$scope.cerrarRenovador = function(){
		$scope.muestraRenovador = false;
	}
	
	$scope.mostrarVendedor = function(item){
		if($scope.suscripcionElegida.tipoOperacionP.id == 1){
			$scope.traerVendedorPorId(item);
			$scope.renovador = {};
		}else{
			$scope.traerRenovadorPorId(item);
			$scope.vendedor = {};
		}
		
	}
	
	$scope.traerSucursalPorId = function(item){
		consultaFactory.sucursalPorId(item).then(function(r){
			$scope.sucursal = r;
			$scope.muestraSucursal = true;
		})
	}
	
	$scope.traerProductoPorId = function(item){
		consultaFactory.productoPorId(item).then(function(r){
			$scope.producto = r;
			$scope.muestraProducto = true;
		})
	}
	
	$scope.traerContactoPorId = function(item){
		consultaFactory.contactoPorId(item).then(function(r){
			$scope.contacto = r;
			$scope.muestraContacto = true;
			
		})		
	}
	
	$scope.mostrarContacto = function(item){
		$scope.traerContactoPorId(item);
	}
	
	$scope.mostrarProducto = function(item){
		$scope.traerProductoPorId(item);
	}
	
	$scope.cerrarProducto = function(){
		$scope.muestraProducto = false;
	}
	
	$scope.cerrarSucursal = function(){
		$scope.muestraSucursal = false;
	}
	
	$scope.cerrarContacto = function(){
		$scope.muestraContacto = false;
	}
	
	
	$scope.mostrarSucursal = function(item){
		$scope.traerSucursalPorId(item);
	}
	
	$scope.decimales = function(numero){
		return parseFloat(Math.round(numero * 100) / 100).toFixed(2);
	}
	
	
	
	$scope.toDate = function(fecha){
		if(!(fecha==null)){
			return new Date(fecha).toLocaleDateString();
		}
	};
	
	$scope.toDate2 = function(fecha){
		var today = new Date(fecha);
		var dateString = today.format("dd-m-yy");
		return dateString;
	};
	
	
	
}])


app.controller('ModalInstanceCtrlAddSeguimiento', ['$scope', '$modalInstance','$timeout','toaster','modalService','suscripcionesFactory','tarea',  function($scope, $modalInstance,$timeout,toaster,modalService,suscripcionesFactory,tarea) {
	$scope.tarea=tarea;
	$scope.nuevo = false;
	$scope.item={tareaDetalleNotaPedido:null,observacion:null,cantidad:null};
	
	$scope.init=function(){
	
		
		$scope.listar();
		
	};
	
	$scope.modalOptions = {
			headerText : 'Servicio',
			bodyText : '',
			type:'danger'
	};
		
	$scope.listar=function(){

		suscripcionesFactory.listSeguimientos($scope.tarea.id).then(function(r) {				
			$scope.seguimientos= r;
			
		});
	};

	
	$scope.guardarItem=function(){
		
		$scope.item.tareaDetalleNotaPedido=$scope.tarea;
		
	
		
		suscripcionesFactory.guardarSeguimiento($scope.item).then(function(r) {
				
					$scope.status = !r.estado;
	
					if (r.estado == true) {
						
						$scope.edicion = false;
						$scope.nuevo = false;
	
						$scope.listar();
						
						toaster
						.pop(
								"success",
								"Seguimiento",
								"Registro guardado satisfactoriamente");
					} else {
						
								toaster.pop("error",
										"Seguimiento",
										r.mensaje);
					}	
			});
	};

	
	$scope.nuevoItem = function(){
		$scope.item={tareaDetalleNotaPedido:null,observacion:null,cantidad:null};
		
		$scope.nuevo = true;
		
	};
	
	$scope.cancelarItem=function(){
		$scope.nuevo = false;
	};
	
	
    $scope.ok = function (item) {
    	//$scope.cliente=item;
    	
      $modalInstance.close("cliente");
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
    
    
  }]); 




   