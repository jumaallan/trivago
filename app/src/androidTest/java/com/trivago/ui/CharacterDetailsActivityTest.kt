package com.trivago.ui

import android.view.View
import androidx.preference.PreferenceManager
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.agoda.kakao.text.KTextView
import com.trivago.R
import com.trivago.base.BaseTest
import com.trivago.core.settings.Settings
import com.trivago.core.utils.TrivagoSharedPreferenceLiveData
import com.trivago.data.dummyFilms
import com.trivago.data.dummyPlanet
import com.trivago.data.dummySpecies
import com.trivago.data.repository.CharacterFilmsRepository
import com.trivago.data.repository.CharacterPlanetRepository
import com.trivago.data.repository.CharacterSpeciesRepository
import com.trivago.ui.viewmodel.CharacterDetailsViewModel
import com.trivago.ui.viewmodel.ThemeViewModel
import com.trivago.ui.views.CharacterDetailsActivity
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.koin.test.mock.declare

@LargeTest
@RunWith(AndroidJUnit4::class)
class CharacterDetailsActivityTest : BaseTest() {

    private val context =
        InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val themeViewModel: ThemeViewModel by inject()

    private val characterSpeciesRepository = mockk<CharacterSpeciesRepository>()
    private val characterFilmsRepository = mockk<CharacterFilmsRepository>()
    private val characterPlanetRepository = mockk<CharacterPlanetRepository>()

    @Before
    override fun setup() {
        super.setup()
        declare { mockk<ThemeViewModel>() }
        declare {
            CharacterDetailsViewModel(
                characterSpeciesRepository,
                characterFilmsRepository,
                characterPlanetRepository
            )
        }
    }

    @Test
    fun basicCharacterInfoIsDisplayed(): Unit = runBlocking {
        every { themeViewModel.getAppTheme() } returns TrivagoSharedPreferenceLiveData(
            sharedPreferences,
            Settings.PREFERENCE_THEME_KEY,
            "Light"
        )
        coEvery { characterPlanetRepository.fetchPlanet(any()) } returns dummyPlanet
        coEvery { characterFilmsRepository.fetchFilms(any()) } returns flowOf(dummyFilms)
        coEvery { characterSpeciesRepository.fetchSpecies(any()) } returns flowOf(dummySpecies)
        val intent = CharacterDetailsActivity.createIntent(
            context,
            "Test",
            "http://some-url",
            "2020",
            "160"
        )
        ActivityScenario.launch<CharacterDetailsActivity>(intent)

        onScreen<CharacterDetailsScreen> {
            birthYear {
                isDisplayed()
                hasText("2020")
            }
            height {
                isDisplayed()
                hasText("160 cm")
            }
        }
        Unit
    }

    @Test
    fun speciesInfoIsDisplayed(): Unit = runBlocking {
        every { themeViewModel.getAppTheme() } returns TrivagoSharedPreferenceLiveData(
            sharedPreferences,
            Settings.PREFERENCE_THEME_KEY,
            "Light"
        )
        coEvery { characterPlanetRepository.fetchPlanet(any()) } returns dummyPlanet
        coEvery { characterFilmsRepository.fetchFilms(any()) } returns flowOf(dummyFilms)
        coEvery { characterSpeciesRepository.fetchSpecies(any()) } returns flowOf(dummySpecies)
        val intent = CharacterDetailsActivity.createIntent(
            context,
            "Test",
            "http://some-url",
            "2020",
            "160"
        )
        ActivityScenario.launch<CharacterDetailsActivity>(intent)

        onScreen<CharacterDetailsScreen> {
            speciesList {
                isDisplayed()

                firstChild<SpeciesItem> {
                    isVisible()
                    name { hasText("Wookie") }
                    language { hasText("Shyriiwook") }
                }
            }
        }
        Unit
    }

    @Test
    fun planetInfoIsDisplayed(): Unit = runBlocking {
        every { themeViewModel.getAppTheme() } returns TrivagoSharedPreferenceLiveData(
            sharedPreferences,
            Settings.PREFERENCE_THEME_KEY,
            "Light"
        )
        coEvery { characterPlanetRepository.fetchPlanet(any()) } returns dummyPlanet
        coEvery { characterFilmsRepository.fetchFilms(any()) } returns flowOf(dummyFilms)
        coEvery { characterSpeciesRepository.fetchSpecies(any()) } returns flowOf(dummySpecies)
        val intent = CharacterDetailsActivity.createIntent(
            context,
            "Test",
            "http://some-url",
            "2020",
            "160"
        )
        ActivityScenario.launch<CharacterDetailsActivity>(intent)

        onScreen<CharacterDetailsScreen> {
            planetName {
                isDisplayed()
                hasText("Tatooine")
            }

            planetPopulation {
                isDisplayed()
                hasText("120000")
            }
        }
        Unit
    }

    @Test
    fun filmInfoIsDisplayed(): Unit = runBlocking {
        every { themeViewModel.getAppTheme() } returns TrivagoSharedPreferenceLiveData(
            sharedPreferences,
            Settings.PREFERENCE_THEME_KEY,
            "Light"
        )
        coEvery { characterPlanetRepository.fetchPlanet(any()) } returns dummyPlanet
        coEvery { characterFilmsRepository.fetchFilms(any()) } returns flowOf(dummyFilms)
        coEvery { characterSpeciesRepository.fetchSpecies(any()) } returns flowOf(dummySpecies)
        val intent = CharacterDetailsActivity.createIntent(
            context,
            "Test",
            "http://some-url",
            "2020",
            "160"
        )
        ActivityScenario.launch<CharacterDetailsActivity>(intent)

        onScreen<CharacterDetailsScreen> {
            filmsList {
                isDisplayed()

                firstChild<FilmsItem> {
                    isVisible()
                    title { hasText("A new Hope") }
                }
            }
        }
        Unit
    }
}

class CharacterDetailsScreen : Screen<CharacterDetailsScreen>() {
    val birthYear = KTextView { withId(R.id.textViewBirthYear) }
    val height = KTextView { withId(R.id.textViewHeightCm) }
    val speciesList = KRecyclerView(
        { withId(R.id.recyclerViewSpecies) },
        itemTypeBuilder = {
            itemType(::SpeciesItem)
        }
    )
    val planetName = KTextView { withId(R.id.textViewPlanetName) }
    val planetPopulation = KTextView { withId(R.id.textViewPlanetPopulation) }
    val filmsList = KRecyclerView(
        { withId(R.id.recyclerViewCharacterFilms) },
        itemTypeBuilder = {
            itemType(::FilmsItem)
        }
    )
}

class SpeciesItem(parent: Matcher<View>) : KRecyclerItem<SpeciesItem>(parent) {
    val name: KTextView = KTextView(parent) { withId(R.id.specieName) }
    val language: KTextView = KTextView(parent) { withId(R.id.specieLanguage) }
}

class FilmsItem(parent: Matcher<View>) : KRecyclerItem<FilmsItem>(parent) {
    val title: KTextView = KTextView(parent) { withId(R.id.textViewFilmTitle) }
}