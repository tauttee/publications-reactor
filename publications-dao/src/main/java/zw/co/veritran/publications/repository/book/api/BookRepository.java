package zw.co.veritran.publications.repository.book.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.veritran.publications.model.Book;

import java.util.List;

/**
 * Created by tyamakura on 29/11/2016.
 */

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
List<Book> findAllBooks();
}
