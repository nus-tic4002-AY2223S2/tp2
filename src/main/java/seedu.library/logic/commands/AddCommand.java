package seedu.library.logic.commands;

import seedu.library.model.Model;
import seedu.library.model.book.Book;
import seedu.library.logic.commands.exceptions.CommandException;

import static java.util.Objects.requireNonNull;
import static seedu.library.logic.parser.CliSyntax.*;

/**
 * Adds a book to the library book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a book to the library book. "
            + "Parameters: "
            + PREFIX_NAME + "TITLE "
            + PREFIX_PHONE + "AUTHOR "
            + PREFIX_EMAIL + "EDITION "
            + PREFIX_ADDRESS + "CATEGORY "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "HARRY POTTER "
            + PREFIX_PHONE + "J K Rowling "
            + PREFIX_EMAIL + "1st Edition "
            + PREFIX_ADDRESS + "Software Engineering "
            + PREFIX_TAG + "Roshan";

    public static final String MESSAGE_SUCCESS = "New book added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This book already exists in the library book";

    private final Book toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Book}
     */
    public AddCommand(Book book) {
        requireNonNull(book);
        toAdd = book;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBook(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addBook(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
