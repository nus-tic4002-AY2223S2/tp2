package seedu.library.model.borrower;

import static seedu.library.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BorrowerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Borrower(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Borrower(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null borrower name
        assertThrows(NullPointerException.class, () -> Borrower.isValidTagName(null));
    }

}
