

$(document).ready(function() {

	/*
	 * autor：Li 2016-03-07 最外层DIV随着窗口宽高改变而改变
	 */
	// 最外层DIV宽高设置
	var defaultmain = function() {
		$("#main").css({
					width : $(window).width(),
					height : $(window).height()
				});
		var contents_height = $("#main").height() - $("#top").height()
				- $("#bottom").height();
		var container_width = $("#contents").width() - $("#left").width()
				- $("#right").width();
		// 设置内容DIV的高度
		$("#contents").css({
					height : contents_height
				});
		// 折折工作容器的宽度
		$("#container").css({
					width : container_width
				});
		// 设置left_contorl的左偏移
		var left_control_left = $("#left").width();
		if (left_control_left <= 0) {

		} else {
			left_control_left = left_control_left - 10;
		}
		var right_control_right = $("#right").width();
		var center_height = $("#contents").height() / 2;

		$("#left_control").css({
					left : left_control_left,
					top : center_height
				});

		// 设置right_contorl的右偏移
		$("#right_control").css({
					right : right_control_right,
					top : center_height
				});
	};
	// 初始化执行
	defaultmain();
	// 窗口改变事件
	$(window).resize(function(e) {
				defaultmain();
			});

	// 替换left样式
	var flag = 0;
	$('#left_control').click(function() {
				if (flag == 0) {
					$('#left').hide();
					$('#left').css({
								width : '0px'
							});
					defaultmain();
					flag++;
					$('#img_left').attr('src', 'Sources/images/right.png');

				} else {
					$('#left').show();
					$('#left').css({
								width : '200px'
							});
					defaultmain();
					flag--;
					$('#img_left').attr('src', 'Sources/images/left.png');
				}
			});

	$('#right_control').click(function() {
				$(this).swaclass('right', 'right');

			});

	$('#test').click(function() {
				$('#container').load("list");
			})

});
