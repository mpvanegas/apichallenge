package helpers;

import com.jayway.jsonpath.JsonPath;
import controllers.TestController;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class CardFunctions {

    public static Response requestCardsOnList(TestController testController, RequestSpecification requestSpecification, String idList){
        try {
            return requestSpecification.when().get(testController.getCardsOnListUrl(idList));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response requestCreateCard(TestController testController, RequestSpecification requestSpecification){
        try {
            return requestSpecification.when().post(testController.getCreateCardUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response requestAddMemberToCard(TestController testController, RequestSpecification requestSpecification, String idCard){
        try {
            return requestSpecification.when().post(testController.getAddMemberToCardUrl(idCard));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response requestAddCommentToCard(TestController testController, RequestSpecification requestSpecification, String idCard){
        try {
            return requestSpecification.when().post(testController.getAddCommentToCardUrl(idCard));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response requestInfoCard(TestController testController, RequestSpecification requestSpecification, String idCard){
        try {
            return requestSpecification.when().put(testController.getInfoCardUrl(idCard));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response requestActionsCard(TestController testController, RequestSpecification requestSpecification, String idCard){
        try {
            return requestSpecification.when().get(testController.getCardActionsURL(idCard));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response requestDeleteCard(TestController testController, RequestSpecification requestSpecification, String idCard){
        try {
            return requestSpecification.when().delete(testController.getDeleteCardUrl(idCard));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCardId(Response response, String CARD){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        for(int i = 0; i < len; i++){
            if(JsonPath.read(responseBody, "$.["+i+"].name").equals(CARD)) {
                return JsonPath.read(responseBody, "$.[" + i + "].id");
            }
        }
        return null;
    }

    public static String getCardId(Response response){
        io.restassured.path.json.JsonPath jsonPath = response.jsonPath();
        return jsonPath.get("id");
    }

    public static String getCardName(Response response){
        io.restassured.path.json.JsonPath jsonPath = response.jsonPath();
        return jsonPath.get("name");
    }

    public static List getCardMembersId(Response response){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        List idMembers = new ArrayList();
        for(int i = 0; i < len; i++){
            idMembers.add(JsonPath.read(responseBody, "$.["+i+"].id"));
        }
        return idMembers;
    }

    public static List getCardComments(Response response){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        List comments = new ArrayList();
        for(int i = 0; i < len; i++){
            comments.add(JsonPath.read(responseBody, "$.["+i+"].data.text"));
        }
        return comments;
    }

    public static List getCardListId(Response response){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        List comments = new ArrayList();
        for(int i = 0; i < len; i++){
            comments.add(JsonPath.read(responseBody, "$.["+i+"]..data.listAfter.id"));
        }
        return comments;
    }
}
