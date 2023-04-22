package seedu.library.logic.parser;

import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.CancelCommand;
import seedu.library.logic.parser.exceptions.ParseException;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class CancelCommandParser implements Parser<CancelCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CancelCommand
     * and returns a CancelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CancelCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CancelCommand(index, args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CancelCommand.MESSAGE_USAGE), pe);
        }
    }
}
