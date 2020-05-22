<#include "security.ftl">

<div class="card-columns">
    <#list apps as app>
        <div class="card my-3">

            <div class="m-2">
                <span>${app.modelpc}</span><br/>
                <i>${app.reason}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-apps/${app.author.id}">${app.authorName}</a>
                <#if app.author.id == currentUserId>
                    <a class="btn btn-primary" href="/user-apps/${app.author.id}?app=${app.id}">
                        Edit
                    </a>
                 <a class="btn btn-primary" href="/main-delete/${app.author.id}?app=${app.id}">
                     Delete
                 </a>
                </#if>
            </div>
        </div>
    <#else>
        No app
    </#list>
</div>
