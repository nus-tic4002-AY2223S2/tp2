package seedu.library.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalIndexes.INDEX_FIRST_BOOK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.library.logic.commands.AddCommand;
import seedu.library.logic.commands.ClearCommand;
import seedu.library.logic.commands.DeleteCommand;
import seedu.library.logic.commands.EditCommand;
import seedu.library.logic.commands.EditCommand.EditBookDescriptor;
import seedu.library.logic.commands.ExitCommand;
import seedu.library.logic.commands.FindCommand;
import seedu.library.logic.commands.HelpCommand;
import seedu.library.logic.commands.ListCommand;
import seedu.library.logic.parser.exceptions.ParseException;
import seedu.library.model.book.TitleContainsKeywordsPredicate;
import seedu.library.model.book.Book;
import seedu.library.testutil.EditBookDescriptorBuilder;
import seedu.library.testutil.BookBuilder;
import seedu.library.testutil.BookUtil;

public class LibraryBookParserTest {

    private final LibraryBookParser parser = new LibraryBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Book book = new BookBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(BookUtil.getAddCommand(book));
        assertEquals(new AddCommand(book), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_BOOK.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_BOOK), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Book book = new BookBuilder().build();
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder(book).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_BOOK.getOneBased() + " " + BookUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_BOOK, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new TitleContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
