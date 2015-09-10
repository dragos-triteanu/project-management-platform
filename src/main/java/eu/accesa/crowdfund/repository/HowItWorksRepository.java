package eu.accesa.crowdfund.repository;

import static eu.accesa.crowdfund.repository.JDBCQueries.RETRIEVE_HTML_FOR_WYSIWYG;
import static eu.accesa.crowdfund.repository.JDBCQueries.UPDATE_WYSIWYG_HTML;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Repository class for managing the 'How It Works' page's WYSIWYG editor's HTML.
 * This repository will always update the single element in the 'HowItWorks' table, that is the reason
 * why a static ID=1 is used.
 * @author dragos.triteanu
 *
 */
@Repository
public class HowItWorksRepository {
	private static final int ID = 1;
	
	@Resource(name="crowdfundingJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Method for updating the HTML used in the HowItWorks page's WYSIWYG editor.
	 * @param html the new HTML as string.
	 */
	public void updateWysiwygHtml(String html){
		jdbcTemplate.update(UPDATE_WYSIWYG_HTML,new Object[]{html,ID});
	}
	
	public String getHTMLForWysiwyg(){
		String html = jdbcTemplate.queryForObject(RETRIEVE_HTML_FOR_WYSIWYG,new Object[]{ID}, String.class);
		return html;
	}
	
}
