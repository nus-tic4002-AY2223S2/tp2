package seedu.library.model.borrower;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

/**
 * Represents a Borrower in the library book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Borrower {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+"; //

    public String borrowerName;

    /**
     * Constructs a {@code Borrower}.
     *
     * @param borrowerName A valid borrower name.
     */
    public Borrower(String borrowerName) {
        requireNonNull(borrowerName);
        checkArgument(isValidTagName(borrowerName), MESSAGE_CONSTRAINTS);
        this.borrowerName = borrowerName;
    }

    /**
     * Returns true if a given string is a valid borrower name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Borrower // instanceof handles nulls
                && borrowerName.equals(((Borrower) other).borrowerName)); // state check
    }

    @Override
    public int hashCode() {
        return borrowerName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + borrowerName + ']';
    }

}
