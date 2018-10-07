(function($) {

	/**
	 * judge 判断集合
	 * 	isEmptyValue：数据判断是否为空
	 */
	judge = {
		isEmptyValue : function(value) {
			var type;
			if (value == null) { // 等同于 value === undefined || value === null
				return true;
			}
			type = Object.prototype.toString.call(value).slice(8, -1);
			switch (type) {
			case 'String':
				return !$.trim(value);
			case 'Array':
				return !value.length;
			case 'Object':
				return $.isEmptyObject(value); // 普通对象使用 for...in 判断，有 key 即为
												// false
			default:
				return false; // 其他对象均视作非空
			}
		}
	};
	dateOperation={
//			日期转换格式
			ChangeDateFormat:function (jsondate) {
				jsondate = jsondate.replace("/Date(", "").replace(")/", "");
				if (jsondate.indexOf("+") > 0) {
					jsondate = jsondate.substring(0, jsondate.indexOf("+"));
				} else if (jsondate.indexOf("-") > 0) {
					jsondate = jsondate.substring(0, jsondate.indexOf("-"));
				}
				var date = new Date(parseInt(jsondate, 10));
				var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1)
						: date.getMonth() + 1;
				var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date
						.getDate();
				var hours = date.getHours() < 10 ? "0" + date.getHours() : date
						.getHours();
				var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes()
						: date.getMinutes();
				var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds()
						: date.getSeconds();
				return month + "-" + currentDate + " "
				+ hours + ":" + minutes + ":" + seconds;
				return date.getFullYear() + "-" + month + "-" + currentDate + "-"+"</br>"
						+ " " + hours + ":" + minutes + ":" + seconds;
			}
	}
})(jQuery)