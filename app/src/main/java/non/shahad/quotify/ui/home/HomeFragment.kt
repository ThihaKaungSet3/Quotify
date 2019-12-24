package non.shahad.quotify.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.quotify.R
import non.shahad.quotify.ui.base.BaseFragment
import non.shahad.quotify.ui.bottomsheets.choosecolor.ChooseColorBottomFragment
import non.shahad.quotify.ui.bottomsheets.choosefont.ChooseFontBottomFragment
import non.shahad.quotify.callbacks.BottomSheetItemChooseListener
import non.shahad.quotify.data.local.database.AppDatabase
import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.data.remote.EndPoint
import non.shahad.quotify.data.remote.QuotesAPI
import non.shahad.quotify.datamodels.Quote
import non.shahad.quotify.repositories.QuotesRepository
import non.shahad.quotify.ui.bottomsheets.choosefont.FontFamily
import non.shahad.quotify.ui.bottomsheets.choosefont.FontSize
import non.shahad.quotify.utils.Constants
import non.shahad.quotify.utils.SharedPreferencesHelper
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : BaseFragment(),BottomSheetItemChooseListener  {
    companion object{
        // Req code for bottomsheet fragment results
        const val colorReqCode : Int = 66
        const val fontReqCode : Int = 77
    }


    private val viewModel: HomeViewModel by viewModel<HomeViewModel>()
    private val manager : CardStackLayoutManager by lazy {CardStackLayoutManager(context,this)}
    private val sharedPrefsHelper : SharedPreferencesHelper by lazy { get<SharedPreferencesHelper>() }
    private val swipeAdapter : SwipeCardAdapter by lazy { SwipeCardAdapter(this)}



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.result.observe(this, Observer {
            swipeAdapter.setQuotesList(quotesList = it.quotes)
        })

        addNecessaryStateToAdapter()
        getSelectedDrawblefromDb()
        initiateSwipeCard()
    }

    private fun addNecessaryStateToAdapter (){
        swipeAdapter.setFontFamily(FontFamily.valueOf(sharedPrefsHelper.fontFamily()))
        swipeAdapter.setFontSize(FontSize.valueOf(sharedPrefsHelper.fontSize()))

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
