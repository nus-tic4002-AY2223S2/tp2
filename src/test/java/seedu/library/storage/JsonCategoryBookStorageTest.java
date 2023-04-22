package seedu.library.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalBooks.ALICE;
import static seedu.library.testutil.TypicalBooks.HOON;
import static seedu.library.testutil.TypicalBooks.IDA;
import static seedu.library.testutil.TypicalBooks.getTypicalLibraryBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.library.commons.exceptions.DataConversionException;
import seedu.library.model.LibraryBook;
import seedu.library.model.ReadOnlyLibraryBook;

public class JsonCategoryBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCategoryBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyLibraryBook> readAddressBook(String filePath) throws Exception {
        return new JsonLibraryBookStorage(Paths.get(filePath)).readLibraryBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        LibraryBook original = getTypicalLibraryBook();
        JsonLibraryBookStorage jsonAddressBookStorage = new JsonLibraryBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveLibraryBook(original, filePath);
        ReadOnlyLibraryBook readBack = jsonAddressBookStorage.readLibraryBook(filePath).get();
        assertEquals(original, new LibraryBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addBook(HOON);
        original.removeBook(ALICE);
        jsonAddressBookStorage.saveLibraryBook(original, filePath);
        readBack = jsonAddressBookStorage.readLibraryBook(filePath).get();
        assertEquals(original, new LibraryBook(readBack));

        // Save and read without specifying file path
        original.addBook(IDA);
        jsonAddressBookStorage.saveLibraryBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readLibraryBook().get(); // file path not specified
        assertEquals(original, new LibraryBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyLibraryBook addressBook, String filePath) {
        try {
            new JsonLibraryBookStorage(Paths.get(filePath))
                    .saveLibraryBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new LibraryBook(), null));
    }
}
