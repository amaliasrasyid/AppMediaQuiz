package com.kontrakanprojects.appgamequiz.util

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.model.PlantMatery

object PlantMateryData {

    fun getPlantMatery(res: Resources,context:Context): ArrayList<PlantMatery>{
        return ArrayList(
            listOf(
                PlantMatery(
                    "AKAR",
                    arrayOf(res.getString(R.string.desc_akar)),
                    arrayOf(ContextCompat.getDrawable(context,R.drawable.img_materi_akar))
                ),PlantMatery(
                    "BATANG",
                    arrayOf(res.getString(R.string.desc_batang)),
                    null
                ),
                PlantMatery(
                    "BUNGA",
                    arrayOf(res.getString(R.string.desc_bunga)),
                    arrayOf(ContextCompat.getDrawable(context,R.drawable.img_struktur_bunga))
                ),
                PlantMatery(
                    "DAUN",
                    arrayOf(res.getString(R.string.desc_daun)),
                    arrayOf(ContextCompat.getDrawable(context,R.drawable.img_jenis_daun)))
                ),

//                res.getString(R.string.desc_akar),
//                res.getString(R.string.desc_batang),
//                res.getString(R.string.desc_bunga),
//                res.getString(R.string.desc_daun),
//                res.getString(R.string.desc_biji),
//                res.getString(R.string.desc_buah),

        )
    }
}