Feature: a librarian displays books

  Background:
    Given a library with books
      | identifier                           | title              | author        | type       |
      | 7aa12dc1-5fba-4ab1-b5d2-b7d3736be3c2 | Clean Code         | Robert Martin | TECHNOLOGY |
      | 9196788b-8593-4a11-b7ed-0a820aea7f2f | Foundation         | Isaac Asimov  | FICTION    |
      | 8f0c195f-06f5-4a53-b53e-17555666f04c | Clean Architecture | Robert Martin | TECHNOLOGY |

  #Rule: authorization checks

  Rule: book quantity
    Example: a librarian can display all books sorted by author then by title
      Given a librarian
      When the user tries to display all books
      Then the books are displayed
        | identifier                           | title              | author        | type       |
        | 9196788b-8593-4a11-b7ed-0a820aea7f2f | Foundation         | Isaac Asimov  | FICTION    |
        | 8f0c195f-06f5-4a53-b53e-17555666f04c | Clean Architecture | Robert Martin | TECHNOLOGY |
        | 7aa12dc1-5fba-4ab1-b5d2-b7d3736be3c2 | Clean Code         | Robert Martin | TECHNOLOGY |
    #Example: 0 books

    #Rule: filter by author / style ? not ALL books