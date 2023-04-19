package seedu.library.logic.parser;

import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.ReturnCommand;
import seedu.library.logic.parser.exceptions.ParseException;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ReturnCommandParser implements Parser<ReturnCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReturnCommand
     * and returns a ReturnCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReturnCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ReturnCommand(index, args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReturnCommand.MESSAGE_USAGE), pe);
        }
    }
}
