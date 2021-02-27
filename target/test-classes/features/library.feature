Feature: As a user, I should be able to login to the library app.

  Background: User already on login page
    Given User already on login page

  Scenario Outline:verify  Students   login

    Given the user login as a "<userName>" and "<password>"
    Then the "<expectedUser>" on  "<expectedPage>"
    @st
    Examples:
      | userName  | password   | expectedPage | expectedUser    |
      | student14 | password14 | books        | Test Student 14 |
      | student15 | password15 | books        | Test Student 15 |
      | student16 | password16 | books        | Test Student 16 |


  @librarian
  Scenario Outline:verify  librarians  login
    Given the user login as a "<userName>" and "<password>"
    Then the "<expectedUser>" on  "<expectedPage>"
    Examples:
      | userName    | password   | expectedPage | expectedUser      |
      | librarian21 | password21 | dashboard    | Test Librarian 21 |


  Scenario: As a student, I should be able to search books with different categories.(US-7)

    Then Student can choose from the list all the category dropdown
      | ALL                     |
      | Action and Adventure    |
      | Anthology               |
      | Classic                 |
      | Comic and Graphic Novel |
      | Crime and Detective     |
      | Drama                   |
      | Fable                   |
      | Fairy Tale              |
      | Fan-Fiction             |
      | Fantasy                 |
      | Historical Fiction      |
      | Horror                  |
      | Science Fiction         |
      | Biography/Autobiography |
      | Humor                   |
      | Romance                 |
      | Short Story             |
      | Essay                   |
      | Memoir                  |
      | Poetry                  |




