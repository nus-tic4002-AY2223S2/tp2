package seedu.library.model;

import javafx.collections.ObservableList;
import seedu.library.model.book.Book;
import seedu.library.model.book.UniqueBookList;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the library-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class LibraryBook implements ReadOnlyLibraryBook {

    private final UniqueBookList books;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        books = new UniqueBookList();
    }

    public LibraryBook() {}

    /**
     * Creates an LibraryBook using the Books in the {@code toBeCopied}
     */
    public LibraryBook(ReadOnlyLibraryBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the book list with {@code books}.
     * {@code books} must not contain duplicate books.
     */
    public void setBooks(List<Book> books) {
        this.books.setBooks(books);
    }

    /**
     * Resets the existing data of this {@code LibraryBook} with {@code newData}.
     */
    public void resetData(ReadOnlyLibraryBook newData) {
        requireNonNull(newData);

        setBooks(newData.getBookList());
    }

    //// book-level operations

    /**
     * Returns true if a book with the same identity as {@code book} exists in the library book.
     */
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return books.contains(book);
    }

    /**
     * Adds a book to the library book.
     * The book must not already exist in the library book.
     */
    public void addBook(Book b) {
        books.add(b);
    }

    /**
     * Replaces the given book {@code target} in the list with {@code editedBook}.
     * {@code target} must exist in the library book.
     * The book identity of {@code editedBook} must not be the same as another existing book in the library book.
     */
    public void setBook(Book target, Book editedBook) {
        requireNonNull(editedBook);

        books.setBook(target, editedBook);
    }

    /**
     * Removes {@code key} from this {@code LibraryBook}.
     * {@code key} must exist in the library book.
     */
    public void removeBook(Book key) {
        books.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return books.asUnmodifiableObservableList().size() + " books";
        // TODO: refine later
    }

    @Override
    public ObservableList<Book> getBookList() {
        return books.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LibraryBook // instanceof handles nulls
                && books.equals(((LibraryBook) other).books));
    }

    @Override
    public int hashCode() {
        return books.hashCode();
    }
}
