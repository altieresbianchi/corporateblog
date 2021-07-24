package br.com.phaneronsoft.corpblog.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.phaneronsoft.corpblog.business.repository.UserRepositoryContract
import br.com.phaneronsoft.corpblog.getOrAwaitValueTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class LoginViewModelTest {
    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userRepositoryMock: UserRepositoryContract
    private lateinit var viewModel: LoginViewModel

    private lateinit var loginResultLiveDataObserver: Observer<Boolean>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        userRepositoryMock = mock()

        loginResultLiveDataObserver = mock()

        viewModel = LoginViewModel(
            Dispatchers.IO,
            userRepositoryMock
        )
    }

    @Test
    fun `Log in, when it is passed email and password, then return authentication status`() {

        // ARRANGE

        val email = "email@email.com"
        val password = "abc123"
        val expectedLoginResponse = true

        whenever(userRepositoryMock.login(email, password))
            .thenReturn(expectedLoginResponse)

        // ACT
        viewModel.login(email, password)
        viewModel.loginResult.observeForever(loginResultLiveDataObserver)

        val resultFromLiveData = viewModel.loginResult.getOrAwaitValueTest()

        // ASSERT
        verify(loginResultLiveDataObserver).onChanged(resultFromLiveData)
        assertEquals(resultFromLiveData, expectedLoginResponse)
    }
}
