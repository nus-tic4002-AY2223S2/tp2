package seedu.library.model;

import javafx.collections.ObservableList;
import seedu.library.commons.core.GuiSettings;
import seedu.library.model.book.Book;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * The API of the Model component.
 */
public interface Model {
//    String newUser = "";
    String getCurrentUser();
    void setCurrentUser(String s);

    /** {@code Predicate} that always evaluate to true */
    Predicate<Book> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' library book file path.
     */
    Path getLibraryBookFilePath();

    /**
     * Sets the user prefs' library book file path.
     */
    void setLibraryBookFilePath(Path libraryBookFilePath);

    /**
     * Replaces library book data with the data in {@code addressBook}.
     */
    void setLibraryBook(ReadOnlyLibraryBook libraryBook);

    /** Returns the AddressBook */
    ReadOnlyLibraryBook getLibraryBook();

    /**
     * Returns true if a book with the same identity as {@code book} exists in the library book.
     */
    boolean hasBook(Book book);

    /**
     * Deletes the given book.
     * The book must exist in the library book.
     */
    void deleteBook(Book target);

    /**
     * Adds the given book.
     * {@code book} must not already exist in the library book.
     */
    void addBook(Book book);

    /**
     * Replaces the given book {@code target} with {@code editedBook}.
     * {@code target} must exist in the library book.
     * The book identity of {@code editedBook} must not be the same as another existing book in the library book.
     */
    void setBook(Book target, Book editedBook);

    /** Returns an unmodifiable view of the filtered book list */
    ObservableList<Book> getFilteredBookList();

    /**
     * Updates the filter of the filtered book list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookList(Predicate<Book> predicate);
}
