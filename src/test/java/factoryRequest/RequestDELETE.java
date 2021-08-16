package factoryRequest;

import io.restassured.response.Response;
import util.ConfigEnv;

import static io.restassured.RestAssured.given;

public class RequestDELETE implements IRequest {
    @Override
    public Response send(RequestInformation information) {
        Response response = given()
                .header("Token",  information.getToken())
                /*.auth()
                .preemptive()
                //.oauth2(information.getToken())
                .basic(ConfigEnv.user,ConfigEnv.password)*/
                .log()
                .all()
                .when()
                .delete(information.getUrl());
        response.then()
                .log()
                .all();
        return response;
    }
}
