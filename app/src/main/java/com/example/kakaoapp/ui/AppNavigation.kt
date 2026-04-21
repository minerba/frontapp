package com.example.kakaoapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kakaoapp.data.CreditViewModel
import kotlinx.coroutines.delay

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // ViewModel을 AppNavigation 레벨에서 생성해 모든 화면이 공유
    val creditViewModel: CreditViewModel = viewModel()

    val navigateToHome: () -> Unit = {
        navController.navigate("home") {
            popUpTo("home") { inclusive = true }
        }
    }

    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("splash") {
            SplashScreen()
            LaunchedEffect(Unit) {
                delay(2000)
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }

        composable("home") {
            HomeScreen(onNavigateToCredit = { navController.navigate("credit_loading") })
        }

        composable("credit_loading") {
            CreditLoadingScreen(onBack = { navController.popBackStack() })
            LaunchedEffect(Unit) {
                delay(2000)
                navController.navigate("credit") {
                    popUpTo("credit_loading") { inclusive = true }
                }
            }
        }

        composable("credit") {
            CreditScreen(
                onBack = { navController.popBackStack() },
                onNavigateToHome = navigateToHome,
                onNavigateToHistory = { type -> navController.navigate("history_loading/$type") },
                onNavigateToLoanBalance = { navController.navigate("loan_balance_loading") },
                creditViewModel = creditViewModel
            )
        }

        composable("history_loading/{type}") { back ->
            val type = back.arguments?.getString("type") ?: "KCB"
            CreditHistoryLoadingScreen(onBack = { navController.popBackStack() })
            LaunchedEffect(type) {
                delay(2000)
                navController.navigate("history/$type") {
                    popUpTo("history_loading/$type") { inclusive = true }
                }
            }
        }

        composable("history/{type}") { back ->
            val type = back.arguments?.getString("type") ?: "KCB"
            key(type) {
                CreditHistoryScreen(
                    scoreType = type,
                    onBack = { navController.popBackStack() },
                    onNavigateToHome = navigateToHome,
                    onTabChange = { newType ->
                        navController.navigate("history_loading/$newType") {
                            popUpTo("history/$type") { inclusive = true }
                        }
                    },
                    creditViewModel = creditViewModel
                )
            }
        }

        composable("loan_balance_loading") {
            LoanBalanceLoadingScreen(onBack = { navController.popBackStack() })
            LaunchedEffect(Unit) {
                delay(1000)
                navController.navigate("loan_balance") {
                    popUpTo("loan_balance_loading") { inclusive = true }
                }
            }
        }

        composable("loan_balance") {
            LoanBalanceScreen(
                onBack = { navController.popBackStack() },
                onNavigateToHome = navigateToHome
            )
        }
    }
}
