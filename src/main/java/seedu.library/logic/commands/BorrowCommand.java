package seedu.library.logic.commands;

import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.logic.parser.EditCommandParser;
import seedu.library.logic.parser.exceptions.ParseException;
import seedu.library.model.Model;
import seedu.library.model.book.Book;
import seedu.library.logic.commands.exceptions.CommandException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Assigns the book, identified using its displayed index from the library book, to the borrower.
 */
public class BorrowCommand extends Command {

    public static final String COMMAND_WORD = "borrow";
    public static final String MESSAGE_NOT_AVAILABLE = "The book that you are trying to borrow is not available " +
            "in the library. Please try other books.";
    public  static final String MESSAGE_BOOK_IS_OUT = "The book was borrowed and has not been returned yet." +
            " For the meantime, you may reserve the book.";
    public static final String MESSAGE_NO_CURRENT_USER = "To borrow books, the user must login first.";
    public String MESSAGE_BORROW_SUCCESS;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the book, identified using its displayed index from the library book, to the borrower.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;
    private final String index;

    public BorrowCommand(Index targetIndex, String index) {

        this.targetIndex = targetIndex;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!model.getCurrentUser().isEmpty()) {
            Book bookToModify = lastShownList.get(targetIndex.getZeroBased());

            if (bookToModify.getBorrowers().isEmpty()) {
                Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
                String userInput = "edit" + index + " t/" + model.getCurrentUser();
                Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

                if (!matcher.matches()) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
                }

                final String arguments = matcher.group("arguments");
                new EditCommandParser().parse(arguments).execute(model);

                MESSAGE_BORROW_SUCCESS = "The book has been added to your account.";
            } else {
                throw new CommandException(MESSAGE_BOOK_IS_OUT);
            }
        } else {
            throw new CommandException(MESSAGE_NO_CURRENT_USER);
        }

        return new CommandResult(String.format(MESSAGE_BORROW_SUCCESS));
    }
}
