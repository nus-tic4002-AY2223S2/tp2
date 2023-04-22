package seedu.library.logic.parser;

import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.ReserveCommand;
import seedu.library.logic.parser.exceptions.ParseException;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ReserveCommandParser implements Parser<ReserveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReserveCommand
     * and returns a ReserveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReserveCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ReserveCommand(index, args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReserveCommand.MESSAGE_USAGE), pe);
        }
    }
}
