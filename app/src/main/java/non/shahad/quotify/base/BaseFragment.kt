package non.shahad.quotify.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yuyakaido.android.cardstackview.Direction
import non.shahad.quotify.callbacks.ItemFromCardOnClickListener

abstract class BaseFragment : Fragment(), ItemFromCardOnClickListener {


    override fun onColorViewClick() {

    }

    override fun onFontViewClick() {

    }

    override fun onShareClick() {

    }

    override fun onAuthorClick() {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {

    }

    open fun showBottomSheet(bottomFragment : BottomSheetDialogFragment?,tag : String?){
        val fm = fragmentManager
        bottomFragment?.show(fm!!,tag)
    }
}