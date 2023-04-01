package ru.netology.vknewsclient.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.netology.vknewsclient.R

sealed class NavigationItem (

    val titleResId: Int,
    val icon: ImageVector

    ){

    object Home: NavigationItem(
        titleResId = R.string.navigation_item_home,
        icon = Icons.Outlined.Home
    )

    object Favourite: NavigationItem(
        titleResId = R.string.navigation_item_favorite,
        icon = Icons.Outlined.Favorite
    )

    object Profile: NavigationItem(
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}