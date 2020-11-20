Feature: display books
  a librarian can display books

  Background:
    Given a library with books
      | title      | author        | type       |
      | Foundation | Isaac Asimov  | FICTION    |
      | Clean Code | Robert Martin | TECHNOLOGY |

  #Rule: authorization checks

  Rule: working case
    Example: a librarian can display all books
      Given a librarian
      When the user tries to display all books
      Then the books are displayed
        | title      | author        | type       |
        | Foundation | Isaac Asimov  | FICTION    |
        | Clean Code | Robert Martin | TECHNOLOGY |