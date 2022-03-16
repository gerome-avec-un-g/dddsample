Feature: add a book
  a librarian can add a book

  #Rule: authorization checks

  Rule: title validation
    Example: title is mandatory
      Given a librarian
      And Today is 2021-04-20
      When the user tries to add a book
        | title | author identifier                    | type    |
        |       | add948ea-a96f-4db6-9918-db070f7ebe6e | FICTION |
      Then i have an error "Field Is Mandatory" with parameters "[title]"
    Example: title has at least 1 characters
      Given a librarian
      And The time is 19:21:59
      When the user tries to add a book
        | title   | author identifier                    | type    |
        | [empty] | add948ea-a96f-4db6-9918-db070f7ebe6e | FICTION |
      Then i have an error "Field Is Too Short" with parameters "[title, , 1]"
    Example: title has at most 40 characters
      Given a librarian
      When the user tries to add a book
        | title                                     | author identifier                    | type    |
        | 01234567890123456789012345678901234567890 | add948ea-a96f-4db6-9918-db070f7ebe6e | FICTION |
      Then i have an error "Field Is Too Long" with parameters "[title, 01234567890123456789012345678901234567890, 40]"
    # TODO better spec for error messages
    @inProgress
    Example: title is not just spaces
      Given a librarian
      When the user tries to add a book
        | title    | author identifier                    | type    |
        | [empty] | add948ea-a96f-4db6-9918-db070f7ebe6e | FICTION |
      Then i have an error "Field Is Empty" with parameters "[title]"

  Rule: author identifier validation
    Example: author identifier is mandatory
      Given a librarian
      When the user tries to add a book
        | title      | author identifier | type    |
        | Foundation |                   | FICTION |
      Then i have an error "Invalid Identifier" with parameters "[null]"
    Example: author identifier has at least 1 characters
      Given a librarian
      When the user tries to add a book
        | title      | author identifier | type    |
        | Foundation | [empty]           | FICTION |
      Then i have an error "Invalid Identifier" with parameters "[]"
    Example: author identifier is not of the good format
      Given a librarian
      When the user tries to add a book
        | title      | author identifier | type    |
        | Foundation | Isaac Asimov      | FICTION |
      Then i have an error "Invalid Identifier" with parameters "[Isaac Asimov]"
  # TODO Example: author identifier exists

  Rule: a book with same name and author can't be added twice
    Example: a book with same name and author can't be added twice
      Given a librarian
      When the user tries to add a book
        | title      | author identifier                    | type    |
        | Foundation | add948ea-a96f-4db6-9918-db070f7ebe6e | FICTION |
        | Foundation | add948ea-a96f-4db6-9918-db070f7ebe6e | FICTION |
      Then i have an error "Book Already Exists" with parameters "[6dbf0947-3fd5-47da-90fa-2598b2331bb0, Foundation, add948ea-a96f-4db6-9918-db070f7ebe6e]"
    Example: a book with same name and author can't be added twice even if type is different
      Given a librarian
      When the user tries to add a book
        | title      | author identifier                    | type       |
        | Foundation | add948ea-a96f-4db6-9918-db070f7ebe6e | FICTION    |
        | Foundation | add948ea-a96f-4db6-9918-db070f7ebe6e | TECHNOLOGY |
      Then i have an error "Book Already Exists" with parameters "[6dbf0947-3fd5-47da-90fa-2598b2331bb0, Foundation, add948ea-a96f-4db6-9918-db070f7ebe6e]"

  Rule: working case
    Example: a book can be added
      Given a librarian
      When the user tries to add a book
        | title      | author identifier                    | type    |
        | Foundation | add948ea-a96f-4db6-9918-db070f7ebe6e | FICTION |
      Then the book is added
        | identifier                           | title      | author identifier                    | type    |
        | c9a147a3-4060-49ea-a546-b02c0cf7d98a | Foundation | add948ea-a96f-4db6-9918-db070f7ebe6e | FICTION |