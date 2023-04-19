package seedu.library.logic.commands;

import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.logic.parser.EditCommandParser;
import seedu.library.logic.parser.exceptions.ParseException;
import seedu.library.model.Model;
import seedu.library.model.book.Book;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.borrower.Borrower;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Assigns the book, identified using its displayed index from the library book, to the borrower.
 */
public class ReturnCommand extends Command {

    public static final String COMMAND_WORD = "return";
    public  static final String MESSAGE_BOOK_IS_AVAILABLE = "The book has not been borrowed yet!";
    public static final String MESSAGE_NO_CURRENT_USER = "To return books, the user must login first.";
    public static final String MESSAGE_NOT_CURRENT_BORROWER = "Only the current borrower of this book is authorized" +
            " to return this book.";
    public String MESSAGE_RETURN_SUCCESS;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Disassociates the book, identified using its displayed index from the library book, from the borrower.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;
    private final String index;

    public ReturnCommand(Index targetIndex, String index) {

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
            Book bookToReturn = lastShownList.get(targetIndex.getZeroBased());
            Iterator<Borrower> iterator = bookToReturn.getBorrowers().iterator();

            if (iterator.hasNext()) {
                Borrower firstElement = iterator.next();

                String firstElementAsString = firstElement.toString().substring(1, firstElement.toString().length() - 1);
                String currentUserAsString = model.getCurrentUser().toString().trim();

                if (firstElementAsString.equals(currentUserAsString)) {

                    Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
                    String userInput = "edit" + index;

                    while (iterator.hasNext()) {
                        String element = iterator.next().toString();
                        userInput += " t/" + element.substring(1, element.toString().length() - 1);;
                    }

                    if (userInput.equals("edit" + index)) {
                        userInput += " t/";
                    }

                    Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

                    if (!matcher.matches()) {
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
                    }

                    final String arguments = matcher.group("arguments");
                    new EditCommandParser().parse(arguments).execute(model);

                    MESSAGE_RETURN_SUCCESS = "The book has been returned successfully.";
                } else {
                    throw new CommandException(MESSAGE_NOT_CURRENT_BORROWER);
                }
            } else {
                throw new CommandException(MESSAGE_BOOK_IS_AVAILABLE);
            }
        } else {
            throw new CommandException(MESSAGE_NO_CURRENT_USER);
        }

        return new CommandResult(String.format(MESSAGE_RETURN_SUCCESS));
    }
}
