package com.uznewmax.viewpagerwithtablayout.utils

import android.content.res.Resources
import android.util.Log
import com.uznewmax.viewpagerwithtablayout.BuildConfig

/**
 * Created by Alisher Kazakbaev on 10.06.2022.
 */
fun showLog(message: String, tag: String = "tekshirmoq") {
    if (BuildConfig.DEBUG) Log.d(tag, message)
}

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

inline val Int.dp: Float get() = this * Resources.getSystem().displayMetrics.density
