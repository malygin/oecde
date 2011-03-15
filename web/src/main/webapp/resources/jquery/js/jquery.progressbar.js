(function($) {	
	$.fn.reportprogress = function(val,maxVal) {			
		var max=100;
		if(maxVal)
			max=maxVal;
		return this.each(
			function(){		
				var div=$(this);
				var innerdiv=div.find(".progress");
				var width=Math.round(val/max*100);
				innerdiv.css("width",width);	
				//div.find(".text").html(width);
			}
		);
	};
})(jQuery);