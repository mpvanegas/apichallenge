package steps;

import controllers.TestController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.UserFunctions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class GetUserSteps {

    //TODO DataSession.getFromSession SerenitySession

    @Steps
    private TestController testController;
    private RequestSpecification requestSpecification;
    private Response response;
    private String idUser;

    @Given("^The user wants their information$")
    public void theUserWantsTheirInformation() {
        requestSpecification = UserFunctions.authenticate(testController);
    }

    @When("^The user sends a petition to get their information$")
    public void theUserSendsAPetitionToGetTheirInformation(){
        response = UserFunctions.requestUserId(testController, requestSpecification);
    }

    @Then("^The Trello API should response with the information of the user$")
    public void theTrelloAPIShouldResponseWithTheInformationOfTheUser() {
        idUser = UserFunctions.getUserId(response);
       // String expectedId = "5c49c26a89360f40897f8147";
        Assert.assertThat("The status of the request is not OK (200)", response.getStatusCode(), Matchers.equalTo(200));
       // Assert.assertThat("The information of the user is not correct",idUser, Matchers.equalTo(expectedId));
    }
}