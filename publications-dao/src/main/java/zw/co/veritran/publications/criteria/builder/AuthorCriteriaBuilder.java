package zw.co.veritran.publications.criteria.builder;

import org.apache.commons.lang3.StringUtils;
import zw.co.veritran.publications.criteria.utils.CriteriaConstants;
import zw.co.veritran.publications.criteria.utils.CriteriaUtils;
import zw.co.veritran.publications.model.Author;
import zw.co.veritran.publications.utils.constants.SystemConstants;
import zw.co.veritran.publications.utils.dates.DateUtils;
import zw.co.veritran.publications.utils.pojo.AuthorWrapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class AuthorCriteriaBuilder {

    private static AuthorCriteriaBuilder instance;

    private final CriteriaUtils<Author> criteriaUtils = new CriteriaUtils<>();

    public static AuthorCriteriaBuilder getInstance() {
        if (instance == null) {
            instance = new AuthorCriteriaBuilder();
        }
        return instance;
    }

    public Predicate buildConjunctionPredicate(final Root<Author> root, final CriteriaBuilder criteriaBuilder, final AuthorWrapper wrapper) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (wrapper == null) {
            return buildAllRecordsConjunctionPredicate(predicate, root, criteriaBuilder);
        }
        predicate = buildEntitySpecificConjunctionPredicate(predicate, root, criteriaBuilder, wrapper);
        predicate = buildEntityDatesConjunctionPredicate(predicate, root, criteriaBuilder, wrapper);
        return buildAllRecordsConjunctionPredicate(predicate, root, criteriaBuilder);
    }

    private Predicate buildAllRecordsConjunctionPredicate(final Predicate conjunctionPredicate, final Root<Author> fromRoot, final CriteriaBuilder criteriaBuilder) {
        return criteriaUtils.buildConjunctionPredicate(conjunctionPredicate, criteriaBuilder,
                criteriaBuilder.notEqual(fromRoot.get("status"), SystemConstants.STATUS_DELETED), SystemConstants.STATUS_DELETED);
    }

    private Predicate buildEntitySpecificConjunctionPredicate(final Predicate conjunctionPredicate, final Root<Author> fromRoot, final CriteriaBuilder criteriaBuilder, AuthorWrapper wrapper) {
        Predicate authorConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(conjunctionPredicate, criteriaBuilder,
                criteriaBuilder.like(criteriaBuilder.upper(fromRoot.get("firstName")),
                        StringUtils.isNotEmpty(wrapper.getFirstName()) ? criteriaUtils.addLikeWildCards(wrapper.getFirstName().toUpperCase()) : wrapper.getFirstName()), wrapper.getFirstName());

        authorConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(authorConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.like(criteriaBuilder.upper(fromRoot.get("lastName")),
                        StringUtils.isNotEmpty(wrapper.getLastName()) ? criteriaUtils.addLikeWildCards(wrapper.getLastName().toUpperCase()) : wrapper.getLastName()), wrapper.getLastName());

        authorConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(authorConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.like(criteriaBuilder.upper(fromRoot.get("publisherName")),
                        StringUtils.isNotEmpty(wrapper.getPublisherName()) ? criteriaUtils.addLikeWildCards(wrapper.getPublisherName().toUpperCase()) : wrapper.getPublisherName()), wrapper.getPublisherName());

        authorConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(authorConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.equal(fromRoot.get("id"), wrapper.getId()), wrapper.getId());

        return criteriaUtils.buildConjunctionPredicate(authorConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.equal(fromRoot.get("status"), wrapper.getStatus()), wrapper.getStatus());




    }


    private Predicate buildEntityDatesConjunctionPredicate(final Predicate conjunctionPredicate, final Root<Author> fromRoot, CriteriaBuilder criteriaBuilder, AuthorWrapper wrapper) {
        if(wrapper.getDateCreated() != null) {
            wrapper.setFromDate(wrapper.getDateCreated());
            wrapper.setToDate(wrapper.getDateCreated());
        }
        final Predicate datesConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(conjunctionPredicate, criteriaBuilder,
                criteriaBuilder.greaterThanOrEqualTo(fromRoot.get(CriteriaConstants.CREATION_DATE), DateUtils.getStartOfDay(wrapper.getFromDate())), wrapper.getFromDate());

        return criteriaUtils.buildConjunctionPredicate(datesConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.lessThanOrEqualTo(fromRoot.get(CriteriaConstants.CREATION_DATE), DateUtils.getEndOfDay(wrapper.getToDate())), wrapper.getToDate());

    }


}
