package seedu.library.model.util;

import seedu.library.model.LibraryBook;
import seedu.library.model.ReadOnlyLibraryBook;
import seedu.library.model.book.*;
import seedu.library.model.borrower.Borrower;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Book[] getSamplePersons() {
        return new Book[] {
            new Book(new Title("Alex Yeoh"), new Author("87438807"), new Edition("alexyeoh@example.com"),
                new Category("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Book(new Title("Bernice Yu"), new Author("99272758"), new Edition("berniceyu@example.com"),
                new Category("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Book(new Title("Charlotte Oliveiro"), new Author("93210283"), new Edition("charlotte@example.com"),
                new Category("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Book(new Title("David Li"), new Author("91031282"), new Edition("lidavid@example.com"),
                new Category("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Book(new Title("Irfan Ibrahim"), new Author("92492021"), new Edition("irfan@example.com"),
                new Category("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Book(new Title("Roy Balakrishnan"), new Author("92624417"), new Edition("royb@example.com"),
                new Category("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyLibraryBook getSampleLibraryBook() {
        LibraryBook sampleLb = new LibraryBook();
        for (Book sampleBook : getSamplePersons()) {
            sampleLb.addBook(sampleBook);
        }
        return sampleLb;
    }

    /**
     * Returns a borrower set containing the list of strings given.
     */
    public static Set<Borrower> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Borrower::new)
                .collect(Collectors.toSet());
    }
}
