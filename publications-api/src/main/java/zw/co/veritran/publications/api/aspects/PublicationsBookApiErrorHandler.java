package zw.co.veritran.publications.api.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import zw.co.veritran.publications.api.rest.messages.BookInfo;
import zw.co.veritran.publications.api.rest.messages.BookListResponse;
import zw.co.veritran.publications.api.rest.messages.BookResponse;
import zw.co.veritran.publications.utils.constants.MessagesI8NCodes;
import zw.co.veritran.publications.utils.enums.ResponseCode;
import zw.co.veritran.publications.utils.exceptions.PublicationsException;
import zw.co.veritran.publications.utils.pojo.BookWrapper;

import java.security.Principal;
import java.util.Locale;

/**
 * Created by tyamakura on 30/11/2016.
 */
@Component
@Aspect
public class PublicationsBookApiErrorHandler {

    @Autowired
    private MessageSource messageSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicationsBookApiErrorHandler.class);

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.findBookById(..)) and args(id, locale, principal)")
    public BookResponse findBookById(final ProceedingJoinPoint joinPoint, final Long id,
                                         final String locale,
                                         final Principal principal) {
        BookResponse bookResponse = new BookResponse();
        try {
            LOGGER.info("Find Book By ID Request :: ID : {}, Username : {}, Locale : {}",
                    id, principal.getName(), locale);
            bookResponse = (BookResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on find book by id : ", pe);
            bookResponse.setResponseCode(pe.getResponseCode().getCode());
            bookResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on find book by id : ", throwable);
            bookResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return bookResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.deleteBook(..)) and args(id, locale, principal)")
    public BookResponse deleteBook(final ProceedingJoinPoint joinPoint, final Long id,
                                         final String locale,
                                         final Principal principal) {
        BookResponse bookResponse = new BookResponse();
        try {
            LOGGER.info("Delete Book Request :: ID : {}, Username : {}, Locale : {}",
                    id, principal.getName(), locale);
            bookResponse = (BookResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on delete book : ", pe);
            bookResponse.setResponseCode(pe.getResponseCode().getCode());
            bookResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on delete book : ", throwable);
            bookResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return bookResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.createBook(..)) and args(request, locale, principal)")
    public BookResponse createBook(final ProceedingJoinPoint joinPoint, final BookInfo request,
                                       final String locale,
                                       final Principal principal) {
        BookResponse bookResponse = new BookResponse();
        try {
            final BookInfo bookInfo = getNonNullBookInfo(request);
            LOGGER.info("Create Book Request :: Username : {}, Locale : {}, " +
                    "Title : {}, First Name : {}, Last Name : {}, Publish Date : {}, Status : {}",
                    principal.getName(), locale,
                    bookInfo.getTitle(), bookInfo.getAuthor() != null ? bookInfo.getAuthor().getFirstName() : "", bookInfo.getStatus(), bookInfo.getPublishDate(), bookInfo.getStatus());
            bookResponse = (BookResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on create book : ", pe);
            bookResponse.setResponseCode(pe.getResponseCode().getCode());
            bookResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on create book : ", throwable);
            bookResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return bookResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.updateBook(..)) and args(id, request, locale, principal)")
    public BookResponse updateBook(final ProceedingJoinPoint joinPoint, final Long id, final BookInfo request,
                                       final String locale,
                                       final Principal principal) {

        BookResponse bookResponse = new BookResponse();
        try {
            final BookInfo bookInfo = getNonNullBookInfo(request);
            LOGGER.info("Edit Book Request :: ID : {}, Username : {}, Locale : {}, " +
                            "Title : {}, First Name : {}, Last Name : {}, Publish Date : {}, Status : {}",
                    id, principal.getName(), locale,
                    bookInfo.getAuthor() != null ? bookInfo.getAuthor().getFirstName() : "", bookInfo.getStatus(), bookInfo.getPublishDate(), bookInfo.getStatus());
            bookResponse = (BookResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on edit book : ", pe);
            bookResponse.setResponseCode(pe.getResponseCode().getCode());
            bookResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on edit book : ", throwable);
            bookResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return bookResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.findAllBooks(..)) and args(locale, principal)")
    public BookListResponse findAllBooks(final ProceedingJoinPoint joinPoint,
                                        final String locale,
                                        final Principal principal) {

        BookListResponse bookResponse = new BookListResponse();
        try {
            LOGGER.info("Find All Books Request :: Username : {}, Locale : {}, ",
                    principal.getName(), locale);
            bookResponse = (BookListResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on Find All Books : ", pe);
            bookResponse.setResponseCode(pe.getResponseCode().getCode());
            bookResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on Find All Books : ", throwable);
            bookResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return bookResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.searchBooks(..)) and args(wrapper, locale, principal)")
    public BookListResponse searchBooks(final ProceedingJoinPoint joinPoint, final BookWrapper wrapper,
                                       final String locale,
                                       final Principal principal) {

        BookListResponse bookResponse = new BookListResponse();
        try {
            final BookWrapper bookWrapper = getNonNullBookWrapper(wrapper);
            LOGGER.info("Search Book Request :: Username : {}, Locale : {}, " +
                            "ID : {}, Title : {}, Synopsis : {}, Publish Date : {}, " +
                            "First Name : {}, Last Name : {}, " +
                            "Publisher : {}, Status : {}, " +
                            "From Date : {}, To Date : {}",
                    principal.getName(), locale,
                    bookWrapper.getId(), bookWrapper.getTitle(), bookWrapper.getSynopsis(), bookWrapper.getPublishDate(),
                    bookWrapper.getAuthorFirstName(), bookWrapper.getStatus(),
                    bookWrapper.getAuthorPublisherName(), bookWrapper.getStatus(),
                    bookWrapper.getFromDate(), bookWrapper.getToDate());
            bookResponse = (BookListResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on search book : ", pe);
            bookResponse.setResponseCode(pe.getResponseCode().getCode());
            bookResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on search book : ", throwable);
            bookResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return bookResponse;
    }



    private static BookInfo getNonNullBookInfo(final BookInfo bookInfo) {
        return bookInfo != null ? bookInfo : new BookInfo();
    }

    private static BookWrapper getNonNullBookWrapper(final BookWrapper bookWrapper) {
        return bookWrapper != null ? bookWrapper : new BookWrapper();
    }

}
