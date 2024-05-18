package bg.fmi.popcornpals.util;

public enum Genre {
    ACTION, COMEDY, DRAMA, HORROR,
    ROMANCE, SCIENCE_FICTION, THRILLER,
    FANTASY, DOCUMENTARY, ANIMATION,
    CRIME, MYSTERY, ADVENTURE,
    HISTORICAL, SUPERHERO, WESTERN,
    MUSICAL, FAMILY, WAR,
    SPORTS, REALITY_TV, BIOGRAPHY, HISTORY,

    OTHER;

    @Override
    public String toString() {
        String name = super.toString();
        String[] words = name.split("_");
        StringBuilder titleCase = new StringBuilder();
        for (String word : words) {
            titleCase.append(word.charAt(0))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return titleCase.toString().trim();
    }
}
