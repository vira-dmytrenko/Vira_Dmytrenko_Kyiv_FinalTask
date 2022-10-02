Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check site main functions
    Given User opens '<homePage>' page
    And User checks header visibility
    And User checks footer visibility
    And User checks search field visibility
    And User checks cart visibility
    And User checks register link visibility
    And User checks 'Sign in' link visibility
    When User opens shop by category popup
    And User checks shop by category popup visibility
    And User closes shop by category popup
    And User points at shopping cart
    And User checks shopping cart popup visibility
    Then User checks that shopping cart popup header is '<header>'

    Examples:
      | homePage              | header             |
      | https://www.ebay.com/ | Your cart is empty |

  Scenario Outline: Check SignIn menu with valid email address and password
    Given User opens '<homePage>' page
    And User clicks 'Sign In' link
    And User checks userId field visibility on sign in page
    When User inputs '<email>' into user id field
    And User clicks continue button
    And User inputs '<password>' in password field
    And User clicks 'Sign In' button
    Then User checks his '<name>' appears in top left corner of the page

    Examples:
      | homePage              | email                 | password        | name |
      | https://www.ebay.com/ | testebay60@google.com | ?96qq)fJT-Uk^GP | Vira |


  Scenario Outline: Check SignIn menu with invalid email or username
    Given User opens '<homePage>' page
    And User checks 'Sign in' link visibility
    And User clicks 'Sign In' link
    When User inputs '<invalid email or username>' into user id field
    And User clicks continue button
    Then User checks error message 'Oops, that's not a match.' appears above user id field

    Examples:
      | homePage              | invalid email or username |
      | https://www.ebay.com/ |                           |
      | https://www.ebay.com/ | @google.com               |
      | https://www.ebay.com/ | !ooppps                   |


  Scenario Outline: User checks search products
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    Then User checks that amount of products in search page is <amountOfProducts>
    And User checks that every product description on the page contains '<keyword>'

    Examples:
      | homePage              | keyword | amountOfProducts |
      | https://www.ebay.com/ | Samsung | 60               |


  Scenario Outline: User checks search products with filter by brand
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User clicks on '<brand>' checkbox
    Then User checks that every product description on the page contains '<brand>'

    Examples:
      | homePage              | keyword     | brand   |
      | https://www.ebay.com/ | Smart Watch | Apple   |
      | https://www.ebay.com/ | Smart Watch | Garmin  |


  Scenario Outline: User checks add product to cart
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User checks product name visibility
    And User clicks on product
    And User clicks 'Add to Cart' button on product
    And User checks shopping cart visibility
    Then User checks that cart header is '<header>'
    And User checks 'go to checkout' button visibility

    Examples:
      | homePage              | keyword      | header                 |
      | https://www.ebay.com/ | 274845052044 | Shopping cart (1 item) |
      | https://www.ebay.com/ | 175296729133 | Shopping cart (1 item) |


  Scenario Outline: Check delete product from cart
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User checks product name visibility
    And User clicks on product
    And User select '<phone model>'
    And User clicks 'Add to Cart' button on product
    And User checks shopping cart visibility
    Then User checks that cart header is '<header>'
    And User clicks on remove link
    And User checks cart title '<expectedCartTitle>'

    Examples:
      | homePage              | keyword      | header                 | phone model            | expectedCartTitle                       |
      | https://www.ebay.com/ | 184880838103 | Shopping cart (1 item) | Samsung Galaxy NOTE 10 | You don\'t have any items in your cart. |


  Scenario Outline: User can't add product to cart without choosing a phone model
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User checks product name visibility
    And User clicks on product
    And User clicks 'Add to Cart' button on product
    And User checks shopping cart visibility
    Then User checks '<warning message>' for phone model selector

    Examples:
      | homePage              | keyword      | warning message             |
      | https://www.ebay.com/ | 184880838103 | Please select a PHONE MODEL |

  Scenario Outline: User can't buy products with invalid quantity
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User checks product name visibility
    And User clicks on product
    Then User inputs '<value>' into quantity field
    And User checks '<warning message>' for quantity field

    Examples:
      | homePage              | keyword      | value | warning message                    |
      | https://www.ebay.com/ | 191771905021 | 0     | Please enter quantity of 1 or more |
      | https://www.ebay.com/ | 191771905021 | -1    | Please enter quantity of 1 or more |


  Scenario Outline: Check subtotal product price in cart
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User checks product name visibility
    And User clicks on product
    Then User inputs '<value>' into quantity field
    And User remembers current product price as 'product price'
    And User clicks 'Add to Cart' button on product
    And User checks shopping cart visibility
    And User checks subtotal cart amount for quantity of <value>

    Examples:
      | homePage              | keyword      | value  |
      | https://www.ebay.com/ | 274845052044 | 2      |
      | https://www.ebay.com/ | 175296729133 | 3      |

