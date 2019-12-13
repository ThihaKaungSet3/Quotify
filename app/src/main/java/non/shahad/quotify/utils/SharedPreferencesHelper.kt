package non.shahad.quotify.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper (context : Context){

    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(Constants.APP_DATA,Context.MODE_PRIVATE)

    companion object {
        private var INSTANCE : SharedPreferencesHelper? = null

        fun getInstance(context: Context)  : SharedPreferencesHelper{
            if (INSTANCE == null){
                INSTANCE = SharedPreferencesHelper(context)
            }
            return INSTANCE!!
        }
    }

    fun selectedBackgroundColor() : Long {
        return sharedPreferences.getLong(Constants.SELECTED_BG_COLOR,1L)
    }

    fun fontSize() : String {
        return sharedPreferences.getString(Constants.QUOTE_FONT_SIZE,"MEDIUM")!!
    }

    fun fontFamily() : String {
        return sharedPreferences.getString(Constants.QUOTE_FONT_FAMILY,"ATALSI")!!
    }

    fun putString(key : String, value : String){
        sharedPreferences.edit().putString(key,value).apply()
    }

    fun putInt(key : String,value : Int){
        sharedPreferences.edit().putInt(key,value).apply()
    }

    fun putBoolean(key : String,value : Boolean){
        sharedPreferences.edit().putBoolean(key,value).apply()
    }

    fun putLong(key : String,value: Long){
        sharedPreferences.edit().putLong(key,value).apply()
    }
}