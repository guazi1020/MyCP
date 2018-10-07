(function($) {
	var isRefresh = false;

	$('#refresh').click(function(e) {

		// console.log("this button");
		$('#grid-basic').bootgrid("destroy");
		// $("#grid-basic").bootgrid("search", "76");
		init(true);
		$('#grid-basic').bootgrid("destroy"); // 没办法改变参数，只能这样
		init(false);

		// getNetData();

	})
	init(isRefresh);
	function init(str_FLG) {
		// $('#grid-basic').bootgrid("destroy");
		var grid = $("#grid-basic")
				.bootgrid(
						{

							ajax : true,
							navigation : 1, // 0:for none 1：for header 2:for
							// footer 3:for both
							sorting : false,
							selection: true,
							multiSelect: true,
							
							ajaxSettings : {
								method : 'GET',
								cache : false
							},
							labels : {
								noResults : "where are my results",
								loading : "你等一会，行不？。。。。" // loading时显示的内容
							},
							post : function() {
								return {
									isRefresh : str_FLG
								}
							},

							// url : '../../MyCP/sources/json/netData.json',
							url : 'getNetData',
							formatters : {
								"sps" : function(column, row) {

									var result = "";

									$.each(row.sps, function(index, item) {
										result += item.ball + " " + item.homeSP
												+ " " + item.pingSP + " "
												+ item.guestSP + "</br>";
									})
									return "<div style='width:120px'><p>"
											+ result;
								},
								"closeDate" : function(column, row) {
									return dateOperation
											.ChangeDateFormat(row.closeData
													.toString());
								},
								"gameDate" : function(column, row) {
									return dateOperation
											.ChangeDateFormat(row.gameData
													.toString());
								},
								"guestE" : function(column, row) {
									return Math.round(row.guestE * 100) / 100;
								},
								"pingE" : function(column, row) {
									return Math.round(row.pingE * 100) / 100;
								},
								"homeE" : function(column, row) {
									return Math.round(row.homeE * 100) / 100;
								},
								"E" : function(column, row) {
									return '全E差：'+(Math.round(row.homeE * 100) / 100-Math.round(row.pingE * 100) / 100-Math.round(row.guestE * 100) / 100)
										+"</br>"+'客平E差'+(Math.round(row.guestE * 100) / 100-Math.round(row.pingE * 100) / 100)
										+"</br>主客E差"+(Math.round(row.homeE*100)/100-Math.round(row.guestE*100)/100)
										+"</br>主平E差"+(Math.round(row.homeE*100)/100-Math.round(row.pingE*100)/100);
								},
								"commands" : function(column, row) {
									
									//class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"
									//return "<div> <p><a id=\"cc\" data-toggle=\"modal\" data-target=\"#myModal\ onclick=\"alert(\"cccccc\")\">根据SPS";
									return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit  btn-primary btn-lg\" data-toggle=\"modal\" data-target=\"#myModal\" data-row-id=\""
											+ row.homeE
											+ "\"><span class=\"fa fa-pencil\">主</span></button> "
											+ "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\""
											+ row.homeE
											+ "\"><span class=\"fa fa-trash-o\">客</span></button>";
								}
							},
							templates : {
								// header:"<input id=\"hehe\" type=\"button\"
								// value=\"button\" />"
								header : "<div id=\"{{ctx.id}}\" class=\"{{css.header}}\">"
										+ "<div class=\"row\">"
										+ "<div class=\"col-sm-12 actionBar\">"
										+ "<p class=\"{{css.search}}\"></p>"
										+ "<p class=\"{{css.actions}}\"></p>"
										+ "</div>" + "</div>" + "</div>"

							}
						})
						.on("loaded.rs.jquery.bootgrid",function(){
							grid.find(".command-edit").on("click", function(e)
								    {
										$("#myModal").modal("show");
								        //alert("You pressed edit on row: " + $(this).data("row-id"));
								        //console.log(($(this)));
								    }).end().find(".command-delete").on("click", function(e)
								    {
								    	getNetData();
								        alert("You pressed delete on row: " + $(this).data("row-id"));
								    });
						})
						.on("click.rs.jquery.bootgrid",
						function(e, columns, row) {
							//console.log(row);
						});

		// isRefresh=false;
	};

	
$("#cc").click(function(){
	
	//console.log("aaaaaaa");
	//$('#identifier').modal(options);
});
	
	
	
	
	$("#getData").click(function() {
		console.log("aaaaa");
		//getNetData();
	})

	function getNetData() {
		console.log("bb");
		$.ajax({
			method : "GET",
			url : "getNetData",
			dataType : 'json',
			data : {
				isRefresh : true
			}
		}).done(function(results) {
			console.log("aa");
			console.log(JSON.stringify(results.rows));
		})

	}
	;

})(jQuery)