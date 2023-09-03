package com.desarrollodeaplicacionesmoviles

import android.text.style.LineBackgroundSpan.Standard
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.desarrollodeaplicacionesmoviles.model.MainUiState
import com.desarrollodeaplicacionesmoviles.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelUnitTest {

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    /**
     * Antes de los test
     */
    @Before
    fun setup() {
        mainViewModel = MainViewModel()
        Dispatchers.setMain(dispatcher)
    }

    /**
     * Después de los test
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain();
    }

    @Test
    fun checkInitialValue() = runTest {
        val value = mainViewModel.strings.value;
        assertEquals(MainUiState("","",""), value)
    }


    // Doesn't work
    @Test
    fun btnComparar() = runTest {

        launch {
            mainViewModel.updateTxt1("a")
            mainViewModel.updateTxt2("a")
        }
        advanceUntilIdle()
        launch {
            mainViewModel.comparar()
        }
        advanceUntilIdle()
        val value = mainViewModel.strings.value?.result
        assertEquals("Las palabras son iguales", value)
    }

    @Test
    fun updateTxt1() = runTest {
        launch {
            mainViewModel.updateTxt1("a")
        }
        advanceUntilIdle()
        val value = mainViewModel.strings.value?.txt1
        assertEquals("a", value)
    }

    @Test
    fun updateTxt2() = runTest {
        launch {
            mainViewModel.updateTxt2("b")
        }
        advanceUntilIdle()
        val value = mainViewModel.strings.value?.txt2
        assertEquals("b", value)
    }
}