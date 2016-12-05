package zw.co.veritran.publications.api.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import zw.co.veritran.publications.api.rest.messages.AuthorInfo;
import zw.co.veritran.publications.api.rest.messages.AuthorListResponse;
import zw.co.veritran.publications.api.rest.messages.AuthorResponse;
import zw.co.veritran.publications.utils.constants.MessagesI8NCodes;
import zw.co.veritran.publications.utils.enums.ResponseCode;
import zw.co.veritran.publications.utils.exceptions.PublicationsException;
import zw.co.veritran.publications.utils.pojo.AuthorWrapper;

import java.security.Principal;
import java.util.Locale;

/**
 * Created by tyamakura on 30/11/2016.
 */
@Component
@Aspect
public class PublicationsAuthorApiErrorHandler {

    @Autowired
    private MessageSource messageSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicationsAuthorApiErrorHandler.class);

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.findAuthorById(..)) and args(id, locale, principal)")
    public AuthorResponse findAuthorById(final ProceedingJoinPoint joinPoint, final Long id,
                                         final String locale,
                                         final Principal principal) {
        AuthorResponse authorResponse = new AuthorResponse();
        try {
            LOGGER.info("Find Author By ID Request :: ID : {}, Username : {}, Locale : {}",
                    id, principal.getName(), locale);
            authorResponse = (AuthorResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on find author by id : ", pe);
            authorResponse.setResponseCode(pe.getResponseCode().getCode());
            authorResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on find author by id : ", throwable);
            authorResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return authorResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.deleteAuthor(..)) and args(id, locale, principal)")
    public AuthorResponse deleteAuthor(final ProceedingJoinPoint joinPoint, final Long id,
                                         final String locale,
                                         final Principal principal) {
        AuthorResponse authorResponse = new AuthorResponse();
        try {
            LOGGER.info("Delete Author Request :: ID : {}, Username : {}, Locale : {}",
                    id, principal.getName(), locale);
            authorResponse = (AuthorResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on delete author : ", pe);
            authorResponse.setResponseCode(pe.getResponseCode().getCode());
            authorResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on delete author : ", throwable);
            authorResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return authorResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.createAuthor(..)) and args(request, locale, principal)")
    public AuthorResponse createAuthor(final ProceedingJoinPoint joinPoint, final AuthorInfo request,
                                       final String locale,
                                       final Principal principal) {
        AuthorResponse authorResponse = new AuthorResponse();
        try {
            final AuthorInfo authorInfo = getNonNullAuthorInfo(request);
            LOGGER.info("Create Author Request :: Username : {}, Locale : {}, " +
                    "ID : {}, First Name : {}, Last Name : {}, Publisher : {}, Status : {}",
                    principal.getName(), locale,
                    authorInfo.getId(), authorInfo.getFirstName(), authorInfo.getLastName(), authorInfo.getStatus(), authorInfo.getPublisherName(), authorInfo.getStatus());
            authorResponse = (AuthorResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on create author : ", pe);
            authorResponse.setResponseCode(pe.getResponseCode().getCode());
            authorResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on create author : ", throwable);
            authorResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return authorResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.updateAuthor(..)) and args(id, request, locale, principal)")
    public AuthorResponse updateAuthor(final ProceedingJoinPoint joinPoint, final Long id, final AuthorInfo request,
                                       final String locale,
                                       final Principal principal) {

        AuthorResponse authorResponse = new AuthorResponse();
        try {
            final AuthorInfo authorInfo = getNonNullAuthorInfo(request);
            LOGGER.info("Edit Author Request :: ID : {}, Username : {}, Locale : {}, " +
                            "First Name : {}, Last Name : {}, Publisher : {}, Status : {}",
                    id, principal.getName(), locale,
                    authorInfo.getFirstName(), authorInfo.getStatus(), authorInfo.getPublisherName(), authorInfo.getStatus());
            authorResponse = (AuthorResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on edit author : ", pe);
            authorResponse.setResponseCode(pe.getResponseCode().getCode());
            authorResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on edit author : ", throwable);
            authorResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return authorResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.findAllAuthors(..)) and args(locale, principal)")
    public AuthorListResponse findAllAuthors(final ProceedingJoinPoint joinPoint,
                                         final String locale,
                                         final Principal principal) {

        AuthorListResponse bookResponse = new AuthorListResponse();
        try {
            LOGGER.info("Find All Authors Request :: Username : {}, Locale : {}, ",
                    principal.getName(), locale);
            bookResponse = (AuthorListResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on Find All Authors : ", pe);
            bookResponse.setResponseCode(pe.getResponseCode().getCode());
            bookResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on Find All Authors : ", throwable);
            bookResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            bookResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return bookResponse;
    }

    @Around("execution(* zw.co.veritran.publications.api.rest.resources.PublicationsServiceResource.searchAuthors(..)) and args(wrapper, locale, principal)")
    public AuthorListResponse searchAuthors(final ProceedingJoinPoint joinPoint, final AuthorWrapper wrapper,
                                       final String locale,
                                       final Principal principal) {

        AuthorListResponse authorResponse = new AuthorListResponse();
        try {
            final AuthorWrapper authorWrapper = getNonNullAuthorWrapper(wrapper);
            LOGGER.info("Search Author Request :: Username : {}, Locale : {}, " +
                            "ID : {}, First Name : {}, Last Name : {}, " +
                            "Publisher : {}, Status : {}, " +
                            "From Date : {}, To Date : {}",
                    principal.getName(), locale,
                    authorWrapper.getId(), authorWrapper.getFirstName(), authorWrapper.getStatus(),
                    authorWrapper.getPublisherName(), authorWrapper.getStatus(),
                    authorWrapper.getFromDate(), authorWrapper.getToDate());
            authorResponse = (AuthorListResponse) joinPoint.proceed();
        } catch (PublicationsException pe) {
            LOGGER.error("Custom Error on search author : ", pe);
            authorResponse.setResponseCode(pe.getResponseCode().getCode());
            authorResponse.setMessage(pe.getMessage());
        } catch (Throwable throwable) {
            LOGGER.error("Error on search author : ", throwable);
            authorResponse.setResponseCode(ResponseCode.FAILED_REQUEST.getCode());
            authorResponse.setMessage(messageSource.getMessage(MessagesI8NCodes.FAILED_REQUEST, new Object []{}, new Locale(locale)));
        }
        return authorResponse;
    }



    private static AuthorInfo getNonNullAuthorInfo(final AuthorInfo authorInfo) {
        return authorInfo != null ? authorInfo : new AuthorInfo();
    }

    private static AuthorWrapper getNonNullAuthorWrapper(final AuthorWrapper authorWrapper) {
        return authorWrapper != null ? authorWrapper : new AuthorWrapper();
    }

}
