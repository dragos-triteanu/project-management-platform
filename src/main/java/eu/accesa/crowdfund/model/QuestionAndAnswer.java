package eu.accesa.crowdfund.model;

/**
 * Model class for representing a question that has an answer, in the 'FAQ' page.
 * @author dragos.triteanu
 *
 */
public class QuestionAndAnswer {
	/**
	 * The unique id of the question.
	 */
	private String id;

	/**
	 * The question, represented as a string.
	 */
	private String question;
	
	/**
	 * Te answer to the question, represented as a string.
	 */
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
