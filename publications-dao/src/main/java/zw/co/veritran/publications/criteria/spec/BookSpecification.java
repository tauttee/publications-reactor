package zw.co.veritran.publications.criteria.spec;

import org.springframework.data.jpa.domain.Specification;
import zw.co.veritran.publications.criteria.builder.BookCriteriaBuilder;
import zw.co.veritran.publications.model.Book;
import zw.co.veritran.publications.utils.pojo.BookWrapper;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class BookSpecification {

    private BookSpecification() {
        super();
    }

    public static Specification<Book> filterByWrapper(final BookWrapper wrapper) {
        return (fromRoot, criteriaDefinition, criteriaBuilder) -> BookCriteriaBuilder.getInstance().buildConjunctionPredicate(fromRoot, criteriaBuilder, wrapper);
    }
}
