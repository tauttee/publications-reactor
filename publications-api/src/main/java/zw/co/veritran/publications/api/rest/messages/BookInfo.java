package zw.co.veritran.publications.api.rest.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import zw.co.veritran.publications.utils.constants.SystemConstants;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class BookInfo implements Serializable {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Synopsis")
    private String synopsis;
    @JsonProperty("PublishDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SystemConstants.SHORT_DATE_FORMAT)
    private Date publishDate;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("DateCreated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SystemConstants.JSON_DATE_FORMAT)
    private Date dateCreated;
    @JsonProperty("DateLastUpdated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SystemConstants.JSON_DATE_FORMAT)
    private Date dateLastUpdated;
    @JsonProperty("Author")
    private AuthorInfo author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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

    public AuthorInfo getAuthor() {
        return author;
    }

    public void setAuthor(AuthorInfo author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookInfo{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", synopsis='").append(synopsis).append('\'');
        sb.append(", publishDate=").append(publishDate);
        sb.append(", status='").append(status).append('\'');
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", dateLastUpdated=").append(dateLastUpdated);
        sb.append(", author=").append(author);
        sb.append('}');
        return sb.toString();
    }
}
