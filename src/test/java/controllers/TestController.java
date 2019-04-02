package controllers;

import helpers.URLBuilder;
import net.thucydides.core.steps.ScenarioSteps;
import utils.Configs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestController extends ScenarioSteps{
    private static final String KEY = Configs.getAuthValueByKey("key");
    private static final String TOKEN = Configs.getAuthValueByKey("token");
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
        return new URL(urlBuilder.addTokensURL().addToken(TOKEN).build());
    }

    public URL getBoardsOfUserUrl(String idUser) throws MalformedURLException {
        return new URL(urlBuilder.addMembersURL().addIdUser(idUser).addBoardsURL().build());
    }

    public URL getMembersOfBoardsUrl(String idBoard) throws MalformedURLException {
        return new URL(urlBuilder.addBoardsURL().addIdBoard(idBoard).addMembersURL().build());
    }

    public URL getListsOfBoardUrl(String idBoard) throws MalformedURLException {
        return new URL(urlBuilder.addBoardsURL().addIdBoard(idBoard).addListsURL().build());
    }

    public URL getCreateCardUrl() throws MalformedURLException {
        return new URL(urlBuilder.addCardsURL().build());
    }

    public URL getCardsOnListUrl(String idList) throws MalformedURLException {
        return new URL(urlBuilder.addListsURL().addIdList(idList).addCardsURL().build());
    }

    public URL getAddMemberToCardUrl(String idCard) throws MalformedURLException {
        return new URL(urlBuilder.addCardsURL().addIdCard(idCard).addIdMembersURL().build());
    }

    public URL getAddCommentToCardUrl(String idCard) throws MalformedURLException {
        return new URL(urlBuilder.addCardsURL().addIdCard(idCard).addActionsURL().addCommentsURL().build());
    }

    public URL getInfoCardUrl(String idCard) throws MalformedURLException {
        return new URL(urlBuilder.addCardsURL().addIdCard(idCard).build());
    }

    public URL getDeleteCardUrl(String idCard) throws MalformedURLException {
        return new URL(urlBuilder.addCardsURL().addIdCard(idCard).build());
    }

    public URL getCreateListUrl() throws MalformedURLException {
        return new URL(urlBuilder.addListsURL().build());
    }

    public URL getListPos(String idList) throws MalformedURLException {
        return new URL(urlBuilder.addListsURL().addIdList(idList).addPosURL().build());
    }

    public URL getCardActionsURL(String idCard) throws MalformedURLException {
        return new URL(urlBuilder.addCardsURL().addIdCard(idCard).addActionsURL().build());
    }
}
