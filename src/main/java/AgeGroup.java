public enum AgeGroup {
	TODDLER(0, 3, "Toddler books"),
	PRESCHOOL(2, 5, "Early picture books"),
	EARLY_ELEMENTARY (6, 8, "Picture Books and first chapter books"),
	ELEMENTARY_TO_MIDDLE (9, 14, "Children novels and chapter books");

	private final int minAge;
	private final int maxAge;

	private final String displayName;

	private AgeGroup( int minAge, int maxAge, String displayName) {
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.displayName = displayName;
	}

	public int getMinAge () {
		return minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public boolean isAgeRange(int age) {
		return (age >= minAge && age <= maxAge);
	}
}
