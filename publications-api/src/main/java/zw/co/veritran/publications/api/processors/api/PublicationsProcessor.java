package zw.co.veritran.publications.api.processors.api;

import zw.co.veritran.publications.api.rest.messages.*;
import zw.co.veritran.publications.utils.pojo.AuthorWrapper;
import zw.co.veritran.publications.utils.pojo.BookWrapper;

import java.util.Locale;

/**
 * Created by tyamakura on 30/11/2016.
 */
public interface PublicationsProcessor {
    AuthorResponse findAuthorById(Long id, Locale locale, String username);
    AuthorListResponse findAllAuthors(Locale locale, String username);

    AuthorResponse createAuthor(AuthorInfo author, Locale locale, String username);

    AuthorResponse updateAuthor(AuthorInfo author, Locale locale, String username);

    AuthorResponse deleteAuthor(Long id, Locale locale, String username);

    AuthorListResponse findByWrapper(AuthorWrapper authorWrapper, Locale locale, String username);

    BookResponse findBookById(Long id, Locale locale, String username);

    BookResponse createBook(BookInfo bookInfo, Locale locale, String username);

    BookResponse updateBook(BookInfo bookInfo, Locale locale, String username);

    BookResponse deleteBook(Long id, Locale locale, String username);

    BookListResponse findAllBooks(Locale locale, String username);

    BookListResponse findByWrapper(BookWrapper bookWrapper, Locale locale, String username);
}
