package seedu.library.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book's phone number in the library book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Author {

    public static final String MESSAGE_CONSTRAINTS = "Author can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs a {@code Author}.
     *
     * @param author A valid author.
     */
    public Author(String author) {
        requireNonNull(author);
        checkArgument(isValidPhone(author), MESSAGE_CONSTRAINTS);
        value = author;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Author // instanceof handles nulls
                && value.equals(((Author) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
