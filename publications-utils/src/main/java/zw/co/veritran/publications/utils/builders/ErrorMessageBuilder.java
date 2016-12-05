package zw.co.veritran.publications.utils.builders;

/**
 * Created by tyamakura on 30/11/2016.
 */
public class ErrorMessageBuilder {

    private ErrorMessageBuilder() {
        super();
    }

    public static void buildMessage(final StringBuilder builder, final boolean isInvalid, final String errorMessage) {
        if(isInvalid) {
            if (builder.length() <= 0) {
                builder.append(errorMessage);
            } else {
                builder.append(", ").append(errorMessage);
            }
        }
    }
}
