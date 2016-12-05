package zw.co.veritran.publications.api.validators.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import zw.co.veritran.publications.api.rest.messages.AuthorInfo;
import zw.co.veritran.publications.api.rest.messages.BookInfo;
import zw.co.veritran.publications.utils.builders.ErrorMessageBuilder;
import zw.co.veritran.publications.utils.constants.MessagesI8NCodes;
import zw.co.veritran.publications.utils.enums.ResponseCode;
import zw.co.veritran.publications.utils.pojo.GenericResponse;

import java.util.Locale;

/**
 * Created by tyamakura on 30/11/2016.
 */
@Service
public class RequestValidationImpl implements zw.co.veritran.publications.api.validators.api.RequestValidation {

    @Autowired
    private MessageSource messageSource;

    @Override
    public GenericResponse validate(AuthorInfo authorInfo, Locale locale, final boolean isCreateRequest) {
        final GenericResponse response = new GenericResponse();
        final boolean isEmptyRequest = authorInfo == null;
        if(isEmptyRequest) {
            response.setResponseCode(ResponseCode.EMPTY_REQUEST.getCode());
            response.setMessage(messageSource.getMessage(MessagesI8NCodes.EMPTY_REQUEST, new Object []{}, locale));
            return response;
        }
        final StringBuilder builder = new StringBuilder();
        if(!isCreateRequest) {
            ErrorMessageBuilder.buildMessage(builder, authorInfo.getId() == null, messageSource.getMessage(MessagesI8NCodes.AUTHOR_FIELD_ID, new Object []{}, locale));
        }
        ErrorMessageBuilder.buildMessage(builder, StringUtils.isEmpty(authorInfo.getFirstName()), messageSource.getMessage(MessagesI8NCodes.AUTHOR_FIELD_FIRST_NAME, new Object []{}, locale));
        ErrorMessageBuilder.buildMessage(builder, StringUtils.isEmpty(authorInfo.getLastName()), messageSource.getMessage(MessagesI8NCodes.AUTHOR_FIELD_LAST_NAME, new Object []{}, locale));
        final boolean isRequiredMissingFields = builder.length() > 0;
        if(isRequiredMissingFields) {
            response.setResponseCode(ResponseCode.MANDATORY_FIELDS_MISSING.getCode());
            response.setMessage(messageSource.getMessage(MessagesI8NCodes.MISSING_MANDATORY_FIELDS, new Object []{}, locale) + " : " + builder.toString());
        }
        return response;
    }

    @Override
    public GenericResponse validate(BookInfo bookInfo, Locale locale, final boolean isCreateRequest) {
        final GenericResponse response = new GenericResponse();
        final boolean isEmptyRequest = bookInfo == null;
        if(isEmptyRequest) {
            response.setResponseCode(ResponseCode.EMPTY_REQUEST.getCode());
            response.setMessage(messageSource.getMessage(MessagesI8NCodes.EMPTY_REQUEST, new Object []{}, locale));
            return response;
        }
        final StringBuilder builder = new StringBuilder();
        if(!isCreateRequest) {
            ErrorMessageBuilder.buildMessage(builder, bookInfo.getId() == null, messageSource.getMessage(MessagesI8NCodes.BOOK_FIELD_ID, new Object []{}, locale));
        }
        ErrorMessageBuilder.buildMessage(builder, StringUtils.isEmpty(bookInfo.getTitle()), messageSource.getMessage(MessagesI8NCodes.BOOK_FIELD_TITLE, new Object []{}, locale));
        ErrorMessageBuilder.buildMessage(builder, StringUtils.isEmpty(bookInfo.getSynopsis()), messageSource.getMessage(MessagesI8NCodes.BOOK_FIELD_SYNOPSIS, new Object []{}, locale));
        ErrorMessageBuilder.buildMessage(builder, bookInfo.getAuthor() == null || bookInfo.getAuthor().getId() == null,
                messageSource.getMessage(MessagesI8NCodes.BOOK_AUTHOR_ID, new Object []{}, locale));
        final boolean isRequiredMissingFields = builder.length() > 0;
        if(isRequiredMissingFields) {
            response.setResponseCode(ResponseCode.MANDATORY_FIELDS_MISSING.getCode());
            response.setMessage(messageSource.getMessage(MessagesI8NCodes.MISSING_MANDATORY_FIELDS, new Object []{}, locale) + " : " + builder.toString());
        }
        return response;
    }
}
