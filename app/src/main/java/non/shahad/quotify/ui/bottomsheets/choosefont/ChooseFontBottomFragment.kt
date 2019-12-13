package non.shahad.quotify.ui.bottomsheets.choosefont

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_choosefont.*
import non.shahad.quotify.R
import non.shahad.quotify.callbacks.BottomSheetItemChooseListener

class ChooseFontBottomFragment : BottomSheetDialogFragment(),View.OnClickListener{

    private val listener by lazy { targetFragment as BottomSheetItemChooseListener }

    companion object{
        fun newInstance () : ChooseFontBottomFragment? =
            ChooseFontBottomFragment()
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(context!!,theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choosefont,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alatsiTxt.setOnClickListener(this)
        calistogaTxt.setOnClickListener(this)
        solwayTxt.setOnClickListener(this)
        regularTxt.setOnClickListener(this)
        mediumTxt.setOnClickListener(this)
        largeTxt.setOnClickListener(this)
    }

    override fun onClick(view : View?) {
        when(view?.id){
            R.id.alatsiTxt -> listener.onFontChoose(FontFamily.ATALSI)
            R.id.calistogaTxt -> listener.onFontChoose(FontFamily.CALISTOGA)
            R.id.solwayTxt -> listener.onFontChoose(FontFamily.SOLWAY)

            R.id.regularTxt -> listener.onFontSizeChange(FontSize.REGULAR)
            R.id.mediumTxt -> listener.onFontSizeChange(FontSize.MEDIUM)
            R.id.largeTxt -> listener.onFontSizeChange(FontSize.LARGE)
        }
    }


}