;
(function($, window, layer) {

	if (!$) {
		throw 'Cann\'t get jQuery support, please make the jQuery available!';
	}

	window.alert2 = function(content, callback) {
		layer.alert(content, {
			icon : 3,
			yes : function(index) {
				if (callback && typeof callback === "function") {
					callback();
				}
				layer.close(index);
			}
		});
	};
	
	window.confirm2 = function(content, callback) {
		layer.confirm(content, callback);
	};

	window.Post = function(service) {

		var _this = this;

		this.service = service;

		this.param = {};

		this.setParame = function(name, value) {
			_this.param[name] = value;
		}

		this.setRangeParam = function(element) {
			$(element).find(":input").each(function(index, item) {
				var name = $(item).attr("name");
				if (name) {
					_this.param[name] = $(item).val().trim();
				} else {
					var id = $(item).attr("id");
					if (id) {
						_this.param[id] = $(item).val().trim();
					}
				}
			});

			// 单选，多选已添加到参数列表标识
			var names = {};

			$(element).find(":radio").each(function(index, item) {
				var name = $(item).attr("name");
				if (name) {
					if (!names[name]) {
						if ($(":radio[name=" + name + "]:checked").length == 1) {
							_this.param[name] = $(":radio[name=" + name + "]:checked").val().trim();
						}
						names[name] = name;
					}
				}
			});

			$(element).find(":checkbox").each(function(index, item) {
				var name = $(item).attr("name");
				if (name) {
					if (!names[name]) {
						if ($(":checkbox[name=" + name + "]:checked").length == 1) {
							_this.param[name] = $(":checkbox[name=" + name + "]:checked").val().trim();
						}
						names[name] = name;
					}
				}
			});
		}

		this.doPost = function() {
			$.ajax({
				url : service,
				dataType : 'json',
				data : _this.param,
				async : false,
				type:'post',
				success : function(response) {
					console.dir(response)
					_this.result = response;
				},
				error : function(err) {
					_this.error = err;
				}
			});
		};

		this.getResult = function() {
			return _this.result;
		};

		this.getCode = function() {
			return _this.result.ack_code;
		};
		
		this.showErr = function(){
			alert2(_this.message);
		};
	}

})($, window, layer);