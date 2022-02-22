package com.qt.qualithon.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Movie model represents the metadata of any movie
 *
 **/
public class Movie {

    String       title;
    String       releaseYear;
    String       director;
    List<String> genres;
    List<String> writers;
    String       rating;
    String       scoreIMd;
    String       scoreRotten;

    /**
     * Get the rating
     *
     * @return rating
     */
    public String getRating () {
        return rating;
    }

    /**
     * Set the rating
     *
     * @param rating
     *            the rating string
     */
    public void setRating ( final String rating ) {
        this.rating = rating;
    }

    /**
     * Get the IMD score
     *
     * @return score
     */
    public String getScoreIMD () {
        return scoreIMd;
    }

    /**
     * Set the imd score
     *
     * @param score
     *            the score as a string
     */
    public void setScoreIMd ( final String score ) {
        this.scoreIMd = score;
    }

    /**
     * Get the rotten tomatoes score
     *
     * @return score
     */
    public String getScoreRott () {
        return scoreRotten;
    }

    /**
     * Set the rotten tomatoes score
     *
     * @param score
     *            the score as a string
     */
    public void setScoreRott ( final String score ) {
        this.scoreRotten = score;
    }

    /**
     * Set the scores based on the full string of ratings
     *
     * @param scores
     *            the string of ratings
     */
    public void setScores ( final String scores ) {
        for ( final String ratings : scores.split( "{" ) ) {
            if ( ratings.contains( "Internet Movie Database" ) ) {
                setScoreIMd( ratings.substring( ratings.lastIndexOf( ':' ) ) );
            }
            if ( ratings.contains( "Rotten Tomatoes" ) ) {
                setScoreRott( ratings.substring( ratings.lastIndexOf( ':' ) ) );
            }
        }

    }

    public Movie () {
        this.genres = new ArrayList<>();
        this.writers = new ArrayList<>();
    }

    /**
     * set movie title
     *
     * @param title
     *            movie title
     **/
    public void setTitle ( final String title ) {
        this.title = title;
    }

    /**
     * get movie title
     *
     * @return movie title
     **/
    public String title () {
        return this.title;
    }

    /**
     * set movie release year
     *
     * @param releaseYear
     *            movie release year
     **/
    public void setReleaseYear ( final String releaseYear ) {
        this.releaseYear = releaseYear;
    }

    /**
     * get movie release year
     *
     * @return movie release year
     **/
    public String releaseYear () {
        return this.releaseYear;
    }

    /**
     * set movie director name
     *
     * @param director
     *            movie director name
     **/
    public void setDirector ( final String director ) {
        this.director = director;
    }

    /**
     * get movie director name
     *
     * @return movie director name as string
     **/
    public String director () {
        return this.director;
    }

    /**
     * set movie genre list
     *
     * @param genres
     *            list of movie genres
     **/
    public void setGenres ( final List<String> genres ) {
        this.genres = genres;
    }

    /**
     * set movie genre list using comma seperated genre string
     *
     * @param commaSeperatedGenres
     *            string of comma seperated movie genres
     **/
    public void setGenres ( final String commaSeperatedGenres ) {
        for ( final String genre : commaSeperatedGenres.split( "," ) ) {
            this.genres.add( genre.trim() );
        }
    }

    /**
     * add movie genre to movie geres list
     *
     * @param genre
     *            movie genre
     **/
    public void addGenre ( final String genre ) {
        this.genres.add( genre );
    }

    /**
     * return a list of movie genres
     *
     * @return a list of movie genres
     **/
    public List<String> genres () {
        return this.genres;
    }

    /**
     * set a list of movie writers
     *
     * @param writers
     *            a list of movie writer name strings
     **/
    public void setWriters ( final List<String> writers ) {
        this.writers = writers;
    }

    /**
     * set a list of movie writers using comma seperated writer names
     *
     * @param commaSeperatedWriters
     *            a comma seperated string of movie writer names
     **/
    public void setWriters ( final String commaSeperatedWriters ) {
        for ( final String writer : commaSeperatedWriters.split( "," ) ) {
            this.writers.add( writer.trim() );
        }
    }

    /**
     * add a movie writer to movie writers list
     *
     * @param writer
     *            movie writer name string
     **/
    public void addWriter ( final String writer ) {
        this.writers.add( writer );
    }

    /**
     * get a list of movie writers
     *
     * @return list of movie writer names
     **/
    public List<String> writers () {
        return this.writers;
    }

    /**
     * a pretty formated representation of movie metadata
     *
     * @return pretty formated movie metadata
     **/
    @Override
    public String toString () {
        return "Title: " + this.title + " (" + this.releaseYear + ")" + "\n" + "Director: " + this.director + "\n"
                + "Genres: " + String.join( ",", this.genres ) + "\n" + "Writers: " + String.join( ",", this.writers );
    }
}
