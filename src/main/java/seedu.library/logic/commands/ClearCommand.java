package seedu.library.logic.commands;

import seedu.library.model.LibraryBook;
import seedu.library.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Clears the library book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Category book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLibraryBook(new LibraryBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
