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
        $("#minimizeChat").hide();
    });
});
