package non.shahad.quotify.ui.bottomsheets.choosecolor

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_choosecolor.*
import non.shahad.quotify.R
import non.shahad.quotify.callbacks.BottomSheetItemChooseListener
import non.shahad.quotify.dagger.Injectable
import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.ui.bottomsheets.choosefont.FontFamily
import non.shahad.quotify.ui.bottomsheets.choosefont.FontSize
import non.shahad.quotify.utils.Constants
import non.shahad.quotify.custom.DaggerBottomSheetDialogFragment
import non.shahad.quotify.utils.SharedPreferencesHelper
import javax.inject.Inject

class ChooseColorBottomFragment : DaggerBottomSheetDialogFragment(),BottomSheetItemChooseListener,Injectable {

    companion object {
        /**
         * If you want to pass arguments as EXTRAS
         * Do it here!!
         */
        fun newInstance() : ChooseColorBottomFragment? =
            ChooseColorBottomFragment()
    }

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private lateinit var viewmodel : ChooseColorViewModel
    private val sharedPrefHelper : SharedPreferencesHelper by lazy { SharedPreferencesHelper.getInstance(context!!) }
    private val layoutManager : LinearLayoutManager by lazy { LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false) }

    private val chooseAdapter : ChooseColorAdapter by lazy {
        ChooseColorAdapter(
            this
        )
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(context!!,theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProviders.of(this,viewmodelFactory).get(ChooseColorViewModel::class.java)
        return inflater.inflate(R.layout.fragment_choosecolor,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseColorRecyclerView.layoutManager = layoutManager


        viewmodel.colorList().observe(this, Observer <List<ColorEntity>>{
            chooseAdapter.setcolorList(it)
            chooseAdapter.setChooseColor(sharedPrefHelper.selectedBackgroundColor())
            chooseColorRecyclerView.adapter = chooseAdapter

        })
    }


    override fun onColorChoose(colorEntity: ColorEntity, position: Int) {
        var onSaveInstanceState = layoutManager.onSaveInstanceState()
        var listener = targetFragment as BottomSheetItemChooseListener
        listener.onColorChoose(colorEntity,position)
        sharedPrefHelper.putLong(Constants.SELECTED_BG_COLOR,colorEntity.id)
        chooseAdapter.setChooseColor(sharedPrefHelper.selectedBackgroundColor())
        chooseColorRecyclerView.adapter = chooseAdapter
        layoutManager.onRestoreInstanceState(onSaveInstanceState)
//        layoutManager.onRestoreInstanceState(onSaveInstanceState)


    }

    override fun onFontChoose(family: FontFamily) {
        // no use
    }

    override fun onFontSizeChange(fontSize: FontSize) {
        // no use
    }


}