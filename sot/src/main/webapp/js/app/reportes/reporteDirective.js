(function(){
	
	var module;
	
	module = angular.module('reporteador', []);
	
	module.directive('reporteador', function(){
		
		return {
			
			restrict: 'E',
			template: '<div>Hola mundo</div>',
			replace: true,
			scope:{
				ngModel: '='
			},
			
			link:function(scope, element, attrs){
				scope.$watch('ngModel', function(newValue){
					
					reload();
					
				});
				//scope.$watch();
				
				var argumentos = {
					width:attrs.args.width==undefined?'100%':attrs.args.width,
					height:attrs.args.height==undefined?'700px':attrs.args.height,
					style:attrs.args.style==undefined?'border:1px solid #000':attrs.args.style
				};

				function reload(){
					element.html("<iframe height='"+ argumentos.height +"' width='"+ argumentos.width +"'  style='"+ argumentos.style +"'  src='"+ attrs.url +"'></iframe>");
				};
				
			}
		
		}
		
		
	});
	
	
	
	
}).call(this);