package seedu.library.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.library.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.library.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.library.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.library.testutil.TypicalIndexes.INDEX_SECOND_BOOK;
import static seedu.library.testutil.TypicalBooks.getTypicalLibraryBook;

import org.junit.jupiter.api.Test;

import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.logic.commands.EditCommand.EditBookDescriptor;
import seedu.library.model.LibraryBook;
import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.UserPrefs;
import seedu.library.model.book.Book;
import seedu.library.testutil.BookBuilder;
import seedu.library.testutil.EditBookDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalLibraryBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Book editedBook = new BookBuilder().build();
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder(editedBook).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new LibraryBook(model.getLibraryBook()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBook = Index.fromOneBased(model.getFilteredBookList().size());
        Book lastBook = model.getFilteredBookList().get(indexLastBook.getZeroBased());

        BookBuilder personInList = new BookBuilder(lastBook);
        Book editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastBook, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new LibraryBook(model.getLibraryBook()), new UserPrefs());
        expectedModel.setBook(lastBook, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK, new EditBookDescriptor());
        Book editedBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new LibraryBook(model.getLibraryBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_BOOK);

        Book personInFilteredList = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book editedBook = new BookBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK,
                new EditBookDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new LibraryBook(model.getLibraryBook()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder(firstBook).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_BOOK, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_BOOK);

        // edit book in filtered list into a duplicate in library book
        Book bookInList = model.getLibraryBook().getBookList().get(INDEX_SECOND_BOOK.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_BOOK,
                new EditBookDescriptorBuilder(bookInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of library book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_BOOK);
        Index outOfBoundIndex = INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of library book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibraryBook().getBookList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditBookDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_BOOK, DESC_AMY);

        // same values -> returns true
        EditBookDescriptor copyDescriptor = new EditBookDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_BOOK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_BOOK, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_BOOK, DESC_BOB)));
    }

}
