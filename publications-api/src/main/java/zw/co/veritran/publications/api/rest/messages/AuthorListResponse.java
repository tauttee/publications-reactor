package zw.co.veritran.publications.api.rest.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class AuthorListResponse implements Serializable{

    @JsonProperty("ResponseCode")
    private String responseCode;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Authors")
    private List<AuthorInfo> authors;

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

    public List<AuthorInfo> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorInfo> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorListResponse{");
        sb.append("responseCode='").append(responseCode).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
