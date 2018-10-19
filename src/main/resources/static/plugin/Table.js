;
(function(window, $) {
	
	var options = {
		pageId: null,
		service:"",
		condition: [],
		hiddenfield: [],
		async: false,
		onRowClick: null,
		onTableDataBind: null,
		pagable: true,
		currentPage: 1,
		pageSize: 10
	};
	
	var time = new Date();
	var m = time.getMonth() + 1;   
	var t = time.getFullYear() + "" + m     
	+ time.getDate() + "" + time.getHours()    
	+ time.getMinutes() + time.getSeconds() + time.getMilliseconds(); 
	options.id = t;
	
	$.fn.extend({
		table: function(){
			options.pager = this;
			var _this = this;
			// 请求地址
			var service = $(this).attr("service");
			// 查询条件
			var condition = $(this).attr("condition");
			// 要在tr上额外设置的字段
			var hiddenfield = $(this).attr("hiddenfield");
			// 是否异步请求
			var async = $(this).attr("async") == "true";
			// 行点击事件
			var onRowClick = $(this).attr("onRowClick");
			// 表格渲染完成事件
			var onTableDataBind = $(this).attr("onTableDataBind");
			var pagable = $(this).attr("pagable") == "true";
			var pageId = $(this).attr("pageId");
			var currentPage = $(this).attr("currentPage");
			var pageSize = $(this).attr("pageSize");
			
			options.service = service;
			options.condition = condition;
			options.async = async;
			options.onRowClick = onRowClick;
			options.onTableDataBind = onTableDataBind;
			options.pagable = pagable;
			options.pageId = pageId;
			options.currentPage = currentPage;
			options.pageSize = pageSize;
			
			if(!options.currentPage || options.currentPage <= 0){
				options.currentPage = 1;
			}
			
			if(!options.pageSize || options.pageSize <= 0){
				options.currentPage = 10;
			}
			
			if(condition){
				if(condition.indexOf(",")>0){
					options.condition = condition.split(",");
				}else{
					options.condition = [condition];
				}
			}
			
			if(hiddenfield){
				if(hiddenfield.indexOf(",")>0){
					var fields = hiddenfield.split(",");
					options.hiddenfield = fields;
				}else{
					options.hiddenfield = [hiddenfield];
				}
			}

			requestAndRender(_this);
			
			return this;
		}
	});
	
	/**
	 * 请求并渲染列表
	 * 
	 * @returns
	 */
	function requestAndRender(_this){
		/*
		 * 请求参数
		 */
		var param = {};
		if(options.condition && options.condition.length > 0){
			for(var i=0; i<options.condition.length; i++){
				param[options.condition[i]] = $("#" + options.condition[i]).val();
			}
		}
		if(options.pagable){
			param.currentPage = options.currentPage
			param.pageSize = options.pageSize
		}
		
		$.ajax({
			url: options.service,
			dataType: 'json',
			data: param,
			async: options.async,
			type: "post",
			success: function(response){
				$(_this).find("tbody").empty();
				var tdCount =  $(_this).find("thead th").length;
				var ack_code = response.ack_code;
				var msg = response.message;
				if(ack_code <= 0){
					layer.alert(msg,{
						icon: 3
					});
					$(_this).find("tbody").append("<tr><td colspan='"+tdCount+"' align='center'>没有符合条件的数据！</td></tr>");
					return;
				}
				if(!response.data || !response.data.total > 0){
					$(_this).find("tbody").append("<tr><td colspan='"+tdCount+"' align='center'>没有符合条件的数据！</td></tr>");
					return;
				}
				var resultData = response.data;
				var row_count = resultData.total;
				options.row_count = row_count;
				var page_count = resultData.pages;
				options.page_count = page_count;
				options.currentPage = resultData.pageNum;
				
				var row_data = resultData.items;
				if(row_data.length <= 0){
					$(_this).find("tbody").append("<tr><td colspan='"+tdCount+"' align='center'>没有符合条件的数据！</td></tr>");
					return;
				}
				
				var content = "";
				
				// 渲染列表行
				for(var i=0; i<row_data.length; i++){
					var tr = "";
					var td = "";
					var row = row_data[i];
					
					if(options.hiddenfield.length > 0){
						for(var h=0; h<options.hiddenfield.length; h++){
							tr += "<tr "+options.hiddenfield[h]+"='"+row[options.hiddenfield[h]]+"' ";
						}
					}else{
						tr += "<tr ";
					}
					$(_this).find("thead th").each(function(){
						var field = $(this).attr("field");
						if(field){
							tr += field+"='" + row[field] + "' ";
							td += getTdContent(this,row,field);
						}else{
							var checkbox = $(this).find(":checkbox");
							var radio = $(this).find(":radio");
							if(radio && (i==row_data.length-1)){
								radio.parent().hide();
							}
							if(checkbox.length == 1){
								var th = checkbox.parents("th");
								var calss = th.attr("class");
								td += "<td class='"+calss+"'>" + th.html() + "</td>";
							}else if(radio.length == 1){
								var th = radio.parents("th");
								var calss = th.attr("class");
								td += "<td class='"+calss+"'>" + th.html() + "</td>";
							}else if($(this).is("[operation]")){
								var operation = $(this).find("div[operation]");
								var calss = $(this).attr("class");
								if(operation.length == 1){
									td += "<td><div class='"+calss+"'>"+operation.html()+"</div></td>";
								}else{
									td += "<td></td>";
								}
							}else{
								td += "<td></td>";
							}
						}
					});
					tr += ">";
					content += (tr + td + "</tr>");
				}
				
				$(_this).find("tbody").append(content);
				
				if(options.pagable){
					writePage(options.pageId,options.page_count,options.row_count,options.currentPage,options.pageSize);
				}
				
				if(options.onTableDataBind && window.dataBind && typeof (eval(options.onTableDataBind)) === "function"){
					eval(options.onTableDataBind)(_this);
				}
				
			},
			error: function(err){
			}
		});
	}
	
	/**
	 * 分页
	 * 
	 * @param pageId
	 * @param page_count
	 * @param row_count
	 * @param currentPage
	 * @param pageSize
	 * @returns
	 */
	function writePage(pageId,page_count,row_count,currentPage,pageSize){
		var pager = `<div page_id="`+options.id+`" class="ui-jqgrid-pager ui-state-default ui-corner-bottom" style="text-aligin: center;margin-bottom:20px;">
				<div class="ui-pager-control" role="group">
					<table class="ui-pg-table ui-common-table ui-pager-table" style="width: 100%;">
						<tbody>
							<tr>
								<td id="grid-pager_center" align="center" style="width: 335px;">
									<table class="ui-pg-table ui-common-table ui-paging-pager">
										<tbody>
											<tr>
												<td class="ui-pg-button ui-corner-all" title="第一页" style="cursor: default;"><span class="ui-icon ace-icon fa fa-angle-double-left bigger-140" page="1"></span></td>
												<td class="ui-pg-button ui-corner-all" title="上一页" style="cursor: default;"><span class="ui-icon ace-icon fa fa-angle-left bigger-140" page="`+((currentPage-1)<=0?1:(currentPage-1))+`"></span></td>
												<td class="ui-pg-button ui-state-disabled" style="cursor: default;"><span class="ui-separator"></span></td>
												<td>共 `+row_count+` 条，每页 <input type="text" class="pageSize" size="1" value="`+pageSize+`"> 条，当前第 <input type="text" class="currentPage" size="1" value="`+currentPage+`"> 页 <span id="sp_1_grid-pager">，共 `+page_count+` 页 </span></td>
												<td class="ui-pg-button ui-state-disabled" style="cursor: default;"><span class="ui-separator"></span></td>
												<td class="ui-pg-button ui-corner-all" title="下一页" style="cursor: pointer;"><span class="ui-icon ace-icon fa fa-angle-right bigger-140" page="`+((currentPage+1)>page_count?page_count:(currentPage+1))+`"></span></td>
												<td class="ui-pg-button ui-corner-all" title="最后一页" style="cursor: default;"><span class="ui-icon ace-icon fa fa-angle-double-right bigger-140" page="`+page_count+`"></span></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>`;
		$("#" + pageId).html(pager);
		pageEventBind();
	}
	
	function getTdContent(td,row,field){
		var onViewBind = $(td).attr("onViewBind");
		var value;
		if(onViewBind && window.dataBind && typeof (eval(onViewBind)) === "function"){
			value = eval(onViewBind)(row[field]);
			return "<td>"+(value?value:"--")+"</td>";
		}else{
			value = row[field];
			return "<td>"+(value?value:"--")+"</td>";
		}
	}
	
	/**
	 * 分页事件
	 * 
	 * @returns
	 */
	function pageEventBind(){
		var page = $("[page_id="+options.id+"]");
		var pager = options.pager;
				 
		// 第一页
		page.find(".fa-angle-double-left").click(function(){
			options.currentPage = 1;
			requestAndRender(pager);
		});
		// 上一页
		page.find(".fa-angle-left").click(function(){
			var page = parseInt($(this).attr("page"));
			if((page + 1) != options.currentPage){
				requestAndRender(pager);
			}else{
				options.currentPage = page
				requestAndRender(pager);
			}
		});
		// 下一页
		page.find(".fa-angle-right").click(function(){
			var page = parseInt($(this).attr("page"));
			if((page - 1) != options.currentPage){
				requestAndRender(pager);
			}else{
				options.currentPage = page
				requestAndRender(pager);
			}
		});
		// 最后一页
		page.find(".fa-angle-double-right").click(function(){
			options.currentPage = parseInt($(this).attr("page"));
			requestAndRender(pager);
		});
		
		// 只能输入正整数
		$(".currentPage,.pageSize").on("keyup",function(){
			var value = $(this).val();
			if(!/^\d*$/.test(value)){
				$(this).val(value.replace(/\D*$/,""));
			}
			if(!$(this).val().trim() || !/^\d*$/.test(value)){
				$(this).val("1");
			}
			if($(this).is(".pageSize")){
				options.pageSize = parseInt(value);
			}else{
				options.currentPage = parseInt(value);
			}
		});
		$(".currentPage,.pageSize").change(function(){
			var value = $(this).val();
			if(!/^\d*$/.test(value)){
				$(this).val(value.replace(/\D*/g,""));
			}
			if($(this).is(".pageSize")){
				options.pageSize = parseInt(value);
			}else{
				options.currentPage = parseInt(value);
			}
		});
	}

})(window, $);