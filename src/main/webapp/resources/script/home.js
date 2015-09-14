$(document).ready(function(){
	 
	var userRole = $("#userRole").val();
	
	if(userRole == 'ADMINISTRATOR'){
		var editor = new wysihtml5.Editor("textarea", {
		    toolbar:      "toolbar",
		    stylesheets:  "./resources/css/wysihtml5/wysihtml5.css",
		    parserRules:  wysihtml5ParserRules
		  });
		window.editor = editor;
		editor.on("load",function(){
			var $doc = $(editor.composer.doc);
			
			$doc.keyup(function(e){
				var editorText = window.editor.getValue();
				$(".wysiContainer").html(editorText);
			});
		});
		
		$("#editSectionButton").on("click",function(){
			var isFormVisible = $("#how-it-works-form").is(":visible")
			if(!isFormVisible){
				var content = $("#wysiwyg-data").val();
				window.editor.setValue(content);
				$("#how-it-works-form").show();
				this.innerHTML = "Cancel";
			}else{
				$("#how-it-works-form").hide();
				this.innerHTML = "Edit This Section";
			}
		});
	}
});