
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    App editor
</a>
<div class="collapse <#if app??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if app??>${app.modelpc}</#if>" name="modelpc" placeholder="Введите модель" />
                <#if modelpcError??>
                    <div class="invalid-feedback">
                        ${modelpcError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if app??>${app.reason}</#if>" name="reason" placeholder="Введите причину поломки">
                <#if reasonError??>
                    <div class="invalid-feedback">
                        ${reasonError}
                    </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if app??>${app.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save app</button>
            </div>
        </form>
    </div>
</div>

