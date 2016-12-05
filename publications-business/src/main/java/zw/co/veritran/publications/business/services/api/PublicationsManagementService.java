package zw.co.veritran.publications.business.services.api;

import zw.co.veritran.publications.model.Author;
import zw.co.veritran.publications.model.Book;
import zw.co.veritran.publications.utils.pojo.AuthorWrapper;
import zw.co.veritran.publications.utils.pojo.BookWrapper;

import java.util.List;
import java.util.Locale;

/**
 * Created by tyamakura on 29/11/2016.
 */
public interface PublicationsManagementService {
    void deleteAuthor(Long id, Locale locale, String username);
    Author findAuthorById(Long id);
    Author createAuthor(Author author, Locale locale, String username);
    Author updateAuthor(Author author, Locale locale, String username);

    List<Author> findAllAuthors(Locale locale, String username);

    List<Author> findByWrapper(AuthorWrapper authorWrapper, Locale locale, String username);

    Book createBook(Book book, Locale locale, String username);
    Book updateBook(Book book, Locale locale, String username);
    void deleteBook(Long id, Locale locale, String username);
    Book findBookById(Long id);

    List<Book> findAllBooks(Locale locale, String username);

    List<Book> findByWrapper(BookWrapper bookWrapper, Locale locale, String username);
}
