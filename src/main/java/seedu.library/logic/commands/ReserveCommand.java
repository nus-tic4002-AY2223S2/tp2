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
 * Adds the current user, identified using its displayed index from the library book, to the list of borrowers.
 */
public class ReserveCommand extends Command {

    public static final String COMMAND_WORD = "reserve";
    public  static final String MESSAGE_BOOK_AVAILABLE = "The book is available! You can borrow it right away.";
    public static final String MESSAGE_NO_CURRENT_USER = "To reserve books, the user must login first.";
    public static final String MESSAGE_BOOK_RESERVED_TO_USER = "You are one of the borrowers who reserved this book.";
    public static final String MESSAGE_BOOK_ASSIGNED_TO_USER = "Book cancellation failed. " +
            "You are the current borrower of this book. You may return the book instead";
    public String MESSAGE_RESERVE_SUCCESS;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the current user, identified using its displayed index from the library book, " +
            "to the list of borrowers.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;
    private final String index;

    public ReserveCommand(Index targetIndex, String index) {

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
            Book bookToReserve = lastShownList.get(targetIndex.getZeroBased());
            Iterator<Borrower> iterator = bookToReserve.getBorrowers().iterator();
            String currentUserAsString = model.getCurrentUser().toString().trim();
            String userInput = "edit" + index;

            if (!bookToReserve.getBorrowers().isEmpty()) {

                if (iterator.hasNext()) {
                    Borrower element = iterator.next();
                    String borrower = element.toString().substring(1, element.toString().length() - 1);

                    if (borrower.equals(currentUserAsString)) {
                        throw new CommandException(MESSAGE_BOOK_ASSIGNED_TO_USER);
                    }

                    userInput += " t/" + borrower;
                }

                while (iterator.hasNext()) {
                    Borrower element = iterator.next();
                    String borrower = element.toString().substring(1, element.toString().length() - 1);

                    if (borrower.equals(currentUserAsString)) {
                        throw new CommandException(MESSAGE_BOOK_RESERVED_TO_USER);
                    }

                    userInput += " t/" + borrower;
                }

                userInput += " t/" + currentUserAsString;

                Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
                Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

                if (!matcher.matches()) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
                }

                final String arguments = matcher.group("arguments");
                new EditCommandParser().parse(arguments).execute(model);

                MESSAGE_RESERVE_SUCCESS = "You have been added to the list of borrowers.";
            } else {
                throw new CommandException(MESSAGE_BOOK_AVAILABLE);
            }
        } else {
            throw new CommandException(MESSAGE_NO_CURRENT_USER);
        }

        return new CommandResult(String.format(MESSAGE_RESERVE_SUCCESS));
    }
}
