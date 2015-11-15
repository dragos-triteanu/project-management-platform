/**
 * Created by Dragos on 9/21/2015.
 */
$(document).ready(function(){
    var row;
    $('#bidBtn').on('click',function(){
        $(this).hide();
        $('#bid').show();
    });

    $('#cancelBid').on('click', function()
    {
        $('#bidBtn').show();
        $('#bid').hide();

    });

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
});

