package seedu.library.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EditionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Edition(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Edition(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Edition.isValidEmail(null));

        // blank email
        assertFalse(Edition.isValidEmail("")); // empty string
        assertFalse(Edition.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Edition.isValidEmail("@example.com")); // missing local part
        assertFalse(Edition.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(Edition.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Edition.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Edition.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Edition.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(Edition.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Edition.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(Edition.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(Edition.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(Edition.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Edition.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Edition.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Edition.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(Edition.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Edition.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Edition.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Edition.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Edition.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Edition.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(Edition.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Edition.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(Edition.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Edition.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Edition.isValidEmail("a@bc")); // minimal
        assertTrue(Edition.isValidEmail("test@localhost")); // alphabets only
        assertTrue(Edition.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(Edition.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Edition.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Edition.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Edition.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
    }
}
