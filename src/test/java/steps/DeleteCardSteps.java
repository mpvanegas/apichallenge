package steps;

import controllers.TestController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.BoardFunctions;
import helpers.CardFunctions;
import helpers.ListFunctions;
import helpers.UserFunctions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Assert;


public class DeleteCardSteps {

    @Steps
    private TestController testController;
    private RequestSpecification requestSpecification;
    private Response response;
    private String idUser;
    private String idBoard;
    private String idList;
    private String idCard;


    @Given("^The user wants to delete the card \"([^\"]*)\" in the list \"([^\"]*)\" on the board \"([^\"]*)\"$")
    public void theUserWantsToDeleteTheCardInTheListOnTheBoard(String CARD, String LIST, String BOARD){
        requestSpecification = UserFunctions.authenticate(testController);
        response = UserFunctions.requestUserId(testController, requestSpecification);
        idUser = UserFunctions.getUserId(response);
        response = BoardFunctions.requestBoardId(testController, requestSpecification, idUser);
        idBoard = BoardFunctions.getBoardId(response, BOARD);
        response = ListFunctions.requestListId(testController,requestSpecification, idBoard);
        idList = ListFunctions.getListId(response, LIST);
        response = CardFunctions.requestCardsOnList(testController, requestSpecification, idList);
        idCard = CardFunctions.getCardId(response, CARD);
    }

    @When("^The user sends a petition to delete the card$")
    public void theUserSendsAPetitionToDeleteTheCard() {
        response = CardFunctions.requestDeleteCard(testController, requestSpecification, idCard);
    }

    @Then("^The Trello API should delete the card$")
    public void theTrelloAPIShouldDeleteTheCard() {
        Assert.assertThat("The status of the request is not OK (200)", response.getStatusCode(), Matchers.equalTo(200));
        Assert.assertThat("The card is still on the list",idList, Matchers.not(Matchers.containsString(idCard)));
    }
}