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
import static seedu.library.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.library.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.library.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.library.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.library.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.library.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.library.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.library.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.library.testutil.TypicalIndexes.INDEX_SECOND_BOOK;
import static seedu.library.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.EditCommand;
import seedu.library.model.book.Category;
import seedu.library.model.book.Edition;
import seedu.library.model.book.Author;
//import seedu.library.model.book.Name;
import seedu.library.model.borrower.Borrower;
import seedu.library.testutil.EditBookDescriptorBuilder;

public class EditCommandParserTest {
/*
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
//        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Author.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Edition.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Category.MESSAGE_CONSTRAINTS); // invalid library
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Borrower.MESSAGE_CONSTRAINTS); // invalid borrower

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Author.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Author.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Book} being edited,
        // parsing it together with a valid borrower results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Borrower.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Borrower.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Borrower.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
//        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
//                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_BOOK;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

 */
/*
    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_BOOK;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditBookDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditBookDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // library
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditBookDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditBookDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_BOOK;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_BOOK;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditBookDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

 */
}
