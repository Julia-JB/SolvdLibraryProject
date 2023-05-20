package libraryItem.periodic;

public enum Frequency {
	DAILY("Daily", "Published every day"),
	WEEKLY("Weekly", "Published once a week"),
	BIWEEKLY("Bi-Weekly", "Published every two weeks"),
	MONTHLY("Monthly", "Published once a month"),
	BIMONTHLY("Bi-Monthly", "Published every two months"),
	QUARTERLY("Quarterly", "Published every three months"),
	ANNUALLY("Annually", "Published once a year");

	private final String displayName;
	private final String description;

	Frequency(String displayName, String description) {
		this.displayName = displayName;
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDescription() {
		return description;
	}

	public int getIssuesPerYear() {
		switch (this) {
			case DAILY:
				return 365;
			case WEEKLY:
				return 52;
			case BIWEEKLY:
				return 26;
			case MONTHLY:
				return 12;
			case BIMONTHLY:
				return 6;
			case QUARTERLY:
				return 4;
			case ANNUALLY:
				return 1;
			default:
				throw new IllegalArgumentException("Unsupported frequency: " + this);
		}
	}
}

