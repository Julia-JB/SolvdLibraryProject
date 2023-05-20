package event;

public enum EventType {
    BOOK_DISCUSSION("libraryItem.book.Book discussion", true),
    AUTHOR_VISIT("Author's visit", true),
    MOVIE_SCREENING("Movie screening", true),
    BOOK_SALE("libraryItem.book.Book sale", true),
    CHILDREN_STORY_TIME("Children story time", false),
    CHILDREN_WORKSHOP("Children workshop", false),
    ELEMENTARY_READING_GROUP("Elementary reading group for children", false),
    SCHOOL_FIELD_TRIP("School field trip for elementary school children", false),
    SUMMER_DAY_CAMP("Summer day camp for children", false);

    private final String displayName;
    private final boolean isPublicEvent;
    EventType(String displayName, boolean isPublicEvent) {
        this.displayName = displayName;
        this.isPublicEvent = isPublicEvent;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isPublicEvent() {
        return isPublicEvent;
    }

    public boolean isChildrenEvent() {
        return displayName.contains("children");
    }
}
