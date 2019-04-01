package controllers;

import net.thucydides.core.steps.ScenarioSteps;
import utils.Configs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestController extends ScenarioSteps{
    private static final String KEY = Configs.getValueByKey("key");
    private static final String TOKEN = Configs.getValueByKey("token");

    public Map<String, String> getAuthParams(){
        Map<String, String> params = new HashMap<>();
        params.put("key",KEY);
        params.put("token",TOKEN);
        return params;
    }

    public Map<String, String> getAuthParamsCreateCard(String idList, String name){
        Map<String, String> params = new HashMap<>();
        params.put("key",KEY);
        params.put("token",TOKEN);
        params.put("idList",idList);
        params.put("name",name);
        return params;
    }

    public Map<String, String> getAuthParamsAddMemberToCard(String idNewMember){
        Map<String, String> params = new HashMap<>();
        params.put("key",KEY);
        params.put("token",TOKEN);
        params.put("value",idNewMember);
        return params;
    }

    public Map<String, String> getAuthParamsAddCommentToCard(String comment){
        Map<String, String> params = new HashMap<>();
        params.put("key",KEY);
        params.put("token",TOKEN);
        params.put("text",comment);
        return params;
    }

    public Map<String, String> getAuthParamsMoveCard(String idList){
        Map<String, String> params = new HashMap<>();
        params.put("key",KEY);
        params.put("token",TOKEN);
        params.put("idList",idList);
        return params;
    }

    public Map<String, String> getAuthParamsCreateList(String idBoard, String name, String pos){
        Map<String, String> params = new HashMap<>();
        params.put("key",KEY);
        params.put("token",TOKEN);
        params.put("idBoard",idBoard);
        params.put("name",name);
        params.put("pos",pos);
        return params;
    }

    //TODO Use the resources file to use (read) json without writing the path on the code

    public URL getUserIdUrl() throws MalformedURLException {
        return new URL("https://api.trello.com/1/tokens/"+TOKEN);
    }

    public URL getBoardsOfUserUrl(String idUser) throws MalformedURLException {
        return new URL("https://api.trello.com/1/members/"+idUser+"/boards");
    }

    public URL getMembersOfBoardsUrl(String idBoard) throws MalformedURLException {
        return new URL("https://api.trello.com/1/boards/"+idBoard+"/members");
    }

    public URL getListsOfBoardUrl(String idBoard) throws MalformedURLException {
        return new URL("https://api.trello.com/1/boards/"+idBoard+"/lists");
    }

    public URL getCreateCardUrl() throws MalformedURLException {
        return new URL("https://api.trello.com/1/cards");
    }

    public URL getCardsOnListUrl(String idList) throws MalformedURLException {
        return new URL("https://api.trello.com/1/lists/"+idList+"/cards");
    }

    public URL getAddMemberToCardUrl(String idCard) throws MalformedURLException {
        return new URL("https://api.trello.com/1/cards/"+idCard+"/idMembers");
    }

    public URL getAddCommentToCardUrl(String idCard) throws MalformedURLException {
        return new URL("https://api.trello.com/1/cards/"+idCard+"/actions/comments");
    }

    public URL getInfoCardUrl(String idCard) throws MalformedURLException {
        return new URL("https://api.trello.com/1/cards/"+idCard);
    }

    public URL getDeleteCardUrl(String idCard) throws MalformedURLException {
        return new URL("https://api.trello.com/1/cards/"+idCard);
    }

    public URL getCreateListUrl() throws MalformedURLException {
        return new URL("https://api.trello.com/1/lists/");
    }

    public URL getListPos(String idList) throws MalformedURLException {
        return new URL("https://api.trello.com/1/lists/"+idList+"/pos");
    }

    public URL getCardActionsURL(String idCard) throws MalformedURLException {
        return new URL("https://api.trello.com/1/cards/"+idCard+"/actions");
    }
}
