package seedu.library.logic.commands;

import static seedu.library.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.library.testutil.TypicalBooks.getTypicalLibraryBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.UserPrefs;
import seedu.library.model.book.Book;
import seedu.library.testutil.BookBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
/*
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLibraryBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Book validBook = new BookBuilder().build();

        Model expectedModel = new ModelManager(model.getLibraryBook(), new UserPrefs());
        expectedModel.addBook(validBook);

        assertCommandSuccess(new AddCommand(validBook), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validBook), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Book bookInList = model.getLibraryBook().getBookList().get(0);
        assertCommandFailure(new AddCommand(bookInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }
*/
}
