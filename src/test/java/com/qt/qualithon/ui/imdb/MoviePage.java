package com.qt.qualithon.ui.imdb;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;

/**
 * page object class represents elements and actions on the IMDb Movie Page
 **/
public class MoviePage extends Page {

    public MoviePage ( final TestSession testSession ) {
        super( testSession );

        // adjust page for tablet formfactor
        final WebElement showMoreLink = this.testSession.driverWait().until( ExpectedConditions
                .presenceOfElementLocated( By.cssSelector( "a[data-testid='title-pc-expand-more-button']" ) ) );

        if ( showMoreLink.isDisplayed() ) {
            showMoreLink.click();
        }

    }

    /**
     * Method to get the rating from the page
     *
     * @return the rating
     */
    public String rating () {
        return this.testSession.driverWait().until( ExpectedConditions
                .visibilityOfElementLocated( By.cssSelector( "h1[data-testid='hero-title-block__metadata']" ) ) )
                .getText();
    }

    public String score () {
        final List<WebElement> scores = this.testSession.driverWait().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy( By.cssSelector( "li.ipc-metadata-list__item" ) ) );

        // traverse scores sections to find the section with Scores
        for ( final WebElement score : scores ) {
            try {
                if ( score.findElement( By.cssSelector( "span" ) ).getText().equalsIgnoreCase( "Source" ) ) {
                    // find score from specific database and return, was not
                    // able to implement without being able to run the code
                    return null;
                }
            }
            catch ( final NoSuchElementException e ) {
            }
        }
        return null;
    }

    /**
     * get movie title
     *
     * @return movie title
     **/
    public String title () {
        return this.testSession.driverWait()
                .until( ExpectedConditions
                        .visibilityOfElementLocated( By.cssSelector( "h1[data-testid='hero-title-block__title']" ) ) )
                .getText();
    }

    /**
     * get movie director name
     *
     * @return movie director name
     **/
    public String director () {
        final List<WebElement> credits = this.testSession.driverWait().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy( By.cssSelector( "li.ipc-metadata-list__item" ) ) );

        // traverse credits sections to find the section with Directors
        for ( final WebElement credit : credits ) {
            try {
                if ( credit.findElement( By.cssSelector( "span" ) ).getText().equalsIgnoreCase( "Director" ) ) {
                    // find director name from child element of section
                    return credit.findElement( By.cssSelector( "a" ) ).getText();
                }
            }
            catch ( final NoSuchElementException e ) {
            }
        }
        throw new NoSuchElementException( "Failed to lookup Director on page" );
    }

    /**
     * get list of movie genres
     *
     * @return list of movie genres
     **/
    public List<String> genres () {
        final List<String> genres = new ArrayList<>();

        // if genres list is empty throw exception
        if ( genres.isEmpty() ) {
            throw new NoSuchElementException( "Could not lookup genres on Movie page" );
        }
        return genres;
    }

    /**
     * get movie release year
     *
     * @return movie release year
     **/
    public String releaseYear () {
        return this.testSession.driverWait()
                .until( ExpectedConditions
                        .presenceOfElementLocated( By.cssSelector( "ul[data-testid='hero-title-block__metadata']" ) ) )
                .getText();
    }

    /**
     * get list of movie writers
     *
     * @return list of movie writer names
     **/
    public List<String> writers () {
        final List<String> writers = new ArrayList<>();
        final List<WebElement> credits = this.testSession.driverWait().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy( By.cssSelector( "li.ipc-metadata-list__item" ) ) );

        // traverse credits sections to find the section with Writers
        for ( final WebElement credit : credits ) {
            try {
                if ( credit.findElement( By.cssSelector( "span" ) ).getText().equalsIgnoreCase( "Writers" ) ) {
                    // traverse list of writers on page to add to writers list
                    final List<WebElement> writersElements = credit.findElements( By.cssSelector( "a" ) );
                    for ( int i = writersElements.size() - 1; i >= 0; i-- ) {
                        writers.add( writersElements.get( i ).getText() );
                    }
                    break;
                }
            }
            catch ( final NoSuchElementException e ) {
            }
        }

        // if writers list is empty throw exception
        if ( writers.isEmpty() ) {
            throw new NoSuchElementException( "Could not lookup Writers on movie page" );
        }
        return writers;
    }

}
