<html>
    <head>
    <#include "*/includes.ftl">
    </head>

    <body>
        <input id="userRole" type="hidden" value="${userRole!'sdasd'}" />

        <#if myVal?string("Da","Nu") == "Da">
            My VAL este = ${myVal?string}
        <#else>
            My VAL este NULL .
        </#if>

        <#list myList as someString>
            <p>${someString}</p>
        </#list>

        <div>
            <form class="form-horizontal" method="post" action="./categories">
                <div class="form-group">
                    <div class="col-md-2">
                        Ceva
                    </div>
                    <div class="col-md-6">
                        <input type="text" name="name" class="form-control"/>
                    </div>
                    <div class="col-md-4">
                        <button type="submit" >BOOM</button>
                    </div>
                </div>
            </form>
        </div>

    </body>


</html>