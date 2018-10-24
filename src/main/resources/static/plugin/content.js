;
(function($, window) {

	$.fn.extend({
		ajax : function(url) {
			$this = $(this);
			var time = new Date();
			var m = time.getMonth() + 1;   
			var t = time.getFullYear() + "" + m     
			+ time.getDate() + "" + time.getHours()    
			+ time.getMinutes() + time.getSeconds() + time.getMilliseconds(); 
			if(url.indexOf("?") > 0){
				url = url + "&timestamp_="+t;
			}else{
				url = url + "?timestamp_="+t;
			}
			$.ajax({
				url: url,
				type: "get",
				dataType: "html",
				success: function(data){
					$this.html(data);
				}
			});
			return $this;
		}
	});
	
})(jQuery, window);

