package seedu.library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.library.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.library.testutil.Assert.assertThrows;
import static seedu.library.testutil.TypicalBooks.ALICE;
import static seedu.library.testutil.TypicalBooks.getTypicalLibraryBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.library.model.book.Book;
import seedu.library.model.book.exceptions.DuplicateBookException;
import seedu.library.testutil.BookBuilder;

public class CategoryBookTest {
/*
    private final LibraryBook libraryBook = new LibraryBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), libraryBook.getBookList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> libraryBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        LibraryBook newData = getTypicalLibraryBook();
        libraryBook.resetData(newData);
        assertEquals(newData, libraryBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Book editedAlice = new BookBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Book> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicateBookException.class, () -> libraryBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> libraryBook.hasBook(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(libraryBook.hasBook(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        libraryBook.addBook(ALICE);
        assertTrue(libraryBook.hasBook(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        libraryBook.addBook(ALICE);
        Book editedAlice = new BookBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(libraryBook.hasBook(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> libraryBook.getBookList().remove(0));
    }
*/
    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    /*
    private static class AddressBookStub implements ReadOnlyLibraryBook {
        private final ObservableList<Book> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Book> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Book> getBookList() {
            return persons;
        }
    }
*/
}
