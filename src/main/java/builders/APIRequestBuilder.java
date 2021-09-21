package builders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import utils.PropertiesUtil;

public class APIRequestBuilder {

    private RequestSpecBuilder builder;

    public RequestSpecBuilder getBuilder() {
        return builder;
    }

    public APIRequestBuilder() {
        builder = new RequestSpecBuilder();
        builder.setBaseUri(PropertiesUtil.getAppPropValues("baseUri"));
        builder.setBasePath(PropertiesUtil.getAppPropValues("basePath"));
        builder.setContentType(ContentType.JSON);
        builder.setAccept(ContentType.JSON);
    }

    public void resetRequestSpecBuilder() {
        builder = null;
    }
}
