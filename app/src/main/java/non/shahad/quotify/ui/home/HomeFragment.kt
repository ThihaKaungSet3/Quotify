package non.shahad.quotify.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_home.*
import non.shahad.quotify.R
import non.shahad.quotify.ui.base.BaseFragment
import non.shahad.quotify.ui.bottomsheets.choosecolor.ChooseColorBottomFragment
import non.shahad.quotify.ui.bottomsheets.choosefont.ChooseFontBottomFragment
import non.shahad.quotify.callbacks.BottomSheetItemChooseListener
import non.shahad.quotify.data.entities.ColorEntity
import non.shahad.quotify.datamodels.Quote
import non.shahad.quotify.ui.bottomsheets.choosefont.FontFamily
import non.shahad.quotify.ui.bottomsheets.choosefont.FontSize
import non.shahad.quotify.utils.Constants
import non.shahad.quotify.utils.SharedPreferencesHelper


class HomeFragment : BaseFragment(),BottomSheetItemChooseListener  {
    companion object{
        // Req code for bottomsheet fragment results
        const val colorReqCode : Int = 66
        const val fontReqCode : Int = 77
    }


    private lateinit var viewModel: HomeViewModel
    private val manager : CardStackLayoutManager by lazy {CardStackLayoutManager(context,this)}
    private val sharedPrefsHelper : SharedPreferencesHelper by lazy { SharedPreferencesHelper.getInstance(context!!) }
    private val swipeAdapter : SwipeCardAdapter by lazy { SwipeCardAdapter(createQuotes(),this)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ColorSelect","${FontFamily.valueOf(sharedPrefsHelper.fontFamily())} ")

        addNecessaryStateToAdapter()
        getSelectedDrawblefromDb()
        initiateSwipeCard()
//        viewModel.getQOD().observe(this, Observer {
//            Log.d("Responce","$it")
//        })
    }

    private fun addNecessaryStateToAdapter (){
        swipeAdapter.setFontFamily(FontFamily.valueOf(sharedPrefsHelper.fontFamily()))
        swipeAdapter.setFontSize(FontSize.valueOf(sharedPrefsHelper.fontSize()))
    }


    private fun initiateSwipeCard (){

        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(2)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        cardStackView.layoutManager = manager

        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false

            }
        }
    }


    private fun getSelectedDrawblefromDb(){
        viewModel.findColorById(sharedPrefsHelper.selectedBackgroundColor()).observe(this, Observer {
            swipeAdapter.setSelectedColor(it)
            cardStackView.adapter = swipeAdapter
        })
    }

    private fun createQuotes() : List<Quote>{
        val quotesList  = ArrayList<Quote>()

        quotesList.add(Quote("Life is really simple, but we insist on making it complicated.","Confucius","motivation"))
        quotesList.add(Quote("When it is obvious that the goals cannot be reached, don't adjust the goals, adjust the action steps.","Confucius","jealousy"))
        quotesList.add(Quote("Life is really simple, but we insist on making it complicated.","Confucius","motivation"))
        quotesList.add(Quote("When it is obvious that the goals cannot be reached, don't adjust the goals, adjust the action steps.","Confucius","jealousy"))
        quotesList.add(Quote("Life is really simple, but we insist on making it complicated.","Confucius","motivation"))
        quotesList.add(Quote("When it is obvious that the goals cannot be reached, don't adjust the goals, adjust the action steps.","Confucius","jealousy"))

        return quotesList
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


}
