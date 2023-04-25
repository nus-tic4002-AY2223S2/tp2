package seedu.library.logic.parser;

import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.BorrowCommand;
import seedu.library.logic.parser.exceptions.ParseException;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class BorrowCommandParser implements Parser<BorrowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BorrowCommand
     * and returns a BorrowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BorrowCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new BorrowCommand(index, args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BorrowCommand.MESSAGE_USAGE), pe);
        }
    }
}
