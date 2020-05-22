<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
    <div class="alert alert-danger" role="alert">
        ${Session.SPRING_SECURITY_LAST_EXCEPTION.app}
    </div>
</#if>
<#if app??>
    <div class="alert alert-${messageType}" role="alert">
        ${app}
    </div>
</#if>
<@l.login "/login" false/>
</@c.page>
