package seedu.library.logic.commands;

import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.model.Model;
import seedu.library.model.book.Book;
import seedu.library.logic.commands.exceptions.CommandException;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a book identified using it's displayed index from the library book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the book identified by the index number used in the displayed book list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Book: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Book bookToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteBook(bookToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, bookToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
