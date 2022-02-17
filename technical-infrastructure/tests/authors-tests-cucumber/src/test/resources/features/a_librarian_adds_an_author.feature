Feature: a librarian adds an author

  Rule: the author's first name should be valid
    Example: the author's first name is mandatory
      Given the connected user is a librarian
      When the user tries to add an author
        | first name | last name |
        |            | Asimov    |
      Then the system raises the error "FieldIsRequired" with parameters "[first name]"

  Rule: the author's last name should be valid
    Example: the author's last name is mandatory
      Given the connected user is a librarian
      When the user tries to add an author
        | first name | last name |
        | Isaac      |           |
      Then the system raises the error "FieldIsRequired" with parameters "[last name]"
