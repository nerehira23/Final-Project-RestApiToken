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

public class testCrudProject {

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
        System.out.println("TOKEN***"+token);

        //CREATE PROJECT
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Content","New project");
        jsonObject.put("Icon",4);
        RequestInformation requestInformation = new RequestInformation(ConfigApi.CREATE_PROJECT,jsonObject.toString(),token);
        response = FactoryRequest.make(FactoryRequest.POST).send(requestInformation);

        response.then()
                .statusCode(200);
               // .body("Email",equalTo(ConfigEnv.user));
        int id = response.then().extract().path("Id");
        System.out.println("ID Project CREATE**: "+id);

        //UPDATE PROJECT
        jsonObject = new JSONObject();
        jsonObject.put("Icon",5);
        requestInformation = new RequestInformation(ConfigApi.UPDATE_PROJECT.replace("ID",String.valueOf(id)),jsonObject.toString(),token);
        response = FactoryRequest.make(FactoryRequest.PUT).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Icon",equalTo(Integer.valueOf(5)));

        //DELETE PROJECT
        requestInformation = new RequestInformation(ConfigApi.DELETE_PROJECT.replace("ID",String.valueOf(id)),jsonObject.toString(),token);
        response = FactoryRequest.make(FactoryRequest.DELETE).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Deleted",equalTo(true));
    }
}
