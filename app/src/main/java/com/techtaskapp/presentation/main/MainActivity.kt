package com.techtaskapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.techtaskapp.presentation.favourit.Favourite
import com.techtaskapp.presentation.home.Home
import com.techtaskapp.presentation.search.Search
import com.techtaskapp.ui.theme.TechTaskAppTheme
import com.techtaskapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //  SmallTopAppBarExample(userViewModel)
            TechTaskAppTheme {
                //remember navController so it does not get recreated on recomposition
                val navController = rememberNavController()


                Surface(color = androidx.compose.ui.graphics.Color.White) {

                    //Scaffold Component
                    Scaffold(
                        //Bottom navigation
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }, content = { padding ->

                            //Navhost: where screens are placed
                            NavHostContainer(
                                viewModel = userViewModel,
                                navController = navController,
                                padding = padding
                            )
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    viewModel: MainViewModel
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "home",

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {

            // route : Home
            composable("home") {
                Home(viewModel)
            }

            // route : search
            composable("search") {
                Search(viewModel)
            }

            // route : profile
            composable("favourite") {

                Favourite()
            }
        })

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(

        //set background color
        backgroundColor = Color(0xFFBB86FC)
    ) {

        //observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        //observe current route to change the icon color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        //Bottom nav items we declared
        Constants.BottomNavItems.forEach { navItem ->

            //Place the bottom nav items
            BottomNavigationItem(
                //it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(
                        navItem.route
                    )
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }


    }
}


