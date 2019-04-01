package helpers;

import com.jayway.jsonpath.JsonPath;
import controllers.TestController;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ListFunctions {

    public static Response requestListId(TestController testController, RequestSpecification requestSpecification, String idBoard){
        try {
            return requestSpecification.when().get(testController.getListsOfBoardUrl(idBoard));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response requestListPos(TestController testController, RequestSpecification requestSpecification, String idList){
        try {
            return requestSpecification.when().get(testController.getListPos(idList));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getListId(Response response, String LIST){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        for(int i = 0; i < len; i++){
            if(JsonPath.read(responseBody, "$.["+i+"].name").equals(LIST)){
                return JsonPath.read(responseBody, "$.["+i+"].id");
            }
        }
        return null;
    }

    public static List getListIds(Response response){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        List idLists = new ArrayList();
        for(int i = 0; i < len; i++){
            idLists.add(JsonPath.read(responseBody, "$.["+i+"].id"));
        }
        return idLists;
    }

    public static List getListCardsId(Response response){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        List idCards = new ArrayList();
        for(int i = 0; i < len; i++){
            idCards.add(JsonPath.read(responseBody, "$.["+i+"].id"));
        }
        return idCards;
    }

    public static String getListName(Response response){
        io.restassured.path.json.JsonPath jsonPath = response.jsonPath();
        return jsonPath.get("name");
    }

    public static int getListPos(Response response){
        String responseBody = response.getBody().asString();
        return JsonPath.read(responseBody, "$._value");
    }

    public static Response requestCreateList(TestController testController, RequestSpecification requestSpecification){
        try {
            return requestSpecification.when().post(testController.getCreateListUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
