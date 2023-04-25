package seedu.library.model;

import javafx.collections.ObservableList;
import seedu.library.model.book.Book;

/**
 * Unmodifiable view of an library book
 */
public interface ReadOnlyLibraryBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Book> getBookList();

}
