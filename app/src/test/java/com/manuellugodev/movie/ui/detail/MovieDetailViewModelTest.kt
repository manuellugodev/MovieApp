package com.manuellugodev.movie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.manuellugodev.movie.usecases.GetMovieDetailByIdUseCase
import com.manuellugodev.movie.usecases.GetPopularMovieUseCase
import com.manuellugodev.movie.usecases.GetTopRatedMovieUseCase
import com.manuellugodev.movie.utils.getOrAwaitValue
import com.manuellugodev.movie.vo.DataResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
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

class MovieDetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)

    private val getMovieDetailByIdUseCase = mockk<GetMovieDetailByIdUseCase>()

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
    fun `given id then called use case with specified id`() {
        val viewModel = buildViewModel()

        coEvery { getMovieDetailByIdUseCase(any()) } returns DataResult.Success(mockk())

        for (id in 1..30) {
            viewModel.fetchMovieDetail(1).getOrAwaitValue { }
            coVerify {
                getMovieDetailByIdUseCase.invoke(eq(1))
            }
        }
    }


    private fun buildViewModel() =
        MovieDetailViewModel(getMovieDetailByIdUseCase)

}