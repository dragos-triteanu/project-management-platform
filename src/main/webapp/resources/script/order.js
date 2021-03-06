/**
 * Created by Dragos on 9/21/2015.
 */
$(document).ready(function(){
    /*apply bid for an order section*/
    $('#bidBtn').on('click',function(){
        $(this).hide();
        $('#bid').show();
    });
    $('#cancelBid').on('click', function()
    {
        $('#bidBtn').show();
        $('#bid').hide();

    });

    /*assign consultant to an order section*/
    var row;
    $("#orderBids tr").draggable({
        helper: "clone"
    });
    $("#assignedConsultant").droppable({
        drop: function (event, ui) {
            var bid = ui.draggable.text().replace(/\s+/g," ").split(" ");
            var outputValue = bid[1]+" "+bid[2]+" : "+bid[3]+" zile | "+bid[4]+" RON"
            $(this).val(outputValue);
            if(row != null)
            {
                $('#orderBids tr:last').after(row);
                $(row).draggable({
                    helper: "clone"
                });
            }
            row=$(ui.draggable).clone();
            $(ui.draggable).remove();
            $(ui.helper).remove();
        }
    });
    $("#assignConsultant").on('click',function(event) {
            event.preventDefault();
            if (row != null) {
                var params = new Object();
                params["consultantId"] =  $(row).attr('id');;
                params["orderId"] = $("#orderId").val();

                $.ajax({
                    url: "/orderDetails/assignConsultant",
                    method: "POST",
                    data: params
                }).success(function (data) {
                    console.log(data);
                    $("#order-details").replaceWith($(data).find("#order-details"));
                });
            }
        }
    );

    /*start order section*/
    $("#starOrder").on('click',function(event) {
        event.preventDefault();
        var params = new Object();
        params["orderId"] = $("#orderId").val();
        $.ajax({
            url: "/myOrderDetails/startOrder",
            method: "POST",
            data: params
        }).success(function (data) {
            window.location.href ="/myOrderDetails?orderId="+ $("#orderId").val();
        });
    });

    /*close order section*/
    $("#closeOrder").on('click',function(event) {
        event.preventDefault();
        var params = new Object();
        params["orderId"] = $("#orderId").val();
        $.ajax({
            url: "/myOrderDetails/closeOrder",
            method: "POST",
            data: params
        }).success(function (data) {
            window.location.href ="/myOrderDetails?orderId="+ $("#orderId").val();
        });
    });
});

