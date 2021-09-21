package enums;

public enum ApiEndpoints {

    GetUserAPI("/users"),
    GetTodosAPI("/todos"),

    GetResreqUserAPI("/users");

    private String resource;

    ApiEndpoints(String resource)
    {
        this.resource=resource;
    }

    public String getResource() {
        return resource;
    }

}
