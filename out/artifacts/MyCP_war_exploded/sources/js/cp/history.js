(function($) {
	console.log("begin");

	$("#grid-basic").bootgrid({
		ajax : true,
		ajaxSettings : {
			method : 'GET',
			cache : false
		},
		url : 'getAllData',
		formatters:{
			"gameDate":function(column,row){
				return dateOperation.ChangeDateFormat(row.gameData.toString())
			}
		}

	});

	$.ajax({
		url : 'getAllData',
		// url : '../../MyCP/sources/json/netData.json',
		method : 'get',
		datatype : 'json'
	}).done(function(results) {
		console.log(jQuery.parseJSON(results).rows[0]);
		//console.log(results);
	});

})(jQuery)