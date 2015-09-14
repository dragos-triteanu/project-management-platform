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
        window.location="/CrowdfundingApp/consultants"
    });
});