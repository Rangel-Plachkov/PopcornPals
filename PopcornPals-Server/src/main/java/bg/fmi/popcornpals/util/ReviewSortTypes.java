package bg.fmi.popcornpals.util;

public enum ReviewSortTypes {
    DATE_DESC("date_desc"),
    DATE_ASC("date_asc"),
    RATING_DESC("rating_desc"),
    RATING_ASC("rating_asc");

    private final String value;

    ReviewSortTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReviewSortTypes fromValue(String value) {
        for (ReviewSortTypes sortType : ReviewSortTypes.values()) {
            if (sortType.getValue().equals(value)) {
                return sortType;
            }
        }
        return null;
    }
}
