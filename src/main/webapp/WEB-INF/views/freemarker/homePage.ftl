<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer />
<#import "*/components/wysiwyg.ftl" as wysiwygRenderer />
<html>
	<head>
	 	<#include "*/includes.ftl">
	 	<link rel="stylesheet" type="text/css" href="./resources/css/wysihtml5/editor.css" />
	 	<link rel="stylesheet" type="text/css" href="./resources/css/wysihtml5/wysihtml5.css" />
	 	<link rel="stylesheet" type="text/css" href="./resources/css/home.css" />
	    <link rel="stylesheet" type="text/css" href="./resources/css/mediaQueries/home-media-queries.css" />
	 	
	</head>
	<body>
		<input id="userRole" type="hidden" class="hidden" value="${userRole}" />
		<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="home" userHasProjects=userHasProjects />
			<#if userRole == 'ADMINISTRATOR'>
				<div class="container">
					<div class="row">
						<div class="col-md-12 col-xs-12" >
							 <div class="wysiContainer">
							 	${htmlForWysiwyg}
							 </div>
						</div>
					</div>
                    <div class="row">
                        <div class="col-md-12 col-xs-12">
                            <select id="selectWhatUsersSee" name="whatUsersSee">
                                <option value="2" >Ce vad clientii</option>
                                <option value="1">Ce vad consultantii</option>
                            </select>
                        </div>
                    </div>
					<div class="row">
						<div class="col-md-12 col-xs-12 edit-section-button-container">
							<button class="btn btn-primary" id="editSectionButton">Edit This Section</button>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 col-xs-12">
							<form id="home-form" action="./home/updatePage" method="POST" style="display:none;">
                                <input type="hidden" name="whatUsersSee" value="1" id="forUser" />
                                <#if htmlForWysiwygEscaped??>
									<input type="hidden" id="wysiwyg-data" value="${htmlForWysiwygEscaped}" />
								</#if>
								<div class="col-md-12 col-xs-12">
							 		<@wysiwygRenderer.wysihtml5 "newHtml" />
							 	</div>
							 	<br/><input type="submit" class="btn btn-primary" style="margin-top:5px;" value="Update Page">
							 </form>	
						</div>
					</div>
				</div>	
			<#else>
				<div class="container">
					<div class="row">
						<div class="col-md-12 col-xs-12" >
						<div class="wysiContainer">
							 ${htmlForWysiwyg}
						</div>	 
						</div>
					</div>
				</div>
			</#if>
			
			<script src="./resources/script/wysihtml5/wysihtml5-parserRules.js"></script>
			<script src="./resources/script/wysihtml5/wysihtml5-0.3.0.js"></script>
			<script src="./resources/script/home.js"></script>
	</body>
</html>