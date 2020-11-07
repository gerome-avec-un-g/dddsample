Feature: add a book
  a librarian can add a book

  # check Ghrekin 6 implementation for @Rule

  #Rule: authorization checks

  #Rule: title validation
    Scenario: title is mandatory
      Given a librarian
      When the user adds a book
        | title |
        |       |
      Then i have an error message "FieldRequired [title]"

  Scenario: title size is more than 1 characters
      Given a librarian
      When the user adds a book
        | title   |
        | [blank] |
      Then i have an error message "FieldMinimumLength [title, , 1]"

  Scenario: title size is less than 20 characters
      Given a librarian
      When the user adds a book
        | title                 |
        | 012345678901234567890 |
      Then i have an error message "FieldMaximumLength [title, 012345678901234567890, 20]"
    # TODO better spec for error messages

  #Rule: author validation
    @inProgress
    Scenario: check author : author is mandatory
      Given a librarian
      When the user adds a book
        | title |
        |       |
      Then i have an error message "FieldRequired [author]"
