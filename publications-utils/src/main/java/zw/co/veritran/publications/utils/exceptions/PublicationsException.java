package zw.co.veritran.publications.utils.exceptions;

import zw.co.veritran.publications.utils.enums.ResponseCode;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class PublicationsException extends RuntimeException {

    private ResponseCode responseCode;

    public PublicationsException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

}
