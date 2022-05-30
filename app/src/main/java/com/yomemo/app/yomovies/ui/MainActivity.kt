package com.yomemo.app.yomovies.ui

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.yomemo.app.yomovies.R
import com.yomemo.app.yomovies.databinding.ActivityMainBinding
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding

    //    private val model: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = navController()
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_movie_theaters,
                R.id.nav_favorite,
                R.id.nav_profile,
            ), binding.drawerLayout
        )
        //Left menu navigation
        binding.navView.setupWithNavController(navController)
        setupBottomNavigation()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_movie_details || destination.id == R.id.nav_edit_profile
                || destination.id == R.id.nav_explore || destination.id == R.id.nav_about
                || destination.id == R.id.nav_scan || destination.id == R.id.nav_my_tickets
            ) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
//        loadProfile()
    }

//    private fun loadProfile() {
//        val user = FirebaseAuth.getInstance().currentUser
//        val nView: View = binding.navView.getHeaderView(0)
//        val textName = nView.findViewById<TextView>(R.id.text_display_name)
//        val textEmail = nView.findViewById<TextView>(R.id.text_email)
//        val profileImage = nView.findViewById<ImageView>(R.id.profile_image)
//        textName.text = user?.displayName
//        textEmail.text = user?.email
//        profileImage.load(user?.photoUrl)
//    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, binding.navHostFragment).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun setupBottomNavigation() {
        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.ic_notification, // the icon
                R.drawable.avd_notification, // the AVD that will be shown in FAB
                R.id.nav_favorite // optional if you use Jetpack Navigation
            ),
            CbnMenuItem(
                R.drawable.ic_dashboard,
                R.drawable.avd_dashboard,
                R.id.nav_movie_theaters
            ),
            CbnMenuItem(
                R.drawable.ic_home,
                R.drawable.avd_home,
                R.id.nav_home
            ),
            CbnMenuItem(
                R.drawable.ic_profile,
                R.drawable.avd_profile,
                R.id.nav_profile
            ),
            CbnMenuItem(
                R.drawable.ic_settings,
                R.drawable.avd_settings,
                R.id.nav_settings
            )
        )
        binding.bottomNavigation.setMenuItems(menuItems, 2)
        binding.bottomNavigation.setupWithNavController(navController())
    }

    private fun navController() =
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

    fun setToolbar(toolbar: Toolbar) {
        toolbar.setupWithNavController(navController(), appBarConfiguration)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}