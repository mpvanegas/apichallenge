package steps;

import controllers.TestController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.BoardFunctions;
import helpers.ListFunctions;
import helpers.UserFunctions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class GetListSteps {

    @Steps
    private TestController testController;
    private RequestSpecification requestSpecification;
    private Response response;
    private String idUser;
    private String idBoard;

    @Given("^The user wants to see the Lists on the \"([^\"]*)\"$")
    public void theUserWantsToSeeTheListsOnThe(String BOARD) {
        requestSpecification = UserFunctions.authenticate(testController);
        response = UserFunctions.requestUserId(testController, requestSpecification);
        idUser = UserFunctions.getUserId(response);
        response = BoardFunctions.requestBoardId(testController, requestSpecification, idUser);
        idBoard = BoardFunctions.getBoardId(response, BOARD);
    }

    @When("^The user sends a petition to get the Lists on a Board$")
    public void theUserSendsAPetitionToGetTheListsOnABoard() {
        response = ListFunctions.requestListId(testController, requestSpecification, idBoard);
    }

    @Then("^The Trello API should response with the list of the Lists on o Board$")
    public void theTrelloAPIShouldResponseWithTheListOfTheListsOnOBoard() {
        String idsLists = ListFunctions.getListIds(response).toString();
     //   String expectedId = "5c48df6f2217f65db47d41e3";
        Assert.assertThat("The status of the request is not OK (200)", response.getStatusCode(), Matchers.equalTo(200));
     //   Assert.assertThat("The information of the lists in the board is not correct. The list DONE does not exist",idsLists, Matchers.containsString(expectedId));
    }
}