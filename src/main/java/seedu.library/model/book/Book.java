package seedu.library.model.book;

import seedu.library.model.borrower.Borrower;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.library.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Book in the library book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Book {

    // Identity fields
    private final Title title;
    private final Author author;
    private final Edition edition;

    // Data fields
    private final Category category;
    private final Set<Borrower> borrowers = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Book(Title title, Author author, Edition edition, Category category, Set<Borrower> borrowers) {
        requireAllNonNull(title, author, edition, category, borrowers);
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.category = category;
        this.borrowers.addAll(borrowers);
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Edition getEdition() {
        return edition;
    }

    public Category getCategory() {
        return category;
    }

    /**
     * Returns an immutable borrower set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Borrower> getBorrowers() {
        return Collections.unmodifiableSet(borrowers);
    }

    /**
     * Returns true if both persons have the same title.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameBook(Book otherBook) {
        if (otherBook == this) {
            return true;
        }

        return otherBook != null
                && otherBook.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Book)) {
            return false;
        }

        Book otherBook = (Book) other;
        return otherBook.getTitle().equals(getTitle())
                && otherBook.getAuthor().equals(getAuthor())
                && otherBook.getEdition().equals(getEdition())
                && otherBook.getCategory().equals(getCategory())
                && otherBook.getBorrowers().equals(getBorrowers());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, author, edition, category, borrowers);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Author: ")
                .append(getAuthor())
                .append("; Edition: ")
                .append(getEdition())
                .append("; Category: ")
                .append(getCategory());

        Set<Borrower> borrowers = getBorrowers();
        if (!borrowers.isEmpty()) {
            builder.append("; Borrowers: ");
            borrowers.forEach(builder::append);
        }
        return builder.toString();
    }

}
