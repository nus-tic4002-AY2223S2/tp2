package seedu.library.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.model.borrower.Borrower;

/**
 * Jackson-friendly version of {@link Borrower}.
 */
class JsonAdaptedBorrower {

    private final String borrowerName;

    /**
     * Constructs a {@code JsonAdaptedBorrower} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedBorrower(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    /**
     * Converts a given {@code Borrower} into this class for Jackson use.
     */
    public JsonAdaptedBorrower(Borrower source) {
        borrowerName = source.borrowerName;
    }

    @JsonValue
    public String getBorrowerName() {
        return borrowerName;
    }

    /**
     * Converts this Jackson-friendly adapted borrower object into the model's {@code Borrower} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted borrower.
     */
    public Borrower toModelType() throws IllegalValueException {
        if (!Borrower.isValidTagName(borrowerName)) {
            throw new IllegalValueException(Borrower.MESSAGE_CONSTRAINTS);
        }
        return new Borrower(borrowerName);
    }

}
