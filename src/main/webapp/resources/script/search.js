
/**
 * Created by Dragos on 10/19/2015.
 */
$(document).ready(function(){
    $('#inputSearch').on('input', function() {

            var params = new Object();
            params["searchText"] =  $(this).val();
            params["selectedSearchCategory"] = $("#searchCategory").val();
            var searchType= $("#searchType").val();
            var url;

            switch (searchType) {
                case "newOrders":
                    url = "/orders";
                    break;
                case "myOrders":
                    url = "/myorders";
                    break;
                case "consultants":
                    url="/consultants"
            }

            $.ajax({
                url:url,
                method: "GET",
                data: params
            }).success(function(data) {
                console.log(data);
                $(".generic-table").replaceWith($(data).find(".generic-table"));
            });
      });
});