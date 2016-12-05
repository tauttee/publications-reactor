package zw.co.veritran.publications.api.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import zw.co.veritran.publications.api.processors.api.PublicationsProcessor;
import zw.co.veritran.publications.api.rest.messages.*;
import zw.co.veritran.publications.utils.pojo.AuthorWrapper;
import zw.co.veritran.publications.utils.constants.SystemConstants;
import zw.co.veritran.publications.utils.pojo.BookWrapper;

import java.security.Principal;
import java.util.Locale;

@RestController
@Scope("request")
@RequestMapping("/rest/services/")
public class PublicationsServiceResource {

    @Autowired
    private PublicationsProcessor publicationsProcessor;


    @RequestMapping(value = "/authors", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AuthorResponse createAuthor(@RequestBody final AuthorInfo request,
                                             @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                             @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.createAuthor(request, new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AuthorResponse updateAuthor(@PathVariable("id") final Long id,
                                       @RequestBody final AuthorInfo request,
                                       @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                       @AuthenticationPrincipal final Principal principal) {
        if(request != null && request.getId() == null) {
            request.setId(id);
        }
        return publicationsProcessor.updateAuthor(request, new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AuthorResponse deleteAuthor(@PathVariable("id") final Long id,
                                       @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                       @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.deleteAuthor(id, new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AuthorResponse findAuthorById(@PathVariable("id") final Long id,
                                       @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                       @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.findAuthorById(id, new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AuthorListResponse findAllAuthors(@RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                             @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.findAllAuthors(new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/authors/search", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public AuthorListResponse searchAuthors(@RequestBody final AuthorWrapper wrapper,
                                           @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                           @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.findByWrapper(wrapper, new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookResponse createBook(@RequestBody final BookInfo request,
                                       @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                       @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.createBook(request, new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookResponse updateBook(@PathVariable("id") final Long id,
                                   @RequestBody final BookInfo request,
                                   @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                   @AuthenticationPrincipal final Principal principal) {
        if(request != null && request.getId() == null) {
            request.setId(id);
        }
        return publicationsProcessor.updateBook(request, new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookResponse deleteBook(@PathVariable("id") final Long id,
                                       @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                       @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.deleteBook(id, new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookResponse findBookById(@PathVariable("id") final Long id,
                                         @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                         @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.findBookById(id, new Locale(locale), principal.getName());
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookListResponse findAllBooks(@RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                        @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.findAllBooks(new Locale(locale), principal.getName());
    }
    @RequestMapping(value = "/books/search", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookListResponse searchBooks(@RequestBody final BookWrapper wrapper,
                                            @RequestHeader(SystemConstants.LOCALE_LANGUAGE) final String locale,
                                            @AuthenticationPrincipal final Principal principal) {
        return publicationsProcessor.findByWrapper(wrapper, new Locale(locale), principal.getName());
    }

}