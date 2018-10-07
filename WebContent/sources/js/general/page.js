(function($) {
	$.fn.createpage = function(options) {
		// 需要传的参数
		// num:总页数
		// tag:url
		// pageno:当前页数
		// tabid:跳转到目标页的tabpanel的id
		// options.type:是否在tabpanel中，0为在，1为不在
		page.options = $.extend({}, options || {});
		obj = $(this);

		page.create(options)
	};
	page = {
		options : {},
		/**
		 * 创建分页中的li；obj,appendTo的对象，opts:传入的参数
		 * 
		 * @param {Object}
		 *            opts
		 */
		create : function(opts) {

			// 定义参数
			// 当前页数
			// var pathname=window.location.pathname
			// var projectName =
			// pathname.substring(0,pathname.substr(1).indexOf('/')+1);
			// var search=window.location.search;
			// pathname+=search;
			// var url=projectName+"/"+opts.tag;

			var pageNo = opts.pageno;
			// var pageNo=parseInt($.Request("pageNo"))||1;
			if (pageNo < 0) {
				pageNo = 1;
			}
			if (opts.type == 1) {
				opts.tag = opts.tag.substring(opts.tag.indexOf('/') + 1);
			}
			// 总页数
			var num = parseInt(opts.num);
			// 构建页面跳转
			var $li_input = $('<li></li>').appendTo(obj);
			var $span_input = $('<span></span>').appendTo($li_input);
			var $text_input = $('<input type="text"></input>').addClass(
					"form-control input-sm page-input").appendTo($span_input);
			var $li_jump = $('<li></li>').appendTo(obj);
			var $a_jump = $(
					'<a data-addtab=' + opts.tabid + ' ajax="true">Go</a>')
					.attr("url", "#");
			if (opts.type == 1) {
				$a_jump = $('<a>Go</a>').attr("href", "#");
			}
			$a_jump.appendTo($li_jump);
			// input变化时需要改变跳转
			$text_input.change(function() {
				// 判定是否是int，如果非int就直接跳转1，如果大于最大数跳转最大页数
				var page_jmp = parseInt($(this).val()) || 1;
				if (page_jmp > num) {
					page_jmp = num;
				}
				if (page_jmp < 0) {
					page_jmp = 1;
				}
				// 为Go增加跳转参数
				var url_jump = $.UrlUpdateParams(opts.tag, "pageNo", page_jmp);
				$a_jump.attr("url", url_jump);
				if (opts.type == 1) {
					$a_jump.attr("href", url_jump);

				}
			});

			// 构建上一页
			var className_left = "";
			var url_left = $.UrlUpdateParams(opts.tag, "pageNo", pageNo - 1);
			if (pageNo == null || pageNo <= 1) {
				className_left = "disabled";
				url_left = $.UrlUpdateParams(opts.tag, "pageNo", pageNo);
			}
			var $li_left = $('<li></li>').addClass(className_left)
					.appendTo(obj);
			var $a_left = $(
					'<a data-addtab=' + opts.tabid + ' ajax="true">&laquo;</a>')
					.attr('url', url_left);
			if (opts.type == 1) {
				$a_left = $('<a>&laquo;</a>').attr("href", url_left);
			}
			$a_left.appendTo($li_left)
			// 数字循环控制
			var i_begin = 5 * parseInt((pageNo - 1) / 5);
			var i_end = 5 * parseInt((pageNo - 1) / 5) + 5;
			if (i_end > num) {
				i_end = num;
			}
			// 构建数字
			for (var i = i_begin; i < i_end; i++) {
				var className = "";
				if ((i + 1) == parseInt(pageNo)) {
					className = "active";
				}
				var li = $('<li></li>').addClass(className);
				var a = $('<a data-addtab=' + opts.tabid + ' ajax="true"></a>')
						.attr('url',
								$.UrlUpdateParams(opts.tag, "pageNo", i + 1))
						.text(i + 1).appendTo(li);
				li.appendTo(obj);
			}

			// 构建下一页
			var className_right = "";
			var url_right = $.UrlUpdateParams(opts.tag, "pageNo",
					parseInt(pageNo) + 1);
			if (pageNo > num - 1) {
				className_right = "disabled";
				url_right = $.UrlUpdateParams(opts.tag, "pageNo",
						parseInt(pageNo));
			}
			var li_right = $('<li></li>').addClass(className_right).appendTo(
					obj);
			var a_right = $(
					'<a data-addtab=' + opts.tabid + ' ajax="true">&raquo;</a>')
					.attr('url', url_right);
			if (opts.type == 1) {
				a_right = $('<a>&raquo;</a>').attr("href", url_right);
			}
			a_right.appendTo(li_right);
			// 构建总页数
			var li_total = $('<li></li>').addClass("active").appendTo(obj);
			$('<span></span>').text("共 " + num + " 页").appendTo(li_total);
		}
	};
})(jQuery)