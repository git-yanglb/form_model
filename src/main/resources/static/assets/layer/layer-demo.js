var active = {
		test1 : function() {
			layer.alert('你好，体验者');
		},
		test2 : function() {
			layer.confirm('您是如何看待前端开发？', {
				btn : [ '重要', '奇葩' ]
			//按钮
			}, function() {
				layer.msg('的确很重要', {
					icon : 1
				});
			}, function() {
				layer.msg('也可以这样', {
					time : 20000, //20s后自动关闭
					btn : [ '明白了', '知道了' ]
				});
			});
		},
		test3 : function() {
			layer.msg('玩命提示中');
		},
		test4 : function() {
			layer.tips('Hi，我是一个提示', this, {
				tips : 1
			});
		},
		test5 : function() {
			layer.open({
				title : '页面层',
				type : 1
				//,skin: 'layui-layer-rim'
				,
				shadeClose : true,
				area : admin.screen() < 2 ? [ '80%', '300px' ] : [ '700px',
						'500px' ],
				content : '<div style="padding: 20px;">放入任意HTML</div>'
			});
		},
		test6 : function() {
			layer.open({
				type : 2,
				content : 'http://fly.layui.com/',
				shadeClose : true,
				area : admin.screen() < 2 ? [ '100%', '80%' ] : [ '375px',
						'500px' ],
				maxmin : true
			});
		},
		test7 : function() {
			layer.prompt({
				title : '输入任何口令，并确认',
				formType : 1
			}, function(pass, index) {
				layer.close(index);
				layer.prompt({
					title : '随便写点啥，并确认',
					formType : 2
				}, function(text, index) {
					layer.close(index);
					layer.msg('演示完毕！您的口令：' + pass + '<br>您最后写下了：' + text);
				});
			});
		},
		test8 : function() {
			layer.tab({
				area : admin.screen() < 2 ? [ '100%', '80%' ] : [ '600px',
						'300px' ],
				tab : [ {
					title : 'TAB1',
					content : '<div style="padding:20px;">内容1</div>'
				}, {
					title : 'TAB2',
					content : '<div style="padding:20px;">内容2</div>'
				} ]
			});
		},
		test9 : function() {
			$.getJSON(layui.setter.base + 'json/layer/photos.js',
					function(json) {
						layer.photos({
							photos : json
						//格式见API文档手册页
						});
					});
		},
		test10 : function() {
			var icon = -1;
			(function changeIcon() {
				var index = layer.alert('Hi，你好！ 点击确认更换图标', {
					icon : icon,
					shadeClose : true,
					title : icon === -1 ? '初体验 - layer ' + layer.v : 'icon：'
							+ icon + ' - layer ' + layer.v
				}, changeIcon);
				if (8 === ++icon)
					layer.close(index);
			}());
		},
		test11 : function() {
			layer.confirm('真的吗？', function(index) {
				layer.msg('哦')
				layer.close(index);
			});
		},
		test12 : function() {
			layer.msg('玩了命提示中');
		},
		test13 : function() {
			//墨绿深蓝风
			layer.alert('墨绿风格，点击确认看深蓝', {
				skin : 'layui-layer-molv',
				closeBtn : 0
			}, function() {
				layer.alert('偶吧深蓝style', {
					skin : 'layui-layer-lan',
					closeBtn : 0,
					anim : 4
				});
			});
		},
		test14 : function() {
			layer
					.open({
						type : 1,
						shade : false,
						title : false,
						content : $('.demo-component-layer'),
						cancel : function() {
							setTimeout(
									function() {
										layer
												.tips(
														'捕获就是从页面已经存在的元素上，包裹 layer 的结构，从而弹出显示。是不是比较好玩呢？',
														'.demo-component-layer',
														{
															tips : 3,
															time : 5000
														});
									}, 300);
						}
					});
		},
		test15 : function() {
			layer.open({
				type : 1,
				skin : 'layui-layer-rim',
				area : [ '420px', '240px' ],
				content : '<div style="padding: 10px;">任意html内容</div>'
			});
		},
		test16 : function() {
			layer
					.open({
						type : 1,
						skin : 'layui-layer-admin',
						closeBtn : false,
						area : '350px',
						anim : 5,
						shadeClose : true,
						content : '<div style="padding:20px;">即传入skin:"样式名"，然后你就可以为所欲为了。你怎么样给她整容都行</div>'
					});
		},
		test17 : function() {
			layer.tips('Hi，我是tips', this, {
				tips : 1
			});
		},
		test18 : function() {
			layer.tips('我是另外一个tips，只不过我长得跟之前那位稍有些不一样。', this, {
				tips : [ 3, '#2F9688' ],
				time : 4000
			});
		},
		test19 : function() {
			layer.open({
				type : 2,
				title : '贤心博客',
				shadeClose : true,
				shade : 0.8,
				area : [ '375px', '500px' ],
				content : 'http://www.layui.com/'
			});
		},
		test20 : function() {
			layer.open({
				type : 2,
				title : '百度一下',
				shade : false,
				maxmin : true,
				area : [ '90%', '90%' ],
				content : 'http://www.baidu.com/'
			});
		},
		test21 : function() {
			var ii = layer.load(0, {
				shade : false
			});
			setTimeout(function() {
				layer.close(ii)
			}, 3000);
		},
		test22 : function() {
			var iii = layer.load(1, {
				shade : [ 0.1, '#fff' ]
			});
			setTimeout(function() {
				layer.close(iii)
			}, 3000);
		},
		test23 : function() {
			layer.prompt({
				title : '写点什么吧'
			}, function(value, index) {
				layer.msg('你写下了：' + value, {
					icon : 6,
					shade : 0.9
				}, function() {
					layer.close(index);
				});
			});
		}

		,
		test26 : function() {
			layer.alert('见到你真的很高兴', {
				icon : 6
			});
		},
		test27 : function() {
			layer.msg('你确定你很帅么？', {
				time : 0 //不自动关闭
				,
				btn : [ '必须啊', '丑到爆' ],
				yes : function(index) {
					layer.close(index);
					layer.msg('雅蠛蝶 O.o', {
						icon : 6,
						time : 0,
						btn : [ '嗷', '嗷', '嗷' ]
					});
				}
			});
		},
		test28 : function() {
			layer.msg('这是最常用的吧');
		},
		test29 : function() {
			layer.msg('并不十分开心。。', {
				icon : 5
			});
		},
		test30 : function() {
			layer.msg('玩命卖萌中', function() {
				layer.msg('卖完了');
			});
		},
		test31 : function() {
			layer.confirm('真的删除吗？', function() {
				layer.msg('正在删除', {
					icon : 16
				}, function() {
					layer.msg('成功删除', {
						icon : 1
					})
				});
			});
		}

		,
		test33 : function() {
			layer
					.open({
						type : 1,
						title : false,
						closeBtn : 0,
						shadeClose : true,
						skin : 'yourClass',
						content : '<div style="padding: 20px; width: 260px; height: 180px;">自定义HTML内容</div>'
					});
		}

		,
		test35 : function() {
			layer.open({
				type : 2,
				area : [ '700px', '450px' ],
				fixed : false,
				maxmin : true,
				content : '../../iframe/layer/iframe.html'
			});
		},
		test36 : function() {
			layer.open({
				type : 2,
				area : [ '360px', '500px' ],
				skin : 'layui-layer-rim',
				content : [ 'http://www.layui.com/', 'no' ]
			});
		},
		test37 : function() {
			layer.load();
			setTimeout(function() {
				layer.closeAll('loading');
			}, 2000);
		},
		test38 : function() {
			layer.load(1);
			setTimeout(function() {
				layer.closeAll('loading');
			}, 2000);
		},
		test39 : function() {
			layer.load(2);
			setTimeout(function() {
				layer.closeAll('loading');
			}, 2000);
		},
		test40 : function() {
			layer.msg('加载中', {
				icon : 16,
				shade : 0.01
			});
		},
		test41 : function() {
			layer.tips('上', this, {
				tips : [ 1, '#000' ]
			});
		},
		test42 : function() {
			layer.tips('默认就是向右的，3秒后关闭', this);
		},
		test43 : function() {
			layer.tips('下', this, {
				tips : 3
			});
		},
		test44 : function() {
			layer.tips('在很久很久以前，在很久很久以前，在很久很久以前……', this, {
				tips : [ 4, '#78BA32' ]
			});
		},
		test45 : function() {
			layer.tips('不会销毁之前的', this, {
				tipsMore : true
			});
		}

		,
		test47 : function() {
			var index = layer.open({
				type : 2,
				content : 'http://baidu.com',
				area : [ '300px', '300px' ],
				maxmin : true
			});
			layer.full(index);
		},
		test48 : function() {
			layer.msg('灵活运用 offset', {
				offset : 't',
				anim : 6
			});
		}
	};