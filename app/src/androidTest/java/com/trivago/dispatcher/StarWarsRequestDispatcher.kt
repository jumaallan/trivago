package com.trivago.dispatcher

import com.trivago.data.*
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

/**
 * Handles Requests from mock web server
 */
internal class StarWarsRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/people/?search=$EXISTING_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(searchSuccess)
            }
            "/people/?search=$NON_EXISTENT_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(searchNoMatch)
            }
            "/people/?search=$ERROR_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(notFound)
            }
            NON_EXISTENT_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(notFound)
            }
            EXISTING_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(characterDetails)
            }
            SPECIES_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(characterSpecies)
            }
            FILM_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(characterFilms)
            }
            PLANET_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(characterPlanet)
            }
            else -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            }
        }
    }

    companion object Constants {
        const val SPECIES_URL = "/api/species/1/"
        const val FILM_URL = "/api/films/1/"
        const val PLANET_URL = "/api/planets/1/"
        const val EXISTING_CHARACTER_URL = "/api/people/1/"
        const val NON_EXISTENT_CHARACTER_URL = "/api/people/2000/"
        const val EXISTING_SEARCH_PARAMS = "Darth"
        const val NON_EXISTENT_SEARCH_PARAMS = "Zipa"
        const val ERROR_SEARCH_PARAMS = "Error"
    }

}