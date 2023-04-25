package seedu.library.testutil;

import static seedu.library.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.library.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.library.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.library.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.library.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.library.logic.commands.AddCommand;
import seedu.library.logic.commands.EditCommand.EditBookDescriptor;
import seedu.library.model.book.Book;
import seedu.library.model.borrower.Borrower;

/**
 * A utility class for Book.
 */
public class BookUtil {

    /**
     * Returns an add command string for adding the {@code book}.
     */
    public static String getAddCommand(Book book) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(book);
    }

    /**
     * Returns the part of command string for the given {@code book}'s details.
     */
    public static String getPersonDetails(Book book) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + book.getTitle().title + " ");
        sb.append(PREFIX_PHONE + book.getAuthor().value + " ");
        sb.append(PREFIX_EMAIL + book.getEdition().value + " ");
        sb.append(PREFIX_ADDRESS + book.getCategory().value + " ");
        book.getBorrowers().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.borrowerName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditBookDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditBookDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.title).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Borrower> borrowers = descriptor.getTags().get();
            if (borrowers.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                borrowers.forEach(s -> sb.append(PREFIX_TAG).append(s.borrowerName).append(" "));
            }
        }
        return sb.toString();
    }
}
