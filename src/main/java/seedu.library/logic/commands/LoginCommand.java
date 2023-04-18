package seedu.library.logic.commands;

import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Model;

import static java.util.Objects.requireNonNull;

public class LoginCommand extends Command {

    private final String borrower;
    public static final String COMMAND_WORD = "login";
    public static final String MESSAGE_CURRENT_BORROWER_EXIST = "The current user must logout first.";
    public String MESSAGE_LOGIN_SUCCESS;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the current user.\n"
            + "Parameters: USERNAME (must be alphanumeric)\n"
            + "Example: " + COMMAND_WORD + " Benjamin";

    public LoginCommand(String newUser) {
        this.borrower = newUser;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.getCurrentUser().isEmpty()) {
            throw new CommandException(MESSAGE_CURRENT_BORROWER_EXIST);
        } else {
            model.setCurrentUser(borrower);
            MESSAGE_LOGIN_SUCCESS = model.getCurrentUser() + " successfully logs in.";
        }

        return new CommandResult(String.format(MESSAGE_LOGIN_SUCCESS));
    }

}
