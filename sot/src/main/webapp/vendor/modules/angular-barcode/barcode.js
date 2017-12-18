(function() {

var module;

module = angular.module('barcode', []);
  
module.directive('barcode', function () {
	  return {
		    restrict: 'AE',
		    replace: true,
		    scope:{
		    	ngModel:"="
		    },
		    link: function (scope, ele, attrs) {

		    	var cnt;
		    	
		    	scope.$watch("ngModel", function(nV, oV){
		    		render(nV);		    		
		    	});
		    	
		    	function render(value){
		    	
		    		ele.JsBarcode(value, {width:1,height:25,displayValue:true});
		    		
		    	}
		    			    		    		   
		    }
		  };
		});

}).call(this);