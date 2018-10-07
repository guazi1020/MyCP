(function($) {


	/**
	 * 所有menu的判断
	 */
	$('#nav_menu li a').click(function() {
		$(this).parent().parent().find('.active').removeClass('active');
		$(this).parent().addClass('active');
		$(this).shadow(); 
		return false;
	});
	
	$('#refreshSP').click(function(){
		$.ajax({
			url:'refreshSP',
			mothed:'GET'
		}).done(function(result){
			if(result=="true"){
				alert("更新完成！");
			}
		})
		
		
		return false;
	});
	
	$('#refreshResult').click(function(){
			$.ajax({
				url:'refreshResult',
				mothed:'GET'
			}).done(function(result){
				if(result=="true"){
					alert("更新完成！");
				}
			})
			
			
			return false;
			
	});
	
//	$('#refreshResult').click(function(){
//		alert("ik");
//		$.ajax({
//			url:"refreshResult",
//			method:"GET"
//		}).done(function(result){
//			if(result="true"){
//			alert("彩果更新完成！");
//			}
//		})
//		return false;
//	});
	
	$('#bt_jisuan').click(function(){
		$('#qec').text(parseFloat($('#ze').val())-parseFloat($('#pe').val())-parseFloat($('#ke').val())); //全E差
		$('#kpec').text(parseFloat($('#ke').val())-parseFloat($('#pe').val()));	//客平差
		$('#zkec').text(parseFloat($('#ze').val())-parseFloat($('#ke').val()));	//主客E差
		$('#zpec').text(parseFloat($('#ze').val())-parseFloat($('#pe').val()));	//主平E差
		$('#bi').text((parseFloat($('#ze').val())-parseFloat($('#pe').val())-parseFloat($('#ke').val())/(parseFloat($('#ze').val()))));
	})
	
	
	
})(jQuery)
