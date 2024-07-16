Feature: Purchase a digital product in Blibli
  Scenario: place an order and cancel the order of digital product
    Given user launches the Blibli app
    When user selects bills & top-ups
    And choose other products
    Then verify list of choices displayed in topUp section
    When select Pulsa from top ups
    Then verify pulsa and data packages Page
    When enter phone number "085765160652" for purchase digital products
    And choose one nominal "Auto_switch_prepaid" from nominals Listed
    And Click make Payment button to proceed
    Then user navigated to login page to proceed checkout
    When user enters email "dineshbabu142004@gmail.com" to login
    And enters the password "Password@123" and clicks Login
    Then user redirects to checkout Page
    When selects dropdown of Payment Choice "Virtual Account"
    And Selects Bank "Bank BCA";
    Then verify "Bank BCA" is selected as the payment option
    And verify nominal "Auto_switch_prepaid" matches in payment page
    When Click Pay now Button to confirm order
    Then verify Order status and return to home
    When navigate to orderList and choose Bills & TopUp
    And View Ordered Product in ordered list
    Then Verify product details of same nominal "Auto_switch_prepaid"
    When cancel the ordered Product
    Then verify the "Auto_switch_prepaid" product in cancelled order section
