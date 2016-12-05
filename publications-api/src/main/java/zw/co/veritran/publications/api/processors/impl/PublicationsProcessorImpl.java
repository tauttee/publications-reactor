package zw.co.veritran.publications.api.processors.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.veritran.publications.api.rest.MessageConverterUtility;
import zw.co.veritran.publications.api.rest.messages.*;
import zw.co.veritran.publications.api.validators.api.RequestValidation;
import zw.co.veritran.publications.business.services.api.PublicationsManagementService;
import zw.co.veritran.publications.model.Author;
import zw.co.veritran.publications.model.Book;
import zw.co.veritran.publications.utils.constants.MessagesI8NCodes;
import zw.co.veritran.publications.utils.enums.ResponseCode;
import zw.co.veritran.publications.utils.pojo.AuthorWrapper;
import zw.co.veritran.publications.utils.pojo.BookWrapper;
import zw.co.veritran.publications.utils.pojo.GenericResponse;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by tyamakura on 30/11/2016.
 */
@Service
@Transactional
public class PublicationsProcessorImpl implements zw.co.veritran.publications.api.processors.api.PublicationsProcessor {

    @Autowired
    private PublicationsManagementService publicationsManagementService;

    @Autowired
    private RequestValidation requestValidation;

    @Autowired
    private MessageSource messageSource;

    @Override
    public AuthorResponse findAuthorById(final Long id, final Locale locale, final String username) {
        final AuthorResponse authorResponse = new AuthorResponse();
        final boolean isIdNull = id == null;
        if(isIdNull) {
            authorResponse.setResponseCode(ResponseCode.EMPTY_REQUEST.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.EMPTY_REQUEST, new Object []{}, locale));
            return authorResponse;
        }
        final Author author = publicationsManagementService.findAuthorById(id);
        if(author == null) {
            authorResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.SEARCH_NO_RESULTS, new Object []{}, locale));
            return authorResponse;
        }
        final AuthorInfo authorInfo = MessageConverterUtility.populate(author);
        authorResponse.setAuthor(authorInfo);
        authorResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.SEARCH_SUCCESSFUL, new Object []{}, locale));
        return authorResponse;

    }

    @Override
    public AuthorListResponse findAllAuthors(Locale locale, String username) {
        return populateAuthorSearchResponse(publicationsManagementService.findAllAuthors(locale, username), locale, username);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuthorResponse createAuthor(final AuthorInfo authorInfo, final Locale locale, final String username) {
        final AuthorResponse authorResponse = new AuthorResponse();
        final GenericResponse validationResponse = requestValidation.validate(authorInfo, locale, true);
        final boolean isFailedValidation = validationResponse.getResponseCode() != null;
        if(isFailedValidation) {
            authorResponse.setResponseCode(validationResponse.getResponseCode());
            authorResponse.setMessage(validationResponse.getMessage());
            return authorResponse;
        }
        final Author author = MessageConverterUtility.populate(authorInfo);
        final Author createdAuthor = publicationsManagementService.createAuthor(author, locale, username);
        if(createdAuthor == null) {
            authorResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.AUTHOR_CREATION_FAILED, new Object []{}, locale));
            return authorResponse;
        }
        authorResponse.setAuthor(MessageConverterUtility.populate(createdAuthor));
        authorResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.AUTHOR_CREATED_SUCCESSFULLY, new Object []{createdAuthor.getFirstName(), createdAuthor.getLastName()}, locale));
        return authorResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuthorResponse updateAuthor(final AuthorInfo authorInfo, final Locale locale, final String username) {
        final AuthorResponse authorResponse = new AuthorResponse();
        final GenericResponse validationResponse = requestValidation.validate(authorInfo, locale, false);
        final boolean isFailedValidation = validationResponse.getResponseCode() != null;
        if(isFailedValidation) {
            authorResponse.setResponseCode(validationResponse.getResponseCode());
            authorResponse.setMessage(validationResponse.getMessage());
            return authorResponse;
        }
        final Author author = MessageConverterUtility.populate(authorInfo);
        final Author updatedAuthor = publicationsManagementService.updateAuthor(author, locale, username);
        if(updatedAuthor == null) {
            authorResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.AUTHOR_EDIT_FAILED, new Object []{}, locale));
            return authorResponse;
        }
        authorResponse.setAuthor(MessageConverterUtility.populate(updatedAuthor));
        authorResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.AUTHOR_EDITED_SUCCESSFULLY, new Object []{updatedAuthor.getFirstName(), updatedAuthor.getLastName()}, locale));
        return authorResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AuthorResponse deleteAuthor(final Long id, final Locale locale, final String username) {
        final AuthorResponse authorResponse = new AuthorResponse();
        final boolean isIdNotSupplied = id == null;
        if(isIdNotSupplied) {
            authorResponse.setResponseCode(ResponseCode.EMPTY_REQUEST.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.EMPTY_REQUEST, new Object []{}, locale));
            return authorResponse;
        }
        publicationsManagementService.deleteAuthor(id, locale, username);
        authorResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.AUTHOR_DELETED_SUCCESSFULLY, new Object []{}, locale));
        return authorResponse;
    }

    @Override
    public AuthorListResponse findByWrapper(final AuthorWrapper authorWrapper, final Locale locale, final String username) {
        return populateAuthorSearchResponse(publicationsManagementService.findByWrapper(authorWrapper, locale, username), locale, username);

    }

    private AuthorListResponse populateAuthorSearchResponse(final List<Author> authorList, final Locale locale, final String username) {
        final AuthorListResponse authorListResponse = new AuthorListResponse();
        if(authorList == null || authorList.isEmpty()) {
            authorListResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.SEARCH_NO_RESULTS, new Object []{username}, locale));
        } else {
            authorListResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.SEARCH_SUCCESSFUL, new Object []{username}, locale));
            final List<AuthorInfo> authorInfos = authorList.stream().map(MessageConverterUtility::populate).collect(Collectors.toList());
            authorListResponse.setAuthors(authorInfos);
        }
        authorListResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        return authorListResponse;
    }

    @Override
    public BookResponse findBookById(final Long id, final Locale locale, final String username) {
        final BookResponse bookResponse = new BookResponse();
        final boolean isIdNull = id == null;
        if(isIdNull) {
            bookResponse.setResponseCode(ResponseCode.EMPTY_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.EMPTY_REQUEST, new Object []{}, locale));
            return bookResponse;
        }
        final Book book = publicationsManagementService.findBookById(id);
        if(book == null) {
            bookResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.SEARCH_NO_RESULTS, new Object []{}, locale));
            return bookResponse;
        }
        final BookInfo bookInfo = MessageConverterUtility.populate(book);
        bookResponse.setBook(bookInfo);
        bookResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.SEARCH_SUCCESSFUL, new Object []{}, locale));
        return bookResponse;

    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BookResponse createBook(final BookInfo bookInfo, final Locale locale, final String username) {
        final BookResponse bookResponse = new BookResponse();
        final GenericResponse validationResponse = requestValidation.validate(bookInfo, locale, true);
        final boolean isFailedValidation = validationResponse.getResponseCode() != null;
        if(isFailedValidation) {
            bookResponse.setResponseCode(validationResponse.getResponseCode());
            bookResponse.setMessage(validationResponse.getMessage());
            return bookResponse;
        }
        final Book book = MessageConverterUtility.populate(bookInfo);
        final Book createdBook = publicationsManagementService.createBook(book, locale, username);
        if(createdBook == null) {
            bookResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.BOOK_CREATION_FAILED, new Object []{}, locale));
            return bookResponse;
        }
        bookResponse.setBook(MessageConverterUtility.populate(createdBook));
        bookResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.BOOK_CREATED_SUCCESSFULLY, new Object []{createdBook.getTitle(),
                createdBook.getAuthor().getFirstName(),
                createdBook.getAuthor().getLastName()}, locale));
        return bookResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BookResponse updateBook(final BookInfo bookInfo, final Locale locale, final String username) {
        final BookResponse bookResponse = new BookResponse();
        final GenericResponse validationResponse = requestValidation.validate(bookInfo, locale, false);
        final boolean isFailedValidation = validationResponse.getResponseCode() != null;
        if(isFailedValidation) {
            bookResponse.setResponseCode(validationResponse.getResponseCode());
            bookResponse.setMessage(validationResponse.getMessage());
            return bookResponse;
        }
        final Book book = MessageConverterUtility.populate(bookInfo);
        final Book updatedBook = publicationsManagementService.updateBook(book, locale, username);
        if(updatedBook == null) {
            bookResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.BOOK_EDIT_FAILED, new Object []{}, locale));
            return bookResponse;
        }
        bookResponse.setBook(MessageConverterUtility.populate(updatedBook));
        bookResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.BOOK_EDITED_SUCCESSFULLY, new Object []{updatedBook.getTitle(),
                updatedBook.getAuthor().getFirstName(),
                updatedBook.getAuthor().getLastName()}, locale));
        return bookResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BookResponse deleteBook(final Long id, final Locale locale, final String username) {
        final BookResponse bookResponse = new BookResponse();
        final boolean isIdNotSupplied = id == null;
        if(isIdNotSupplied) {
            bookResponse.setResponseCode(ResponseCode.EMPTY_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.EMPTY_REQUEST, new Object []{}, locale));
            return bookResponse;
        }
        publicationsManagementService.deleteBook(id, locale, username);
        bookResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.BOOK_DELETED_SUCCESSFULLY, new Object []{}, locale));
        return bookResponse;
    }

    @Override
    public BookListResponse findAllBooks(Locale locale, String username) {
        return populateBookSearchResponse(publicationsManagementService.findAllBooks(locale, username), locale, username);
    }

    @Override
    public BookListResponse findByWrapper(final BookWrapper bookWrapper, final Locale locale, final String username) {
        return populateBookSearchResponse(publicationsManagementService.findByWrapper(bookWrapper, locale, username), locale, username);

    }


    private BookListResponse populateBookSearchResponse(final List<Book> bookList, final Locale locale, final String username) {
        final BookListResponse bookListResponse = new BookListResponse();
        if(bookList == null || bookList.isEmpty()) {
            bookListResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.SEARCH_NO_RESULTS, new Object []{username}, locale));
        } else {
            bookListResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.SEARCH_SUCCESSFUL, new Object []{username}, locale));
            final List<BookInfo> bookInfos = bookList.stream().map(MessageConverterUtility::populate).collect(Collectors.toList());
            bookListResponse.setBooks(bookInfos);
        }
        bookListResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
        return bookListResponse;
    }


}
