package steps;

import controllers.TestController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.BoardFunctions;
import helpers.UserFunctions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class GetBoardsSteps {

    @Steps
    private TestController testController;
    private RequestSpecification requestSpecification;
    private Response response;
    private String idUser;


    @Given("^The user wants to view the boards he is in$")
    public void theUserWantsToViewTheBoardsHeIsIn(){
        requestSpecification = UserFunctions.authenticate(testController);
        response = UserFunctions.requestUserId(testController, requestSpecification);
        idUser = UserFunctions.getUserId(response);
    }

    @When("^The user sends a petition to view the boards$")
    public void theUserSendsAPetitionToViewTheBoards() {
        response = BoardFunctions.requestBoardId(testController, requestSpecification, idUser);
    }

    @Then("^The Trello API should show the information of the boards$")
    public void theTrelloAPIShouldShowTheInformationOfTheBoards() {
        String idsBoards = BoardFunctions.getBoardIds(response).toString();
      //  String expectedId = "5c48d85de5f8a01996d278ac";
        Assert.assertThat("The status of the request is not OK (200)", response.getStatusCode(), Matchers.equalTo(200));
      //  Assert.assertThat("The information of the user is not correct",idsBoards, Matchers.containsString(expectedId));
    }
}