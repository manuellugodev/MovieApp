package com.manuellugodev.movie.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.manuellugodev.movie.usecases.GetMovieBySearchUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    private val getMovieBySearchUseCase= mockk<GetMovieBySearchUseCase>()
    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanup(){
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `Given call to fetch movies searched invoke useCase`(){
        val viewModel=buildViewModel()

        viewModel.search("Prueba")

        coVerify { getMovieBySearchUseCase.invoke(any()) }
    }

    fun buildViewModel(): SearchViewModel {
        return SearchViewModel(getMovieBySearchUseCase)
    }
}