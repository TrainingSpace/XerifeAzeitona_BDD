#Author: fernanda.menks@accenture.com
#Keywords Summary : BDD, Cucumber, Selenium, jUnit, Maven, java
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@Top
Feature: Zoo Adoption Center website
  The main objective of this feature file is to describe a series of scenarios, testing the functionality of the Zoo Adoption Center website

#  @Contact
#  Scenario: Check if the Contact form is working
#            The goal here is to test if the Contact form can be submitted without errors when filled with valid data.
#
#    Given I am on the zoo site
#    When I navigate to the contact page
#    And Fill the form with valid data
#    Then I check that the form has been submitted

#  @Adoption
#  Scenario: Submit adoption using valid data
#    			The main objective of this scenario is to setup an adoption using valid data in all fields.

    #	The use of Data Tables allows for changes to be made directly in the feature file with no need to change the code.
    # This technique gives more freedom to the person responsible for creating and maintaining the feature file,
    # but it also makes coding a little more complex so use it when the person responsible for the feature file is not
    # the same responsible for the step definition file.
#    Given I am on the zoo site
#    When I navigate to the adoption page
#    And Select the start date for adoption
#    And Check if a turtle is available
#    And I fill the form with the following data
#      | Description | Field Name     | Value                        |
#      | Name        | name_field     | Xerife Azeitona              |
#      | Address     | address_field  | 555, Oliveiras St. N         |
#      | Postal Code | postcode_field | J3N 4G9                      |
#      | Email       | email_field    | xerifeazeitona@oliveiras.com |
#    Then I check that the adoption has been set up

  @GoogleSearch
  Scenario: Make a Google search
            The goal here is to test if we can make a simple google search using the chromedriver

    Given I am on Google
    When I perform a search
    Then I check that the search was made
    