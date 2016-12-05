package zw.co.veritran.publications.api.rest.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class AuthorResponse implements Serializable{
    @JsonProperty("ResponseCode")
    private String responseCode;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Author")
    private AuthorInfo author;

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

    public AuthorInfo getAuthor() {
        return author;
    }

    public void setAuthor(AuthorInfo author) {
        this.author = author;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorResponse{");
        sb.append("responseCode='").append(responseCode).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", author=").append(author);
        sb.append('}');
        return sb.toString();
    }
}
