package bg.fmi.popcornpals.enums;

public interface RegexPattern {

    String NAME = "^[a-zA-Z0-9 .,'&()-]+$";

    String TITLE = "^[a-zA-Z0-9 .,'&()-]+$";

    String DESCRIPTION = "^[a-zA-Z0-9 .,'&()\\-!?:;\"%$@#\\n\\r\\t]+$";

}
