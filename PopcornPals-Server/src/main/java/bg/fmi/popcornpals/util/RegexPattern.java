package bg.fmi.popcornpals.util;

public interface RegexPattern {
    String STUDIO_NAME = "^[a-zA-Z0-9 .,'&()-]+$";
    String MEDIA_TITLE = "^[a-zA-Z0-9 .,'&()-]+$";
    String DESCRIPTION = "^[a-zA-Z0-9 .,'&()\\-!?:;\"%$@#\\n\\r\\t]+$";
    String PERSON_NAME = "^[A-Za-z]+(?:[-'][A-Za-z]+)*\\s[A-Za-z]+(?:[-'][A-Za-z]+)*$";
    String PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";
    String USERNAME = "^[a-zA-Z][a-zA-Z0-9_.]+$";
}
