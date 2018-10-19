;
(function($, window) {

	$.fn.extend({
		ajax : function(url) {
			$this = $(this);
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

