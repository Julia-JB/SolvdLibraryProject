package libraryItem;

public enum BookGenre {
        FICTION("Fiction", "Books that are made up or imaginary"),
        NON_FICTION("Non-Fiction", "Books that are based on facts or real events"),
        MYSTERY("Mystery", "Books that involve the solving of a crime or puzzle"),
        ROMANCE("Romance", "Books that focus on a romantic relationship between characters"),
        SCIENCE_FICTION("Science Fiction", "Books that explore the possibilities of future " +
                "science and technology"),
        CHILDRENS_PICTURE_BOOK("Children's Picture libraryItem.Book", "Books that are illustrated with " +
                "pictures and are designed for young children"),
        CHILDRENS_CHAPTER_BOOK("Children's Chapter libraryItem.Book", "Books that are aimed at older children and are divided into chapters"),
        CHILDRENS_NOVEL("Children's Novel", "Books that are longer and more complex than chapter books and are aimed at young adults");

        private final String displayName;
        private final String description;

        BookGenre(String displayName, String description) {
                this.displayName = displayName;
                this.description = description;
        }

        public String getDisplayName() {
                return displayName;
        }

        public String getDescription() {
                return description;
        }
}
