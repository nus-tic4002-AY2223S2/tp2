package seedu.library.logic.parser;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.library.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.library.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.library.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.library.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.library.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.library.testutil.TypicalBooks.AMY;
import static seedu.library.testutil.TypicalBooks.BOB;

import org.junit.jupiter.api.Test;

import seedu.library.logic.commands.AddCommand;
import seedu.library.model.book.Category;
import seedu.library.model.book.Edition;
import seedu.library.model.book.Author;
import seedu.library.model.book.Name;
import seedu.library.model.book.Person;
import seedu.library.model.borrower.Borrower;
import seedu.library.testutil.BookBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new BookBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last library accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new BookBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new BookBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing library prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Author.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Edition.MESSAGE_CONSTRAINTS);

        // invalid library
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Category.MESSAGE_CONSTRAINTS);

        // invalid borrower
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Borrower.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
