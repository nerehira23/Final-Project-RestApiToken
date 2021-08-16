package factoryRequest;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import util.ConfigEnv;

import static io.restassured.RestAssured.given;

public class RequestGET implements IRequest{

    @Override
    public Response send(RequestInformation information) {
        Response response = given()
                .header("Token",  information.getToken())
                //.param("Token", information.getToken())
                /*.auth()
                .preemptive()
                .oauth2(information.getToken())
                //.basic(ConfigEnv.user,ConfigEnv.password)*/
                .log()
                .all()
                .when()
                .get(information.getUrl());
        response.then()
                .log()
                .all();
        return response;
    }
}
