Feature: a librarian adds an author

  # TODO scenario outline ? https://cucumber.io/docs/gherkin/reference/

  Rule: the author's first name should be valid
    Example: the author's first name is mandatory
      Given the connected user is a librarian
      When the connected user tries to add an author
        | first name | last name |
        |            | Asimov    |
      Then the system raises the error "Field Is Mandatory" with parameters "[first name]"
    Example: the author's first name can't be empty
      Given the connected user is a librarian
      When the connected user tries to add an author
        | first name | last name |
        | [empty]    | Asimov    |
      Then the system raises the error "Field Is Not Properly Formatted" with parameters "[first name, , [a-zA-Z \.-]{1,40}]"
    Example: an author's first name with one character is valid
      Given the connected user is a librarian
      When the connected user tries to add an author
        | first name | last name |
        | I          | Asimov    |
      Then the author is added
        | identifier                           | first name | last name |
        | c9a147a3-4060-49ea-a546-b02c0cf7d98a | I          | Asimov    |
    Example: an author's first name with 40 characters is valid
      Given the connected user is a librarian
      When the connected user tries to add an author
        | first name                               | last name |
        | IsaacIsaacIsaacIsaacIsaacIsaacIsaacIsaac | Asimov    |
      Then the author is added
        | identifier                           | first name                               | last name |
        | c9a147a3-4060-49ea-a546-b02c0cf7d98a | IsaacIsaacIsaacIsaacIsaacIsaacIsaacIsaac | Asimov    |
    Example: an author's first name with 41 characters is invalid
      Given the connected user is a librarian
      When the connected user tries to add an author
        | first name                                | last name |
        | IsaacIsaacIsaacIsaacIsaacIsaacIsaacIsaacI | Asimov    |
      Then the system raises the error "Field Is Not Properly Formatted" with parameters "[first name, IsaacIsaacIsaacIsaacIsaacIsaacIsaacIsaacI, [a-zA-Z \.-]{1,40}]"
    Example: hyphen is valid
      Given the connected user is a librarian
      When the connected user tries to add an author
        | first name | last name |
        | Jean-Paul  | Asimov    |
      Then the author is added
        | identifier                           | first name | last name |
        | c9a147a3-4060-49ea-a546-b02c0cf7d98a | Jean-Paul  | Asimov    |
    Example: point is valid
      Given the connected user is a librarian
      When the connected user tries to add an author
        | first name   | last name |
        | George R. R. | Martin    |
      Then the author is added
        | identifier                           | first name   | last name |
        | c9a147a3-4060-49ea-a546-b02c0cf7d98a | George R. R. | Martin    |
    Example: space is valid
      Given the connected user is a librarian
      When the connected user tries to add an author
        | first name   | last name |
        | George R. R. | Martin    |
      Then the author is added
        | identifier                           | first name   | last name |
        | c9a147a3-4060-49ea-a546-b02c0cf7d98a | George R. R. | Martin    |

  Rule: the author's last name should be valid
    Example: the author's last name is mandatory
      Given the connected user is a librarian
      When the connected user tries to add an author
        | first name | last name |
        | Isaac      |           |
      Then the system raises the error "Field Is Mandatory" with parameters "[last name]"
  # TODO all other rules same as first name

  # TODO more complex naming conventions ? USA with middle letter, J.R.R. Tolkien, just 1 pseudo...

  Rule: two authors with the same first name and last name can't be added
    Example: two authors with the same first name and last name can't be added
      Given the connected user is a librarian
      And the existing authors are
        | identifier                           | first name | last name |
        | b1f241b0-d568-48d6-8dfc-3783302fb437 | Isaac      | Asimov    |
      When the connected user tries to add an author
        | first name | last name |
        | Isaac      | Asimov    |
      Then the system raises the error "Author Already Exists" with parameters "[b1f241b0-d568-48d6-8dfc-3783302fb437, Isaac, Asimov]"
