package ro.management.platform.repository;

import ro.management.platform.model.entities.WysiwygHtml;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for managing the 'How It Works' page's WYSIWYG editor's HTML.
 * This repository will always update the single element in the 'landingPage' table, that is the reason
 * why a static ID=1 is used.
 *
 * @author dragos.triteanu
 */
@Repository
@Transactional(readOnly = false)
public class LandingPageRepository {
    private static final Logger LOG = LoggerFactory.getLogger(LandingPageRepository.class);

    private static final int ID = 1;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Method for updating the HTML used in the HowItWorks page's WYSIWYG editor.
     *
     * @param html the new HTML as string.
     */
    public void updateWysiwygHtml(String html) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WysiwygHtml.class);
        criteria.add(Restrictions.eq("id", ID));
        WysiwygHtml wysiwygHtml = (WysiwygHtml) criteria.uniqueResult();
        if (wysiwygHtml == null) {
            wysiwygHtml = new WysiwygHtml();
            wysiwygHtml.setId(ID);
        }
        wysiwygHtml.setHtml(html);
        sessionFactory.getCurrentSession().saveOrUpdate(wysiwygHtml);
    }

    public String getHTMLForWysiwyg() {
        String html = "";
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WysiwygHtml.class);
            criteria.add(Restrictions.eq("id", ID));
            WysiwygHtml wysiwygHtml = (WysiwygHtml) criteria.uniqueResult();
            html = wysiwygHtml.getHtml();
        } catch (Exception e) {
            LOG.info("No HTML found for wysiwyg.Returning empty html");
        }
        return html;
    }

}
