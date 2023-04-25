package seedu.library.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book's email in the library book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Edition {

    public static final String MESSAGE_CONSTRAINTS = "Edition can take any values, and it should not be blank";
    // alphanumeric and special characters
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Edition}.
     *
     * @param edition A valid book edition.
     */
    public Edition(String edition) {
        requireNonNull(edition);
        checkArgument(isValidEmail(edition), MESSAGE_CONSTRAINTS);
        value = edition;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Edition // instanceof handles nulls
                && value.equals(((Edition) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
