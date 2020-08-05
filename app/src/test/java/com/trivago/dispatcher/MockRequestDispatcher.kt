package com.trivago.dispatcher

import com.google.common.io.Resources
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.File
import java.net.HttpURLConnection

open class MockRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/people/?search=$EXISTING_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/character_search.json"))
            }
            "/people/?search=$NON_EXISTENT_SEARCH_PARAMS" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(
                        getJson("json/character_search_no_match.json")
                    )
            }
            NON_EXISTENT_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                    .setBody(getJson("json/not_found.json"))
            }
            EXISTING_CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/character_details.json"))
            }
            SPECIES_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/characters_species.json"))
            }
            FILM_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/character_films.json"))
            }
            PLANET_URL -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/character_planet.json"))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path}")
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
    }
}

fun getJson(path: String): String {
    val uri = Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}