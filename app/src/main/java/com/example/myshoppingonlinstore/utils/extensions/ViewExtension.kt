package com.example.myshoppingonlinstore.utils.extensions

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myshoppingonlinstore.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber
import kotlin.math.min

/**
 * ViewExtension
 */

/**
 * Get parent activity from given view.
 */
fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context

    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }

        context = context.baseContext
    }

    return null
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInVisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

/*
 * Sets View visibility due to condition
 */
fun View.showIf(statement: Boolean?) {
    this.visibility = if (statement != null && statement) View.VISIBLE else View.GONE
}

fun View.invisibleIf(statement: Boolean?) {
    this.visibility = if (statement != null && statement) View.VISIBLE else View.INVISIBLE
}
//TODO
/*
fun View.showBadge(
    context: Context?,
    @IdRes itemId: Int,
    value: String?
) {
    val bottomNavigationView = this
    removeBadge(bottomNavigationView as BottomNavigationView, itemId)
    val itemView: BottomNavigationItemView = bottomNavigationView.findViewById(itemId)
    val badge: View = LayoutInflater.from(context)
        .inflate(R.layout.layout_notification_badge, bottomNavigationView, false)
    val text = badge.findViewById<TextView>(R.id.tv_notification_badge)
    val layoutBudget = badge.findViewById<RelativeLayout>(R.id.la_badget)
    text.text = value
    itemView.addView(badge)
}

fun removeBadge(bottomNavigationView: BottomNavigationView, @IdRes itemId: Int) {
    val itemView: BottomNavigationItemView = bottomNavigationView.findViewById(itemId)
    if (itemView.childCount == 3) {
        itemView.removeViewAt(2)
    }
}
*/

fun drawCircle(
    color:Int = Color.LTGRAY,
    bitmapWidth:Int = 100,
    bitmapHeight:Int = 50,
    radius:Float = min(bitmapWidth, bitmapHeight) / 2f,
    cx:Float = bitmapWidth / 2f,
    cy:Float = bitmapHeight / 2f,
    canvasBackground:Int = Color.WHITE
): Bitmap {
    val bitmap = Bitmap.createBitmap(
        bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888
    )

    // canvas to draw circle
    val canvas = Canvas(bitmap).apply {
        drawColor(canvasBackground)
    }

    // paint to draw on canvas
    val paint = Paint().apply {
        this.color = color
        isAntiAlias = true
    }

    // draw circle on canvas
    canvas.drawCircle(
        cx, // x-coordinate of the center of the circle
        cy, // y-coordinate of the center of the circle
        radius, // radius of the circle
        paint // paint used to draw the circle
    )

    return bitmap
}
