package zw.co.veritran.publications.criteria.builder;

import org.apache.commons.lang3.StringUtils;
import zw.co.veritran.publications.criteria.utils.CriteriaConstants;
import zw.co.veritran.publications.criteria.utils.CriteriaUtils;
import zw.co.veritran.publications.model.Book;
import zw.co.veritran.publications.utils.constants.SystemConstants;
import zw.co.veritran.publications.utils.dates.DateUtils;
import zw.co.veritran.publications.utils.pojo.BookWrapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class BookCriteriaBuilder {

    private static BookCriteriaBuilder instance;
    private final CriteriaUtils<Book> criteriaUtils = new CriteriaUtils<>();

    public static BookCriteriaBuilder getInstance() {
        if (instance == null) {
            instance = new BookCriteriaBuilder();
        }
        return instance;
    }

    public Predicate buildConjunctionPredicate(final Root<Book> root, final CriteriaBuilder criteriaBuilder, final BookWrapper wrapper) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (wrapper == null) {
            return buildAllRecordsConjunctionPredicate(predicate, root, criteriaBuilder);
        }
        predicate = buildAuthorSpecificConjunctionPredicate(predicate, root, criteriaBuilder, wrapper);
        predicate = buildEntityDatesConjunctionPredicate(predicate, root, criteriaBuilder, wrapper);
        predicate = buildBookSpecificConjunctionPredicate(predicate, root, criteriaBuilder, wrapper);
        return buildAllRecordsConjunctionPredicate(predicate, root, criteriaBuilder);
    }

    private Predicate buildAllRecordsConjunctionPredicate(final Predicate conjunctionPredicate, final Root<Book> fromRoot, final CriteriaBuilder criteriaBuilder) {
        return criteriaUtils.buildConjunctionPredicate(conjunctionPredicate, criteriaBuilder,
                criteriaBuilder.notEqual(fromRoot.get("status"), SystemConstants.STATUS_DELETED), SystemConstants.STATUS_DELETED);
    }

    private Predicate buildAuthorSpecificConjunctionPredicate(final Predicate conjunctionPredicate, final Root<Book> fromRoot, final CriteriaBuilder criteriaBuilder, BookWrapper wrapper) {
        Predicate authorConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(conjunctionPredicate, criteriaBuilder,
                criteriaBuilder.like(criteriaBuilder.upper(fromRoot.get(CriteriaConstants.AUTHOR_ROOT).get("firstName")),
                        StringUtils.isNotEmpty(wrapper.getAuthorFirstName()) ? criteriaUtils.addLikeWildCards(wrapper.getAuthorFirstName().toUpperCase()) : wrapper.getAuthorFirstName()), wrapper.getAuthorFirstName());

        authorConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(authorConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.like(criteriaBuilder.upper(fromRoot.get(CriteriaConstants.AUTHOR_ROOT).get("lastName")),
                        StringUtils.isNotEmpty(wrapper.getAuthorLastName()) ? criteriaUtils.addLikeWildCards(wrapper.getAuthorLastName().toUpperCase()) : wrapper.getAuthorLastName()), wrapper.getAuthorLastName());

        return criteriaUtils.buildConjunctionPredicate(authorConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.like(criteriaBuilder.upper(fromRoot.get(CriteriaConstants.AUTHOR_ROOT).get("publisherName")),
                        StringUtils.isNotEmpty(wrapper.getAuthorPublisherName()) ? criteriaUtils.addLikeWildCards(wrapper.getAuthorPublisherName().toUpperCase()) : wrapper.getAuthorPublisherName()), wrapper.getAuthorPublisherName());


    }

    private Predicate buildBookSpecificConjunctionPredicate(final Predicate conjunctionPredicate, final Root<Book> fromRoot, final CriteriaBuilder criteriaBuilder, BookWrapper wrapper) {
        Predicate bookConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(conjunctionPredicate, criteriaBuilder,
                criteriaBuilder.like(criteriaBuilder.upper(fromRoot.get("title")),
                        StringUtils.isNotEmpty(wrapper.getTitle()) ? criteriaUtils.addLikeWildCards(wrapper.getTitle().toUpperCase()) : wrapper.getTitle()), wrapper.getTitle());

        bookConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(bookConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.like(criteriaBuilder.upper(fromRoot.get("synopsis")),
                        StringUtils.isNotEmpty(wrapper.getSynopsis()) ? criteriaUtils.addLikeWildCards(wrapper.getSynopsis().toUpperCase()) : wrapper.getSynopsis()), wrapper.getSynopsis());

        bookConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(bookConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.equal(fromRoot.get("id"), wrapper.getId()), wrapper.getId());

         return criteriaUtils.buildConjunctionPredicate(bookConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.equal(fromRoot.get("status"), wrapper.getStatus()), wrapper.getStatus());

    }


    private Predicate buildEntityDatesConjunctionPredicate(final Predicate conjunctionPredicate, Root<Book> fromRoot, CriteriaBuilder criteriaBuilder, BookWrapper wrapper) {
        if(wrapper.getDateCreated() != null) {
            wrapper.setFromDate(wrapper.getDateCreated());
            wrapper.setToDate(wrapper.getDateCreated());
        }
        Predicate datesConjunctionPredicate = criteriaUtils.buildConjunctionPredicate(conjunctionPredicate, criteriaBuilder,
                criteriaBuilder.greaterThanOrEqualTo(fromRoot.get(CriteriaConstants.CREATION_DATE), DateUtils.getStartOfDay(wrapper.getFromDate())), wrapper.getFromDate());

        return criteriaUtils.buildConjunctionPredicate(datesConjunctionPredicate, criteriaBuilder,
                criteriaBuilder.lessThanOrEqualTo(fromRoot.get(CriteriaConstants.CREATION_DATE), DateUtils.getEndOfDay(wrapper.getToDate())), wrapper.getToDate());

    }


}
