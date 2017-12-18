'use strict';

angular.module('ui.iframeloader', []).directive('uiIframeloader', function() {

	return {
		restrict : 'AE',
		replace : true,
		scope : {
			ngModel : "="
		},
		link : function(scope, elm, attrs, ctrl) {
			elm.html("<iframe src='" + attrs.ngModel + "' frameborder='0' border='0'  style='border-style:none; width:100%; height:300px'></iframe>");

		}
	}
});
