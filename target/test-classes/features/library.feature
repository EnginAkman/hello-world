Feature: As a user, I should be able to use whole libraryCT application

  Background: User already on login page
    Given User already on login page

  @wip
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


  Scenario:As a user, I should be able to logout from the library app(US-2).

    Given Student is on books page
    Then Student can click logout and logout


  Scenario: Students should have access to 2 modules (US-3-1)
    Given the student on the home page
    Then the user should see following modules
      | Books           |
      | Borrowing Books |


  Scenario: Librarians  should have access to 3 modules (US-3-2)
    Given the librarian on the homepage
    Then the librarian should see following modules
      | Dashboard       |
      | Books           |
      | Borrowing Books |


  Scenario:As a librarian, I should be able to add users from users page(US-4).
    When the librarian on the homepage
    Then add users with all valid info.
    And  Librarians able to close the add user window with close button
    Then Librarians able to edit user info.


  Scenario: As a librarian, I should be able to see book records on user page(US-5)
    Then verify that the default record is 10
    Then  Show records for <count> options
      | 5   |
      | 10  |
      | 15  |
      | 50  |
      | 100 |


  Scenario: As a students, I should be able to see tables with default info (US-6)
  Table columns names

    Given the user on the homepage
    Then the user shoulde see the following column names
      | Actions     |
      | ISBN        |
      | Name        |
      | Author      |
      | Category    |
      | Year        |
      | Borrowed By |

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


  Scenario: As a user, I should able to see my borrowing books.(US-8)
  borrowing books tabe columns names

    Given the user on the homepage
    When the user click Borrowing Books module
    Then the student shoulde see the following column names:
      | Action              |
      | Book Name           |
      | Borrowed Data       |
      | Planned Return Data |
      | Return Data         |
      | Is Returned         |


  Scenario: user group category features(US-9)
  verify user categories

    When the librarian on the homepage
    When the user click users moudle
    And the user click User Group dopdown
    Then the user should see the following options:
      | All       |
      | Librarian |
      | Students  |


  Scenario: verify user status (US-10)

    When the librarian on the homepage
    When the user click users moudle
    And the user click Status dopdown
    Then the user should see the following status options

      | ACTIVE   |
      | INACTIVE |

  @wip
  Scenario: user management table columns names (US-11)

    When the librarian on the homepage
    When the user click users moudle
    Then the librarian shoulde see the following column names:
      | Actions   |
      | User ID   |
      | Full Name |
      | Email     |
      | Group     |
      | Status    |


















