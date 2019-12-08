package non.shahad.quotify.bottomsheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_choosecolor.*
import non.shahad.quotify.R
import non.shahad.quotify.models.Color

class ChooseColorBottomFragment : BottomSheetDialogFragment() {

    companion object {
        /**
         * If you want to pass arguments as EXTRAS
         * Do it here!!
         */
        fun newInstance() : ChooseColorBottomFragment? = ChooseColorBottomFragment()
    }

    private val chooseAdapter : ChooseColorAdapter by lazy { ChooseColorAdapter(createColorList()) }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(context!!,theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choosecolor,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseColorRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        chooseColorRecyclerView.adapter = chooseAdapter
    }

    private fun createColorList(): List<Color>{
        val colorList = ArrayList<Color>()

        colorList.add(Color("#20BF55","#01BAEF","Global Warming"))
        colorList.add(Color("#009FFD","#2A2A72","Adele's first love"))
        colorList.add(Color("#09C6F9","#045DE9","Rich Neighbor"))
        colorList.add(Color("#EC9F05","#FF4E00","Mars Conquest"))

        colorList.add(Color("#F67062","#FC5296","Battery Life"))
        colorList.add(Color("#647DEE","#7F53AC","Anonymous Porn"))
        colorList.add(Color("#3fc5f0","#42dee1","Custom"))
        colorList.add(Color("#087EE1","#05E8BA","Herd of Birds"))



        return colorList
    }








}