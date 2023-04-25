---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Login : `login`

To Login to the application.

* User will only be able to borrow or run commands after login.

Format: `login [name]`

### Logout : `logout`

To Logout of the application

Format: `logout [name]`

### Adding a book : `add`

Adds a book to the category book.

Format: `add n/TITLE p/AUTHOR e/EDITION a/CATEGORY [t/TAG]...​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A book can have any number of borrowers (including 0)
</div>

Examples:
* `add n/HARRY POTTER p/J K Rowling e/1st Edition a/Software Engineering t/Roshan`
* `add n/HARRY POTTER p/J K Rowling e/2nd Edition a/Software Engineering t/Kang`

### Listing all books : `list`

Shows a list of all books in the category book.

Format: `list`

### Editing a book : `edit`

Edits an existing book in the category book.

Format: `edit INDEX [n/TITLE] [p/AUTHOR] [e/EDITION] [a/CATEGORY] [t/TAG]...​`

* Edits the book at the specified `INDEX`. The index refers to the index number shown in the displayed book list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing borrowers, the existing borrowers of the book will be removed i.e adding of borrowers is not cumulative.
* You can remove all the book’s borrowers by typing `t/` without
    specifying any borrowers after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the author number and edition category of the 1st book to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the title of the 2nd book to be `Betsy Crower` and clears all existing borrowers.

### Locating books by title: `find`

Finds books whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the title is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a book : `delete`

Deletes the specified book from the category book.

Format: `delete INDEX`

* Deletes the book at the specified `INDEX`.
* The index refers to the index number shown in the displayed book list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd book in the category book.
* `find Betsy` followed by `delete 1` deletes the 1st book in the results of the `find` command.

### Cancel : `cancel`

Removes the current user.

Format: `cancel INDEX`

* Removes the current user at specified `INDEX` from the library book, from the list of borrowers.
* The index **must be a positive integer** 1, 2, 3, …​

### Reserve : `reserve`

Reserve a Book.

Format: `reserve INDEX`

* Adds the current user at specified `INDEX` from the library book, to the list of borrowers.
* The index **must be a positive integer** 1, 2, 3, …​

### Return : `return`

Returns a book.

Format: `return INDEX`

* Disassociates the book at specified `INDEX` from the library book, from the borrower.
* The index **must be a positive integer** 1, 2, 3, …​

### Clearing all entries : `clear`

Clears all entries from the category book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/TITLE p/AUTHOR e/EDITION a/CATEGORY [t/TAG]...​` <br> e.g., `add n/HARRY POTTER p/J K Rowling e/1st Edition a/Software Engineering t/Roshan`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/TITLE] [p/AUTHOR] [e/EDITION] [a/CATEGORY] [t/TAG]…​`<br> e.g.,`edit 1 p/J K Rowling e/1st Edition`
**Borrow** | `borrow INDEX`<br> e.g. `borrow 2`
**Login** | `login <NAME>` <br> e.g. `login Kang`
**Logout** | `logout <NAME>` <br> e.g. `logout Kang`
**Reserve** | `reserve INDEX` <br> e.g. `reserve 8`
**Return** | `return INDEX` <br> e.g. `return 9`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`

## Sample Flow
--------------------------------------------------------------------------------------------------------------------
Sequential order
* login Kang
* help
* list
* borrow 1
* return 1
* delete 1
* add n/HARRY POTTER p/J K Rowling e/1st Edition a/Software Engineering t/Roshan
* reserve 2
* find harry
* logout kang

## How to run Jar File
--------------------------------------------------------------------------------------------------------------------
`Step 1:`
* copy jar file to any empty directory
* type cmd in explorer header (Windows)
* type java -jar <jar file name>
