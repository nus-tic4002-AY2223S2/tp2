package seedu.library.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.library.model.book.Book;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Book}.
 */
public class BookCard extends UiPart<Region> {

    private static final String FXML = "BookListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on LibraryBook level 4</a>
     */

    public final Book book;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label author;
    @FXML
    private Label category;
    @FXML
    private Label edition;
    @FXML
    private FlowPane borrower;

    /**
     * Creates a {@code BookCode} with the given {@code Book} and index to display.
     */
    public BookCard(Book book, int displayedIndex) {
        super(FXML);
        this.book = book;
        id.setText(displayedIndex + ". ");
        title.setText(book.getTitle().title);
        author.setText(book.getAuthor().value);
        category.setText(book.getCategory().value);
        edition.setText(book.getEdition().value);
        book.getBorrowers().stream().forEach(tag -> borrower.getChildren().add(new Label(tag.borrowerName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookCard)) {
            return false;
        }

        // state check
        BookCard card = (BookCard) other;
        return id.getText().equals(card.id.getText())
                && book.equals(card.book);
    }
}
