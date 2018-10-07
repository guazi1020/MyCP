(function($) {
	$.fn.createModal = function(options) {	
		modal.options = $.extend({
//需要传的参数
//modTitle:模态框标题
//modBody:模态框主体内容
//tabid:模态框点击保存或提交按钮后跳转到的页面id（tabpanel中用）
//id:modal的id（用于一个页面使用多个modal
///modTitle:模态框标题
//modBody:模态框主体内容
//tabid:模态框点击保0存或提交按钮后跳转到的页面id（tabpanel中用）
//url:点击保存后跳转的url
//method:访问类型
//title:返回页面的标题
//parentid:form表单的id（只用于form表单）
		}, options || {});
		obj = $(this);
		
		modal.createMod(options)
	};

	modal = {
		options: {},
		//创建 modal
		createMod: function(opts) {
			var myModal=$("<div>",{
				'class':'modal fade',
				'id':opts.id,
				'tabindex':'-1',
				'role':'dialog',
				'aria-labelledby':'myModalLabel',
				'aria-hidden':'true'
			});
			var dialog=$("<div>",{
				'class':'modal-dialog',
				'style':opts.style,		
			});
			var modContent=$("<div>",{
				'class':'modal-content',
			})
			var header=$("<div>",{
				'class':"modal-header",
			})
			var button=$("<button>",{
				'type':'button',
				'class':'close',
				'data-dismiss':'modal',
				'aria-hidden':true
			})	
			var h4=$("<h4>",{
				'class':'modal-title',
				'id':'myModalLabel'
			})	
			var modalBody=$("<div>",{
				'class':'modal-body',
			});	
			var footer=$("<div>",{
				'class':'modal-footer',
			});
			var submitButton=$("<button>",{
				'type':'button',
				'class':'btn btn-primary',
				'data-addtab':opts.tabid,
				'ajax':true,
				'id':'modelSumbit',
				'data-dismiss':'modal',//点击后关闭
				'method':opts.method,
				'title':opts.title,
				'parentid':opts.parentid,
				'url':opts.url,
			});	
			var excButton=$("<button>",{
				'type':'button',
				'class':'btn btn-default',
				'data-dismiss':'modal',
			});
			button.html("&times;");
			h4.html(opts.modTitle);
			modalBody.html(opts.modBody);
			submitButton.html("确定");	
			excButton.html("取消");	
			myModal.append(dialog);	
			dialog.append(modContent);
			modContent.append(header);
			header.append(button);
			header.append(h4);
			modContent.append(modalBody);
			modContent.append(footer);
			footer.append(submitButton);
			footer.append(excButton);
			if(!$('#'+opts.id)[0]){
				obj.append(myModal);
			}
			
		}

	}
})(jQuery)	