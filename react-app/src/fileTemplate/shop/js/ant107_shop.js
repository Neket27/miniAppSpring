(function ($) {
    'use strict';

    $(window).on('load', function () {
        // ant107_shop-minus
        $(".ant107_shop-minus").on('click', function () {
            this.parentNode.querySelector('input[type=number]').stepDown();
        });

        // ant107_shop-plus
        $(".ant107_shop-plus").on('click', function () {
            this.parentNode.querySelector('input[type=number]').stepUp();
        });

        if($('.ant107_shop-product-preview-wrap').length){
            $('.ant107_shop-product-preview-wrap .tab-pane img').magnify();
        }
    });

})(jQuery);