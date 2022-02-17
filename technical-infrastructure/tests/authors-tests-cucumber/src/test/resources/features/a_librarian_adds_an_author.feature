Feature: a librarian adds an author
  # TODO add or create ?

  Rule: the author's first name should be valid
    Example: the author's first name is mandatory
      Given the connected user is a librarian
      When the user tries to add an author
        | first name | last name |
        |            | Asimov    |
      Then the system raises the error "FieldIsRequired" with parameters "[first name]"
    Example: the author's first name is valid
      Given the connected user is a librarian
      When the user tries to add an author
        | first name | last name |
        | Isaac      | Asimov    |
      Then the author is added
        | identifier                           | first name | last name |
        | c9a147a3-4060-49ea-a546-b02c0cf7d98a | Isaac      | Asimov    |

  Rule: the author's last name should be valid
    Example: the author's last name is mandatory
      Given the connected user is a librarian
      When the user tries to add an author
        | first name | last name |
        | Isaac      |           |
      Then the system raises the error "FieldIsRequired" with parameters "[last name]"
