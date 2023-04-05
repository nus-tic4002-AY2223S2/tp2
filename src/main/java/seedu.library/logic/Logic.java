package seedu.library.logic;

import javafx.collections.ObservableList;
import seedu.library.commons.core.GuiSettings;
import seedu.library.model.ReadOnlyLibraryBook;
import seedu.library.model.person.Person;
import seedu.library.logic.commands.CommandResult;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.logic.parser.exceptions.ParseException;

import java.nio.file.Path;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.library.model.Model#getLibraryBook()
     */
    ReadOnlyLibraryBook getLibraryBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getLibraryBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
