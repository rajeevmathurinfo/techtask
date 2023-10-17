package com.techtaskapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import com.techtaskapp.domain.model.BottomNavItem

class Constants {
    companion object {
        const val BASE_URL = "https://api.thecatapi.com/"
        const val API_KEY = "live_ck81qaOtX9Bo9iOxNPY2u5ykTiowhL40ecvgZn6JZ0bR6VH1Xqwh2rppZNWfhSRo"

        val BottomNavItems = listOf(
            BottomNavItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = "home"
            ),
            BottomNavItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = "search"
            ),
            BottomNavItem(
                label = "Favourite",
                icon = Icons.Filled.Favorite,
                route = "favourite"
            )
        )
    }
}
