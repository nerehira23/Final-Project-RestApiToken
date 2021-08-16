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
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class testUpdateUser {

    @BeforeEach
    public void before() throws IOException {
        new GetProperties().readProperties();
    }

    @Test
    public void testUpdateUserAuthenticationToken(){
        //GET TOKEN
        Response response = given()
                .auth()
                .preemptive()
                .basic(ConfigEnv.user,ConfigEnv.password)
                .log()
                .all()
                .when()
                .get(ConfigApi.GET_TOKEN);

        response.then()
                .statusCode(200)
                .body("UserEmail",equalTo(ConfigEnv.user));

        String token = response.then().extract().path("TokenString");
        System.out.println("TOKEN CREADO***"+token);

        //GET ID USER
        JSONObject jsonObject = new JSONObject();
        RequestInformation requestInformation = new RequestInformation(ConfigApi.CREATE_USER,jsonObject.toString(),token);
        response = FactoryRequest.make(FactoryRequest.GET).send(requestInformation);

        response.then()
                .statusCode(200);
               // .body("Email",equalTo(ConfigEnv.user));
        int id = response.then().extract().path("Id");

        //Update
        jsonObject = new JSONObject();
        jsonObject.put("Email","test.morales@gmail.com");
        requestInformation = new RequestInformation(ConfigApi.UPDATE_USER.replace("ID",String.valueOf(id)),jsonObject.toString(),token);
        response = FactoryRequest.make(FactoryRequest.PUT).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Id",equalTo(Integer.valueOf(id)));

    }
}
