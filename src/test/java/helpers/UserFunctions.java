package helpers;

import controllers.TestController;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.MalformedURLException;

public class UserFunctions {

    public static RequestSpecification authenticate(TestController testController){
        return RestAssured.given().contentType("application/json")
                .and().queryParams(testController.getAuthParams());
    }

    public static RequestSpecification authenticateCreateCard(TestController testController, String idList, String name){
        return RestAssured.given().contentType("application/json")
                .and().queryParams(testController.getAuthParamsCreateCard(idList, name));
    }

    public static RequestSpecification authenticateAddMemberToCard(TestController testController, String idNewMember){
        return RestAssured.given().contentType("application/json")
                .and().queryParams(testController.getAuthParamsAddMemberToCard(idNewMember));
    }

    public static RequestSpecification authenticateAddCommentToCard(TestController testController, String comment){
        return RestAssured.given().contentType("application/json")
                .and().queryParams(testController.getAuthParamsAddCommentToCard(comment));
    }

    public static RequestSpecification authenticateMoveCard(TestController testController, String idList){
        return RestAssured.given().contentType("application/json")
                .and().queryParams(testController.getAuthParamsMoveCard(idList));
    }

    public static RequestSpecification authenticateNewList(TestController testController, String idBoard, String name, String pos){
        return RestAssured.given().contentType("application/json")
                .and().queryParams(testController.getAuthParamsCreateList(idBoard, name, pos));
    }

    public static Response requestUserId(TestController testController, RequestSpecification requestSpecification) {
        try {
            return requestSpecification.when().get(testController.getUserIdUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUserId(Response response){
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath);
        return jsonPath.get("idMember");
    }
}