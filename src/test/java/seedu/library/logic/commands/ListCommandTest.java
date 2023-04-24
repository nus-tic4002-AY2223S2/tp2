package seedu.library.logic.commands;

import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.library.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.library.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.library.testutil.TypicalBooks.getTypicalLibraryBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
/*
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLibraryBook(), new UserPrefs());
//        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_BOOK);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

 */
}
