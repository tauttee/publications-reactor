package zw.co.veritran.publications.model;

import org.apache.commons.lang3.StringUtils;
import zw.co.veritran.publications.utils.constants.SystemConstants;
import zw.co.veritran.publications.utils.keygen.KeyGen;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tyamakura on 29/11/2016.
 */
@Entity
@NamedQueries({@NamedQuery(name = "Book.findAllBooks", query = "select b from Book b where b.status <> '" + SystemConstants.STATUS_DELETED + "'")})
@Table(name = "book", indexes = {
            @Index(name = "indx_bk_title", columnList = "title"),
            @Index(name = "indx_bk_status", columnList = "status"),
            @Index(name = "indx_bk_title_author", columnList = "title, author", unique = true),
            @Index(name = "indx_bk_publish_date", columnList = "publish_date"),
            @Index(name = "indx_bk_datecreated", columnList = "date_created")
        })
public class Book {
    @Id
    private Long id;
    @Column(length = 50)
    private String title;
    @Column(length = 300)
    private String synopsis;
    @Temporal(TemporalType.DATE)
    @Column(name = "publish_date")
    private Date publishDate;
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

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "author")
    private Author author;

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
        this.title = title.toUpperCase();

    }

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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
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
