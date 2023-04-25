package seedu.library.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.library.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.library.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.library.testutil.TypicalIndexes.INDEX_SECOND_BOOK;
import static seedu.library.testutil.TypicalBooks.getTypicalLibraryBook;

import org.junit.jupiter.api.Test;

import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.UserPrefs;
import seedu.library.model.book.Book;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {
/*
    private Model model = new ModelManager(getTypicalLibraryBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Book bookToDelete = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_BOOK);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, bookToDelete);

        ModelManager expectedModel = new ModelManager(model.getLibraryBook(), new UserPrefs());
        expectedModel.deleteBook(bookToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_BOOK);

        Book bookToDelete = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_BOOK);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, bookToDelete);

        Model expectedModel = new ModelManager(model.getLibraryBook(), new UserPrefs());
        expectedModel.deleteBook(bookToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_BOOK);

        Index outOfBoundIndex = INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of library book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibraryBook().getBookList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_BOOK);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_BOOK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_BOOK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
*/
    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    /*
    private void showNoPerson(Model model) {
        model.updateFilteredBookList(p -> false);

        assertTrue(model.getFilteredBookList().isEmpty());
    }
    */

}
