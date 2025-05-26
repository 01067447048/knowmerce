package com.morphine_coder.knowmerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.morphine_coder.knowmerce.core.common.navigation.Destination
import com.morphine_coder.knowmerce.core.common.navigation.NavigationAction
import com.morphine_coder.knowmerce.core.common.navigation.Navigator
import com.morphine_coder.knowmerce.core.designsystem.snackbar.SnackBarController
import com.morphine_coder.knowmerce.core.designsystem.utils.ObserveAsEvent
import com.morphine_coder.knowmerce.feature.search.SearchScreen
import com.morphine_coder.knowmerce.feature.search.SearchViewModel
import com.morphine_coder.knowmerce.ui.theme.KnowmerceTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KnowmerceTheme {
                val snackBarHostState = remember {
                    SnackbarHostState()
                }
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackBarHostState)
                    }
                ) { innerPadding ->

                    ObserveAsEvent(
                        flow = SnackBarController.events,
                        snackBarHostState
                    ) { event ->
                        scope.launch {
                            snackBarHostState.currentSnackbarData?.dismiss()

                            val result = snackBarHostState.showSnackbar(
                                message = event.message,
                                actionLabel = event.action?.name,
                                duration = event.duration
                            )

                            if (result == SnackbarResult.ActionPerformed) {
                                event.action?.action?.invoke()
                            }
                        }
                    } // Snackbar

                    ObserveAsEvent(
                        flow = navigator.navigationActions
                    ) { action ->
                        when(action) {
                            is NavigationAction.Navigate -> navController.navigate(
                                action.destination
                            ) {
                                action.navOptions(this)
                            }
                            NavigationAction.NavigateUp -> navController.navigateUp()
                            else -> { }
                        }
                    } // Navigation

                    NavHost(
                        navController = navController,
                        startDestination = navigator.startDestination,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Destination.SearchRoute> { navBackStackEntry ->
                            SearchScreen(
                                viewModel = hiltViewModel(navBackStackEntry)
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KnowmerceTheme {
        Greeting("Android")
    }
}