package com.microservice.movie.dto.request;

/*
 * Created by dendy-prtha on 17/09/2019.
 * get movie by id request
 */

public class GetMovieByIdRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
