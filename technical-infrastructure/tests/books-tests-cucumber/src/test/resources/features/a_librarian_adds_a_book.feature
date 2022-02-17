Feature: add a book
  a librarian can add a book

  #Rule: authorization checks

  Rule: title validation
    Example: title is mandatory
      Given a librarian
      And Today is 2021-04-20
      When the user tries to add a book
        | title | author       | type    |
        |       | Isaac Asimov | FICTION |
      Then i have an error "FieldIsRequired" with parameters "[title]"

    Example: title has at least 1 characters
      Given a librarian
      And The time is 19:21:59
      When the user tries to add a book
        | title   | author | type    |
        | [blank] |        | FICTION |
      Then i have an error "FieldMinimumLength" with parameters "[title, , 1]"

    Example: title has at most 40 characters
      Given a librarian
      When the user tries to add a book
        | title                                     | author | type    |
        | 01234567890123456789012345678901234567890 |        | FICTION |
      Then i have an error "FieldMaximumLength" with parameters "[title, 01234567890123456789012345678901234567890, 40]"
    # TODO better spec for error messages

    @inProgress
    Example: title is not just spaces
      Given a librarian
      When the user tries to add a book
        | title    | author | type    |
        | [spaces] |        | FICTION |
      Then i have an error "FieldIsEmpty" with parameters "[title]"

  Rule: author validation
    Example: author is mandatory
      Given a librarian
      When the user tries to add a book
        | title      | author | type    |
        | Foundation |        | FICTION |
      Then i have an error "FieldIsRequired" with parameters "[author]"

    Example: author has at least 1 characters
      Given a librarian
      When the user tries to add a book
        | title      | author  | type    |
        | Foundation | [blank] | FICTION |
      Then i have an error "FieldMinimumLength" with parameters "[author, , 1]"

    Example: author has at most 25 characters
      Given a librarian
      When the user tries to add a book
        | title      | author                     | type    |
        | Foundation | AsimovAsimovAsimovAsimovAs | FICTION |
      Then i have an error "FieldMaximumLength" with parameters "[author, AsimovAsimovAsimovAsimovAs, 25]"

  Rule: a book with same name and author can't be added twice
    Example: a book with same name and author can't be added twice
      Given a librarian
      When the user tries to add a book
        | title      | author       | type    |
        | Foundation | Isaac Asimov | FICTION |
        | Foundation | Isaac Asimov | FICTION |
      Then i have an error "BookAlreadyExists" with parameters "[Foundation, Isaac Asimov]"
    Example: a book with same name and author can't be added twice even if type is different
      Given a librarian
      When the user tries to add a book
        | title      | author       | type       |
        | Foundation | Isaac Asimov | FICTION    |
        | Foundation | Isaac Asimov | TECHNOLOGY |
      Then i have an error "BookAlreadyExists" with parameters "[Foundation, Isaac Asimov]"

  Rule: working case
    Example: a book can be added
      Given a librarian
      When the user tries to add a book
        | title      | author       | type    |
        | Foundation | Isaac Asimov | FICTION |
      Then the book is added
        | identifier                           | title      | author       | type    |
        | c9a147a3-4060-49ea-a546-b02c0cf7d98a | Foundation | Isaac Asimov | FICTION |