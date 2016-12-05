package zw.co.veritran.publications.api.rest;

import zw.co.veritran.publications.api.rest.messages.AuthorInfo;
import zw.co.veritran.publications.api.rest.messages.BookInfo;
import zw.co.veritran.publications.model.Author;
import zw.co.veritran.publications.model.Book;

import java.util.stream.Collectors;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class MessageConverterUtility {

    private MessageConverterUtility() {
        super();
    }

    public static Author populate(final AuthorInfo authorInfo) {
        if(authorInfo == null) {
            return null;
        }
        final Author author = new Author();
        author.setId(authorInfo.getId() != null && authorInfo.getId() > 0 ? authorInfo.getId() : null);
        author.setFirstName(authorInfo.getFirstName());
        author.setLastName(authorInfo.getLastName());
        author.setPublisherName(authorInfo.getPublisherName());
        author.setStatus(authorInfo.getStatus());
        return author;
    }

    public static AuthorInfo populate(final Author author) {
        return populate(author, false);
    }

    private static AuthorInfo populate(final Author author, final boolean excludeBooks) {
        if(author == null) {
            return  null;
        }
        final AuthorInfo authorInfo = new AuthorInfo();
        authorInfo.setId(author.getId());
        authorInfo.setFirstName(author.getFirstName());
        authorInfo.setLastName(author.getLastName());
        authorInfo.setPublisherName(author.getPublisherName());
        authorInfo.setStatus(author.getStatus());
        authorInfo.setDateCreated(author.getDateCreated());
        authorInfo.setDateLastUpdated(author.getDateLastUpdated());
        if(author.getBooks() != null && (!excludeBooks)) {
            authorInfo.setBooks(author.getBooks().stream().map(book -> populate(book, true)).collect(Collectors.toList()));
        }
        return authorInfo;
    }

    public static Book populate(final BookInfo bookInfo) {
        if(bookInfo == null) {
            return null;
        }
        final Book book = new Book();
        book.setId(bookInfo.getId() != null && bookInfo.getId() > 0 ? bookInfo.getId() : null);
        book.setTitle(bookInfo.getTitle());
        book.setSynopsis(bookInfo.getSynopsis());
        book.setAuthor(populate(bookInfo.getAuthor()));
        book.setStatus(bookInfo.getStatus());
        book.setPublishDate(bookInfo.getPublishDate());
        book.setDateCreated(bookInfo.getDateCreated());
        book.setDateLastUpdated(bookInfo.getDateLastUpdated());
        return book;
    }

    public static BookInfo populate(final Book book) {
        return populate(book, false);
    }

    private static BookInfo populate(final Book book, final boolean excludeAuthor) {
        if(book == null) {
            return null;
        }
        final BookInfo bookInfo = new BookInfo();
        bookInfo.setId(book.getId());
        bookInfo.setTitle(book.getTitle());
        bookInfo.setSynopsis(book.getSynopsis());
        if(!excludeAuthor) {
            bookInfo.setAuthor(populate(book.getAuthor(), true));
        }
        bookInfo.setStatus(book.getStatus());
        bookInfo.setPublishDate(book.getPublishDate());
        bookInfo.setDateCreated(book.getDateCreated());
        bookInfo.setDateLastUpdated(book.getDateLastUpdated());
        return bookInfo;
    }
}
