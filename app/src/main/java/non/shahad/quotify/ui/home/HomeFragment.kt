package non.shahad.quotify.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yuyakaido.android.cardstackview.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.fragment_home.*
import non.shahad.quotify.R
import non.shahad.quotify.ui.base.BaseFragment
import non.shahad.quotify.ui.bottomsheets.choosecolor.ChooseColorBottomFragment
import non.shahad.quotify.ui.bottomsheets.choosefont.ChooseFontBottomFragment
import non.shahad.quotify.callbacks.BottomSheetItemChooseListener
import non.shahad.quotify.dagger.Injectable
import non.shahad.quotify.data.local.database.AppDatabase
import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.ui.bottomsheets.choosefont.FontFamily
import non.shahad.quotify.ui.bottomsheets.choosefont.FontSize
import non.shahad.quotify.utils.Constants
import non.shahad.quotify.utils.SharedPreferencesHelper
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import javax.inject.Inject


class HomeFragment : BaseFragment(),BottomSheetItemChooseListener,Injectable {
    companion object{
        // Req code for bottomsheet fragment results
        const val colorReqCode : Int = 66
        const val fontReqCode : Int = 77
    }


    @Inject
    lateinit var viewmodelFactory : ViewModelProvider.Factory

    @Inject
    lateinit var sharedPrefsHelper : SharedPreferencesHelper

    private lateinit var viewModel : HomeViewModel

    private val manager : CardStackLayoutManager by lazy {CardStackLayoutManager(context,this)}

    private val swipeAdapter : SwipeCardAdapter by lazy { SwipeCardAdapter(this)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this,viewmodelFactory).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.quotesByCategory("motivational").observe(this, Observer {
            swipeAdapter.setQuotesList(it)

        })

        setUpButtons()
        addNecessaryStateToAdapter()
        getSelectedDrawblefromDb()
        initiateSwipeCard()

    }



    private fun addNecessaryStateToAdapter (){
        swipeAdapter.setFontFamily(FontFamily.valueOf(sharedPrefsHelper.fontFamily()))
        swipeAdapter.setFontSize(FontSize.valueOf(sharedPrefsHelper.fontSize()))
    }

    private fun setUpButtons(){
        likeBtn.setOnClickListener{
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()

            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }


        unlikeBtn.setOnClickListener{
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()

            manager.setSwipeAnimationSetting(setting)
            cardStackView.swipe()
        }
    }


    private fun initiateSwipeCard (){

        with(manager){
            setStackFrom(StackFrom.None)
            setVisibleCount(2)
            setTranslationInterval(8.0f)
            setScaleInterval(0.95f)
            setSwipeThreshold(0.3f)
            setMaxDegree(20.0f)
            setDirections(Direction.HORIZONTAL)
            setCanScrollHorizontal(true)
            setCanScrollVertical(true)
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }

        cardStackView.layoutManager = manager
        cardStackView.adapter = swipeAdapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false

            }
        }
    }


    private fun getSelectedDrawblefromDb(){

        viewModel.findColorById(sharedPrefsHelper.selectedBackgroundColor()).observe(this, Observer {
            Log.d("AutumnSong","$it")
            swipeAdapter.setSelectedColor(it)

        })
    }


    override fun onColorViewClick() {
        val colorSheet : ChooseColorBottomFragment? = ChooseColorBottomFragment.newInstance()
        showBottomSheet(this,colorSheet, colorReqCode,"colorBottomSheet")
    }

    override fun onFontViewClick() {
        val fontSheet : ChooseFontBottomFragment? = ChooseFontBottomFragment.newInstance()
        showBottomSheet(this,fontSheet, fontReqCode,"fontBottomSheet")
    }

    override fun onShareClick() {
        Log.d("HomeFragment", "share")
    }

    override fun onAuthorClick() {
        Log.d("HomeFragment", "Author")
    }

    override fun onCardSwiped(direction: Direction?) {
    }

    override fun onCardCanceled() {

    }


    /**
     * Callbacks below are from Bottomsheet fragments
     */
    override fun onColorChoose(colorEntity: ColorEntity, position: Int) {
        swipeAdapter.setSelectedColor(colorEntity)
        sharedPrefsHelper.putLong(Constants.SELECTED_BG_COLOR,colorEntity.id)
        swipeAdapter.notifyDataSetChanged()
    }


    override fun onFontChoose(family: FontFamily) {
        when(family){
            FontFamily.ATALSI -> {
                sharedPrefsHelper.putString("ATALSI","ATALSI")
                swipeAdapter.setFontFamily(FontFamily.valueOf("ATALSI"))
            }
            FontFamily.CALISTOGA -> {
                sharedPrefsHelper.putString("CALISTOGA","CALISTOGA")
                swipeAdapter.setFontFamily(FontFamily.valueOf("CALISTOGA"))
            }
            FontFamily.SOLWAY -> {
                sharedPrefsHelper.putString("SOLWAY","SOLWAY")
                swipeAdapter.setFontFamily(FontFamily.valueOf("SOLWAY"))
            }
        }
        swipeAdapter.notifyDataSetChanged()
    }

    override fun onFontSizeChange(fontSize: FontSize) {
        when(fontSize){
            FontSize.REGULAR -> {
                sharedPrefsHelper.putString("REGULAR","REGULAR")
                swipeAdapter.setFontSize(FontSize.valueOf("REGULAR"))
            }
            FontSize.MEDIUM -> {
                sharedPrefsHelper.putString("MEDIUM","MEDIUM")
                swipeAdapter.setFontSize(FontSize.valueOf("MEDIUM"))
            }
            FontSize.LARGE -> {
                sharedPrefsHelper.putString("LARGE","LARGE")
                swipeAdapter.setFontSize(FontSize.valueOf("LARGE"))
            }
        }
        swipeAdapter.notifyDataSetChanged()

    }

    override fun onDestroy() {
        cardStackView.adapter = null
        super.onDestroy()
    }


}
