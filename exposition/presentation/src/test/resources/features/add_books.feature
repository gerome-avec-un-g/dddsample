Feature: add a book
  a librarian can add a book

  @important
  Scenario: check title : title is mandatory
    Given a librarian
    When the user adds a book
      | title |
      |       |
    Then i have an error message "FieldRequired [title]"

  Scenario: check title : title size is more than 1 characters
    Given a librarian
    When the user adds a book
      | title   |
      | [blank] |
    Then i have an error message "FieldMinimumLength [title, , 1]"

  Scenario: check title : title size is less than 20 characters
    Given a librarian
    When the user adds a book
      | title                 |
      | 012345678901234567890 |
    Then i have an error message "FieldMaximumLength [title, 012345678901234567890, 20]"

  @inProgress
  Scenario: check author : author is mandatory
    Given a librarian
    When the user adds a book
      | title |
      |       |
    Then i have an error message "FieldRequired [author]"