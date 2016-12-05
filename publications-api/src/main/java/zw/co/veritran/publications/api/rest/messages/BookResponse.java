package zw.co.veritran.publications.api.rest.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class BookResponse implements Serializable{

    @JsonProperty("ResponseCode")
    private String responseCode;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Book")
    private BookInfo book;

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

    public BookInfo getBook() {
        return book;
    }

    public void setBook(BookInfo book) {
        this.book = book;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookResponse{");
        sb.append("responseCode='").append(responseCode).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", book=").append(book);
        sb.append('}');
        return sb.toString();
    }
}
