package step_definitions;

import command_providers.ActOn;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page_objects.Home;
import page_objects.RealApr;
import utilities.ReadConfigFiles;

import java.util.List;
import java.util.Map;

public class MortgageCalculatorSteps {
    private static final Logger LOGGER = LogManager.getLogger(MortgageCalculatorSteps.class);
    WebDriver driver = Hooks.driver;

    @Given("^user is in mortgage calculator home page$")
    public void navigateToMortgageCalculatorHomePage() {
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("MortgageURL"));
        LOGGER.info("Landed on the Mortgage Calculator Home Page");
    }

    @And("^user navigate to Real Apr page$")
    public void navigateToRealAprPage() {
        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr();
        LOGGER.info("Navigated to Real APR Page");
    }

    @When("^user click on calculate button upon entering the data$")
    public void clickOnCalculateButtonUponEnteringTheData(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (Map<String, String> cells: data) {
            new RealApr(driver)
                    .typeHomePrice(cells.get("HomePrice"))
                    .clickDownPaymentInDollar()
                    .typeDownPayment(cells.get("DownPayment"))
                    .typeInterestRate(cells.get("InterestRate"))
                    .clickOnCalculateButton();
        }
        LOGGER.info("Real APR rate is calculated upon entering the data");
    }

    @Then("^the real apr rate is \"(.+?)\"$")
    public void validateRealAprRate(String realApr) {
        new RealApr(driver)
                .validateRealAprRate(realApr);
        LOGGER.info("Real APR rate is validated");
    }
}
