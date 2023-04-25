package seedu.library.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.library.testutil.TypicalBooks.getTypicalLibraryBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.library.commons.core.GuiSettings;
import seedu.library.model.LibraryBook;
import seedu.library.model.ReadOnlyLibraryBook;
import seedu.library.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;
/*
    @BeforeEach
    public void setUp() {
        JsonLibraryBookStorage addressBookStorage = new JsonLibraryBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {

        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {

        LibraryBook original = getTypicalLibraryBook();
        storageManager.saveLibraryBook(original);
        ReadOnlyLibraryBook retrieved = storageManager.readLibraryBook().get();
        assertEquals(original, new LibraryBook(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getLibraryBookFilePath());
    }
*/
}
