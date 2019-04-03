package helpers;

import utils.Configs;

public class TrelloURLBuilder {
    private String URL;

    public TrelloURLBuilder() {
        URL = Configs.getURLValueByKey("baseURL");
    }

    public TrelloURLBuilder addTokensURL() {
        URL = URL + "/" + Configs.getURLValueByKey("tokensURL");
        return this;
    }

    public TrelloURLBuilder addMembersURL() {
        URL = URL + "/" + Configs.getURLValueByKey("membersURL");
        return this;
    }

    public TrelloURLBuilder addBoardsURL() {
        URL = URL + "/" + Configs.getURLValueByKey("boardsURL");
        return this;
    }

    public TrelloURLBuilder addListsURL() {
        URL = URL + "/" + Configs.getURLValueByKey("listsURL");
        return this;
    }

    public TrelloURLBuilder addCardsURL() {
        URL = URL + "/" + Configs.getURLValueByKey("cardsURL");
        return this;
    }

    public TrelloURLBuilder addIdMembersURL() {
        URL = URL + "/" + Configs.getURLValueByKey("idMembersURL");
        return this;
    }

    public TrelloURLBuilder addActionsURL() {
        URL = URL + "/" + Configs.getURLValueByKey("actionsURL");
        return this;
    }

    public TrelloURLBuilder addCommentsURL() {
        URL = URL + "/" + Configs.getURLValueByKey("commentsURL");
        return this;
    }

    public TrelloURLBuilder addPosURL() {
        URL = URL + "/" + Configs.getURLValueByKey("posURL");
        return this;
    }

    public TrelloURLBuilder addToken(String TOKEN) {
        URL = URL + "/" + TOKEN;
        return this;
    }

    public TrelloURLBuilder addIdUser(String idUser) {
        URL = URL + "/" + idUser;
        return this;
    }

    public TrelloURLBuilder addIdBoard(String idBoard) {
        URL = URL + "/" + idBoard;
        return this;
    }

    public TrelloURLBuilder addIdList(String idList) {
        URL = URL + "/" + idList;
        return this;
    }

    public TrelloURLBuilder addIdCard(String idCard) {
        URL = URL + "/" + idCard;
        return this;
    }

    public String build(){
        String builtURL = URL;
        resetURL();
        return builtURL;
    }

    private void resetURL(){
        URL = Configs.getURLValueByKey("baseURL");
    }
}
