package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.entities.WysiwygHtml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Repository class for managing the 'How It Works' page's WYSIWYG editor's HTML.
 * This repository will always update the single element in the 'landingPage' table, that is the reason
 * why a static ID=1 is used.
 * @author dragos.triteanu
 *
 */
@Repository
@Transactional(readOnly = false)
public class LandingPageRepository {
    private static final Logger LOG = LoggerFactory.getLogger(LandingPageRepository.class);

	private static final int ID = 1;

	@Resource
	private HibernateTemplate hibernateTemplate;
    /**
	 * Method for updating the HTML used in the HowItWorks page's WYSIWYG editor.
	 * @param html the new HTML as string.
	 */
	public void updateWysiwygHtml(String html){
        WysiwygHtml wysiwygHtml = hibernateTemplate.get(WysiwygHtml.class, ID);
        wysiwygHtml.setHtml(html);
        hibernateTemplate.update(wysiwygHtml);
	}

	public String getHTMLForWysiwyg(){
        String html = "";
        try{
            WysiwygHtml wysiwygHtml = hibernateTemplate.get(WysiwygHtml.class, ID);
            html = wysiwygHtml.getHtml();
		}catch(Exception e){
			LOG.info("No HTML found for wysiwyg.Returning empty html");
		}
		return html;
	}
	
}
