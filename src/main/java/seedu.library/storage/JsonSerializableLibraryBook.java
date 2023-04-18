package seedu.library.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.library.commons.exceptions.IllegalValueException;
import seedu.library.model.LibraryBook;
import seedu.library.model.ReadOnlyLibraryBook;
import seedu.library.model.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "library")
class JsonSerializableLibraryBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate book(s).";

    private final List<JsonAdaptedBook> books = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableLibraryBook(@JsonProperty("books") List<JsonAdaptedBook> books) {
        this.books.addAll(books);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableLibraryBook(ReadOnlyLibraryBook source) {
        books.addAll(source.getBookList().stream().map(JsonAdaptedBook::new).collect(Collectors.toList()));
    }

    /**
     * Converts this library book into the model's {@code LibraryBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LibraryBook toModelType() throws IllegalValueException {
        LibraryBook libraryBook = new LibraryBook();
        for (JsonAdaptedBook jsonAdaptedBook : books) {
            Book book = jsonAdaptedBook.toModelType();
            if (libraryBook.hasBook(book)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            libraryBook.addBook(book);
        }
        return libraryBook;
    }

}
