package zw.co.veritran.publications.criteria.spec;

import org.springframework.data.jpa.domain.Specification;

import zw.co.veritran.publications.criteria.builder.AuthorCriteriaBuilder;
import zw.co.veritran.publications.model.Author;
import zw.co.veritran.publications.utils.pojo.AuthorWrapper;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class AuthorSpecification {

    private AuthorSpecification() {
        super();
    }

    public static Specification<Author> filterByWrapper(final AuthorWrapper wrapper) {
        return (fromRoot, criteriaDefinition, criteriaBuilder) -> AuthorCriteriaBuilder.getInstance().buildConjunctionPredicate(fromRoot, criteriaBuilder, wrapper);
    }
}
