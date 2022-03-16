Feature: a librarian displays books

  Background:
    Given a library with books
      | identifier                           | title                  | author identifier                    | type       |
      | 7aa12dc1-5fba-4ab1-b5d2-b7d3736be3c2 | Clean Code             | 2f61ae82-df6d-4d84-a68d-c0de24a54782 | TECHNOLOGY |
      | 9196788b-8593-4a11-b7ed-0a820aea7f2f | Foundation             | b1f241b0-d568-48d6-8dfc-3783302fb437 | FICTION    |
      | 8f0c195f-06f5-4a53-b53e-17555666f04c | Clean Architecture     | 2f61ae82-df6d-4d84-a68d-c0de24a54782 | TECHNOLOGY |
      | a473a317-2103-4150-92c6-dba8ebea3a0a | A Song of Ice and Fire | f28e0d3b-4050-4d7a-811c-e8996d975fee | FICTION    |
    And a library with authors
      | identifier                           | first name   | last name |
      | 2f61ae82-df6d-4d84-a68d-c0de24a54782 | Robert       | Martin    |
      | b1f241b0-d568-48d6-8dfc-3783302fb437 | Isaac        | Asimov    |
      | f28e0d3b-4050-4d7a-811c-e8996d975fee | George R. R. | Martin    |
  #Rule: authorization checks

  Rule: book quantity
    #Example: 0 book
    #Example: 1 book
    Example: a librarian can display all books sorted by author's last name, then author's first name then by title
      Given a librarian
      When the user tries to display all books
      Then the books are displayed
        | identifier                           | title                  | author first name | author last name | type       |
        | 9196788b-8593-4a11-b7ed-0a820aea7f2f | Foundation             | Isaac             | Asimov           | FICTION    |
        | a473a317-2103-4150-92c6-dba8ebea3a0a | A Song of Ice and Fire | George R. R.      | Martin           | FICTION    |
        | 8f0c195f-06f5-4a53-b53e-17555666f04c | Clean Architecture     | Robert            | Martin           | TECHNOLOGY |
        | 7aa12dc1-5fba-4ab1-b5d2-b7d3736be3c2 | Clean Code             | Robert            | Martin           | TECHNOLOGY |

    #Rule: filter by author / style ? not ALL books