<#macro renderTable ordersList userRole >
    <#if ordersList?has_content>
    <form id="payment-form" class="create-form form-horizontal" role="form" action='https://www.2checkout.com/checkout/purchase' method='post'>
        <input type='hidden' name='sid' value='1303908' >
        <input type='hidden' name='mode' value='2CO' >
        <input type='hidden' name='li_0_type' value='product' >
        <input type='hidden' name='li_0_tangible' value='N' >
        <input type='hidden' name='state' value='RO' >
        <input type='hidden' name='country' value='RO'>
        <input id="product-name" type='hidden' name='li_0_name'>
        <input type='hidden' name='purchase_step' value='payment-method' >
        <div class="form-group">
            <div id="infoPanel" class="alert alert-info col-md-10 col-xs-10">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 col-xs-4">
                <label for=orders-dropdown" class="control-label">Alege Comanda:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <select id="orders-dropdown" name="li_0_product_id" class="form-control">
                    <option value="-1">Alege Comanda</option>
                    <#list ordersList as order>
                        <option  value="${order.orderId}" name="li_0_product_id"> ${order.subject}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 col-xs-4">
                <label for="price" class="control-label">Suma:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <input id="price"  class="form-control" type="number" name="li_0_price" required  oninvalid="this.setCustomValidity('Introduceti o valoare valida.')"  oninput="setCustomValidity('')">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 col-xs-4">
                <label for="card-holder-name" class="control-label">Titular Cardului:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <input id="card-holder-name" class="form-control" type="text" name="card_holder_name"  oninvalid="this.setCustomValidity('Introduceti o valoare valida.')"  oninput="setCustomValidity('')" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 col-xs-4">
                <label for="address" class="control-label">Adresa:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <input id="address" type="text" class="form-control" name="street_address" oninvalid="this.setCustomValidity('Introduceti o valoare valida.')"  oninput="setCustomValidity('')" required >
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 col-xs-4">
                <label for="city" class="control-label">Oras:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <input type="text" class="form-control" name="city" required   oninvalid="this.setCustomValidity('Introduceti o valoare valida.')"  oninput="setCustomValidity('')">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 col-xs-4">
                <label for="zipCode" class="control-label">Cod postal:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <input id="zipCode" class="form-control" type="text" name="zip" required   oninvalid="this.setCustomValidity('Introduceti o valoare valida.')"  oninput="setCustomValidity('')">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 col-xs-4">
                <label for="mail" class="control-label">Adresa de mail:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <input id="mail" class="form-control" type="email" name="email" required  oninvalid="this.setCustomValidity('Introduceti o valoare valida.')"  oninput="setCustomValidity('')">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 col-xs-4">
                <label for="phone" class="control-label">Numer de telefon:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <input id=phone" class="form-control" type="tel" name="phone" required oninvalid="this.setCustomValidity('Introduceti o valoare valida.')"  oninput="setCustomValidity('')">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-offset-7 col-xs-offset-7 col-md-4 col-xs-4">
                <input id="checkout-btn" type="submit" class="btn btn-default" name="submit" value="Checkout">
            </div>
        </div>
    </form>
    <#else>
    <div style="margin-left: 25%">Nu a fost gasita nici o comanda pentru care sa efectuati plata.</div>
    </#if>
</#macro>