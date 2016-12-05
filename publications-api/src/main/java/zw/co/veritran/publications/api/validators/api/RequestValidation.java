package zw.co.veritran.publications.api.validators.api;

import zw.co.veritran.publications.api.rest.messages.AuthorInfo;
import zw.co.veritran.publications.api.rest.messages.BookInfo;
import zw.co.veritran.publications.utils.pojo.GenericResponse;

import java.util.Locale;

/**
 * Created by tyamakura on 30/11/2016.
 */
public interface RequestValidation {
    GenericResponse validate(AuthorInfo authorInfo, Locale locale, boolean isCreateRequest);

    GenericResponse validate(BookInfo bookInfo, Locale locale, boolean isCreateRequest);
}
