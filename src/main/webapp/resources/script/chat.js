/**
 * Created by Dragos on 10/7/2015.
 */
$(document).ready(function() {
     $('#btnHide').on('click', function () {
         $("#openChat").hide();
         $("#minimizeChat").show();
    });

    $('#btnShow').on('click', function () {
        $("#openChat").show();
        $("#chatBody").scrollTop($("#chatBody")[0].scrollHeight);
        $("#minimizeChat").hide();
    });

    $('#chatBody').bind("DOMSubtreeModified",function(){
        $("#chatBody").scrollTop($("#chatBody")[0].scrollHeight);
    });
});
