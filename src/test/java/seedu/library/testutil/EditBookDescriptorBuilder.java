package seedu.library.testutil;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.library.logic.commands.EditCommand;
import seedu.library.logic.commands.EditCommand.EditBookDescriptor;
import seedu.library.model.book.Edition;
import seedu.library.model.book.Category;
import seedu.library.model.book.Author;
import seedu.library.model.book.Title;
import seedu.library.model.book.Book;
import seedu.library.model.borrower.Borrower;

/**
 * A utility class to help with building EditBookDescriptor objects.
 */
public class EditBookDescriptorBuilder {

    private EditBookDescriptor descriptor;

    public EditBookDescriptorBuilder() {
        descriptor = new EditCommand.EditBookDescriptor();
    }

    public EditBookDescriptorBuilder(EditBookDescriptor descriptor) {
        this.descriptor = new EditBookDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookDescriptor} with fields containing {@code book}'s details
     */
    public EditBookDescriptorBuilder(Book book) {
        descriptor = new EditBookDescriptor();
        descriptor.setName(book.getTitle());
        descriptor.setPhone(book.getAuthor());
        descriptor.setEmail(book.getEdition());
        descriptor.setAddress(book.getCategory());
        descriptor.setTags((LinkedHashSet<Borrower>) book.getBorrowers());
    }

    /**
     * Sets the {@code Title} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withName(String title) {
        descriptor.setName(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Author(phone));
        return this;
    }

    /**
     * Sets the {@code Edition} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Edition(email));
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Category(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Borrower>} and set it to the {@code EditBookDescriptor}
     * that we are building.
     */
    public EditBookDescriptorBuilder withTags(String... tags) {
        LinkedHashSet<Borrower> borrowerSet = (LinkedHashSet<Borrower>) Stream.of(tags).map(Borrower::new).collect(Collectors.toSet());
        descriptor.setTags(borrowerSet);
        return this;
    }

    public EditCommand.EditBookDescriptor build() {
        return descriptor;
    }
}
