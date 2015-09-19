/**
 * Created by Dragos on 9/13/2015.
 */
$(document).ready(function() {

    $(".toggleCategories").on("click",function(){
       var isShown = $(".categoryContainer").is(":visible");
       if(isShown){
           $(".categoryContainer").hide();
           this.innerHTML = "Gestioneaza categorii"
       }else{
           $(".categoryContainer").show();
           this.innerHTML = "Inchide";
       }
    });

    loadCategoryEvents();
});

var loadCategoryEvents = function(){
    $(".addCategoryBtn").on("click",function(){
        var categoryName =  $(".newCategoryNameInput").val();
        if(categoryName == null || categoryName == ""){
            alert("BOOM");
        }else{
            var params = new Object();
            params["name"] = categoryName;

            $.ajax({
                url:"./createConsultant/createCategory",
                method:"POST",
                data: params
            }).success(function(data){
                $(".categoryTableContainer").html(data);
                var appender = "";

                populateSpeciality(appender);
                loadCategoryEvents();
            });
        }
    });

    $(".removeCategory").on("click",function(){
        var deleteId = $(this).attr("deleteId");

        var params = new Object();

        params["deleteId"] = deleteId;

        $.ajax({
            url:"./createConsultant/deleteCategory",
            method:"POST",
            data: params
        }).success(function(data){
            $(".categoryTableContainer").html(data);
            var appender = "";
            populateSpeciality(appender);
            loadCategoryEvents();
        });
    });
}


function populateSpeciality(appender) {
    $(".table-data").each(function (index, currentElement) {
        var option = "<option value=" + $(currentElement).find(".categoryId").val() + ">" + $(currentElement).find(".categoryName").text() + "</option>";
        appender += option;
    });
    console.log(appender);
    $("#category-dropdown").html(appender);
}