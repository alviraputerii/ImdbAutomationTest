package org.blibli.future.controller;

import io.restassured.response.Response;
import org.blibli.future.request.GetDirectorNameRequest;
import static io.restassured.RestAssured.given;

public class ImdbController {
    public Response getFilm(){
        return given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                when().
                get("http://localhost:17001/api/blibli/film");

    }

    public Response getDirector(String filmName){
        GetDirectorNameRequest request = GetDirectorNameRequest.builder()
                .filmName(filmName)
                .build();

        return given().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                body(request).
                when().
                post("http://localhost:17001/api/blibli/film");

    }
}
