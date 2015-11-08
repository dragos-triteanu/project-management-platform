package eu.accesa.platform.repository;

/**
 * Utility class for housing the named queries.
 * @author dragos.triteanu
 *
 */
public class Queries {

    /**
     * {@link eu.accesa.platform.model.entities.Consultant} named queries.
     */
    public static final String UPDATE_CONSULTANT = "updateConsultant";
    public static final String UPDATE_CONSULTANT_WITH_CV = "updateConsultantWithCV";
    public static final String DELETE_CONSULTANT_BY_ID = "deleteConsultant";
    public static final String RETRIEVE_CONSULTANTS_BY_NAME = "retrieveConsultantsByName";
    public static final String RETRIEVE_CONSULTANTS_BY_ADDRESS = "retrieveConsultantsByAddress";

    /**
     * {@link eu.accesa.platform.model.entities.ConsultantSpeciality} named queries.
     */
    public static final String DELETE_CONSULTANT_CATEGORY_BY_ID = "deleteConsultantCategoryById";

    /**
     * {@link eu.accesa.platform.model.entities.Order} named queries.
     */
    public static final String UPDATE_ORDER = "updateOrder";

    /**
     * {@link eu.accesa.platform.model.entities.ConsultantOrder} named queries.
     */
    public static final String DELETE_BID = "deleteBidByConsultantIdAndOrderId";

    /**
     * {@link eu.accesa.platform.model.entities.QuestionAndAnswer} QAA queries.
     */
    public static final String DELETE_QAA_BY_ID = "deleteQaaById";
}
