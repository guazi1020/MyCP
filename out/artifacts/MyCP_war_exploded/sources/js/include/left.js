(function($) {
	$.fn.createleft = function(options) {
		leftMenu.options = $.extend({

		}, options || {});
		obj = $(this);
		
		leftMenu.createMenu({

		})
	};

	leftMenu = {
		options: {},
		//创建 nav-sidebar
		createMenu: function(opts) {
			obj.empty();
			var div=$("<div>",{
				'class':'panel panel-default',
				'style':'margin:0;'
			})
			var leftdiv=$('<div>',{'id':'collapseOne',
				'class':'panel-collapse collapse in',
				'role':'tabpanel',
				'aria-labelledby':'headingOne'
			});			
			var ul = $('<ul>', {
				'class': 'nav nav-sidebar',
			});									
			var leftheaddiv=$('<div>',{
				'class':'panel-heading',
				'role':'tab',
				'id':'headingOne',
				//'style':'background:black',
			});			
			var h4=$('<h4>',{
				'class':"panel-title",
				'style':"text-align:center;",
			});				        		
			leftheaddiv.append(h4);
			leftdiv.append(ul);
			div.append(leftheaddiv);
			div.append(leftdiv);
			$.ajax({
				url: leftMenu.options.url,
				type: 'get',
				success: function(data) {	
					$.each(data, function(name, items) {
						var a=$('<a>',{
	                    	'id':'lefttitle',
	                    	'style':"text-decoration:none;color:#666;",	                    	
	                    }).html(items[0].uplevelname).appendTo(h4);	        						
						$.each(items, function(name, item) {							
							var li = $('<li>', {}).appendTo(ul);
							var a = $('<a>', {
								'id':item.url,								
								'url':item.url,
								'data-addtab':item.returntab,
								'style':"cursor:pointer;border-bottom:1px solid #ccc;text-align:center",
								'ajax':true
							}).text(item.name).appendTo(li);
	
						})
					})
					obj.append(div);
					
				}

			});
			
		}

	}
})(jQuery)