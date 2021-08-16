package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConfigApi;
import util.ConfigEnv;
import util.GetProperties;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class testCreateUser {
    @BeforeEach
    public void before() throws IOException {
        new GetProperties().readProperties();
    }

    @Test
    public void testCreateUserAuthenticationToken(){
        //GET TOKEN
        Response response;
        String token = "";

        //Create
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Email","ucb7@new.user");
        jsonObject.put("Password",7654321);
        jsonObject.put("FullName","New user7");
        RequestInformation requestInformation = new RequestInformation(ConfigApi.CREATE_USER,jsonObject.toString(),token);
        response = FactoryRequest.make(FactoryRequest.POST).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Email",equalTo("ucb7@new.user"));
    }
}
