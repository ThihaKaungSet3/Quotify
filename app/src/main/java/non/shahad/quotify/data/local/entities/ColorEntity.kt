package non.shahad.quotify.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "background_color")
data class ColorEntity (
    @ColumnInfo(name = "start_color") var startColor : String,
    @ColumnInfo(name = "end_color") var endColor : String,
    @ColumnInfo(name = "name") var name : String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Long = 0,
    var isSelectedBackground : Boolean = false){


    companion object{
        fun populatedColors() : List<ColorEntity>{
            val colorList  = ArrayList<ColorEntity>()
            colorList.add(ColorEntity("#20BF55","#01BAEF","Global Warming"))
            colorList.add(ColorEntity("#009FFD","#2A2A72","Adele's first love"))
            colorList.add(ColorEntity("#09C6F9","#045DE9","Rich Neighbor"))
            colorList.add(ColorEntity("#EC9F05","#FF4E00","Mars Conquest"))
            colorList.add(ColorEntity("#F67062","#FC5296","Battery Life"))
            colorList.add(ColorEntity("#647DEE","#7F53AC","Anonymous Porn"))
            colorList.add(ColorEntity("#3fc5f0","#42dee1","Custom"))
            colorList.add(ColorEntity("#087EE1","#05E8BA","Herd of Birds"))

            return colorList
        }
    }
}
