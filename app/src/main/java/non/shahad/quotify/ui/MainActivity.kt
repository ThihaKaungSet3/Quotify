package non.shahad.quotify.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import non.shahad.quotify.MyApplication
import non.shahad.quotify.R
import non.shahad.quotify.data.local.daos.BackgroundColorDao
import non.shahad.quotify.data.remote.BrainyAPI
import non.shahad.quotify.data.remote.EndPoint
import non.shahad.quotify.utils.SharedPreferencesHelper
import non.shahad.quotify.utils.toast
import org.koin.android.ext.android.get
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, HasAndroidInjector{

    private lateinit var drawerToggle : ActionBarDrawerToggle
    private lateinit var navController: NavController

    private lateinit var sharedPrefHelper : SharedPreferencesHelper
    @Inject
    lateinit var  dispatchingAndroidInjector : DispatchingAndroidInjector<Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        Log.d("Injected","${dispatchingAndroidInjector}")
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR



        modifyActionbar()
        setUpNavigationController()
        initNavDrawer()

    }

    private fun setUpNavigationController(){
        navController = Navigation.findNavController(this,
            R.id.nav_host_fragment
        )

        NavigationUI.setupWithNavController(navigationView,navController)
    }

    /**
     * Initiate Navigation Drawer
     */
    private fun initNavDrawer(){
        drawerToggle = ActionBarDrawerToggle(this,drawerlayout,toolbar,
            R.string.opendrawer,
            R.string.closedrawer
        )
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerlayout.addDrawerListener(drawerToggle)
        navigationView.setNavigationItemSelectedListener(this)
        drawerToggle.syncState()
    }

    /**
     * All about actionbar
     */
    private fun modifyActionbar(){
        // used that trick as displayShowTitleEnabled was not work
        toolbar.title = ""

        setSupportActionBar(toolbar)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    /**
     * Create Toolbar Menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_actionbar_menu,menu)
        return true
    }

    /**
     * Toolbar Item Selection
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                drawerlayout.openDrawer(GravityCompat.START)
                return true
            }
            R.id.search_menu -> {
                toast("Search view is in progress")
            }

        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * attach navigate action to Drawer
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this,
            R.id.nav_host_fragment
        ),drawerlayout)
    }

    /**
     * NAVIGATION ITEM SELECTION OF nav drawer
     */
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.home -> {
                navigateNow(menuItem, R.id.homeFragment)
                return true
            }

            R.id.explore -> {
                navigateNow(menuItem, R.id.exploreFragment)
                return true
            }

            R.id.saved -> {
                navigateNow(menuItem,
                    R.id.savedQuotesFragment
                )
                return true
            }

            R.id.setting -> {
                navigateNow(menuItem, R.id.settingFragment)
                return true
            }
        }

        return true
    }

    private fun navigateNow(menuItem : MenuItem,resId : Int){
        menuItem.isChecked = true
        drawerlayout.closeDrawer(GravityCompat.START)
        navController.navigate(resId)
    }

    override fun onBackPressed() {

        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawers()
        }else super.onBackPressed()

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }


}
