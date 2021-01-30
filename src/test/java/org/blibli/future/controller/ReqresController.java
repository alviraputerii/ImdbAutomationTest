package org.blibli.future.controller;


import io.restassured.response.Response;
import org.blibli.future.request.CreateUserRequest;

import static io.restassured.RestAssured.given;

public class ReqresController {

    public Response createUser(String name, String job){
        CreateUserRequest request = CreateUserRequest.builder()
                .name(name)
                .job(job)
                .build();

        return given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body(request).
                when().
                post("https://reqres.in/api/users");

    }
}
