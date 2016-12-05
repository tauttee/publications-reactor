package zw.co.veritran.publications.repository.author.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.veritran.publications.model.Author;
import zw.co.veritran.publications.model.Book;

import java.util.List;

/**
 * Created by tyamakura on 29/11/2016.
 */

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author>{

    List<Author> findAllAuthors();
}
