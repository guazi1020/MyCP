(function($) {
	/**
	 * 定义了shadow的方法
	 */
	$.fn.shadow = function(opts) {
		/**
		 * defaults默认参数
		 */
		var defaults = {
			url : 'term', // 指向地址
			space : 'content' // 内容填入目标的空间
		};
		var options = $.extend(defaults, opts); // 扩展参数

		return this.each(function() { // 连缀内置方法
			var $element = $(this); // 获取当前的dom
			// console.log($element.attr('href'));
			if (!judge.isEmptyValue($element.attr('href'))) {

				options.url = $element.attr('href'); // 根据dom的href来决定跳转地址
			}
			var space = options.space;
			$('#' + space).empty();
			$('#' + space).load(options.url); // 装载url地址
		});
	};
})(jQuery);