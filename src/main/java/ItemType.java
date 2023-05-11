public enum ItemType {
	BOOK("Book"),
	CD("CD"),
	DVD("DVD"),
	MAGAZINE("Magazine"),
	NEWS_PAPER("Newspaper");

	private final String displayName;

	ItemType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
