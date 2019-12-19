package com.microservice.movie.dto.request;

import lombok.Data;

/*
 * Created by dendy-prtha on 17/09/2019.
 * get movie by genre id request
 */

@Data
public class GetMovieByGenreRequest {
    private int genreId;

}
