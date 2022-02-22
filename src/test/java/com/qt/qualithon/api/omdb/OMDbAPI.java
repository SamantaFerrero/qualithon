package com.qt.qualithon.api.omdb;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.qt.qualithon.model.Movie;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

/**
 * OMDb API Rest Client to get movie metadata. The data is represented as
 * com.qt.qualithon.model.Movie model
 **/
public class OMDbAPI {

    /**
     * get movie metadata from OMDb
     *
     * @param movieTitle
     *            exact movie name
     * @return Movie object representing metadata
     **/
    public Movie getMovie ( final String movieTitle ) throws UnsupportedEncodingException {
        // call OMDb movie api
        final HttpResponse<JsonNode> response = Unirest.get( "https://www.omdbapi.com/?apikey=b569cb4e&t="
                + URLEncoder.encode( movieTitle, StandardCharsets.UTF_8.toString() ) ).asJson();
        final JSONObject movieMetadata = response.getBody().getObject();

        // init Movie model
        final Movie movie = new Movie();
        movie.setTitle( movieMetadata.getString( "Title" ) );
        movie.setReleaseYear( movieMetadata.getString( "Year" ) );
        movie.setDirector( movieMetadata.getString( "Language" ) );
        movie.setGenres( movieMetadata.getString( "Genre" ) );
        movie.setWriters( movieMetadata.getString( "Writer" ) );
        movie.setRating( movieMetadata.getString( "Rated" ) );
        movie.setScoreIMd( movieMetadata.getString( "Ratings" ) );

        return movie;
    }

}
