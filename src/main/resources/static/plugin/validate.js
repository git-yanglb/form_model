;
(function($, window) {

	var encoding = "zh_CN";
	
	$.fn.validate = function() {
		
		var result = true;
		
		/**
		 * input, textarea, select
		 */
		$(this).find(":input").each(function(index, item) {
			for ( var type in validateType) {
				if ($(item).attr(type)) {
					if(!validateType[type](item)){
						result = false;
						return false;
					}
				}
			}
		});

		if(!result){
			return result;
		}
		
		/**
		 * radio
		 */
		$(this).find(":radio").each(function(index, item) {
			for ( var type in validateType) {
				if ($(item).attr(type)) {
					if(!validateType[type](item)){
						result = false;
						return false;
					}
				}
			}
		});

		if(!result){
			return result;
		}
		
		/**
		 * checkbox
		 */
		$(this).find(":checkbox").each(function(index, item) {
			for ( var type in validateType) {
				if ($(item).attr(type)) {
					if(!validateType[type](item)){
						result = false;
						return false;
					}
				}
			}
		});

		return result;
	}

	var validateType = {
		/**
		 * 非空验证
		 */
		notnull : function(element) {
			var notnull = $(element).attr("notnull") == "true";
			if (notnull) {
				if ($(element).is(":radio")) {
					var name = $(element).attr("name");
					var desc = $(element).attr("desc");
					if ($(":radio[name=" + name + "]:checked").length <= 0) {
						layer.tips(desc + validateMsg["notnull"][encoding], $(":radio[name=" + name + "]:last"));
						return false;
					}
				}else if ($(element).is(":checkbox")) {
					var name = $(element).attr("name");
					var desc = $(element).attr("desc");
					if ($(":checkbox[name=" + name + "]:checked").length <= 0) {
						layer.tips(desc + validateMsg["notnull"][encoding], $(":checkbox[name=" + name + "]:last"));
						return false;
					}
				}else{
					var value = $(element).val().trim();
					var desc = $(element).attr("desc");
					if(!value){
						layer.tips(desc + validateMsg["notnull"][encoding], $(element)[0]);
						return false;
					}
				}
				return true;
			}
		}
	};
	
	var validateMsg = {
		notnull:{
			zh_CN: '不能为空！',
			en_US: ' cann\'t null!'
		}
	};
	

})($, window);