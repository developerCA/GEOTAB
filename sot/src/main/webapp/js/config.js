// config

var app = angular.module('app').config(
		[
				'$controllerProvider',
				'$compileProvider',
				'$filterProvider',
				'$provide',
				function($controllerProvider, $compileProvider,
						$filterProvider, $provide) {

					// lazy controller, directive and service
					app.controller = $controllerProvider.register;
					app.directive = $compileProvider.directive;
					app.filter = $filterProvider.register;
					app.factory = $provide.factory;
					app.service = $provide.service;
					app.constant = $provide.constant;
					app.value = $provide.value;
				} ]).config(
		[ '$translateProvider', function($translateProvider) {
			// Register a loader for the static files
			// So, the module will search missing translation tables under the
			// specified urls.
			// Those urls are [prefix][langKey][suffix].
			$translateProvider.useStaticFilesLoader({
				prefix : 'l10n/',
				suffix : '.js'
			});
			// Tell the module what language to use by default
			$translateProvider.preferredLanguage('es');
			// Tell the module to store the language in the local storage
			$translateProvider.useLocalStorage();
		} ]).config(function(blockUIConfig) {

	blockUIConfig.requestFilter = function(config) {
		console.log(config.url);
		if (config.url == "api/geotab/fechaDispositivos") {
			return false;
		} else if (config.url == "api/geotab/procesarDatos") {
			console.log("1.1");
			return false;
		} else if (config.url == "geotab/api/geotab/procesarDatos") {
			console.log("2.1");
			return false;
		} else if (config.url == "api/geotab/procesarTablero") {
			console.log("2.2");
			return false;
		}else if (config.url == "api/geotab/fechasDispositivos") {
			console.log("2.4");
			return false;
		}
		

	}

})

.config([ "RestangularProvider", function(RestangularProvider) {
	RestangularProvider.setBaseUrl('api');
} ]);
