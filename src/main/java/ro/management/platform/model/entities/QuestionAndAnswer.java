package ro.management.platform.model.entities;

import javax.persistence.*;

import static ro.management.platform.repository.Queries.DELETE_QAA_BY_ID;

/**
 * Created by dragos.triteanu on 10/31/15.
 */
@Entity
@Table(name="faq")
@NamedQueries({
    @NamedQuery(name = DELETE_QAA_BY_ID , query = "DELETE FROM QuestionAndAnswer WHERE id=:faqId")
})
public class QuestionAndAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer", nullable = false)
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "QuestionAndAnswerEntity{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
