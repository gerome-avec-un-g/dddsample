Feature: add a book
  a librarian can add a book

Scenario: check title : title size is less than 20
  Given a librarian
  When the user adds a book
  |title|
  |012345678901234567890|
  Then i have an error message "FieldMaximumLength [title, 012345678901234567890, 20]"