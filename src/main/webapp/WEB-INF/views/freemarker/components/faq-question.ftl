<#macro renderQuestion faqId faqQuestion faqAnswer> 
	<input type="hidden" class="hidden" value="${faqId}" />
	<div class="panel panel-default">
	    <div class="panel-heading">
	        <h4 class="panel-title">
	            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse${faqId}">${faqQuestion}</a>
	        </h4>
	    </div>
	    <div id="collapse${faqId}" class="panel-collapse collapse">
	        <div class="panel-body">
	            ${faqAnswer}
	        </div>
	    </div>
	</div>
</#macro>

<#macro renderTable questionsAndAnswers>
	<table class="table faqTable">
		<thead>
			<tr>
				<th class="table-header">Id</th>
			    <th class="table-header">Question</th>
			    <th class="table-header">Answer</th>
			    <th class="table-header">Remove</th>
			</tr>
		</thead>
		<tbody>
			<#if questionsAndAnswers??>
				<#list questionsAndAnswers as qaa>
					<tr class="tableRow">
						<td class="qaaId">${qaa.id}</td>
						<td class="qaaQuestion">${qaa.question}</td>
						<td class="qaaAnswer">${qaa.answer}</td>
						<td>
							<form class="delete-qaa-${qaa.id}" action="./faq/delete" method="POST">
								<input type="hidden" name="deleteId" value="${qaa.id}" />
								<button id="delete${qaa.id}" type="submit" class="btn btn-danger delete-button">Remove</button>
							</form>
						</td>
					</tr>
				</#list>
				<form class="add-qaa-form" action="./faq/add" method="POST">
					<tr class="tableRow">
						<td></td>
						<td><input type="text" name="question" placeholder="Question" class="qaaQuestion" required/></td>
						<td><input type="text" name="answer" placeholder="Answer" class="qaaAnswer" required/></td>
						<td><button id="newQAABtn" type="submit" class="btn btn-primary">Add Question</button></td>
					</tr>				
				</form>
			<#else>
				There should be questions and answers here :(
			</#if>
		</tbody>
	</table>
</#macro>