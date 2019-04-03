package controllers;

import helpers.TrelloURLBuilder;
import net.thucydides.core.steps.ScenarioSteps;
import utils.Configs;
import utils.URLBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestController extends ScenarioSteps{
    private static final String KEY = Configs.getAuthValueByKey("key");
    private static final String TOKEN = Configs.getAuthValueByKey("token");

    private static final String BASEURL = Configs.getURLValueByKey("baseURL");
    private static final String tokensURL = Configs.getURLValueByKey("tokensURL");
    private static final String membersURL = Configs.getURLValueByKey("membersURL");
    private static final String boardsURL = Configs.getURLValueByKey("boardsURL");
    private static final String listsURL = Configs.getURLValueByKey("listsURL");
    private static final String cardsURL = Configs.getURLValueByKey("cardsURL");
    private static final String actionsURL = Configs.getURLValueByKey("actionsURL");
    private static final String commentsURL = Configs.getURLValueByKey("commentsURL");
    private static final String posURL = Configs.getURLValueByKey("posURL");

    private TrelloURLBuilder trelloURLBuilder = new TrelloURLBuilder();
    private URLBuilder urlBuilder = new URLBuilder();

    private Map<String, String> putIdList(Map<String, String> params, String idList){
        params.put("idList",idList);
        return params;
    }

    private Map<String, String> putName(Map<String, String> params, String name){
        params.put("name",name);
        return params;
    }

    private Map<String, String> putValue(Map<String, String> params, String idNewMember){
        params.put("value",idNewMember);
        return params;
    }

    private Map<String, String> putText(Map<String, String> params, String comment){
        params.put("text",comment);
        return params;
    }

    private Map<String, String> putIdBoard(Map<String, String> params, String idBoard){
        params.put("idBoard",idBoard);
        return params;
    }

    private Map<String, String> putPosition(Map<String, String> params, String pos){
        params.put("pos",pos);
        return params;
    }

    public Map<String, String> getAuthParams(){
        Map<String, String> params = new HashMap<>();
        params.put("key",KEY);
        params.put("token",TOKEN);
        return params;
    }

    public Map<String, String> getAuthParamsCreateCard(String idList, String name){
        Map<String, String> params = getAuthParams();
        params = putIdList(params,idList);
        params = putName(params,name);
        return params;
    }

    public Map<String, String> getAuthParamsAddMemberToCard(String idNewMember){
        Map<String, String> params = getAuthParams();
        params = putValue(params,idNewMember);
        return params;
    }

    public Map<String, String> getAuthParamsAddCommentToCard(String comment){
        Map<String, String> params = getAuthParams();
        params = putText(params,comment);
        return params;
    }

    public Map<String, String> getAuthParamsMoveCard(String idList){
        Map<String, String> params = getAuthParams();
        params = putIdList(params,idList);
        return params;
    }

    public Map<String, String> getAuthParamsCreateList(String idBoard, String name, String pos){
        Map<String, String> params = getAuthParams();
        params = putIdBoard(params,idBoard);
        params = putName(params,name);
        params = putPosition(params,pos);
        return params;
    }

    public URL getUserIdUrl() throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(tokensURL).addToPath(TOKEN).build();
    }

    public URL getBoardsOfUserUrl(String idUser) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(membersURL).addToPath(idUser).addToPath(boardsURL).build();
    }

    public URL getMembersOfBoardsUrl(String idBoard) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(boardsURL).addToPath(idBoard).addToPath(membersURL).build();
    }

    public URL getListsOfBoardUrl(String idBoard) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(boardsURL).addToPath(idBoard).addToPath(listsURL).build();
    }

    public URL getCreateCardUrl() throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(cardsURL).build();
    }

    public URL getCardsOnListUrl(String idList) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(listsURL).addToPath(idList).addToPath(cardsURL).build();
    }

    public URL getAddMemberToCardUrl(String idCard) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(cardsURL).addToPath(idCard).addToPath(membersURL).build();
    }

    public URL getAddCommentToCardUrl(String idCard) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(cardsURL).addToPath(idCard).addToPath(actionsURL).addToPath(commentsURL).build();
    }

    public URL getInfoCardUrl(String idCard) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(cardsURL).addToPath(idCard).build();
    }

    public URL getDeleteCardUrl(String idCard) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(cardsURL).addToPath(idCard).build();
    }

    public URL getCreateListUrl() throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(listsURL).build();
    }

    public URL getListPos(String idList) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(listsURL).addToPath(idList).addToPath(posURL).build();
    }

    public URL getCardActionsURL(String idCard) throws MalformedURLException {
        return urlBuilder.addBaseURL(BASEURL).addToPath(cardsURL).addToPath(idCard).addToPath(actionsURL).build();
    }
}