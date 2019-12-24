package non.shahad.quotify.callbacks

import non.shahad.quotify.data.local.entities.ColorEntity
import non.shahad.quotify.ui.bottomsheets.choosefont.FontFamily
import non.shahad.quotify.ui.bottomsheets.choosefont.FontSize

interface BottomSheetItemChooseListener {
    fun onColorChoose(colorEntity: ColorEntity,position : Int)

    fun onFontChoose(family: FontFamily)

    fun onFontSizeChange(fontSize: FontSize)
}