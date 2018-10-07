/**
 * Website: http://git.oschina.net/hbbcs/bootStrap-addTabs
 *
 * Version : 1.0
 *
 * Created by joe on 2016-2-4.
 */


$(function () {
            $('#tabs').addtabs();
        })
$.fn.addtabs = function (options) {
    obj = $(this);
    Addtabs.options = $.extend({
        content: '', //直接指定所有页面TABS内容
        close: true, //是否可以关闭
        monitor: 'body', //监视的区域
        iframeUse: true, //使用iframe还是ajax
        iframeHeight: $(document).height() - 107, //固定TAB中IFRAME高度,根据需要自己修改
        iframeWidth: '100%',
        method: 'init',
        callback: function () { //关闭后回调函数
        }
    }, options || {});


    $(Addtabs.options.monitor).on('click', '[data-addtab]', function () {
    	
        Addtabs.add({
            id: $(this).attr('data-addtab'),
            title: $(this).attr('title') ? $(this).attr('title') : $(this).html(),
            content: Addtabs.options.content ? Addtabs.options.content : $(this).attr('content'),
            url: $(this).attr('url'),
            page: $(this).attr('page') ? true : false,
            ajax: $(this).attr('ajax') ? true : false,
            method:$(this).attr('method')? "post" : "get",
            parentid:$(this).attr('parentid')//父元素id
        });
    });

    obj.on('click', '.close-tab', function () {
        var id = $(this).parent("a").attr("aria-controls");
        Addtabs.close(id);
    });

    $(window).resize(function () {
        obj.find('iframe').attr('height', Addtabs.options.iframeHeight);
        obj.find('iframe').attr('width', Addtabs.options.iframeWidth);
       
    });

};

window.Addtabs = {
	    options : {},
    add: function (opts) {
        var id = 'tab_' + opts.id;
        $('li[role = "presentation"].active').removeClass('active');
        $('div[role = "tabpanel"].active').removeClass('active');
        //如果TAB不存在，创建一个新的TAB
        if (!$("#" + id)[0]) {
            //创建新TAB的title
            var title = $('<li>', {
                'role': 'presentation',
                'id': 'tab_' + id,
                'aria-url':opts.url
            })
            var a=$('<a>', {
                                'href': '#' + id,
                                'aria-controls': id,
                                'role': 'tab',
                                'data-toggle': 'tab'
                            }).html(opts.title)           
            title.append(a)   

            //是否允许关闭
            if (Addtabs.options.close) {
            	a.append($('<i>', {'class': 'close-tab glyphicon glyphicon-remove'}))
                   
            }
            //创建新TAB的内容
            var content = $('<div>', {
                'class': 'tab-pane',
                'id': id,
                'role': 'tabpanel'
            });
            //加入TABS
            $('.nav-tabs').append(title);            
            $(".tab-content").append(content);
           
        } else {
            var content = $('#' + id);
            content.html('');
        }

        //是否指定TAB内容
        if (opts.content) {
            content.append(opts.content);
        } else if (Addtabs.options.iframeUse && !opts.ajax) {//没有内容，使用IFRAME打开链接
            content.append(
                $('<iframe>', {
                    'class': 'iframeClass',
                    'height': Addtabs.options.iframeHeight,
                    'width': Addtabs.options.iframeWidth,
                    'frameborder': "no",
                    'border': "0",
                    'src': opts.url
                })
            );
        }
        
        else {
        	if(opts.method=="post"){
        		var $form = $('#'+opts.parentid); // 很关键
        		//alert($form+","+opts.parentid)
                // 得到 form 的提交路径
                url = $form.attr('action'); // 得到该 form 的提交路径,并赋值到 url
                // 以 post 方式提交, 回调函数function 返回 data
                alert($form.serialize())
               $.post(url, $form.serialize(), function(data)
                {
                	 content.append(data);
                });
        	}else{
        		
            $.get(opts.url, function (data) {
                content.append(data);
            });
        	}
        }
        //激活TAB
        $('#tab_' + id).addClass('active');
        $('#' + id).addClass('active');
       
    },

    close: function (id) {
        //如果关闭的是当前激活的TAB，激活他的前一个TAB
        //if (obj.find("li.active").attr('id') === "tab_" + id) {
            $("#tab_" + id).prev().addClass('active');
            $("#" + id).prev().addClass('active');
       // }
        //关闭TAB
        $("#tab_" + id).remove();
        $("#" + id).remove();
       
        Addtabs.options.callback();
    },
    closeAll: function () {
        $.each(obj.find('li[id]'), function () {
            var id = $(this).children('a').attr('aria-controls');
            $("#tab_" + id).remove();
            $("#" + id).remove();
        });
        obj.find('li[role = "presentation"]').first().addClass('active');
        var firstID=obj.find('li[role = "presentation"]').first().children('a').attr('aria-controls');
        $('#'+firstID).addClass('active');
       
    },
}