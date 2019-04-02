package helpers;

import utils.Configs;

public class URLBuilder {
    private String URL;

    public URLBuilder(){
        URL = Configs.getURLValueByKey("baseURL");
    }

    public URLBuilder addTokensURL(){
        URL = URL + "/" + Configs.getURLValueByKey("tokensURL");
        return this;
    }

    public URLBuilder addMembersURL(){
        URL = URL + "/" + Configs.getURLValueByKey("membersURL");
        return this;
    }

    public URLBuilder addBoardsURL(){
        URL = URL + "/" + Configs.getURLValueByKey("boardsURL");
        return this;
    }

    public URLBuilder addListsURL(){
        URL = URL + "/" + Configs.getURLValueByKey("listsURL");
        return this;
    }

    public URLBuilder addCardsURL(){
        URL = URL + "/" + Configs.getURLValueByKey("cardsURL");
        return this;
    }

    public URLBuilder addIdMembersURL(){
        URL = URL + "/" + Configs.getURLValueByKey("idMembersURL");
        return this;
    }

    public URLBuilder addActionsURL(){
        URL = URL + "/" + Configs.getURLValueByKey("actionsURL");
        return this;
    }

    public URLBuilder addCommentsURL(){
        URL = URL + "/" + Configs.getURLValueByKey("commentsURL");
        return this;
    }

    public URLBuilder addPosURL(){
        URL = URL + "/" + Configs.getURLValueByKey("posURL");
        return this;
    }

    public URLBuilder addToken(String TOKEN){
        URL = URL + "/" + TOKEN;
        return this;
    }

    public URLBuilder addIdUser(String idUser){
        URL = URL + "/" + idUser;
        return this;
    }

    public URLBuilder addIdBoard(String idBoard){
        URL = URL + "/" + idBoard;
        return this;
    }

    public URLBuilder addIdList(String idList){
        URL = URL + "/" + idList;
        return this;
    }

    public URLBuilder addIdCard(String idCard){
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
