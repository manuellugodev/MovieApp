package com.manuellugodev.movie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.manuellugodev.movie.data.home.RepositoryMovies
import com.manuellugodev.movie.usecases.GetMovieDetailByIdUseCase
import com.manuellugodev.movie.usecases.GetPopularMovieUseCase
import com.manuellugodev.movie.usecases.GetTopRatedMovieUseCase
import com.manuellugodev.movie.utils.getOrAwaitValue
import com.manuellugodev.movie.vo.DataResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.justRun
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)


    private val getTopRatedMovieUseCase = mockk<GetTopRatedMovieUseCase>()
    private val getPopularMovieUseCase = mockk<GetPopularMovieUseCase>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `given call to fetch Popular invoke useCase`() {
        coEvery { getPopularMovieUseCase() } returns DataResult.Success(listOf())

        val viewModel = buildViewModel()

        viewModel.fetchMoviePopularList.getOrAwaitValue()

        coVerify { getPopularMovieUseCase.invoke() }
    }


    @Test
    fun `given call to top rated invoke useCase`() {
        coEvery { getTopRatedMovieUseCase() } returns DataResult.Success(listOf())

        val viewModel = buildViewModel()

        viewModel.fetchMovieTopRated.getOrAwaitValue()

        coVerify { getTopRatedMovieUseCase.invoke() }
    }

    private fun buildViewModel() =
        HomeViewModel(getTopRatedMovieUseCase, getPopularMovieUseCase)


}