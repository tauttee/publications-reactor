package zw.co.veritran.publications.utils.enums;

/**
 * Created by tyamakura on 30/11/2016.
 */
public enum ResponseCode {
    SUCCESS("200"),
    EMPTY_REQUEST("400"),
    DUPLICATE_REQUEST("409"),
    NOT_FOUND("404"),
    FAILED_REQUEST("500"),
    MANDATORY_FIELDS_MISSING("601");
    private String responseCode;

    private ResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getCode() {
        return responseCode;
    }
}
