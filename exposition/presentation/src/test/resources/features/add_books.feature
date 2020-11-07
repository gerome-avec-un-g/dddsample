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
    Then i have an error "FieldRequired" with message "[title]"

  Scenario: title has at least 1 characters
    Given a librarian
    When the user tries to add a book
      | title   |
      | [blank] |
    Then i have an error "FieldMinimumLength" with message "[title, , 1]"

  Scenario: title has less than 21 characters
    Given a librarian
    When the user tries to add a book
      | title                 |
      | 012345678901234567890 |
    Then i have an error "FieldMaximumLength" with message "[title, 012345678901234567890, 20]"
    # TODO better spec for error messages

  @inProgress
  Scenario: title is not just spaces
    Given a librarian
    When the user tries to add a book
      | title    |
      | [spaces] |
    Then i have an error "FieldIsEmpty" with message "[title]"

  #Rule: author validation
  Scenario: author is mandatory
    Given a librarian
    When the user tries to add a book
      | title      | author |
      | Foundation |        |
    Then i have an error "FieldRequired" with message "[author]"

  Scenario: author has at least 1 characters
    Given a librarian
    When the user tries to add a book
      | title      | author  |
      | Foundation | [blank] |
    Then i have an error "FieldMinimumLength" with message "[author, , 1]"

  Scenario: author has less than 26 characters
    Given a librarian
    When the user tries to add a book
      | title      | author           |
      | Foundation | AsimovAsimovAsimovAsimovAs |
    Then i have an error "FieldMaximumLength" with message "[author, AsimovAsimovAsimovAsimovAs, 25]"

  #Rule: can't add a second book with same title and author
  Scenario: a book can't be added twice
    Given a librarian
    When the user tries to add a book
      | title      | author       |
      | Foundation | Isaac Asimov |
      | Foundation | Isaac Asimov |
    Then i have an error "BookAlreadyExists" with message "[Book{title=Title{value='Foundation'}, author=Author{value='Isaac Asimov'}}]"

  #Rule: working case
  Scenario: a book can be added
    Given a librarian
    When the user tries to add a book
      | title      | author       |
      | Foundation | Isaac Asimov |
    Then the book is added
      | title      | author       |
      | Foundation | Isaac Asimov |