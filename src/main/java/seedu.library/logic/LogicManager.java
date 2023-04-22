package seedu.library.logic;

import javafx.collections.ObservableList;
import seedu.library.commons.core.GuiSettings;
import seedu.library.commons.core.LogsCenter;
import seedu.library.model.Model;
import seedu.library.model.ReadOnlyLibraryBook;
import seedu.library.model.book.Book;
import seedu.library.storage.Storage;
import seedu.library.logic.commands.Command;
import seedu.library.logic.commands.CommandResult;
import seedu.library.logic.commands.exceptions.CommandException;
import seedu.library.logic.parser.LibraryBookParser;
import seedu.library.logic.parser.exceptions.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final LibraryBookParser libraryBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        libraryBookParser = new LibraryBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = libraryBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveLibraryBook(model.getLibraryBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyLibraryBook getLibraryBook() {
        return model.getLibraryBook();
    }

    @Override
    public ObservableList<Book> getFilteredPersonList() {
        return model.getFilteredBookList();
    }

    @Override
    public Path getLibraryBookFilePath() {
        return model.getLibraryBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
