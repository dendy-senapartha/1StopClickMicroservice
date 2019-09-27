package com.microservice.movie.dto.request;

/*
 * Created by dendy-prtha on 17/09/2019.
 * get movie by genre id request
 */

public class GetMovieByGenreRequest {
    private int genreId;

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
