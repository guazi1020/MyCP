!function ($) {

    "use strict"; // jshint ;_;
    /* TAB CLASS DEFINITION
  * ==================== */
    var Tab = function (element) {
        this.element = $(element)
    }
 
    Tab.prototype = {

        constructor: Tab ,
      
        show: function () {
       		
            var $this = this.element
            , $ul = $this.closest('ul:not(.dropdown-menu)')//鎵惧埌瑙﹀彂鍖虹殑瀹瑰櫒
            , selector = $this.attr('data-target')//鍙栧緱瀵瑰簲鐨勯潰鏉跨殑CSS琛ㄨ揪寮�
            , previous
            , $target
            , e
		console.log($this);
            if (!selector) {
                selector = $this.attr('href')//娌℃湁鍒欎粠href寰楀埌
                selector = selector && selector.replace(/.*(?=#[^\s]*$)/, '') //strip for ie7
            }

            if ( $this.parent('li').hasClass('active') ) return

            previous = $ul.find('.active:last a')[0]//瀵瑰緱涔嬪墠琚縺娲荤殑閾炬帴

            e = $.Event('show', {
                relatedTarget: previous
            })

            $this.trigger(e)

            if (e.isDefaultPrevented()) return

            $target = $(selector)

            this.activate($this.parent('li'), $ul);
            this.activate($target, $target.parent(), function () {
                $this.trigger({
                    type: 'shown'
                    ,
                    relatedTarget: previous
                })
            })
        }  ,
       
        activate: function ( element, container, callback) {
            var $deactivate = container.find('> .active')
            , transition = callback
            && $.support.transition
            && $deactivate.hasClass('fade')

            function next() {
               
                $deactivate
                .removeClass('active')
                .find('> .dropdown-menu > .active')
                .removeClass('active')
              
                element.addClass('active')

                if (transition) {
                    element[0].offsetWidth // reflow for transition
                    element.addClass('in')
                } else {
                    element.removeClass('fade')
                }

                if ( element.parent('.dropdown-menu') ) {
                    element.closest('li.dropdown').addClass('active')
                }
               
                callback && callback()
            }

            transition ?
            $deactivate.one($.support.transition.end, next) :
            next()
          
            $deactivate.removeClass('in')
        }
    }


    /* TAB PLUGIN DEFINITION
  * ===================== */

    var old = $.fn.tab

    $.fn.tab = function ( option ) {
        return this.each(function () {
            var $this = $(this)
            , data = $this.data('tab')
            if (!data) $this.data('tab', (data = new Tab(this)))
            if (typeof option == 'string') data[option]()//show
        })
    }

    $.fn.tab.Constructor = Tab


    /* TAB NO CONFLICT
  * =============== */

    $.fn.tab.noConflict = function () {
        $.fn.tab = old
        return this
    }


    /* TAB DATA-API
  * ============ */
   
    $(document).on('click.tab.data-api', '[data-toggle="tab"], [data-toggle="pill"]', function (e) {
        e.preventDefault();
        
        $(this).tab('show');
     //  $(e.currentTarget.nodeName).load("index");
   
     //console.log(e);
    })

}(window.jQuery);