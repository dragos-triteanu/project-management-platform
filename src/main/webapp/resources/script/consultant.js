/**
 * Created by Dragos on 9/13/2015.
 */
var EMPTY_UID = "00000000-0000-0000-0000-000000000000";
$(document).ready(function() {
    $('#category-dropdown').on('change', function () {
        var optionSelected = $(this).find("option:selected");
        if (optionSelected.val() == EMPTY_UID) {
            $('#all-categories').hide();
            $('#new-category').show();
        }
    });

    $('#back-to-all-categories').on('click', function () {
        $("#new-category").hide();
        $('#all-categories').show();
    });

    $('#cancel-btn').on('click', function() {
        window.location="/consultants"
    });


    $(".delete-consultant-button").on("click",function(){
        deleteOnSubmit();
    });
    
    $(".uploadNewCVButton").on("click",function(){
    	$(".fileContainer").replaceWith($(".fileContainerBackup"));
    	$(".fileContainerBackup").removeClass("hidden");
    });
});

var deleteOnSubmit = function(){
    bootbox.confirm("Esti sigur bosz?", function(result) {
        if(!result){
            bootbox.hideAll();
        }else{
            $(".delete-consultant-form").submit();
        }
    });
};

