package seedu.library.model;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.library.commons.core.GuiSettings;
import seedu.library.commons.core.LogsCenter;
import seedu.library.model.book.Book;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the in-memory model of the library book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LibraryBook libraryBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Book> filteredBooks;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyLibraryBook libraryBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(libraryBook, userPrefs);

        logger.fine("Initializing with library book: " + libraryBook + " and user prefs " + userPrefs);

        this.libraryBook = new LibraryBook(libraryBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBooks = new FilteredList<>(this.libraryBook.getBookList());
    }

    public ModelManager() {
        this(new LibraryBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getLibraryBookFilePath() {
        return userPrefs.getLibraryBookFilePath();
    }

    @Override
    public void setLibraryBookFilePath(Path libraryBookFilePath) {
        requireNonNull(libraryBookFilePath);
        userPrefs.setLibraryBookFilePath(libraryBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setLibraryBook(ReadOnlyLibraryBook libraryBook) {
        this.libraryBook.resetData(libraryBook);
    }

    @Override
    public ReadOnlyLibraryBook getLibraryBook() {
        return libraryBook;
    }

    @Override
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return libraryBook.hasBook(book);
    }

    @Override
    public void deleteBook(Book target) {
        libraryBook.removeBook(target);
    }

    @Override
    public void addBook(Book book) {
        libraryBook.addBook(book);
        updateFilteredBookList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setBook(Book target, Book editedBook) {
        requireAllNonNull(target, editedBook);

        libraryBook.setBook(target, editedBook);
    }

    //=========== Filtered Book List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Book} backed by the internal list of
     * {@code versionedLibraryBook}
     */
    @Override
    public ObservableList<Book> getFilteredBookList() {
        return filteredBooks;
    }

    @Override
    public void updateFilteredBookList(Predicate<Book> predicate) {
        requireNonNull(predicate);
        filteredBooks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return libraryBook.equals(other.libraryBook)
                && userPrefs.equals(other.userPrefs)
                && filteredBooks.equals(other.filteredBooks);
    }

}
