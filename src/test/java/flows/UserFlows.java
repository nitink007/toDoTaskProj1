package flows;

import actions.ApiActions;
import enums.ApiEndpoints;
import enums.HttpMethod;
import enums.Locations;
import io.restassured.response.Response;
import pojo.user.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserFlows {

    ApiActions apiActions;

    public List<Users> getAllUsers() {
        apiActions = new ApiActions(ApiEndpoints.GetUserAPI, HttpMethod.GET);
        Response response = apiActions.executeAPI().andReturn();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/usersResponseSchema.json"));
        List<Users> userList = Arrays.asList(response.as(Users[].class));
        return userList;
    }

    public List<Users> getLocationWiseUsers(String location) {
        apiActions = new ApiActions(ApiEndpoints.GetUserAPI, HttpMethod.GET);
        Response response = apiActions.executeAPI().andReturn();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/usersResponseSchema.json"));
        List<Users> userList = Arrays.asList(response.as(Users[].class));
        List<Users> validUserList = new ArrayList<>();
        for (Users user : userList) {
            if (isValidUserByLocation(user, Locations.valueOf(location))) {
                validUserList.add(user);
            }
        }
        return validUserList;
    }

    // Private Methods
    private boolean isValidUserByLocation(Users user, Locations location) {
        if (((Float.parseFloat(user.getAddress().getGeo().getLat()) > location.getMinLat()) &&
            (Float.parseFloat(user.getAddress().getGeo().getLat()) < location.getMaxLat())) &&
            (((Float.parseFloat(user.getAddress().getGeo().getLng()) > location.getMinLng()) &&
            (Float.parseFloat(user.getAddress().getGeo().getLng()) < location.getMaxLng())))) {
            return true;
        }
        return false;
    }

}
