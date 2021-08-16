package factoryRequest;

import io.restassured.response.Response;
import util.ConfigEnv;

import static io.restassured.RestAssured.given;

public class RequestPOST implements IRequest{
    @Override
    public Response send(RequestInformation information) {
        Response response = given()
                .header("Token",  information.getToken())
                /*.auth()
                .preemptive()
                //.oauth2(information.getToken())
                .basic(ConfigEnv.user,ConfigEnv.password)*/
                .body(information.getBody())
                .log()
                .all()
                .when()
                .post(information.getUrl());
        response.then()
                .log()
                .all();
        return response;
    }
}
