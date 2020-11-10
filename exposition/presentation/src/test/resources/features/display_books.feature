Feature: display books
  a librarian can display books

  Background:
    Given a library with books
      | title      | author        |
      | Foundation | Isaac Asimov  |
      | Clean Code | Robert Martin |

  #Rule: authorization checks

  Rule: working case
    Example: a librarian can display all books
      Given a librarian
      When the user tries to display all books
      Then the books are displayed
        | title      | author        |
        | Foundation | Isaac Asimov  |
        | Clean Code | Robert Martin |