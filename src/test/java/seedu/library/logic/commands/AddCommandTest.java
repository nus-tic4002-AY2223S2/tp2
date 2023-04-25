package seedu.library.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.library.commons.core.GuiSettings;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.model.LibraryBook;
import seedu.library.model.Model;
import seedu.library.model.ReadOnlyLibraryBook;
import seedu.library.model.ReadOnlyUserPrefs;
import seedu.library.model.book.Book;
import seedu.library.testutil.BookBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }
/*
    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingBookAdded modelStub = new ModelStubAcceptingBookAdded();
        Book validBook = new BookBuilder().build();

        CommandResult commandResult = new AddCommand(validBook).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validBook), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBook), modelStub.booksAdded);
    }

    @Test
    public void execute_duplicateBook_throwsCommandException() {
        Book validBook = new BookBuilder().build();
        AddCommand addCommand = new AddCommand(validBook);
        ModelStub modelStub = new ModelStubWithBook(validBook);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }
*/
    @Test
    public void equals() {
        Book alice = new BookBuilder().withName("Alice").build();
        Book bob = new BookBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different book -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private abstract class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getLibraryBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        public void setLibraryBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        public void addBook(Book book) {
            throw new AssertionError("This method should not be called.");
        }

        public void setLibraryBook(ReadOnlyLibraryBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLibraryBook getLibraryBook() {
            throw new AssertionError("This method should not be called.");
        }

        public boolean hasBook(Book book) {
            throw new AssertionError("This method should not be called.");
        }

        public void deleteBook(Book target) {
            throw new AssertionError("This method should not be called.");
        }

        public void setBook(Book target, Book editedBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Book> getFilteredBookList() {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredBookList(Predicate<Book> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single book.
     */
    private abstract class ModelStubWithBook extends ModelStub {
        private final Book book;

        ModelStubWithBook(Book book) {
            requireNonNull(book);
            this.book = book;
        }

        @Override
        public boolean hasBook(Book book) {
            requireNonNull(book);
            return this.book.isSameBook(book);
        }
    }

    /**
     * A Model stub that always accept the book being added.
     */
    private abstract class ModelStubAcceptingBookAdded extends ModelStub {
        final ArrayList<Book> booksAdded = new ArrayList<>();

        @Override
        public boolean hasBook(Book book) {
            requireNonNull(book);
            return booksAdded.stream().anyMatch(book::isSameBook);
        }

        @Override
        public void addBook(Book book) {
            requireNonNull(book);
            booksAdded.add(book);
        }

        @Override
        public ReadOnlyLibraryBook getLibraryBook() {
            return new LibraryBook();
        }
    }

}
