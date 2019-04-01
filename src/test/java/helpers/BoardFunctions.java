package helpers;

import com.jayway.jsonpath.JsonPath;
import controllers.TestController;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class BoardFunctions {

    public static Response requestBoardId(TestController testController, RequestSpecification requestSpecification, String idUser){
        try {
            return requestSpecification.when().get(testController.getBoardsOfUserUrl(idUser));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBoardId(Response response, String BOARD){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        for(int i = 0; i < len; i++){
            if(JsonPath.read(responseBody, "$.["+i+"].name").equals(BOARD)){
                return JsonPath.read(responseBody, "$.["+i+"].id");
            }
        }
        return null;
    }

    public static List getBoardIds(Response response){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        List idBoards = new ArrayList();
        for(int i = 0; i < len; i++){
            idBoards.add(JsonPath.read(responseBody, "$.["+i+"].id"));
        }
        return idBoards;
    }

    public static Response requestBoardMembers(TestController testController, RequestSpecification requestSpecification, String idBoard){
        try {
            return requestSpecification.when().get(testController.getMembersOfBoardsUrl(idBoard));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List getBoardMembers(Response response, String BOARD){
        String responseBody = response.getBody().asString();
        int len = JsonPath.read(responseBody,"$.length()");
        List members = new ArrayList();
        for(int i = 0; i < len; i++){
            members.add(JsonPath.read(responseBody, "$.["+i+"].id"));
        }
        return members;
    }
}
