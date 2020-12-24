package steps;

import flows.TodoFlows;
import flows.UserFlows;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

import pojo.todo.Todo;
import pojo.user.Users;

import java.util.List;

public class TodoTasks {

    UserFlows userFlows = new UserFlows();
    TodoFlows todoFlows = new TodoFlows();
    List<Users> users;
    List<Todo> todos;

    @Given("User belongs to the city {string}")
    public void userBelongsToTheCity(String location) {
        List<Users> locationWiseUsers = userFlows.getLocationWiseUsers(location);
        assertThat(locationWiseUsers).hasSizeGreaterThan(0)
                .describedAs("No user belongs to the Location: "+location);
        users = locationWiseUsers;
    }

    @And("User has the Todo tasks")
    public void userHasTheTodoTasks() {
        List<Todo> todosByUser = null;
        for (Users user : users) {
            todosByUser = todoFlows.getTodosByUser(user.getId().toString());
            assertThat(todosByUser).hasSizeGreaterThan(0)
                    .describedAs("User with UserId: "+user.getId()+" has NO todo task");
        }
    }

    @Then("User {string} task percentage should be {string} {string}%")
    public void userTaskPercentageShouldBe(String taskStatus, String comparison, String percentage) {
        for (Users user : users) {
            List<Todo> todosByUser = todoFlows.getTodosByUser(user.getId().toString());
            float totalTodoTasks = todosByUser.size();
            List<Todo> todoTaskByStatus = todoFlows.getTodosByTaskStatusForUser(user.getId().toString(), taskStatus);
            float completedTask = todoTaskByStatus.size();

            System.out.println("Total tasks: "+totalTodoTasks);
            System.out.println("Completed tasks: "+completedTask);

            float perc = (completedTask/totalTodoTasks)*100;

            System.out.println("Actual Completed Percentage: "+perc+" Expected Completed Percentage: "+percentage);

            switch (comparison.toUpperCase()) {
                case "GREATER THAN":
                    assertThat(perc).isGreaterThan(Float.parseFloat(percentage))
                            .describedAs(taskStatus+" task is not "+comparison+" given percentage");
                    break;
                case "LESS THAN":
                    assertThat(perc).isLessThan(Float.parseFloat(percentage))
                            .describedAs(taskStatus+" task is not "+comparison+" given percentage");
                    break;
                case "GREATER THAN EQUAL TO":
                    assertThat(perc).isGreaterThanOrEqualTo(Float.parseFloat(percentage))
                            .describedAs(taskStatus+" task is not "+comparison+" given percentage");
                    break;
                case "LESS THAN EQUAL TO":
                    assertThat(perc).isLessThanOrEqualTo(Float.parseFloat(percentage))
                            .describedAs(taskStatus+" task is not "+comparison+" given percentage");
                    break;
                case "EQUAL TO":
                    assertThat(perc).isEqualTo(Float.parseFloat(percentage))
                            .describedAs(taskStatus+" task is not "+comparison+" given percentage");
                    break;
            }
        }
    }



}
