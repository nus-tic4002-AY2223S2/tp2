package seedu.library.logic.commands;

import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.Model;

import static java.util.Objects.requireNonNull;

public class LogoutCommand extends Command {

    private final String borrower;
    public static final String COMMAND_WORD = "logout";
    public static final String MESSAGE_LOGGING_OUT_FAILED = "Logging out failed. Username entered did not match the current username.";
    public static final String MESSAGE_NO_CURRENT_USER = "There is no user currently logged in.";
    public String MESSAGE_LOGOUT_SUCCESS;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Signs out the user.\n"
            + "Parameters: USERNAME (must be alphanumeric)\n"
            + "Example: " + COMMAND_WORD + " Benjamin";

    public LogoutCommand(String newUser) {
        this.borrower = newUser;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getCurrentUser().isEmpty()) {
            throw new CommandException(MESSAGE_NO_CURRENT_USER);
        } else {
            if (model.getCurrentUser().equals(borrower)) {
                MESSAGE_LOGOUT_SUCCESS = model.getCurrentUser() + " successfully logs out.";
                model.setCurrentUser("");

            } else {
                throw new CommandException(MESSAGE_LOGGING_OUT_FAILED);
            }
        }

        return new CommandResult(String.format(MESSAGE_LOGOUT_SUCCESS));
    }

}
