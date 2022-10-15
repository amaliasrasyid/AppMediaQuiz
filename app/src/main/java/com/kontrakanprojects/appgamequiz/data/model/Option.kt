package com.kontrakanprojects.appgamequiz.data.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.ImageHeaderParser.ImageType

class Option(
    val image: Bitmap? = null,
    val numberSequence: Int,
    val text: String? = null,
)
//sealed class ImageType<T>(val image: T){
//    class ImageBitmap(val image: Bitmap): ImageType<Bitmap>(image)
//    class ImageDrawable(val image: Drawable): ImageType<Drawable>(image)
//}