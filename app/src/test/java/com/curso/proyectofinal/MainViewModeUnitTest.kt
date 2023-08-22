package com.curso.proyectofinal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.curso.proyectofinal.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModeUnitTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        var value = viewModel.modelo.value?.resultado
        assertEquals("",value)
    }

    @Test
    fun mainViewModel_TestCompararIguales() = runTest(){
        launch {
            viewModel.comparar("hola","hola")
        }
        advanceUntilIdle()
        val value = viewModel.modelo.value?.resultado
        assertEquals("iguales", value)
    }

    @Test
    fun mainViewModel_TestCompararDistintos() = runTest(){
        launch {
            viewModel.comparar("hola","chau")
        }
        advanceUntilIdle()
        val value = viewModel.modelo.value?.resultado
        assertEquals("diferentes", value)
    }

    @Test
    fun mainViewModel_TestCompararVacio() = runTest(){
        launch {
            viewModel.comparar("","")
        }
        advanceUntilIdle()
        val value = viewModel.modelo.value?.resultado
        assertEquals("", value)
    }
}