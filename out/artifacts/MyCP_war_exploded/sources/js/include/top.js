/**
 * description：根据data创建导航(top)
 * 					控件自增属性：
 * 						url:需要调用的数据地址，必须是返回格式为json
 * 								{
										"menu_top": [
											{
												"menu_id": "1",
												 "url": "list",
												"name": "按钮1"
											}, {
												"menu_id": "2",
												"url": "top",
												"name": "按钮2"
											},{
												"menu_id": "3",
												"url": "list",
												"name": "按钮1"
											}, {
												"menu_id": "4",
												"url": "top",
												"name": "按钮2"
											}
										]
									}
* 				 				
 * Author:li
 * data:2016/11/30
 */

(function($){
	$.fn.menuCreate=function(){
		obj=$(this);
		
		//参数
		topMenu.options = $.extend({
			changeTo:obj.attr('changeTo'),
			url:obj.attr('url')
		});
	
		//创建
		topMenu.create({
			url:obj.attr('url')
		});
		
		
		//绑定click事件
		$(obj).on('click','[url]',function(){
			$('#'+topMenu.options.changeTo).createleft({
				url:$(this).attr('url')
			});
			

		})
	};
	
	topMenu={
		options:{},
		create:function(opts){
		
			//循环读取json数据
			var ul=$('<ul>',{
						'class':'nav navbar-nav navbar-right'
						});
			$.get(opts.url,function(data){
				$.each(data,function(name,items){ //最顶层循环
					$.each(items,function(nam,item){ //第二层循环
						
					/*创建<li><a>*/			
					var li=$('<li>',{
						}).appendTo(ul);

					var a=$('<a>',{
						'url':item.url,
						'href':'javascript:void(0)'
						}).text(item.name).appendTo(li);

					})
				})
			});
			
			//装载
			obj.append(ul);
	
		}
	}
})(jQuery)