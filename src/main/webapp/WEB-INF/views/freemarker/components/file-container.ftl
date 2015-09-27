<div class="fileContainer">
	<div class="col-md-2 col-xs-2">
	    <label for="consultant"class="control-label">CV:</label>
	</div>
	<div class="col-md-3 col-xs-3">
	    <#if consultant?has_content>
	        <#if consultant.cvURL??>
	        	<div class="fileContainer">
	        		<a href="${consultant.cvURL}"> ${consultant.firstName} ${consultant.lastName} </a>
	        		<div class="fileContainerBackup hidden">
						<div class="col-md-2 col-xs-2">
						    <label for="consultant"class="control-label">CV:</label>
						</div>
						<div class="col-md-3 col-xs-3">
							<input type="file" name="cvFile" class="form-control">						
						</div>					
	        		</div>
	        	</div>
	    	<#else>
	    		<input type="file" name="cvFile" class="form-control">
	    	</#if>
	    <#else>
	        <input type="file" name="cvFile" class="form-control">
	    </#if>
	</div>
	<div class="col-md-3 col-xs-3 uploadNewCvButtonContainer">
		<button class="btn btn-danger uploadNewCVButton" type="button">
			<span class="glyphicon glyphicon-remove"></span>
		</button>
	</div>
</div>