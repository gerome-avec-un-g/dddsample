Feature: add a book
  a librarian can add a book

  # check Ghrekin 6 implementation for @Rule

  #Rule: authorization checks

  #Rule: title validation
  Scenario: title is mandatory
    Given a librarian
    When the user tries to add a book
      | title |
      |       |
    Then i have an error message "FieldRequired [title]"

  Scenario: title has at least 1 characters
    Given a librarian
    When the user tries to add a book
      | title   |
      | [blank] |
    Then i have an error message "FieldMinimumLength [title, , 1]"

  Scenario: title has less than 21 characters
    Given a librarian
    When the user tries to add a book
      | title                 |
      | 012345678901234567890 |
    Then i have an error message "FieldMaximumLength [title, 012345678901234567890, 20]"
    # TODO better spec for error messages

  @inProgress
  Scenario: title is not just spaces
    Given a librarian
    When the user tries to add a book
      | title    |
      | [spaces] |
    Then i have an error message "FieldIsEmpty [title]"

  #Rule: author validation
  Scenario: author is mandatory
    Given a librarian
    When the user tries to add a book
      | title      | author |
      | Foundation |        |
    Then i have an error message "FieldRequired [author]"

  Scenario: author has at least 1 characters
    Given a librarian
    When the user tries to add a book
      | title      | author  |
      | Foundation | [blank] |
    Then i have an error message "FieldMinimumLength [author, , 1]"

  Scenario: author has less than 16 characters
    Given a librarian
    When the user tries to add a book
      | title      | author           |
      | Foundation | AsimovAsimovAsim |
    Then i have an error message "FieldMaximumLength [author, AsimovAsimovAsim, 15]"

  #Rule: can't add a second book with same title and author
  Scenario: a book can't be added twice
    Given a librarian
    When the user tries to add a book
      | title      | author       |
      | Foundation | Isaac Asimov |
      | Foundation | Isaac Asimov |
    Then i have an error message "BookAlreadyExists [Book{title=Title{value='Foundation'}, author=Author{value='Isaac Asimov'}}]"

  #Rule: working case
  Scenario: a book can be added
    Given a librarian
    When the user tries to add a book
      | title      | author       |
      | Foundation | Isaac Asimov |
    Then the book is added
      | title      | author       |
      | Foundation | Isaac Asimov |