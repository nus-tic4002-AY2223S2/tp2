package seedu.library.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.library.model.book.*;
import seedu.library.model.book.Author;
import seedu.library.model.borrower.Borrower;
import seedu.library.model.util.SampleDataUtil;

/**
 * A utility class to help with building Book objects.
 */
public class BookBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Title name;
    private Author author;
    private Edition edition;
    private Category category;
    private Set<Borrower> borrowers;

    /**
     * Creates a {@code BookBuilder} with the default details.
     */
    public BookBuilder() {
        name = new Title(DEFAULT_NAME);
        author = new Author(DEFAULT_PHONE);
        edition = new Edition(DEFAULT_EMAIL);
        category = new Category(DEFAULT_ADDRESS);
        borrowers = new HashSet<>();
    }

    /**
     * Initializes the BookBuilder with the data of {@code personToCopy}.
     */
    public BookBuilder(Book personToCopy) {
        name = personToCopy.getTitle();
        author = personToCopy.getAuthor();
        edition = personToCopy.getEdition();
        category = personToCopy.getCategory();
        borrowers = new HashSet<>(personToCopy.getBorrowers());
    }

    /**
     * Sets the {@code Title} of the {@code Book} that we are building.
     */
    public BookBuilder withName(String name) {
        this.name = new Title(name);
        return this;
    }

    /**
     * Parses the {@code borrowers} into a {@code Set<Borrower>} and set it to the {@code Book} that we are building.
     */
    public BookBuilder withTags(String ... tags) {
        this.borrowers = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code Book} that we are building.
     */
    public BookBuilder withAddress(String address) {
        this.category = new Category(address);
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code Book} that we are building.
     */
    public BookBuilder withPhone(String phone) {
        this.author = new Author(phone);
        return this;
    }

    /**
     * Sets the {@code Edition} of the {@code Book} that we are building.
     */
    public BookBuilder withEmail(String email) {
        this.edition = new Edition(email);
        return this;
    }

    public Book build() {
        return new Book(name, author, edition, category, borrowers);
    }

}
