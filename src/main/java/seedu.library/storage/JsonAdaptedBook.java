package seedu.library.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.model.book.*;
import seedu.library.model.borrower.Borrower;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Book}.
 */
class JsonAdaptedBook {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Book's %s field is missing!";

    private final String title;
    private final String author;
    private final String edition;
    private final String category;
    private final List<JsonAdaptedBorrower> borrower = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedBook} with the given book details.
     */
    @JsonCreator
    public JsonAdaptedBook(@JsonProperty("title") String title, @JsonProperty("author") String author,
                           @JsonProperty("edition") String edition, @JsonProperty("category") String category,
                           @JsonProperty("borrower") List<JsonAdaptedBorrower> borrower) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.category = category;
        if (borrower != null) {
            this.borrower.addAll(borrower);
        }
    }

    /**
     * Converts a given {@code Book} into this class for Jackson use.
     */
    public JsonAdaptedBook(Book source) {
        title = source.getTitle().title;
        author = source.getAuthor().value;
        edition = source.getEdition().value;
        category = source.getCategory().value;
        borrower.addAll(source.getBorrowers().stream()
                .map(JsonAdaptedBorrower::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted book object into the model's {@code Book} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted book.
     */
    public Book toModelType() throws IllegalValueException {
        final List<Borrower> bookBorrowers = new ArrayList<>();
        for (JsonAdaptedBorrower tag : borrower) {
            bookBorrowers.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (author == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName()));
        }
        if (!Author.isValidPhone(author)) {
            throw new IllegalValueException(Author.MESSAGE_CONSTRAINTS);
        }
        final Author modelAuthor = new Author(author);

        if (edition == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Edition.class.getSimpleName()));
        }
        if (!Edition.isValidEmail(edition)) {
            throw new IllegalValueException(Edition.MESSAGE_CONSTRAINTS);
        }
        final Edition modelEdition = new Edition(edition);

        if (category == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName()));
        }
        if (!Category.isValidCategory(category)) {
            throw new IllegalValueException(Category.MESSAGE_CONSTRAINTS);
        }
        final Category modelCategory = new Category(category);

        final LinkedHashSet<Borrower> modelBorrowers = new LinkedHashSet<>(bookBorrowers);
        return new Book(modelTitle, modelAuthor, modelEdition, modelCategory, modelBorrowers);
    }

}
