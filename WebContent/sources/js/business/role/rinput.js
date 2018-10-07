$(function() {

	$('.check_ground input').on('ifCreated ifClicked ifChanged ifChecked ifUnchecked ifDisabled ifEnabled ifDestroyed', function(event) {
		console.log(event.type);
		//callbacks_list.prepend('<li><span>#' + this.id + '</span> is ' + event.type.replace('if', '').toLowerCase() + '</li>');
	}).iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue',
		increaseArea: '20%'
	});

	$('.creat').roleCreate();
});
(function($) {

	$.fn.roleCreate = function() {

		obj = $(this);
		Check.Create();
	};
	Check = {
		opts: {},
		Create: function(options) {
			obj.append('aaa');
		}
	}

})(jQuery)