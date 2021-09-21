package flows;

import actions.ApiActions;
import enums.ApiEndpoints;
import enums.HttpMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.resreq.Resreq;
import pojo.resreq.Support;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResreqFlows {

    static ApiActions apiActions;


    public static void hittingTheApi1(String queryParam, String value) {

        apiActions = new ApiActions(ApiEndpoints.GetResreqUserAPI, HttpMethod.GET);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(queryParam, value);
        Response response = apiActions.executeWithQueryParams(queryParams).andReturn();
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/todoResponseSchema.json"));

        Resreq resObj = response.as(Resreq.class);
        Support support = resObj.getSupport();
        support.getText();


    }

    public static void main(String[] args) {

        String uri1 = "https://reqres.in/api/users?page=1";
        String uri2 = "https://reqres.in/api/users?page=2";


        hittingTheApi1("page", "3");



    }

}
