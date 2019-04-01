package steps;

import controllers.TestController;
import cucumber.api.java.en.And;
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

public class PostListSteps {

    @Steps
    private TestController testController;
    private RequestSpecification requestSpecification;
    private Response response;
    private String idUser;
    private String idBoard;
    private String nameList;
    private String idListA;
    private int posListA;
    private String idListB;

    @And("^The user wants to create a new list \"([^\"]*)\" between the list \"([^\"]*)\" and the list \"([^\"]*)\" in the board \"([^\"]*)\"$")
    public void theUserWantsToCreateANewListBetweenTheListAndTheListInTheBoard(String New_LIST, String LIST_A, String LIST_B, String BOARD){
        nameList = New_LIST;
        requestSpecification = UserFunctions.authenticate(testController);
        response = UserFunctions.requestUserId(testController, requestSpecification);
        idUser = UserFunctions.getUserId(response);
        response = BoardFunctions.requestBoardId(testController, requestSpecification, idUser);
        idBoard = BoardFunctions.getBoardId(response, BOARD);
        response = ListFunctions.requestListId(testController,requestSpecification, idBoard);
        idListA = ListFunctions.getListId(response, LIST_A);
        idListB = ListFunctions.getListId(response, LIST_B);
        response = ListFunctions.requestListPos(testController,requestSpecification, idListA);
        posListA = ListFunctions.getListPos(response);
        requestSpecification = UserFunctions.authenticateNewList(testController, idBoard, nameList, Integer.toString(posListA+1));
    }

    @When("^The user sends a petition to create a list on the board$")
    public void theUserSendsAPetitionToCreateAListOnTheBoard() {
        response = ListFunctions.requestCreateList(testController, requestSpecification);
    }

    @Then("^The Trello API should create a list on the board$")
    public void theTrelloAPIShouldCreateAListOnTheBoard() {
        String nameList = ListFunctions.getListName(response);
        String expectedName = nameList;
        Assert.assertThat("The status of the request is not OK (200)", response.getStatusCode(), Matchers.equalTo(200));
        Assert.assertThat("The list QA is not created",nameList, Matchers.equalTo(expectedName));
    }
}