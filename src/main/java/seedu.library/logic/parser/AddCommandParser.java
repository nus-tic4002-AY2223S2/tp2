package seedu.library.logic.parser;

import seedu.library.model.book.*;
import seedu.library.model.borrower.Borrower;
import seedu.library.logic.commands.AddCommand;
import seedu.library.logic.parser.exceptions.ParseException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.library.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.library.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Author author = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Edition edition = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Category category = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Borrower> borrowerList = ParserUtil.parseBorrowers(argMultimap.getAllValues(PREFIX_TAG));

        Book book = new Book(title, author, edition, category, (LinkedHashSet<Borrower>) borrowerList);

        return new AddCommand(book);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
