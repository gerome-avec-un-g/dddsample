Feature: a librarian displays ? lists ? reads ? all or some ? filters ? authors

  Rule: depending on the quantity of authors, we display an information message or a list
    Example: if there is no authors, an information message is displayed
      Given the connected user is a librarian
      When the connected user lists all authors
      Then the author list is
        | first name | last name |
      And the information message is "empty author list"
    Example: if there is one author, we display a table with one row
      Given the connected user is a librarian
      And the existing authors are
        | identifier                           | first name | last name |
        | b1f241b0-d568-48d6-8dfc-3783302fb437 | Isaac      | Asimov    |
      When the connected user lists all authors
      Then the author list is
        | first name | last name |
        | Isaac      | Asimov    |
      And there is no information message
    Example: if there is multiple authors, we display a table with multiple rows, sorted by last name then first name
      Given the connected user is a librarian
      And the existing authors are
        | identifier                           | first name   | last name |
        | 2f61ae82-df6d-4d84-a68d-c0de24a54782 | Robert       | Martin    |
        | b1f241b0-d568-48d6-8dfc-3783302fb437 | Isaac        | Asimov    |
        | f28e0d3b-4050-4d7a-811c-e8996d975fee | George R. R. | Martin    |
      When the connected user lists all authors
      Then the author list is
        | last name | first name   |
        | Asimov    | Isaac        |
        | Martin    | George R. R. |
        | Martin    | Robert       |
      And there is no information message

#  TODO Rule: authorization checks
#  TODO Rule: filter by author / style ? not ALL books