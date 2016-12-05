package zw.co.veritran.publications.business.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.veritran.publications.business.services.api.PublicationsManagementService;
import zw.co.veritran.publications.criteria.spec.AuthorSpecification;
import zw.co.veritran.publications.criteria.spec.BookSpecification;
import zw.co.veritran.publications.model.Author;
import zw.co.veritran.publications.model.Book;
import zw.co.veritran.publications.repository.author.api.AuthorRepository;
import zw.co.veritran.publications.repository.book.api.BookRepository;
import zw.co.veritran.publications.utils.constants.MessagesI8NCodes;
import zw.co.veritran.publications.utils.constants.SystemConstants;
import zw.co.veritran.publications.utils.enums.ResponseCode;
import zw.co.veritran.publications.utils.exceptions.PublicationsException;
import zw.co.veritran.publications.utils.pojo.AuthorWrapper;
import zw.co.veritran.publications.utils.pojo.BookWrapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

/**
 * Created by tyamakura on 29/11/2016.
 */
@Service
@Transactional
public class PublicationsManagementServiceImpl implements PublicationsManagementService {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private AuthorRepository authorRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Author createAuthor(final Author author, final Locale locale, final String username) {
        final boolean isIdNotNull = author.getId() != null;
        if(isIdNotNull) {
            final Author existingAuthor = findAuthorById(author.getId());
            final boolean isAuthorExisting = existingAuthor != null;
            if(isAuthorExisting) {
                throw new PublicationsException(ResponseCode.DUPLICATE_REQUEST, messageSource.getMessage(MessagesI8NCodes.AUTHOR_EXISTS, new Object []{}, locale));
            }
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(final Author author, final Locale locale, final String username) {
        final Author existingAuthor = findAuthorById(author.getId());
        final boolean isAuthorNotExisting = existingAuthor == null;
        if(isAuthorNotExisting) {
            throw new PublicationsException(ResponseCode.NOT_FOUND, messageSource.getMessage(MessagesI8NCodes.AUTHOR_NOT_FOUND, new Object []{}, locale));
        }
        final boolean isStatusSet = StringUtils.isNotEmpty(author.getStatus());
        if(isStatusSet) {
            existingAuthor.setStatus(author.getStatus());
        }
        existingAuthor.setFirstName(author.getFirstName());
        existingAuthor.setLastName(author.getLastName());
        existingAuthor.setPublisherName(author.getPublisherName());
        return authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteAuthor(final Long id, final Locale locale, final String username) {
        final Author existingAuthor = findAuthorById(id);
        final boolean isAuthorNotExisting = existingAuthor == null;
        if(isAuthorNotExisting) {
            throw new PublicationsException(ResponseCode.NOT_FOUND, messageSource.getMessage(MessagesI8NCodes.AUTHOR_NOT_FOUND, new Object []{}, locale));
        }
        existingAuthor.setStatus(SystemConstants.STATUS_DELETED);
        authorRepository.save(existingAuthor);
    }

    @Override
    @Transactional(readOnly = true)
    public Author findAuthorById(final Long id) {
        final Author author = authorRepository.findOne(id);
        final boolean isDeleted = author != null && SystemConstants.STATUS_DELETED.equalsIgnoreCase(author.getStatus());
        if(isDeleted) {
            return null;
        }
        return author;
    }

    @Override
    public List<Author> findAllAuthors(Locale locale, String username) {
        return authorRepository.findAllAuthors();

    }

    @Override
    public List<Author> findByWrapper(AuthorWrapper authorWrapper, Locale locale, String username) {
        return authorRepository.findAll(AuthorSpecification.filterByWrapper(authorWrapper));

    }

    @Override
    public Book createBook(final Book book, final Locale locale, final String username) {
        final boolean isIdNotNull = book.getId() != null;
        if(isIdNotNull) {
            final Book existingBook = findBookById(book.getId());
            final boolean isBookExisting = existingBook != null;
            if(isBookExisting) {
                throw new PublicationsException(ResponseCode.DUPLICATE_REQUEST, messageSource.getMessage(MessagesI8NCodes.BOOK_EXISTS, new Object []{}, locale));
            }
        }
        addAuthor(book, book.getAuthor());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(final Book book, final Locale locale, final String username) {
        final Book existingBook = findBookById(book.getId());
        final boolean isBookNotExisting = existingBook == null;
        if(isBookNotExisting) {
            throw new PublicationsException(ResponseCode.NOT_FOUND, messageSource.getMessage(MessagesI8NCodes.BOOK_NOT_FOUND, new Object []{}, locale));
        }
        final boolean isStatusSet = StringUtils.isNotEmpty(book.getStatus());
        if(isStatusSet) {
            existingBook.setStatus(book.getStatus());
        }

        final Author newAuthor = book.getAuthor();
        addAuthor(existingBook, newAuthor);
        existingBook.setTitle(book.getTitle());
        existingBook.setSynopsis(book.getSynopsis());
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(final Long id, final Locale locale, final String username) {
        final Book existingBook = findBookById(id);
        final boolean isBookNotExisting = existingBook == null;
        if(isBookNotExisting) {
            throw new PublicationsException(ResponseCode.NOT_FOUND, messageSource.getMessage(MessagesI8NCodes.BOOK_NOT_FOUND, new Object []{}, locale));
        }
        existingBook.setStatus(SystemConstants.STATUS_DELETED);
        bookRepository.save(existingBook);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findBookById(final Long id) {
        final Book book = bookRepository.findOne(id);
        final boolean isDeleted = book != null && SystemConstants.STATUS_DELETED.equalsIgnoreCase(book.getStatus());
        if(isDeleted) {
            return null;
        }
        return book;
    }

    @Override
    public List<Book> findAllBooks(Locale locale, String username) {
        return bookRepository.findAllBooks();

    }

    @Override
    public List<Book> findByWrapper(BookWrapper bookWrapper, Locale locale, String username) {
        return bookRepository.findAll(BookSpecification.filterByWrapper(bookWrapper));

    }

    private void addAuthor(final Book book, final Author newAuthor) {
        final boolean isAuthorIdSupplied = newAuthor == null ? false : newAuthor.getId() != null;
        if(isAuthorIdSupplied) {
            final Author existingAuthor = findAuthorById(newAuthor.getId());
            final boolean isAuthorExisting = existingAuthor != null;
            if(isAuthorExisting) {
                book.setAuthor(existingAuthor);
            }
        }
    }
}
