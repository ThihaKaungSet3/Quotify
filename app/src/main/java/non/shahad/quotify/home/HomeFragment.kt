package non.shahad.quotify.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_home.*
import non.shahad.quotify.R
import non.shahad.quotify.base.BaseFragment
import non.shahad.quotify.bottomsheets.ChooseColorBottomFragment
import non.shahad.quotify.bottomsheets.ChooseFontBottomFragment
import non.shahad.quotify.callbacks.ItemFromCardOnClickListener
import non.shahad.quotify.models.Quote


class HomeFragment : BaseFragment(), ItemFromCardOnClickListener  {

    private val swipeAdapter : SwipeCardAdapter by lazy { SwipeCardAdapter(createQuotes(),this)}
    private val manager : CardStackLayoutManager by lazy {CardStackLayoutManager(context,this)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateSwipeCard()
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
        cardStackView.adapter = swipeAdapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false

            }
        }
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
        showBottomSheet(colorSheet,"colorBottomSheet")
    }

    override fun onFontViewClick() {
        val fontSheet : ChooseFontBottomFragment? = ChooseFontBottomFragment.newInstance()
        showBottomSheet(fontSheet,"fontBottomSheet")
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



}
