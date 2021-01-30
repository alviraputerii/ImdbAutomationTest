package org.blibli.future.tests;

import io.restassured.response.Response;
import org.blibli.future.controller.ReqresController;
import org.blibli.future.responses.CreateUserResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTest {

    private ReqresController reqresController;

    @Before
    public void setup(){
        reqresController = new ReqresController();
    }

    @Test
    public void createUserTest(){
        String name = "budi";
        String job = "office worker";

        Response response = reqresController.createUser(name, job);

        assertThat("Status code is not 201", response.getStatusCode(), equalTo(201));

        CreateUserResponse createUserResponse = response.getBody().as(CreateUserResponse.class);

        assertThat(("Name is not " + name), createUserResponse.getName(), equalTo(name));
        assertThat(("Job is not " + job), createUserResponse.getJob(), equalTo(job));
        assertThat("Id is null ", createUserResponse.getId(), notNullValue());
        assertThat("Create at is null", createUserResponse.getCreatedAt(),notNullValue());

    }
}
