(function($) {

	$.extend({
		/**
		 * 读取参数
		 * @param {Object} m 需要读取的url参数名称
		 */
		Request: function(m) {
			var sValue = location.search.match(new RegExp("[\?\&]" + m + "=([^\&]*)(\&?)", "i"));
			return sValue ? sValue[1] : sValue;
		},
		/**
		 * 替换参数返回url，如果没有则增加
		 * @param {Object} url 需要替补还的url
		 * @param {Object} name 需要替换的参数名称
		 * @param {Object} value 替换的值
		 */
		UrlUpdateParams: function(url, name, value) {
			var r = url;
			if(r != null && r != 'undefined' && r != "") {
				value = encodeURIComponent(value);
				var reg = new RegExp("(^|)" + name + "=([^&]*)(|$)");
				var tmp = name + "=" + value;
				if(url.match(reg) != null) {
					r = url.replace(eval(reg), tmp);
				} else {
					if(url.match("[\?]")) {
						r = url + "&" + tmp;
					} else {
						r = url + "?" + tmp;
					}
				}
			}
			return r;
		}

	});
})(jQuery);