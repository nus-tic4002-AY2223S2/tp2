package seedu.library.testutil;

import seedu.library.model.LibraryBook;
import seedu.library.model.book.Book;

/**
 * A utility class to help with building Librarybook objects.
 * Example usage: <br>
 *     {@code LibraryBook ab = new LibraryBookBuilder().withBook("John", "Doe").build();}
 */
public class LibraryBookBuilder {

    private LibraryBook libraryBook;

    public LibraryBookBuilder() {
        libraryBook = new LibraryBook();
    }

    public LibraryBookBuilder(LibraryBook libraryBook) {
        this.libraryBook = libraryBook;
    }

    /**
     * Adds a new {@code Book} to the {@code AddressBook} that we are building.
     */
    public LibraryBookBuilder withBook(Book book) {
        libraryBook.addBook(book);
        return this;
    }

    public LibraryBook build() {
        return libraryBook;
    }
}
