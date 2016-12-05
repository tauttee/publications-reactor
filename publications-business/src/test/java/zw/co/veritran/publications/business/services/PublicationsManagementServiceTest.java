package zw.co.veritran.publications.business.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import zw.co.veritran.publications.business.services.api.PublicationsManagementService;
import zw.co.veritran.publications.business.services.impl.PublicationsManagementServiceImpl;
import zw.co.veritran.publications.model.Author;
import zw.co.veritran.publications.repository.author.api.AuthorRepository;
import zw.co.veritran.publications.repository.book.api.BookRepository;
import zw.co.veritran.publications.utils.exceptions.PublicationsException;

import java.util.Locale;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by tyamakura on 1/12/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PublicationsManagementServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private PublicationsManagementService publicationsManagementService = new PublicationsManagementServiceImpl();

    private Author nullIdAuthor;
    private Author authorWillAllValues;
    private Locale locale;
    private String username;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        locale = new Locale("en");
        username = "veritran";
        nullIdAuthor = new Author();
        nullIdAuthor.setFirstName("Taurai");
        nullIdAuthor.setLastName("Nyamakura");
        nullIdAuthor.setPublisherName("ZPH Publishers");

        authorWillAllValues = new Author();
        authorWillAllValues.setId(12345L);
        authorWillAllValues.setFirstName("Autsin");
        authorWillAllValues.setLastName("Nyamakura");
        authorWillAllValues.setPublisherName("ZPH Publishers");
    }
    @Test
    public void createAuthorShouldSaveAuthorIfAuthorIdIsNull() {
        when(authorRepository.save(nullIdAuthor)).thenReturn(nullIdAuthor);
        final Author createdAuthor = publicationsManagementService.createAuthor(nullIdAuthor, locale, username);
        assertThat(createdAuthor, is(notNullValue()));
        verifyZeroInteractions(messageSource);
        verify(authorRepository, times(1)).save(nullIdAuthor);
    }

    @Test(expected = PublicationsException.class)
    public void createAuthorShouldThrowPublicationsExceptionIfIdIsNotNullAndAuthorExists() {
        try {
            when(authorRepository.findOne(authorWillAllValues.getId())).thenReturn(authorWillAllValues);
            final Author createdAuthor = publicationsManagementService.createAuthor(authorWillAllValues, locale, username);
        } finally {
            verify(authorRepository, times(0)).save(authorWillAllValues);
            verify(messageSource, times(1)).getMessage(anyString(), anyObject(), anyObject());
        }

    }

    @Test
    public void createAuthorShouldReturnNonNullIfIdIsNotNullAndAuthorDoesNotExist() {
        when(authorRepository.findOne(authorWillAllValues.getId())).thenReturn(null);
        when(authorRepository.save(authorWillAllValues)).thenReturn(authorWillAllValues);
        final Author createdAuthor = publicationsManagementService.createAuthor(authorWillAllValues, locale, username);
        assertThat(createdAuthor, is(notNullValue()));
        verifyZeroInteractions(messageSource);
        verify(authorRepository, times(1)).findOne(authorWillAllValues.getId());
        verify(authorRepository, times(1)).save(authorWillAllValues);

    }
}
