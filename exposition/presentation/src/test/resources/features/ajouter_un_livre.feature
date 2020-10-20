# language: fr

Fonctionnalité: ajouter un livre
  un bibliothéquaire ajoute un livre

  @important
  Scénario: check title : title is mandatory
    Etant donné a librarian
    Quand the user adds a book
      | title |
      |       |
    Alors i have an error message "FieldRequired [title]"

  Scénario: check title : title size is more than 1 characters
    Etant donné a librarian
    Quand the user adds a book
      | title   |
      | [blank] |
    Alors i have an error message "FieldMinimumLength [title, , 1]"

  Scénario: check title : title size is less than 20 characters
    Etant donné a librarian
    Quand the user adds a book
      | title                 |
      | 012345678901234567890 |
    Alors i have an error message "FieldMaximumLength [title, 012345678901234567890, 20]"

  @inProgress
  Scénario: check author : author is mandatory
    Etant donné a librarian
    Quand the user adds a book
      | title |
      |       |
    Alors i have an error message "FieldRequired [author]"