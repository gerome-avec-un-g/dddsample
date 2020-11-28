Feature: add a book
  a librarian can add a book

  #Rule: authorization checks

  Rule: title validation
    Example: title is mandatory
      Given a librarian
      When the user tries to add a book
        | title | author       | type    |
        |       | Isaac Asimov | FICTION |
      Then i have an error "FieldRequired" with message "[title]"

    Example: title has at least 1 characters
      Given a librarian
      When the user tries to add a book
        | title   | author | type    |
        | [blank] |        | FICTION |
      Then i have an error "FieldMinimumLength" with message "[title, , 1]"

    Example: title has less than 41 characters
      Given a librarian
      When the user tries to add a book
        | title                 | author | type    |
        | 01234567890123456789012345678901234567890 |        | FICTION |
      Then i have an error "FieldMaximumLength" with message "[title, 01234567890123456789012345678901234567890, 40]"
    # TODO better spec for error messages

    @inProgress
    Example: title is not just spaces
      Given a librarian
      When the user tries to add a book
        | title    | author | type    |
        | [spaces] |        | FICTION |
      Then i have an error "FieldIsEmpty" with message "[title]"

  Rule: author validation
    Example: author is mandatory
      Given a librarian
      When the user tries to add a book
        | title      | author | type    |
        | Foundation |        | FICTION |
      Then i have an error "FieldRequired" with message "[author]"

    Example: author has at least 1 characters
      Given a librarian
      When the user tries to add a book
        | title      | author  | type    |
        | Foundation | [blank] | FICTION |
      Then i have an error "FieldMinimumLength" with message "[author, , 1]"

    Example: author has less than 26 characters
      Given a librarian
      When the user tries to add a book
        | title      | author                     | type    |
        | Foundation | AsimovAsimovAsimovAsimovAs | FICTION |
      Then i have an error "FieldMaximumLength" with message "[author, AsimovAsimovAsimovAsimovAs, 25]"

  Rule: a book can't be added twice
    Example: a book with same name and author can't be added twice
      Given a librarian
      When the user tries to add a book
        | title      | author       | type    |
        | Foundation | Isaac Asimov | FICTION |
        | Foundation | Isaac Asimov | FICTION |
      Then i have an error "BookAlreadyExists" with message "[Book{title=Title{value='Foundation'}, author=Author{value='Isaac Asimov'}, type=FICTION}]"

    Example: a book with same name and author can't be added twice even if type is different
      Given a librarian
      When the user tries to add a book
        | title      | author       | type       |
        | Foundation | Isaac Asimov | FICTION    |
        | Foundation | Isaac Asimov | TECHNOLOGY |
      Then i have an error "BookAlreadyExists" with message "[Book{title=Title{value='Foundation'}, author=Author{value='Isaac Asimov'}, type=TECHNOLOGY}]"


  Rule: working case
    Example: a book can be added
      Given a librarian
      When the user tries to add a book
        | title      | author       | type    |
        | Foundation | Isaac Asimov | FICTION |
      Then the book is added
        | title      | author       | type    |
        | Foundation | Isaac Asimov | FICTION |