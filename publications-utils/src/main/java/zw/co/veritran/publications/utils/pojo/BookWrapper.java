package zw.co.veritran.publications.utils.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class BookWrapper implements Serializable {
    private Long id;
    private String authorFirstName;
    private String authorLastName;
    private String authorPublisherName;
    private String title;
    private String synopsis;
    private String status;
    private Date publishDate;
    private Date dateCreated;
    private Date dateLastUpdated;
    private Date fromDate;
    private Date toDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorPublisherName() {
        return authorPublisherName;
    }

    public void setAuthorPublisherName(String authorPublisherName) {
        this.authorPublisherName = authorPublisherName;
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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookWrapper{");
        sb.append("id=").append(id);
        sb.append(", authorFirstName='").append(authorFirstName).append('\'');
        sb.append(", authorLastName='").append(authorLastName).append('\'');
        sb.append(", authorPublisherName='").append(authorPublisherName).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", synopsis='").append(synopsis).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", publishDate=").append(publishDate);
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", dateLastUpdated=").append(dateLastUpdated);
        sb.append(", fromDate=").append(fromDate);
        sb.append(", toDate=").append(toDate);
        sb.append('}');
        return sb.toString();
    }
}
