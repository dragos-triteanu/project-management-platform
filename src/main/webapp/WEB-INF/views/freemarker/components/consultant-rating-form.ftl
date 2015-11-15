<#macro renderRatingForm order>
    <div class="consultantRatingContainer col-md-offset-4 col-xs-offset-4">
        <form class="consultantRatingForm" method="post" action="./myOrderDetails/rateConsultant" novalidate>
            <input type="hidden" name="orderId" value="${order.orderId}" />
            <div class = "form-group">
                <input id="input-1" name="consultantRating" type="number" class="rating" data-min="0" data-max="5" data-size="lg">
            </div>
            <div class="form-group">
                <button class="btn btn-danger col-md-offset-2" type="submit">Ofera feedback</button>
            </div>
        </form>
    </div>
</#macro>