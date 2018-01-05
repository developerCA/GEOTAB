'use strict';

/**
 * Config for the router
 */
angular
		.module('app')
		.run(
				[ '$rootScope', '$state', '$stateParams',
						function($rootScope, $state, $stateParams) {
							$rootScope.$state = $state;
							$rootScope.$stateParams = $stateParams;
						} ])
		.config(
				[
						'$stateProvider',
						'$urlRouterProvider',
						function($stateProvider, $urlRouterProvider) {

							$urlRouterProvider.otherwise('/app/dashboard-v1');
							$stateProvider
									.state('app', {
										abstract : true,
										url : '/app',
										templateUrl : 'tpl/app.html'
									})
									.state(
											'app.dashboard-v1',
											{
												url : '/dashboard-v1',
												templateUrl : 'tpl/app_dashboard_v1.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'flotchart',
																				'js/controllers/chart.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/user/userController.js',
																				'toaster' ]);
															} ]
												}
											})
									.state(
											'app.usuarios',
											{
												url : '/usuarios',
												templateUrl : 'tpl/app/administracion/usuarios/usuarios.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad

																		.load(
																				[
																						'toaster',
																						'ui.select',
																						'js/app/administracion/notaPedido/notaPedidoFactory.js',
																						'js/app/user/userController.js',
																						'js/app/administracion/usuarios/usuariosController.js',
																						'js/app/administracion/usuarios/usuariosFactory.js',
																						'js/app/administracion/perfiles/perfilesFactory.js',
																						'js/app/administracion/cooperativas/cooperativaFactory.js',
																						'js/app/administracion/regiones/regionesFactory.js',
																						'js/app/administracion/clientes/clienteFactory.js',
																						'js/app/administracion/sucursales/sucursalesFactory.js',
																						'js/app/administracion/permisos/asignacionPermisoFactory.js',
																						'js/app/administracion/rutas/rutasFactory.js',
																						

																		
																			])
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load([ 'js/controllers/toaster.js' ]);
																				});
															} ]
												}
											})
									.state(
										'app.rutas', {
											url : '/rutas',
											templateUrl : 'tpl/app/administracion/rutas/rutas.html',
											resolve : {
												deps : [
													'$ocLazyLoad',
														function(
															$ocLazyLoad
														) {
															return $ocLazyLoad
																.load([
																	'toaster',
																	'js/app/administracion/rutas/rutasController.js',
																	'js/app/administracion/rutas/rutasFactory.js'
																])
																.then(
																	function() {
																		return $ocLazyLoad
																			.load([ 'js/controllers/toaster.js' ]);
																	});
															}
													]
											}
										})
									.state(
											'app.roles',
											{
												url : '/roles',
												templateUrl : 'tpl/app/administracion/roles/roles.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				[
																						'toaster',
																						'js/app/administracion/notaPedido/notaPedidoFactory.js',
																						'js/app/administracion/usuarios/usuariosFactory.js',
																						'js/app/user/userController.js',
																						'js/app/administracion/roles/rolesController.js',
																						'js/app/administracion/roles/rolesFactory.js' ])
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load([ 'js/controllers/toaster.js' ]);
																				});
															} ]
												}
											})
									.state(
											'app.perfiles',
											{
												url : '/perfiles',
												templateUrl : 'tpl/app/administracion/perfiles/perfiles.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				[
																						'toaster',
																						'js/app/administracion/notaPedido/notaPedidoFactory.js',
																						'js/app/administracion/usuarios/usuariosFactory.js',
																						'js/app/user/userController.js',
																						'js/app/administracion/perfiles/perfilesController.js',
																						'js/app/administracion/perfiles/perfilesFactory.js' ])
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/toaster.js');
																				});

															} ]
												}
											})
									.state(
											'app.cooperativas',
											{
												url : '/cooperativas',
												templateUrl : 'tpl/app/administracion/cooperativas/cooperativas.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				[
																						'toaster',
																						'js/app/administracion/notaPedido/notaPedidoFactory.js',
																						'js/app/administracion/usuarios/usuariosFactory.js',
																						'js/app/user/userController.js',
																						'js/app/administracion/cooperativas/cooperativaController.js',
																						'js/app/administracion/cooperativas/cooperativaFactory.js',
																						'js/app/administracion/permisos/asignacionPermisoFactory.js',
																						'js/app/administracion/perfiles/perfilesFactory.js',
																						'angularBootstrapNavTree'

																				])
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/toaster.js');
																				});

															} ]
												}
											})
									.state(
											'app.asignacion',
											{
												url : '/asignacion',
												templateUrl : 'tpl/app/administracion/permisos/asignacion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				[
																						'toaster',
																						'js/app/administracion/notaPedido/notaPedidoFactory.js',
																						'js/app/administracion/usuarios/usuariosFactory.js',
																						'js/app/user/userController.js',
																						'js/app/administracion/permisos/asignacionPermisoController.js',
																						'js/app/administracion/permisos/asignacionPermisoFactory.js',
																						'angularBootstrapNavTree' ])
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/toaster.js');
																				});

															} ]
												}
											})
									.state(
											'app.permisos',
											{
												url : '/permisos',
												templateUrl : 'tpl/app/administracion/permisos/permisos.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				[
																						'toaster',
																						'js/app/administracion/notaPedido/notaPedidoFactory.js',
																						'js/app/administracion/usuarios/usuariosFactory.js',
																						'js/app/user/userController.js',
																						'js/app/administracion/permisos/permisosController.js',
																						'js/app/administracion/permisos/permisosFactory.js',
																						'js/app/administracion/cooperativas/cooperativaFactory.js',
																						'angularBootstrapNavTree' ])
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/toaster.js');
																				});

															} ]
												}
											})
									.state(
											'app.region',
											{
												url : '/region',
												templateUrl : 'tpl/app/administracion/regiones/regiones.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/regiones/regionesController.js',
																				'js/app/user/userController.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/administracion/regiones/regionesFactory.js' ]);
															} ]
												}
											})

									.state(
											'app.categoria',
											{
												url : '/categoria',
												templateUrl : 'tpl/app/administracion/categorias/categorias.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/categorias/categoriasController.js',
																				'js/app/user/userController.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/administracion/categorias/categoriasFactory.js' ]);
															} ]
												}
											})

									.state(
											'app.costos',
											{
												url : '/costos',
												templateUrl : 'tpl/app/administracion/categorias/costos.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/categorias/categoriasController.js',
																				'js/app/user/userController.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/administracion/categorias/categoriasFactory.js' ]);
															} ]
												}
											})
									.state(
											'app.vendedores',
											{
												url : '/vendedores',
												templateUrl : 'tpl/app/administracion/vendedores/vendedores.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/vendedores/vendedoresController.js',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js' ]);
															} ]
												}
											})

									.state(
											'app.migracion',
											{
												url : '/migracion',
												templateUrl : 'tpl/app/administracion/migracion/migracion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/migracion/migracionController.js',
																				'js/app/administracion/migracion/migracionFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})

									.state(
											'app.profesion',
											{
												url : '/profesiones',
												templateUrl : 'tpl/app/administracion/profesiones/profesiones.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				[
																						'toaster',
																						'ui.select',
																						'js/app/administracion/profesiones/profesionesController.js',
																						'js/app/administracion/profesiones/profesionesFactory.js',
																						'js/app/user/userController.js' ])
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load([ 'js/controllers/toaster.js' ]);
																				});
																;
															} ]
												}
											})
									.state(
											'app.sucursales',
											{
												url : '/sucursales',
												templateUrl : 'tpl/app/administracion/sucursales/sucursales.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/regiones/regionesFactory.js',
																				'js/app/administracion/sucursales/sucursalesController.js',
																				'js/app/administracion/sucursales/sucursalesFactory.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.calibracion',
											{
												url : '/calibracion',
												templateUrl : 'tpl/app/administracion/calibracion/calibracion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/sincronizar/sincronizarFactory.js',
																				'js/app/administracion/calibracion/calibracionController.js',
																				'js/app/administracion/calibracion/calibracionFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.consultac',
											{
												url : '/concalibracion',
												templateUrl : 'tpl/app/administracion/calibracion/consulta.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'ui.select',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'angularFileUpload',
																				'js/app/administracion/calibracion/calibracionController.js',
																				'js/app/administracion/calibracion/calibracionFactory.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})

									.state(
											'app.consultacg',
											{
												url : '/congencalibracion',
												templateUrl : 'tpl/app/administracion/calibracion/consultaGeneral.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'ui.select',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'angularFileUpload',
																				'js/app/administracion/calibracion/calibracionController.js',
																				'js/app/administracion/calibracion/calibracionFactory.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
	                              .state(
											'app.informeOT',
											{
												url : '/suscripciones/informeOT',
												templateUrl : 'tpl/app/administracion/informes/informeOT.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'ui.select',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/app/administracion/consultas/consultaController.js',
																				'js/app/administracion/consultas/consultaFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/app/administracion/suscripciones/suscripcionesFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/administracion/genericos/generico.js',
																				'js/app/user/userController.js', ]);

															} ]
												}
											})
									 .state(
											'app.resultados',
											{
												url : '/informeTotales',
												templateUrl : 'tpl/app/administracion/informes/informeTotales.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'ui.select',
																				'js/app/administracion/consultas/consultaController.js',
																				'js/app/administracion/consultas/consultaFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/app/administracion/suscripciones/suscripcionesFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/administracion/genericos/generico.js',
																				'js/app/user/userController.js', ]);

															} ]
												}
											})		

									.state(
											'app.reset',
											{
												url : '/reset',
												templateUrl : 'tpl/app/administracion/usuarios/reset.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})

									.state(
										'app.sincronizar', {
											url : '/sincronizar',
											templateUrl : 'tpl/app/administracion/sincronizar/sincronizar.html',
											resolve : {
												deps : [
													'$ocLazyLoad',
														function(
															$ocLazyLoad
														) {
															return $ocLazyLoad
																.load([
																	'toaster',
																	'js/app/administracion/sincronizar/sincronizarController.js',
																	'js/app/administracion/sincronizar/sincronizarFactory.js'
																])
																.then(
																	function() {
																		return $ocLazyLoad
																			.load([ 'js/controllers/toaster.js' ]);
																	});
															}
													]
											}
										})
									.state(
											'app.dispositivos',
											{
												url : '/dispositivos',
												templateUrl : 'tpl/app/administracion/dispositivos/dispositivos.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/dispositivos/dispositivosController.js',
																				'js/app/administracion/dispositivos/dispositivosFactory.js'
																		]);
															} ]
												}
											})
									.state(
											'app.tablero',
											{
												url : '/tablero',
												templateUrl : 'tpl/app/administracion/tablero/tablero.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/tablero/tableroController.js',
																				'js/app/administracion/tablero/tableroFactory.js'
																		]);
															} ]
												}
											})
									.state(
											'app.notaPedido',
											{
												url : '/ot',
												templateUrl : 'tpl/app/administracion/notasPedido/notasPedido.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'ui.select',
																				'toaster',
																				'angularFileUpload',
																				'js/app/administracion/modales/buscarClienteController.js',
																				'js/app/administracion/notaPedido/notaPedidoController.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/app/administracion/productos/productosFactory.js',
																				'js/app/administracion/categorias/categoriasFactory.js',
																				'js/app/administracion/productos/productosController.js',
																				'js/app/administracion/detalleNotaPedido/detalleNotaPedidoFactory.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/clientes/clienteController.js',
																				'js/app/administracion/paises/paisesFactory.js',
																				'js/app/administracion/provincias/provinciasFactory.js',
																				'js/app/administracion/ciudades/ciudadFactory.js',
																				'js/app/administracion/profesiones/profesionesFactory.js',
																				'js/app/administracion/tipoClientes/tipoClienteFactory.js',
																				'js/app/administracion/regiones/regionesFactory.js',
																				'js/app/administracion/sucursales/sucursalesFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js' ]);

															} ]
												}
											})

									.state(
											'app.seguimiento',
											{
												url : '/seguimiento',
												templateUrl : 'tpl/app/administracion/seguimiento/seguimiento.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'ui.select',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'angularFileUpload',
																				'js/app/administracion/consultas/consultaController.js',
																				'js/app/administracion/consultas/consultaFactory.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/app/administracion/generasuscripcion/suscripcionFactory.js',
																				'js/app/administracion/suscripciones/suscripcionesFactory.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})

									.state(
											'app.servicios',
											{
												url : '/service',
												templateUrl : 'tpl/app/administracion/productos/servicios.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/productos/productosFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/productos/servicioController.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/clientes/clienteController.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js' ]);

															} ]
												}
											})

									.state(
											'app.vendedor',
											{
												url : '/vendedor',
												templateUrl : 'tpl/app/administracion/vendedores/vendedores.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/vendedores/vendedoresController.js',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js' ]);

															} ]
												}
											})

									.state(
											'app.generar',
											{
												url : '/suscripcion',
												templateUrl : 'tpl/app/administracion/generasuscripcion/generarSuscripcion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
                                                                                'ui.select',
																				'textAngular',
																				'toaster',
																				'angularFileUpload',
																				'js/app/administracion/generasuscripcion/suscripcionController.js',
																				'js/app/administracion/generasuscripcion/suscripcionFactory.js',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/app/administracion/profesiones/profesionesFactory.js',
																				'js/app/administracion/paises/paisesFactory.js',
																				'js/app/administracion/provincias/provinciasFactory.js',
																				'js/app/administracion/ciudades/ciudadFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js',
																				'js/app/administracion/usuarios/usuariosFactory.js', ]);

															} ]
												}
											})
									.state(
											'app.suscripciones',
											{
												url : '/suscripciones',
												templateUrl : 'tpl/app/administracion/suscripciones/suscripciones.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'textAngular',
																				'toaster',
																				'js/app/administracion/suscripciones/suscripcionesController.js',
																				'js/app/administracion/suscripciones/suscripcionesFactory.js',
																				'js/app/administracion/generasuscripcion/suscripcionFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/app/administracion/profesiones/profesionesFactory.js',
																				'js/app/administracion/paises/paisesFactory.js',
																				'js/app/administracion/provincias/provinciasFactory.js',
																				'js/app/administracion/ciudades/ciudadFactory.js',
																				'js/app/administracion/renovacionesPendientes/renovacionesPendientesController.js',
																				'js/app/administracion/renovacionesPendientes/renovacionesPendientesFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js' ]);

															} ]
												}
											})

									.state(
											'app.suscripciones.aprobar',
											{
												url : '/suscripciones/aprobar',
												templateUrl : 'tpl/app/administracion/suscripciones/aprobacion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([

																		]);

															} ]
												}
											})

									.state(
											'app.suscripciones.accesos',
											{
												url : '/suscripciones/accesos',
												templateUrl : 'tpl/app/administracion/suscripciones/accesos.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([

																		]);

															} ]
												}
											})
									.state(
											'app.suscripciones.consultas',
											{
												url : '/suscripciones/consultas',
												templateUrl : 'tpl/app/administracion/suscripciones/consultas.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/consultas/consultaController.js',
																				'js/app/administracion/consultas/consultaFactory.js',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/suscripciones/suscripcionesFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js', ]);

															} ]
												}
											})
									.state(
											'app.suscripciones.renovaciones',
											{
												url : '/suscripciones/renovaciones',
												templateUrl : 'tpl/app/administracion/suscripciones/renovaciones.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js' ]);

															} ]
												}
											})
									.state(
											'app.consultas',
											{
												url : '/suscripciones/consultasGeneral',
												templateUrl : 'tpl/app/administracion/suscripciones/consultasGeneral.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'ui.select',
																				'js/app/administracion/consultas/consultaController.js',
																				'js/app/administracion/consultas/consultaFactory.js',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/app/administracion/suscripciones/suscripcionesFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js', ]);

															} ]
												}
											})
									.state(
											'app.consultaequipos',
											{
												url : '/suscripciones/consultasServicios',
												templateUrl : 'tpl/app/administracion/suscripciones/busquedaServicios.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'ui.select',
																				'js/app/administracion/consultas/consultaController.js',
																				'js/app/administracion/consultas/consultaFactory.js',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/app/administracion/suscripciones/suscripcionesFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js', ]);

															} ]
												}
											})	
									.state(
											'app.consultatecnicos',
											{
												url : '/suscripciones/consultasTecnicos',
												templateUrl : 'tpl/app/administracion/suscripciones/busquedaExperiencia.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'ui.select',
																				'js/app/administracion/consultas/consultaController.js',
																				'js/app/administracion/vendedores/vendedoresFactory.js',
																				'js/app/administracion/consultas/consultaFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/app/administracion/suscripciones/suscripcionesFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js', ]);

															} ]
												}
											})	
									.state(
											'app.clientes',
											{
												url : '/cliente',
												templateUrl : 'tpl/app/administracion/clientes/clientes.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'js/app/administracion/clientes/clienteController.js',
																				'js/app/administracion/clientes/clienteFactory.js',
																				'js/app/administracion/paises/paisesFactory.js',
																				'js/app/administracion/provincias/provinciasFactory.js',
																				'js/app/administracion/ciudades/ciudadFactory.js',
																				'js/app/administracion/profesiones/profesionesFactory.js',
																				'js/app/administracion/tipoClientes/tipoClienteFactory.js',
																				'js/app/administracion/regiones/regionesFactory.js',
																				'js/app/administracion/sucursales/sucursalesFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.etiquetado',
											{
												url : '/etiquetado',
												templateUrl : 'tpl/app/administracion/etiquetado/etiquetado.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/etiquetado/etiquetadoController.js',
																				'js/app/administracion/etiquetado/etiquetadoFactory.js',
																				'js/app/administracion/fechassuscripcion/fechasRenovacionFactory.js',
																				'js/controllers/toaster.js',
																				'reporteador',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.fechas',
											{
												url : '/fechas',
												templateUrl : 'tpl/app/administracion/fechassuscripcion/fechassuscripcion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/fechassuscripcion/fechasRenovacionController.js',
																				'js/app/administracion/fechassuscripcion/fechasRenovacionFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.avisos',
											{
												url : '/avisos',
												templateUrl : 'tpl/app/administracion/avisosrenovacion/avisosrenovacion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/avisosrenovacion/avisosrenovacionController.js',
																				'js/app/administracion/avisosrenovacion/avisosrenovacionFactory.js',
																				'js/app/administracion/sucursales/sucursalesFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.distribucion',
											{
												url : '/distribucion',
												templateUrl : 'tpl/app/administracion/distribucion/distribucion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/distribucion/distribucionController.js',
																				'js/app/administracion/distribucion/distribucionFactory.js',
																				'js/app/administracion/subcategoria/subCategoriaFactory.js',
																				'js/app/administracion/fechassuscripcion/fechasRenovacionFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.recibidos',
											{
												url : '/recibidos',
												templateUrl : 'tpl/app/administracion/avisosrecibidos/avisosrecibidos.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'textAngular',
																				'toaster',
																				'js/app/administracion/generasuscripcion/suscripcionFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/avisosrenovacion/avisosrenovacionFactory.js',
																				'js/app/administracion/avisosrecibidos/avisosrecibidosController.js',
																				'js/app/administracion/avisosrecibidos/avisosrecibidosFactory.js',
																				'js/directives/utils.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.gestion',
											{
												url : '/gestion',
												templateUrl : 'tpl/app/administracion/gestioncomercial/gestioncomercial.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'ui.select',
																				'xeditable',
																				'toaster',
																				'js/app/administracion/gestioncomercial/gestioncomercialController.js',
																				'js/app/administracion/gestioncomercial/gestioncomercialFactory.js',
																				'js/app/administracion/productos/productosFactory.js',
																				'js/app/administracion/fechassuscripcion/fechasRenovacionFactory.js',
																				'js/app/administracion/notaPedido/notaPedidoFactory.js',
																				'js/app/administracion/productos/productosFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/administracion/usuarios/usuariosFactory.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.prefactura',
											{
												url : '/prefactura',
												templateUrl : 'tpl/app/administracion/prefacturas/prefacturar.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/prefacturas/prefacturarController.js',
																				'js/app/administracion/prefacturas/prefacturarFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.bodega',
											{
												url : '/bodega',
												templateUrl : 'tpl/app/administracion/bodegas/bodegas.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																				'js/app/administracion/bodega/bodegaController.js',
																				'js/app/administracion/bodega/bodegaFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.activacion',
											{
												url : '/activaciones',
												templateUrl : 'tpl/app/administracion/activacion/activaciones.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'ui.select',
																				'toaster',
																				'js/app/administracion/activaciones/activacionesController.js',
																				'js/app/administracion/activaciones/activacionesFactory.js',
																				'js/app/administracion/suscripciones/suscripcionesFactory.js',
																				'js/controllers/toaster.js',
																				'js/app/user/userController.js' ]);
															} ]
												}
											})
									.state(
											'app.actualizacion',
											{
												abstract : true,
												url : '/actualizacion',
												templateUrl : 'tpl/app/administracion/productos/actualizacion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'toaster',
																		// 'intro'
																		])
															} ]
												}
											})
									.state(
											'app.actualizacion.productos',
											{
												url : '/productos',
												templateUrl : 'tpl/app/administracion/productos/actualizacionVersion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'ui.select',
																				'js/app/administracion/productos/actualizacionVersionFactory.js',
																				'js/app/administracion/productos/actualizacionVersionController.js',
																				'js/app/user/userController.js' ])
															} ]
												}
											})
									.state(
											'app.actualizacion.gestion',
											{
												url : '/gestion',
												templateUrl : 'tpl/app/administracion/productos/gestionActualizacion.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'reporteador',
																				'ui.select',
																				'barcode',
																				'js/app/administracion/productos/actualizacionVersionFactory.js?r='
																						+ Math
																								.random(),
																				'js/app/administracion/productos/gestionActualizacionFactory.js?r='
																						+ Math
																								.random(),
																				'js/app/administracion/productos/gestionActualizacionController.js?r='
																						+ Math
																								.random(),
																				'js/app/user/userController.js' ])
															} ]
												}
											})
									.state(
											'app.dashboard-v2',
											{
												url : '/dashboard-v2',
												templateUrl : 'tpl/app_dashboard_v2.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([ 'js/controllers/chart.js' ]);
															} ]
												}
											})
									.state(
											'app.ui',
											{
												url : '/ui',
												template : '<div ui-view class="fade-in-up"></div>'
											})
									.state('app.ui.buttons', {
										url : '/buttons',
										templateUrl : 'tpl/ui_buttons.html'
									})
									.state('app.ui.icons', {
										url : '/icons',
										templateUrl : 'tpl/ui_icons.html'
									})
									.state('app.ui.grid', {
										url : '/grid',
										templateUrl : 'tpl/ui_grid.html'
									})
									.state('app.ui.widgets', {
										url : '/widgets',
										templateUrl : 'tpl/ui_widgets.html'
									})
									.state('app.ui.bootstrap', {
										url : '/bootstrap',
										templateUrl : 'tpl/ui_bootstrap.html'
									})
									.state('app.ui.sortable', {
										url : '/sortable',
										templateUrl : 'tpl/ui_sortable.html'
									})
									.state('app.ui.portlet', {
										url : '/portlet',
										templateUrl : 'tpl/ui_portlet.html'
									})
									.state('app.ui.timeline', {
										url : '/timeline',
										templateUrl : 'tpl/ui_timeline.html'
									})
									.state(
											'app.ui.tree',
											{
												url : '/tree',
												templateUrl : 'tpl/ui_tree.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				'angularBootstrapNavTree')
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/tree.js');
																				});
															} ]
												}
											})

									.state(
											'app.ui.toaster',
											{
												url : '/toaster',
												templateUrl : 'tpl/ui_toaster.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				'toaster')
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/toaster.js');
																				});
															} ]
												}
											})
									.state(
											'app.ui.jvectormap',
											{
												url : '/jvectormap',
												templateUrl : 'tpl/ui_jvectormap.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load('js/controllers/vectormap.js');
															} ]
												}
											})
									.state(
											'app.ui.googlemap',
											{
												url : '/googlemap',
												templateUrl : 'tpl/ui_googlemap.html',
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load(
																				[
																						'js/app/map/load-google-maps.js',
																						'js/app/map/ui-map.js',
																						'js/app/map/map.js' ])
																		.then(
																				function() {
																					return loadGoogleMaps();
																				});
															} ]
												}
											})
									.state(
											'app.chart',
											{
												url : '/chart',
												templateUrl : 'tpl/ui_chart.html',
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load('js/controllers/chart.js');
															} ]
												}
											})
									// table
									.state('app.table', {
										url : '/table',
										template : '<div ui-view></div>'
									})
									.state('app.table.static', {
										url : '/static',
										templateUrl : 'tpl/table_static.html'
									})
									.state(
											'app.table.datatable',
											{
												url : '/datatable',
												templateUrl : 'tpl/table_datatable.html'
											})
									.state('app.table.footable', {
										url : '/footable',
										templateUrl : 'tpl/table_footable.html'
									})
									.state(
											'app.table.grid',
											{
												url : '/grid',
												templateUrl : 'tpl/table_grid.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				'ngGrid')
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/grid.js');
																				});
															} ]
												}
											})
									// form
									.state(
											'app.form',
											{
												url : '/form',
												template : '<div ui-view class="fade-in"></div>',
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load('js/controllers/form.js');
															} ]
												}
											})
									.state('app.form.elements', {
										url : '/elements',
										templateUrl : 'tpl/form_elements.html'
									})
									.state(
											'app.form.validation',
											{
												url : '/validation',
												templateUrl : 'tpl/form_validation.html'
											})
									.state('app.form.wizard', {
										url : '/wizard',
										templateUrl : 'tpl/form_wizard.html'
									})
									.state(
											'app.form.fileupload',
											{
												url : '/fileupload',
												templateUrl : 'tpl/form_fileupload.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				'angularFileUpload')
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/file-upload.js');
																				});
															} ]
												}
											})
									.state(
											'app.form.imagecrop',
											{
												url : '/imagecrop',
												templateUrl : 'tpl/form_imagecrop.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				'ngImgCrop')
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/imgcrop.js');
																				});
															} ]
												}
											})
									.state(
											'app.form.select',
											{
												url : '/select',
												templateUrl : 'tpl/form_select.html',
												controller : 'SelectCtrl',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				'ui.select')
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/select.js');
																				});
															} ]
												}
											})
									.state(
											'app.form.slider',
											{
												url : '/slider',
												templateUrl : 'tpl/form_slider.html',
												controller : 'SliderCtrl',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				'vr.directives.slider')
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/slider.js');
																				});
															} ]
												}
											})
									.state(
											'app.form.editor',
											{
												url : '/editor',
												templateUrl : 'tpl/form_editor.html',
												controller : 'EditorCtrl',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load(
																				'textAngular')
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('js/controllers/editor.js');
																				});
															} ]
												}
											})
									// pages
									.state(
											'app.paises',
											{
												url : '/paises',
												templateUrl : 'tpl/app/administracion/paises/paises.html'
											})
									.state(
											'app.page',
											{
												url : '/page',
												template : '<div ui-view class="fade-in-down"></div>'
											})
									.state('app.page.profile', {
										url : '/profile',
										templateUrl : 'tpl/page_profile.html'
									})
									.state('app.page.post', {
										url : '/post',
										templateUrl : 'tpl/page_post.html'
									})
									.state('app.page.search', {
										url : '/search',
										templateUrl : 'tpl/page_search.html'
									})
									.state('app.page.invoice', {
										url : '/invoice',
										templateUrl : 'tpl/page_invoice.html'
									})
									.state('app.page.price', {
										url : '/price',
										templateUrl : 'tpl/page_price.html'
									})
									.state('app.docs', {
										url : '/docs',
										templateUrl : 'tpl/docs.html'
									})
									// others
									.state('lockme', {
										url : '/lockme',
										templateUrl : 'tpl/page_lockme.html'
									})
									.state(
											'access',
											{
												url : '/access',
												template : '<div ui-view class="fade-in-right-big smooth"></div>'
											})
									.state(
											'access.signin',
											{
												url : '/signin',
												templateUrl : 'tpl/page_signin.html',
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load([ 'js/controllers/signin.js' ]);
															} ]
												}
											})
									.state(
											'access.signup',
											{
												url : '/signup',
												templateUrl : 'tpl/page_signup.html',
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load([ 'js/controllers/signup.js' ]);
															} ]
												}
											})
									.state('access.forgotpwd', {
										url : '/forgotpwd',
										templateUrl : 'tpl/page_forgotpwd.html'
									})
									.state('access.404', {
										url : '/404',
										templateUrl : 'tpl/page_404.html'
									})

									// fullCalendar
									.state(
											'app.calendar',
											{
												url : '/calendar',
												templateUrl : 'tpl/app_calendar.html',
												// use resolve to load other
												// dependences
												resolve : {
													deps : [
															'$ocLazyLoad',
															'uiLoad',
															function(
																	$ocLazyLoad,
																	uiLoad) {
																return uiLoad
																		.load(
																				[
																						'vendor/jquery/fullcalendar/fullcalendar.css',
																						'vendor/jquery/fullcalendar/theme.css',
																						'vendor/jquery/jquery-ui-1.10.3.custom.min.js',
																						'vendor/libs/moment.min.js',
																						'vendor/jquery/fullcalendar/fullcalendar.min.js',
																						'js/app/calendar/calendar.js' ])
																		.then(
																				function() {
																					return $ocLazyLoad
																							.load('ui.calendar');
																				})
															} ]
												}
											})

									// mail
									.state(
											'app.mail',
											{
												abstract : true,
												url : '/mail',
												templateUrl : 'tpl/mail.html',
												// use resolve to load other
												// dependences
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load([
																				'js/app/mail/mail.js',
																				'js/app/mail/mail-service.js',
																				'vendor/libs/moment.min.js' ]);
															} ]
												}
											})
									.state('app.mail.list', {
										url : '/inbox/{fold}',
										templateUrl : 'tpl/mail.list.html'
									})
									.state('app.mail.detail', {
										url : '/{mailId:[0-9]{1,4}}',
										templateUrl : 'tpl/mail.detail.html'
									})
									.state('app.mail.compose', {
										url : '/compose',
										templateUrl : 'tpl/mail.new.html'
									})

									.state('layout', {
										abstract : true,
										url : '/layout',
										templateUrl : 'tpl/layout.html'
									})
									.state(
											'layout.fullwidth',
											{
												url : '/fullwidth',
												views : {
													'' : {
														templateUrl : 'tpl/layout_fullwidth.html'
													},
													'footer' : {
														templateUrl : 'tpl/layout_footer_fullwidth.html'
													}
												},
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load([ 'js/controllers/vectormap.js' ]);
															} ]
												}
											})
									.state(
											'layout.mobile',
											{
												url : '/mobile',
												views : {
													'' : {
														templateUrl : 'tpl/layout_mobile.html'
													},
													'footer' : {
														templateUrl : 'tpl/layout_footer_mobile.html'
													}
												}
											})
									.state(
											'layout.app',
											{
												url : '/app',
												views : {
													'' : {
														templateUrl : 'tpl/layout_app.html'
													},
													'footer' : {
														templateUrl : 'tpl/layout_footer_fullwidth.html'
													}
												},
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load([ 'js/controllers/tab.js' ]);
															} ]
												}
											})
									.state('apps', {
										abstract : true,
										url : '/apps',
										templateUrl : 'tpl/layout.html'
									})
									.state(
											'apps.note',
											{
												url : '/note',
												templateUrl : 'tpl/apps_note.html',
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load([
																				'js/app/note/note.js',
																				'vendor/libs/moment.min.js' ]);
															} ]
												}
											})
									.state(
											'apps.contact',
											{
												url : '/contact',
												templateUrl : 'tpl/apps_contact.html',
												resolve : {
													deps : [
															'uiLoad',
															function(uiLoad) {
																return uiLoad
																		.load([ 'js/app/contact/contact.js' ]);
															} ]
												}
											})
									.state(
											'app.weather',
											{
												url : '/weather',
												templateUrl : 'tpl/apps_weather.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load({
																			name : 'angular-skycons',
																			files : [
																					'js/app/weather/skycons.js',
																					'vendor/libs/moment.min.js',
																					'js/app/weather/angular-skycons.js',
																					'js/app/weather/ctrl.js' ]
																		});
															} ]
												}
											})
									.state(
											'music',
											{
												url : '/music',
												templateUrl : 'tpl/music.html',
												controller : 'MusicCtrl',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([
																				'com.2fdevs.videogular',
																				'com.2fdevs.videogular.plugins.controls',
																				'com.2fdevs.videogular.plugins.overlayplay',
																				'com.2fdevs.videogular.plugins.poster',
																				'com.2fdevs.videogular.plugins.buffering',
																				'js/app/music/ctrl.js',
																				'js/app/music/theme.css' ]);
															} ]
												}
											})
									.state('music.home', {
										url : '/home',
										templateUrl : 'tpl/music.home.html'
									})
									.state('music.genres', {
										url : '/genres',
										templateUrl : 'tpl/music.genres.html'
									})
									.state('music.detail', {
										url : '/detail',
										templateUrl : 'tpl/music.detail.html'
									})
									.state('music.mtv', {
										url : '/mtv',
										templateUrl : 'tpl/music.mtv.html'
									})
									.state(
											'music.mtvdetail',
											{
												url : '/mtvdetail',
												templateUrl : 'tpl/music.mtv.detail.html'
											})
									/* PMI Apps */
									.state(
											'app.forms2',
											{
												url : '/forms2',
												templateUrl : 'tpl/app_form.html',
												resolve : {
													deps : [
															'$ocLazyLoad',
															function(
																	$ocLazyLoad) {
																return $ocLazyLoad
																		.load([ 'js/app/forms/form.js' ]);
															} ]
												}
											})

						} ]);
