/**
 * Created by Dragos on 9/21/2015.
 */
$(document).ready(function(){
    $('#bidBtn').on('click',function(){
        $(this).hide();
        $('#bid').show();
    });

    $('#cancelBid').on('click', function()
    {
        $('#bidBtn').show();
        $('#bid').hide();

    });

    });

