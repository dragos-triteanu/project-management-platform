var amountDue;
var nrOfEffectuated0fTransactions;

function getMinimumCost(){
    switch (nrOfEffectuated0fTransactions) {
        case 0 :
            return Math.round(amountDue / 3);
        case 1 :
            return Math.round(amountDue / 2);
        case 2 :
            return amountDue;
    }
}

$(document).ready(function(){
        $("#orders-dropdown").on("change",function(){
            var optionSelected = $(this).find("option:selected");
            var valueSelected  = optionSelected.val();
            if(valueSelected==-1) {
                $("#infoPanel").hide();
                return;
            }

            var textSelected  = optionSelected.text();
            $("#product-name").val(textSelected);

            var params = new Object();
            params["orderId"] = valueSelected;
            $.ajax({
                url: "/payments/getpaymentsdetails",
                method: "GET",
                dataType: "json",
                data: params
            }).success(function (data) {
                $("#infoPanel").show();
                amountDue = data.amountDue;
                nrOfEffectuated0fTransactions =  data.nrOfEffectuated0fTransactions;
                var message = "Mai aveti de achitat :"+data.amountDue + " RON in maxim " + (3 - data.nrOfEffectuated0fTransactions)+" rate. Suma minima pentru o tranzictie este: "+getMinimumCost()+" RON.";
                $("#infoPanel").text(message);
            });
        })

        $("#payment-form").on("submit", function(event){
            if( $("#orders-dropdown").find("option:selected").val()==-1) {
                event.preventDefault();
                alert("Alegeti comanda pentru care doreti sa efectuati plata.")
                return;
            }
            if(getMinimumCost() > parseInt( $("#price").val())){
                event.preventDefault();
                alert("Suma introdusa pentru plata este mai mica decat cea minima.")
                return;
            }
        });
    }
);