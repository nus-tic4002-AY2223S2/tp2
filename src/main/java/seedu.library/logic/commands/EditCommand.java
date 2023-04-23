package seedu.library.logic.commands;

import seedu.library.commons.core.Messages;
import seedu.library.commons.core.index.Index;
import seedu.library.commons.util.CollectionUtil;
import seedu.library.model.Model;
import seedu.library.model.book.*;
import seedu.library.model.borrower.Borrower;
import seedu.library.logic.commands.exceptions.CommandException;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static seedu.library.logic.parser.CliSyntax.*;
import static seedu.library.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Edits the details of an existing book in the library book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the book identified "
            + "by the index number used in the displayed book list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "TITLE] "
            + "[" + PREFIX_PHONE + "AUTHOR] "
            + "[" + PREFIX_EMAIL + "EDITION] "
            + "[" + PREFIX_ADDRESS + "CATEGORY] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "J K Rowling "
            + PREFIX_EMAIL + "1st Edition";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Book: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This book already exists in the library book.";

    private final Index index;
    private final EditBookDescriptor editBookDescriptor;

    /**
     * @param index of the book in the filtered book list to edit
     * @param editBookDescriptor details to edit the book with
     */
    public EditCommand(Index index, EditBookDescriptor editBookDescriptor) {
        requireNonNull(index);
        requireNonNull(editBookDescriptor);

        this.index = index;
        this.editBookDescriptor = new EditBookDescriptor(editBookDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Book bookToEdit = lastShownList.get(index.getZeroBased());
        Book editedBook = createEditedPerson(bookToEdit, editBookDescriptor);

        if (!bookToEdit.isSameBook(editedBook) && model.hasBook(editedBook)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setBook(bookToEdit, editedBook);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedBook));
    }

    /**
     * Creates and returns a {@code Book} with the details of {@code bookToEdit}
     * edited with {@code editBookDescriptor}.
     */
    private static Book createEditedPerson(Book bookToEdit, EditBookDescriptor editBookDescriptor) {
        assert bookToEdit != null;

        Title updatedTitle = editBookDescriptor.getName().orElse(bookToEdit.getTitle());
        Author updatedAuthor = editBookDescriptor.getPhone().orElse(bookToEdit.getAuthor());
        Edition updatedEdition = editBookDescriptor.getEmail().orElse(bookToEdit.getEdition());
        Category updatedCategory = editBookDescriptor.getAddress().orElse(bookToEdit.getCategory());
        Set<Borrower> updatedBorrowers = editBookDescriptor.getTags().orElse(bookToEdit.getBorrowers());

        return new Book(updatedTitle, updatedAuthor, updatedEdition, updatedCategory, updatedBorrowers);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editBookDescriptor.equals(e.editBookDescriptor);
    }

    /**
     * Stores the details to edit the book with. Each non-empty field value will replace the
     * corresponding field value of the book.
     */
    public static class EditBookDescriptor {
        private Title title;
        private Author author;
        private Edition edition;
        private Category category;
        private LinkedHashSet<Borrower> borrowers;

        public EditBookDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code borrowers} is used internally.
         */
        public EditBookDescriptor(EditBookDescriptor toCopy) {
            setName(toCopy.title);
            setPhone(toCopy.author);
            setEmail(toCopy.edition);
            setAddress(toCopy.category);
            setTags(toCopy.borrowers);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, author, edition, category, borrowers);
        }

        public void setName(Title title) {
            this.title = title;
        }

        public Optional<Title> getName() {
            return Optional.ofNullable(title);
        }

        public void setPhone(Author author) {
            this.author = author;
        }

        public Optional<Author> getPhone() {
            return Optional.ofNullable(author);
        }

        public void setEmail(Edition edition) {
            this.edition = edition;
        }

        public Optional<Edition> getEmail() {
            return Optional.ofNullable(edition);
        }

        public void setAddress(Category category) {
            this.category = category;
        }

        public Optional<Category> getAddress() {
            return Optional.ofNullable(category);
        }

        /**
         * Sets {@code borrowers} to this object's {@code borrowers}.
         * A defensive copy of {@code borrowers} is used internally.
         */
        public void setTags(LinkedHashSet<Borrower> borrowers) {
            this.borrowers = (borrowers != null) ? new LinkedHashSet<>(borrowers) : null;
        }

        /**
         * Returns an unmodifiable borrower set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code borrowers} is null.
         */
        public Optional<Set<Borrower>> getTags() {
            return (borrowers != null) ? Optional.of(Collections.unmodifiableSet(borrowers)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBookDescriptor)) {
                return false;
            }

            // state check
            EditBookDescriptor e = (EditBookDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
