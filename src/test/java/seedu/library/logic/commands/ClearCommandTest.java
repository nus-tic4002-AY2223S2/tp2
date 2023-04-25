package seedu.library.logic.commands;

import static seedu.library.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.library.testutil.TypicalBooks.getTypicalLibraryBook;

import org.junit.jupiter.api.Test;

//import seedu.library.model.AddressBook;
import seedu.library.model.Model;
import seedu.library.model.ModelManager;
import seedu.library.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

//        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
/*
    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalLibraryBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalLibraryBook(), new UserPrefs());
//        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
*/
}
