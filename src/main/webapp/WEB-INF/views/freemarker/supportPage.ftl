<!DOCTYPE html>
<#assign userRole = "${userRole}">
<html>
<head>
    <#include "*/includes.ftl">
    <#import "*/components/navbar.ftl" as navbarRenderer/>
</head>
<body>
    <input id="userRole" type="hidden" class="hidden" value="${userRole}" />
    <@navbarRenderer.renderNavbar userRole="${userRole}" activePage="support"/>

    <#if mailSent?? && mailSent?string('true','false') == 'true'>
        <h2 class="col-md-5">Mailul dumneavoastra a fost trimis cu success<h2>
    <#else>
    <div class="container">
        <div class="supportPageContainer">
            <div class="panel-group" style="text-align: center;">
                <div class="h2" >Contactati un administrator</div>
            </div>
            <div class="adminMessageContainer">
                <div class="col-md-12">
                    <form role="form" class="adminMessageForm form-horizontal" method="post" action="./support/sendMessageToAdmin">
                        <div class="form-group">
                            <div class="col-md-2">
                                <label for="subject" class="control-label">Subiect:</label>
                            </div>
                            <div class="col-md-8">
                                <input type="text" id="subject" class="form-control" max="100" required name="subject"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-2">
                                <label for="message" class="control-label">Mesaj:</label>
                            </div>
                            <div class="col-md-8">
                                <textarea class="form-control" name="content" maxlength="300" required></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <button type="submit" class="btn btn-primary">Trimite</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </#if>
</body>
</html>