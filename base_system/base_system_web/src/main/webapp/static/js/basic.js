jQuery(document).ready(function($) {
            $(document).on('click', 'table tbody tr', function(event) {
                $(this).find('.checkbox_j').addClass('on').parents("tr").siblings('tr').find('.checkbox_j').removeClass('on');
            });
        });