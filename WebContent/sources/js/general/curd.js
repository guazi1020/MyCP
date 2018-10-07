(function($){
/**
 * 根据lastname判断是否唯一，
 * @param {Object} lastName 判断字段
 * @param {Object} url 提交url
 * @version 0.12016/11/7
 */
	$.onlyByLastName=function(lastName,url){
		var defer=$.Deferred();
			$.ajax({
				url :url,
				method : "POST",
				data : {
					lastName :lastName
				},
				success : function(result) {
					defer.resolve(result);
				}
			})
		return defer.promise();
	}
})(jQuery)



function onlyByLastName(lastName)
{
	var defer=$.Deferred();
	$.ajax({
				url : "../isOnly",
				method : "POST",
				
				data : {
					lastName : lastName
				},
				success : function(result) {
					
					defer.resolve(result);
				}
			});
			return defer.promise();
}