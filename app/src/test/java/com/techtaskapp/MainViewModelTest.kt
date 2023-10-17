package com.techtaskapp

import com.techtaskapp.domain.model.Breed
import com.techtaskapp.domain.model.CatItem
import com.techtaskapp.domain.model.Weight
import com.techtaskapp.domain.repository.CatRepository
import com.techtaskapp.presentation.main.MainViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import com.techtaskapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    lateinit var catRepository: CatRepository

    private lateinit var mainViewModel: MainViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        mainViewModel = MainViewModel(catRepository)
    }

    /*@Before
    fun setUp() {
        catRepository = mock(CatRepository::class.java) // Assuming you're using Mockito for mocking

    }*/
    @Test
    fun `getCats should set Loading and then update _user with cat data`() = runBlockingTest {
        // Given
        val limit = 20

        val breed1 = Breed(
            adaptability = 4,
            affection_level = 5,
            alt_names = "Siamese Cat",
            cfa_url = "https://cfa.org/siamese-cat/",
            child_friendly = 4,
            country_code = "TH",
            country_codes = "THA",
            description = "Description of Siamese Cat",
            dog_friendly = 3,
            energy_level = 5,
            experimental = 0,
            grooming = 1,
            hairless = 0,
            health_issues = 3,
            hypoallergenic = 1,
            id = "siamese",
            indoor = 0,
            intelligence = 5,
            lap = 5,
            life_span = "8 - 12",
            name = "Siamese",
            natural = 1,
            origin = "Thailand",
            rare = 0,
            reference_image_id = "12345",
            rex = 0,
            shedding_level = 2,
            short_legs = 0,
            social_needs = 5,
            stranger_friendly = 5,
            suppressed_tail = 0,
            temperament = "Active, Vocal, Social",
            vcahospitals_url = "https://vcahospitals.com/know-your-pet/cat-breeds/siamese",
            vetstreet_url = "https://www.vetstreet.com/cats/siamese",
            vocalisation = 5,
            weight = Weight(imperial = "6 - 10", metric = "3 - 5"),
            wikipedia_url = "https://en.wikipedia.org/wiki/Siamese_cat"
        )


        val catItemWithBreeds = CatItem(
            breeds = listOf(breed1, breed1),
            height = 30,
            id = "1",
            url = "https://example.com/cat1.jpg",
            width = 40
        )

        val catItemWithoutBreeds = CatItem(
            breeds = null,
            height = 25,
            id = "2",
            url = "https://example.com/cat2.jpg",
            width = 35
        )

        val catItemWithEmptyBreeds = CatItem(
            breeds = emptyList(),
            height = 20,
            id = "3",
            url = "https://example.com/cat3.jpg",
            width = 25
        )


        val mockCats = listOf(catItemWithoutBreeds, catItemWithBreeds,catItemWithEmptyBreeds)
        Mockito.`when`(catRepository.getCats(limit)).thenReturn(Result.Success(mockCats))

        // When
        mainViewModel.getCats(limit)

        // Then
        val loadingResult = mainViewModel.cat.getOrAwaitValue()
        Assert.assertTrue(loadingResult is Result.Loading)

        // Continue to let the coroutine finish
        delay(100) // Adjust this delay based on your actual implementation

        val loadedResult = mainViewModel.cat.getOrAwaitValue()
        Assert.assertTrue(loadedResult is Result.Success)
        Assert.assertEquals(mockCats, (loadedResult as Result.Success).data)
    }



    @Test
    fun testGetCatsSuccess() = runBlocking {
        // Given
        val limit = 20

        val breed1 = Breed(
            adaptability = 4,
            affection_level = 5,
            alt_names = "Siamese Cat",
            cfa_url = "https://cfa.org/siamese-cat/",
            child_friendly = 4,
            country_code = "TH",
            country_codes = "THA",
            description = "Description of Siamese Cat",
            dog_friendly = 3,
            energy_level = 5,
            experimental = 0,
            grooming = 1,
            hairless = 0,
            health_issues = 3,
            hypoallergenic = 1,
            id = "siamese",
            indoor = 0,
            intelligence = 5,
            lap = 5,
            life_span = "8 - 12",
            name = "Siamese",
            natural = 1,
            origin = "Thailand",
            rare = 0,
            reference_image_id = "12345",
            rex = 0,
            shedding_level = 2,
            short_legs = 0,
            social_needs = 5,
            stranger_friendly = 5,
            suppressed_tail = 0,
            temperament = "Active, Vocal, Social",
            vcahospitals_url = "https://vcahospitals.com/know-your-pet/cat-breeds/siamese",
            vetstreet_url = "https://www.vetstreet.com/cats/siamese",
            vocalisation = 5,
            weight = Weight(imperial = "6 - 10", metric = "3 - 5"),
            wikipedia_url = "https://en.wikipedia.org/wiki/Siamese_cat"
        )


        val catItemWithBreeds = CatItem(
            breeds = listOf(breed1, breed1),
            height = 30,
            id = "1",
            url = "https://example.com/cat1.jpg",
            width = 40
        )

        val catItemWithoutBreeds = CatItem(
            breeds = null,
            height = 25,
            id = "2",
            url = "https://example.com/cat2.jpg",
            width = 35
        )

        val catItemWithEmptyBreeds = CatItem(
            breeds = emptyList(),
            height = 20,
            id = "3",
            url = "https://example.com/cat3.jpg",
            width = 25
        )


        val mockCats = listOf(catItemWithoutBreeds, catItemWithBreeds,catItemWithEmptyBreeds)
        Mockito.`when`(catRepository.getCats(limit)).thenReturn(Result.Success(mockCats))

        // When
        mainViewModel.getCats(limit)

        // Then
        val result = mainViewModel.cat.getOrAwaitValue()
        Assert.assertTrue(result is Result.Success)
        Assert.assertEquals(mockCats, (result as Result.Success).data)
    }

    @Test
    fun testGetCatsError() = runBlocking {
        // Given
        val limit = 20
        val mockError = Exception("An error occurred")
        Mockito.`when`(catRepository.getCats(limit)).thenReturn(Result.Error(mockError))


        // When
        mainViewModel.getCats(limit)

        // Then
        val result = mainViewModel.cat.getOrAwaitValue()
        Assert.assertTrue(result is Result.Error)
        Assert.assertEquals(mockError, (result as Result.Error).exception)
    }


}
