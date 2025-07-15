package com.project.e_commerce.android.presentation.ui.navigation

import com.project.e_commerce.android.R

sealed class Screens(val route: String, val title : String? = null, val icon : Int? = null) {


    object SplashScreen : Screens(route = "splash_screen" )
    object LoginScreen : Screens(route = "login_screen"){
        object ChangePasswordScreen : Screens(route = "reset_password_screen")
        object CreateAccountScreen : Screens(route = "create_account_screen")

        object EnterEmailScreen: Screens(route = "enter_email_screen")
        object ResetPasswordScreen : Screens(route = "verify_code_screen")
        object PasswordChangedSuccessScreen : Screens(route = "password_changed_success_screen")

    }
    object ReelsScreen: Screens(title = "Reels",route = "reels_screen", icon = R.drawable.ic_home, ){
        object SearchReelsAndUsersScreen : Screens(route = "search_reels_screen")
        object ExploreScreen : Screens(route = "explore")
        object SoundPageScreen : Screens(route = "sound")
    }

    object ProductScreen : Screens(title = "Products",route = "product_screen",icon = R.drawable.ic_products_filled) {
        object DetailsScreen : Screens("details_screen")
        object SearchScreen : Screens("search_screen")
        object AllProductsScreen : Screens("all_products_screen")
    }

    object CartScreen : Screens(title =  "Cart", route = "cart_screen", icon = R.drawable.ic_cart){

        object PaymentScreen : Screens(route = "payment_screen")
    }


    object ProfileScreen : Screens(title = "Profile", route = "profile_screen",icon = R.drawable.ic_profile, ){

        object EditPersonalProfile: Screens(route = "edit_personal_profile")

        object OrdersHistoryScreen : Screens(route = "orders_history_screen")

        object TrackOrderScreen : Screens(route = "orders_track_screen")

        object SettingsScreen : Screens(route = "settings_screen")

        object RequestHelpScreen : Screens(route = "request_help_screen")

        object FavouritesScreen  : Screens(route = "favourites_screen")

        object RecentlyScreen  : Screens(route = "recently_screen")

        object AddNewContentScreen: Screens(route = "add_new_content_screen")

        object NotificationScreen : Screens(route = "notification_screen")

        object EditProfileScreen : Screens("edit_profile_screen")



    }


}