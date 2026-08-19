@tag
Feature: Purchase the order from Ecommerce Website

Background:
Given I landed on Ecommerce page

@tag2
Scenario Outline: Positive Test of Submitting the order
Given Logged in with username <name> and password <password>
When I add the product <productname> to Cart
And Checkout <productname> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

Examples:
| name 					| password 	|productname |
|chetnasharma@gmail.com |Chetna@123 |ZARA COAT 3 |
