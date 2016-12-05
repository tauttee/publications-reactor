package zw.co.veritran.publications.utils.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class AuthorWrapper implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String publisherName;
    private String status;
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
        final StringBuilder sb = new StringBuilder("AuthorWrapper{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", publisherName='").append(publisherName).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", dateLastUpdated=").append(dateLastUpdated);
        sb.append(", fromDate=").append(fromDate);
        sb.append(", toDate=").append(toDate);
        sb.append('}');
        return sb.toString();
    }
}
