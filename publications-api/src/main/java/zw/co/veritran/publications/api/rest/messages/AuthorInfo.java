package zw.co.veritran.publications.api.rest.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import zw.co.veritran.publications.utils.constants.SystemConstants;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class AuthorInfo implements Serializable {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("PublisherName")
    private String publisherName;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("DateCreated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SystemConstants.JSON_DATE_FORMAT)
    private Date dateCreated;
    @JsonProperty("DateLastUpdated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SystemConstants.JSON_DATE_FORMAT)
    private Date dateLastUpdated;
    @JsonProperty("Books")
    private List<BookInfo> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(Date dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public List<BookInfo> getBooks() {
        return books;
    }

    public void setBooks(List<BookInfo> books) {
        this.books = books;
    }
}
