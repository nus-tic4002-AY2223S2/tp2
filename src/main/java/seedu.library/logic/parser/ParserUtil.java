package seedu.library.logic.parser;

import seedu.library.commons.core.index.Index;
import seedu.library.commons.util.StringUtil;
import seedu.library.model.book.Category;
import seedu.library.model.book.Edition;
import seedu.library.model.book.Author;
import seedu.library.model.book.Title;
import seedu.library.model.borrower.Borrower;
import seedu.library.logic.parser.exceptions.ParseException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Title parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Title.isValidTitle(trimmedName)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedName);
    }

    /**
     * Parses a {@code String author} into a {@code Author}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Author parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Author.isValidPhone(trimmedPhone)) {
            throw new ParseException(Author.MESSAGE_CONSTRAINTS);
        }
        return new Author(trimmedPhone);
    }

    /**
     * Parses a {@code String category} into an {@code Category}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code library} is invalid.
     */
    public static Category parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Category.isValidCategory(trimmedAddress)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        return new Category(trimmedAddress);
    }

    /**
     * Parses a {@code String edition} into an {@code Edition}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Edition parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Edition.isValidEmail(trimmedEmail)) {
            throw new ParseException(Edition.MESSAGE_CONSTRAINTS);
        }
        return new Edition(trimmedEmail);
    }

    /**
     * Parses a {@code String borrower} into a {@code Borrower}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code borrower} is invalid.
     */
    public static Borrower parseBorrower(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Borrower.isValidTagName(trimmedTag)) {
            throw new ParseException(Borrower.MESSAGE_CONSTRAINTS);
        }
        return new Borrower(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Borrower>}.
     */
    public static Set<Borrower> parseBorrowers(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Borrower> borrowerSet = new HashSet<>();
        for (String tagName : tags) {
            borrowerSet.add(parseBorrower(tagName));
        }
        return borrowerSet;
    }

}
