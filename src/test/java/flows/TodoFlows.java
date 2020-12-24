package flows;

import actions.ApiActions;
import enums.ApiEndpoints;
import enums.HttpMethod;
import io.restassured.response.Response;
import pojo.todo.Todo;

import java.util.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TodoFlows {

    ApiActions apiActions;

    public List<Todo> getAllTodos() {
        apiActions = new ApiActions(ApiEndpoints.GetTodosAPI, HttpMethod.GET);
        Response response = apiActions.executeAPI().andReturn();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/todoResponseSchema.json"));
        List<Todo> todoList = Arrays.asList(response.as(Todo[].class));
        return todoList;
    }

    public List<Todo> getTodosByUser(String userId) {
        apiActions = new ApiActions(ApiEndpoints.GetTodosAPI, HttpMethod.GET);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId",userId);
        Response response = apiActions.executeWithQueryParams(queryParams).andReturn();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/todoResponseSchema.json"));
        List<Todo> todoList = Arrays.asList(response.as(Todo[].class));
        return todoList;
    }

    public List<Todo> getTodosByTaskStatusForUser(String userId, String taskStatus) {
        apiActions = new ApiActions(ApiEndpoints.GetTodosAPI, HttpMethod.GET);
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userId",userId);
        Response response = apiActions.executeWithQueryParams(queryParams).andReturn();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/todoResponseSchema.json"));
        List<Todo> todoList = Arrays.asList(response.as(Todo[].class));
        List<Todo> taskStatusTodoList = new ArrayList<>();
        for (Todo todo : todoList) {
            switch (taskStatus.toUpperCase()) {
                case "COMPLETED":
                    if (todo.getCompleted()) taskStatusTodoList.add(todo);
                    break;
                case "NOT COMPLETED":
                    if (!todo.getCompleted()) taskStatusTodoList.add(todo);
                    break;
            }
        }
        return taskStatusTodoList;
    }


}
