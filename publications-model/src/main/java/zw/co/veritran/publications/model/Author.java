package zw.co.veritran.publications.model;

import org.apache.commons.lang3.StringUtils;
import zw.co.veritran.publications.utils.constants.SystemConstants;
import zw.co.veritran.publications.utils.keygen.KeyGen;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by tyamakura on 29/11/2016.
 */

@Entity
@NamedQueries({@NamedQuery(name = "Author.findAllAuthors", query = "select distinct (a) from Author a left join fetch a.books where a.status <> '" + SystemConstants.STATUS_DELETED + "' order by a.dateCreated DESC")})
@Table(name = "author", indexes = {
        @Index(name = "indx_ath_first_name", columnList = "first_name"),
        @Index(name = "indx_ath_last_name", columnList = "last_name"),
        @Index(name = "indx_ath_status", columnList = "status"),
        @Index(name = "indx_ath_publisher", columnList = "publisher"),
        @Index(name = "indx_ath_datecreated", columnList = "date_created")
})
public class Author {

    @Id
    private Long id;
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Column(name = "last_name", length = 50)
    private String lastName;
    @Column(name = "publisher", length = 50)
    private String publisherName;
    @Column(length = 20)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_last_updated")
    private Date dateLastUpdated;
    @Version
    private long version;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.REMOVE})
    private Set<Book> books;

    @PrePersist
    protected void init() {
        if(id == null) {
            id = KeyGen.getUniqueId();
        }

        if(dateCreated == null) {
            dateCreated = new Date();
            dateLastUpdated = new Date();
        }

        if(status == null) {
            status = SystemConstants.STATUS_ACTIVE;
        }
        this.toUpperCase();

    }

    @PreUpdate
    protected void reload() {
        dateLastUpdated = new Date();
        this.toUpperCase();
    }

    private void toUpperCase() {
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        if(StringUtils.isNotEmpty(this.publisherName)) {
            this.publisherName = publisherName.toUpperCase();
        }
    }

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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", publisherName='").append(publisherName).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", dateLastUpdated=").append(dateLastUpdated);
        sb.append('}');
        return sb.toString();
    }
}
