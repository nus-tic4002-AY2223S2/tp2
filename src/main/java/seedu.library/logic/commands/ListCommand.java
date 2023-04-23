package seedu.library.logic.commands;

import seedu.library.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.library.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Lists all persons in the library book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all books";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
