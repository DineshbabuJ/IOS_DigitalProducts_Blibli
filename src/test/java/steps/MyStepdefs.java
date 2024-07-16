package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.*;

import java.net.MalformedURLException;
import java.net.URL;

public class MyStepdefs {
    HomePage homePage;
    DigitalPage digitalPage;
    LoginPage loginPage;
    CheckOutPage checkOutPage;
    OrderList orderListPage;
    AppiumDriver driver;
    @Given("user launches the Blibli app")
    public void userLaunchesTheBlibliApp() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITEST");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 13");
        capabilities.setCapability("udid", "4A253E5E-1589-46B9-AB26-3FEA4C4A3AA2");
//        capabilities.setCapability("udid", "E9D7DAA9-0F22-4062-BE56-33A9D5F08316");
        capabilities.setCapability("bundleId", "com.blibli.mobile");
        capabilities.setCapability("noReset", true);
        driver= new IOSDriver(new URL("http://0.0.0.0:4720/wd/hub"),capabilities);
    }
    @When("user selects bills & top-ups")
    public void userSelectsBillsTopUps() {
        homePage=new HomePage(driver);
        Assert.assertTrue("bills Section Not Clickable",homePage.isClickable(homePage.BILLS_TOPUP));
        homePage.chooseBillsTopUpSection();
    }

    @And("choose other products")
    public void chooseOtherProducts() {
        Assert.assertTrue("Other Products Not Clickable",homePage.isClickable(homePage.OTHER_PRODUCTS));
        homePage.clickotherProducts();
    }

    @Then("verify list of choices displayed in topUp section")
    public void verifyListOfChoicesDisplayedInTopUpSection() {
        digitalPage= new DigitalPage(driver);
        Assert.assertTrue("not navigated to bills section",digitalPage.isDisplayed(digitalPage.BILLS_NAV));
        Assert.assertTrue("bills section is not displayed",digitalPage.isDisplayed(digitalPage.TOP_UP_TITLE));
    }

    @When("select Pulsa from top ups")
    public void selectPulsaFromTopUps() {
        homePage.selectPulsa();
    }

    @Then("verify pulsa and data packages Page")
    public void verifyPulsaAndDataPackagesPage() {
        Assert.assertTrue("Not Navigated to PULSA section ",digitalPage.isTitleVisible(digitalPage.PULSA_TITLE_NAV));
    }

    @When("enter phone number {string} for purchase digital products")
    public void enterPhoneNumberForPurchaseDigitalProducts(String number) {
        Assert.assertTrue("Number Field not editable ",digitalPage.isEnabled(digitalPage.PHONE_NO));
        digitalPage.enterPhoneNumber(number);
        Assert.assertEquals("Phone number mismatch",number,digitalPage.getInput());
    }

//    @Then("verify list of nominals are listed")
//    public void verifyListOfNominalsAreListed() {
//        Assert.assertTrue("NO Nominals Are listed",digitalPage.isNominalsListed());
//    }

    @When("choose one nominal {string} from nominals Listed")
    public void chooseOneNominalFromNominalsListed(String nominal){
        digitalPage.chooseNominal(nominal);
    }

    @And("Click make Payment button to proceed")
    public void clickMakePaymentButtonToProceed() {
        Assert.assertTrue("Payment button not displayed",digitalPage.isEnabled(digitalPage.MAKE_PAYMENT_BTN));
        digitalPage.makePayment();
    }

    @Then("user navigated to login page to proceed checkout")
    public void userNavigatedToLoginPageToProceedCheckout() {
        loginPage=new LoginPage(driver);
        Assert.assertTrue("Not navigated to login",loginPage.isEnabled(loginPage.EMAIL_FIELD));
    }

    @When("user enters email {string} to login")
    public void userEntersEmailToLogin(String email) {
        loginPage.EnterEmail(email);
        loginPage.clickLogin();
    }

    @Then("verifies emailfield reflects same email id entered {string}")
    public void verifiesEmailfieldReflectsSameEmailIdEntered(String email) {
        Assert.assertEquals("Email mismatch error",email,loginPage.getEmail());
    }

    @And("enters the password {string} and clicks Login")
    public void entersThePasswordAndClicksLogin(String password) {
        loginPage.enterPassword(password);
        loginPage.continueLogin();
    }

    @Then("user redirects to checkout Page")
    public void userRedirectsToCheckoutPage() {
        checkOutPage=new CheckOutPage(driver);
        Assert.assertTrue("Not navigated to checkout page ",checkOutPage.isVisible(checkOutPage.PAYMENT_NAV));
    }

    @When("selects dropdown of Payment Choice {string}")
    public void selectsDropdownOfPaymentChoice(String paymentChoice) {
        if("Virtual Account".equals(paymentChoice))
            checkOutPage.selectVirtualAccount();
    }
    @And("Selects Bank {string};")
    public void selectsBank(String bank) {
        checkOutPage.chooseBank(bank);
    }
    @Then("verify {string} is selected as the payment option")
    public void verifyIsSelectedAsThePaymentPage(String paymentOption) {
        Assert.assertEquals("Payment bank mismatch",paymentOption,checkOutPage.getBank());
    }

    @And("verify nominal {string} matches in payment page")
    public void verifyNominalMatchesInPaymentPage(String nominal) {
        Assert.assertEquals("Nominal misMatch",nominal,checkOutPage.getNominal());
    }

    @When("Click Pay now Button to confirm order")
    public void clickPayNowButtonToConfirmOrder() {
        checkOutPage.clickPayBtn();
    }

    @Then("verify Order status and return to home")
    public void verifyOrderStatusAndReturnToHome() {
        Assert.assertTrue("Not navigated to order status page ",checkOutPage.isDisplayed(checkOutPage.ORDER_STATUS_TITLE));
        checkOutPage.goToHome();

    }

    @When("navigate to orderList and choose Bills & TopUp")
    public void navigateToOrderListAndChooseBillsTopUp() {
        orderListPage=new OrderList(driver);
        orderListPage.navigateToOrderList();
        orderListPage.goToBillsSection();
    }

    @And("View Ordered Product in ordered list")
    public void viewOrderedProductInOrderedList() {
        orderListPage.viewOrderedProduct();
    }

    @Then("Verify product details of same nominal {string}")
    public void verifyProductDetailsOfSameNominal(String nominal) {
        Assert.assertEquals("nominal mismatch in ordered section",nominal,orderListPage.getNominal());
    }

    @When("cancel the ordered Product")
    public void cancelTheOrderedProduct() throws InterruptedException {
        orderListPage.cancelOrder();
        orderListPage.navigateToCancelledSection();
    }


    @Then("verify the {string} product in cancelled order section")
    public void verifyTheProductInCancelledOrderSection(String nominal) {
        Assert.assertTrue("nominal is not present in cancelled section",orderListPage.verifyProduct(nominal));
    }

}
