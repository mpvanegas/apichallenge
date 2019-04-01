package steps;

import com.jayway.jsonpath.JsonPath;
import controllers.TestController;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
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

import java.util.List;

public class PostCardSteps {

    @Steps
    private TestController testController;
    private RequestSpecification requestSpecification;
    private Response response;
    private String idUser;
    private String idBoard;
    private String idList;
    private String idListTODO;
    private String idListINP;
    private String idListDONE;
    private String idListA;
    private String idListB;
    private String idCard;
    private String name;
    private String comment;
    private List idMembers;
    private String idListNEW;

    @Given("^The user is member of the board \"([^\"]*)\"$")
    public void theUserIsMemberOfTheBoard(String BOARD){
        requestSpecification = UserFunctions.authenticate(testController);
        response = UserFunctions.requestUserId(testController, requestSpecification);
        idUser = UserFunctions.getUserId(response);
        response = BoardFunctions.requestBoardId(testController, requestSpecification, idUser);
        idBoard = BoardFunctions.getBoardId(response, BOARD);
    }

    @And("^The board has the list \"([^\"]*)\"$")
    public void theBoardHasTheList(String LIST){
        response = ListFunctions.requestListId(testController,requestSpecification, idBoard);
        idListTODO = ListFunctions.getListId(response, LIST);
    }

    @Given("^The user wants to create a new card \"([^\"]*)\" on the list \"([^\"]*)\" on the board \"([^\"]*)\"$")
    public void theUserWantsToCreateANewCardOnTheListOnTheBoard(String NAME, String LIST, String BOARD){
        name = NAME;
        requestSpecification = UserFunctions.authenticateCreateCard(testController, idListTODO, name);
    }

    @When("^The user sends a petition to create a card on the list in the board$")
    public void theUserSendsAPetitionToCreateACardOnTheListInTheBoard() {
        response = CardFunctions.requestCreateCard(testController, requestSpecification);
    }

    @Then("^The Trello API should create a card on the list in the board$")
    public void theTrelloAPIShouldCreateACardOnTheListInTheBoard() {
        String nameCard = CardFunctions.getCardName(response);
        String expectedName = name;
        Assert.assertThat("The status of the request is not OK (200)", response.getStatusCode(), Matchers.equalTo(200));
        Assert.assertThat("The list 'For Testing' is not created",nameCard, Matchers.equalTo(expectedName));
    }

    @Given("^The board \"([^\"]*)\" has a list \"([^\"]*)\" and the list has a card \"([^\"]*)\"$")
    public void theBoardHasAListAndTheListHasACard(String BOARD, String LIST, String CARD){
        name = CARD;
        response = ListFunctions.requestListId(testController,requestSpecification, idBoard);
        idList = ListFunctions.getListId(response, LIST);
        response = CardFunctions.requestCardsOnList(testController, requestSpecification, idList);
        requestSpecification = UserFunctions.authenticateCreateCard(testController, idList, name);
        response = CardFunctions.requestCreateCard(testController, requestSpecification);
        idCard = CardFunctions.getCardId(response);
        requestSpecification = UserFunctions.authenticate(testController);
    }

    @And("^The user wants to add a member to the card \"([^\"]*)\" on the list \"([^\"]*)\" on the board \"([^\"]*)\"$")
    public void theUserWantsToAddAMemberToTheCardOnTheListOnTheBoard(String CARD, String LIST, String BOARD){
        response = BoardFunctions.requestBoardMembers(testController, requestSpecification, idBoard);
        idMembers = BoardFunctions.getBoardMembers(response, idBoard);
        requestSpecification = UserFunctions.authenticateAddMemberToCard(testController, (String)idMembers.get(1));
    }

    @When("^The user sends a petition to add a member to the card$")
    public void theUserSendsAPetitionToAddAMemberToTheCard() {
        response = CardFunctions.requestAddMemberToCard(testController, requestSpecification, idCard);
    }

    @Then("^The Trello API should add a member to the card$")
    public void theTrelloAPIShouldAddAMemberToTheCard() {
        System.out.println(response.getBody().asString());
        String idsMembers = CardFunctions.getCardMembersId(response).toString();
        String expectedId = (String) idMembers.get(1);
        Assert.assertThat("The status of the request is not OK (200)", response.getStatusCode(), Matchers.equalTo(200));
        Assert.assertThat("The new member is not added to the card",idsMembers, Matchers.containsString(expectedId));
    }

    @And("^The user wants to add the comment \"([^\"]*)\" to the card \"([^\"]*)\" on the list \"([^\"]*)\" on the board \"([^\"]*)\"$")
    public void theUserWantsToAddACommentToTheCardOnTheListOnTheBoard(String COMMENT, String CARD, String LIST, String BOARD){
        comment = COMMENT;
        requestSpecification = UserFunctions.authenticateAddCommentToCard(testController, comment);
    }

    @When("^The user sends a petition to add a comment to the card$")
    public void theUserSendsAPetitionToAddACommentToTheCard() {
        response = CardFunctions.requestAddCommentToCard(testController, requestSpecification, idCard);
    }

    @Then("^The Trello API should add a comment to the card$")
    public void theTrelloAPIShouldAddACommentToTheCard() {
        String responseBody = response.getBody().asString();
        String expectedComment = JsonPath.read(responseBody, "$.data.text");
        response = CardFunctions.requestActionsCard(testController, requestSpecification, idCard);
        System.out.println(response.getBody().asString());
        String comments = CardFunctions.getCardComments(response).toString();
        Assert.assertThat("The status of the request is not OK (200)", response.getStatusCode(), Matchers.equalTo(200));
        Assert.assertThat("The new comment is not added to the card",comments, Matchers.containsString(expectedComment));
    }

    @And("^The user wants to move the card \"([^\"]*)\" from list \"([^\"]*)\" to \"([^\"]*)\" on the board \"([^\"]*)\"$")
    public void theUserWantsToMoveTheCardFromListToOnTheBoard(String CARD, String LIST_A, String LIST_B, String BOARD){
        idListA = ListFunctions.getListId(response, LIST_A);
        idListB = ListFunctions.getListId(response, LIST_B);
        requestSpecification = UserFunctions.authenticateMoveCard(testController, idListB);
    }

    @When("^The user sends a petition to move the card$")
    public void theUserSendsAPetitionToMoveTheCard() {
        response = CardFunctions.requestInfoCard(testController, requestSpecification, idCard);
    }

    @Then("^The Trello API should move the card$")
    public void theTrelloAPIShouldMoveTheCard() {
        response = CardFunctions.requestActionsCard(testController, requestSpecification, idCard);
        System.out.println(response.getBody().asString());
        String idList = CardFunctions.getCardListId(response).toString();
        String expectedId = idListB;
        Assert.assertThat("The status of the request is not OK (200)", response.getStatusCode(), Matchers.equalTo(200));
        Assert.assertThat("The card is not in the list it should be",idList, Matchers.containsString(expectedId));
    }
}