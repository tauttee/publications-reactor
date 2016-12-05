package zw.co.veritran.publications.api.rest.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class BookListResponse implements Serializable{

    @JsonProperty("ResponseCode")
    private String responseCode;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Books")
    private List<BookInfo> books;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookInfo> getBooks() {
        return books;
    }

    public void setBooks(List<BookInfo> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookListResponse{");
        sb.append("responseCode='").append(responseCode).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", books=").append(books);
        sb.append('}');
        return sb.toString();
    }
}
