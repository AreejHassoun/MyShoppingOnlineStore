package com.example.myshoppingonlinstore.utils

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.myshoppingonlinstore.R
import com.example.myshoppingonlinstore.utils.enums.FontType
import com.example.myshoppingonlinstore.utils.extensions.getParentActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber


//region Binding properties
/**
 * Set [View] properties.
 */
@BindingAdapter(
    "mutableVisibility",
    "mutableEnable",
    "mutableAlpha",
    "mutableBackgroundId",
    "layoutMarginStart",
    requireAll = false
)
fun bindingAdapterView(
    view: View,
    @Nullable visibility: LiveData<Int?>?,
    @Nullable enabled: LiveData<Boolean?>?,
    @Nullable alpha: LiveData<Float?>?,
    @Nullable backgroundId: LiveData<Int?>?,
    @Nullable marginStart: Float?
) {
    view.getParentActivity()?.let { parentActivity ->
        visibility?.let { visibility ->
            visibility.observe(
                parentActivity
            ) { value -> value?.let { view.visibility = it } }
        }

        enabled?.let { enabled ->
            enabled.observe(
                parentActivity
            ) { value ->
                value?.let {
                    view.isEnabled = it
                    view.alpha = when (it) {
                        true -> 1.0f
                        else -> 0.3f
                    }
                }
            }
        }

        alpha?.let { alpha ->
            alpha.observe(
                parentActivity
            ) { value -> value?.let { view.alpha = it } }
        }

        backgroundId?.let { backgroundId ->
            backgroundId.observe(
                parentActivity
            ) { value -> value?.let { view.setBackgroundResource(it) } }
        }
    }
}

/**
 * Set [ImageView] properties.
 */
@BindingAdapter(
    "imageUrl",
    "imageUri",
    "imagePlaceholder",
    "mutableTintColorId",
    "mutableResourceId",
    "imageStringUrl",
    "imageBitmap",
    requireAll = false
)
fun bindingAdapterImageView(
    view: ImageView,
    @Nullable imageUrl: LiveData<String?>?,
    @Nullable imageUri: LiveData<String?>?,
    @Nullable imagePlaceholder: Drawable?,
    @Nullable tintColorId: LiveData<Int?>?,
    @Nullable resourceId: LiveData<Int?>?,
    @Nullable imageStringUrl: String?,
    @Nullable imageBitmap: LiveData<Bitmap?>?,
) {
    view.getParentActivity()?.let { parentActivity ->
        imageUrl?.let { imageUrl ->
            imageUrl.observe(parentActivity) { value ->
                value?.let {
                    when (it.isNotBlank()) {
                        true -> loadImageWithCoil(view, it, imagePlaceholder)

                        else -> {
                            imagePlaceholder?.let {
                                view.setImageDrawable(imagePlaceholder)
                            }
                        }
                    }
                }
            }
        }

        imageBitmap?.let { imageBitmap ->
            imageBitmap.observe(parentActivity) { value ->
                value?.let {
                    view.setImageBitmap(value)
                }
            }
        }

        imageUri?.let { imageUri ->
            imageUri.observe(parentActivity) { value ->
                value?.let {
                    when (it.isNotBlank()) {
                        true -> view.setImageDrawable(Drawable.createFromPath(it))
                    }
                }
            }
        }

        tintColorId?.let { tintColorId ->
            tintColorId.observe(parentActivity) { value ->
                value?.let {
                    ImageViewCompat.setImageTintList(
                        view,
                        ColorStateList.valueOf(ContextCompat.getColor(parentActivity, it))
                    )
                }
            }
        }

        resourceId?.let { resourceId ->
            resourceId.observe(parentActivity) { value ->
                value?.let { view.setImageResource(it) }
            }
        }

        imagePlaceholder?.let { imagePlaceholder ->
            when {
                imageUrl?.value == null && imageUri?.value == null
                        && tintColorId?.value == null && resourceId?.value == null ->
                    view.setImageDrawable(imagePlaceholder)
            }
        }

        imageStringUrl?.let {
            when (it.isNotBlank()) {
                true -> loadImageWithCoil(view, it, imagePlaceholder)

                else -> {
                    imagePlaceholder?.let {
                        view.setImageDrawable(imagePlaceholder)
                    }
                }
            }
        }
    }
}

/**
 * Set [TextView] properties.
 */
@BindingAdapter(
    "mutableText",
    "mutableTextId",
    "mutableSpannableString",
    "mutableSpannableStringBuilder",
    "mutableTextColorId",
    "strikeThrough",
    "font",
    "android:textSize",
    "textColor",
    requireAll = false
)
fun bindingAdapterTextView(
    view: TextView,
    @Nullable text: LiveData<String?>?,
    @Nullable textId: LiveData<Int?>?,
    @Nullable spannableString: LiveData<String?>?,
    @Nullable spannableStringBuilder: LiveData<SpannableStringBuilder?>?,
    @Nullable textColorId: LiveData<Int?>?,
    @Nullable strikeThrough: Boolean?,
    @Nullable fontType: FontType?,
    @Nullable textSize: Int?,
    @Nullable textColor: String?,
) {
    view.getParentActivity()?.let { parentActivity ->
        text?.let { text ->
            text.observe(parentActivity) { value -> value?.let { view.text = it } }
        }

        textId?.let { textId ->
            textId.observe(parentActivity) { value -> value?.let { view.setText(it) } }
        }

        spannableString?.let { spannableString ->
            spannableString.observe(
                parentActivity
            ) { value -> value?.let { view.text = it } }
        }

        spannableStringBuilder?.let { spannableStringBuilder ->
            spannableStringBuilder.observe(
                parentActivity
            ) { value -> value?.let { view.setText(it, TextView.BufferType.SPANNABLE) } }
        }

        textColorId?.let { textColorId ->
            textColorId.observe(
                parentActivity
            ) { value ->
                value?.let {
                    view.setTextColor(ContextCompat.getColor(parentActivity, it))
                }
            }
        }
        if (strikeThrough == true) {
            view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            view.paintFlags = view.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        fontType?.let {
            try {
                val typeface = ResourcesCompat.getFont(parentActivity, fontType.fontRes)
                view.typeface = typeface
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        textSize?.let {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, it.toFloat())
        }

        textColor?.let {
            try {
                view.setTextColor(Color.parseColor(it))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        loadImageWithCoil(view, imageUrl, null)
    }
}

@BindingAdapter(
    "progressMax",
    "progressValue",
    requireAll = false
)
fun bindProgressBar(
    view: ProgressBar,
    @Nullable progressMax: LiveData<Int?>?,
    @Nullable progressValue: LiveData<Int?>?
) {
    view.getParentActivity()?.let { parentActivity ->
        progressMax?.let { max ->
            max.value?.let {
                view.max = it
            }
        }
        progressValue?.let { progress ->
            progress.value?.let {
                view.progress = it
            }
        }
    }
}

/**
 * Set [EditText] properties.
 */
@BindingAdapter("mutableHint", requireAll = false)
fun bindingAdapterEditText(
    view: EditText,
    @Nullable hint: LiveData<String?>?
) {
    view.getParentActivity()?.let { parentActivity ->
        hint?.let { hint ->
            hint.observe(parentActivity) { value -> value?.let { view.hint = it } }
        }
    }
}

/**
 * Set [TextInputLayout] properties.
 */
@BindingAdapter("mutablePrefix", requireAll = false)
fun bindingAdapterTextInputLayout(
    view: TextInputLayout,
    @Nullable hint: LiveData<String?>?
) {
    view.getParentActivity()?.let { parentActivity ->
        hint?.let { hint ->
            hint.observe(parentActivity) { value -> value?.let { view.prefixText = it } }
        }
    }
}

/**
 * Set [ViewPager2] properties.
 */
@BindingAdapter("mutablePosition", requireAll = false)
fun bindingAdapterViewPager2(
    view: ViewPager2,
    @Nullable position: LiveData<Int?>?
) {
    view.getParentActivity()?.let { parentActivity ->
        position?.let { position ->
            position.observe(
                parentActivity,
                { value -> value?.let { view.currentItem = it } })
        }
    }
}

/**
 * Set [CompoundButton] properties.
 */
@BindingAdapter("mutableCheck", requireAll = false)
fun bindingAdapterCompoundButton(view: CompoundButton, @Nullable checked: LiveData<Boolean?>?) {
    view.getParentActivity()?.let { parentActivity ->
        checked?.let { checked ->
            checked.observe(
                parentActivity
            ) { value -> value?.let { view.isChecked = it } }
        }
    }
}

/**
 * Set [MaterialButton] properties.
 */
@BindingAdapter(
    "mutableEnableButton",
    "mutableFilteredButton",
    "mutableIcon",
    requireAll = false
)
fun bindingAdapterMaterialButton(
    view: MaterialButton,
    @Nullable enabled: LiveData<Boolean?>?,
    @Nullable filtered: LiveData<Boolean?>?,
    @Nullable icon: Int?
) {
    view.getParentActivity()?.let { parentActivity ->
        enabled?.let { enabled ->
            enabled.observe(
                parentActivity
            ) { value ->
                value?.let {
                    view.isEnabled = it
                    view.alpha = when (it) {
                        true -> 1.0f
                        else -> 0.3f
                    }
                }
            }
        }
        filtered?.let { filtered ->
            filtered.observe(
                parentActivity
            ) { value ->
                value?.let {
                    view.isEnabled = it
                    when (it) {
                        true -> {
                            view.setBackgroundColor(
                                ContextCompat.getColor(
                                    parentActivity,
                                    R.color.white
                                )
                            )
                            view.setTextColor(
                                ContextCompat.getColor(
                                    parentActivity,
                                    R.color.gray_dark
                                )
                            )
                            view.textSize = 14f
                        }
                        else -> {
                            view.setBackgroundColor(
                                ContextCompat.getColor(
                                    parentActivity,
                                    R.color.cimon
                                )
                            )
                            view.setTextColor(
                                ContextCompat.getColor(
                                    parentActivity,
                                    R.color.black
                                )
                            )
                            view.textSize = 16f
                        }
                    }
                }
            }
        }
        icon?.let {
            view.setIconResource(icon)
        }
    }
}

/**
 * Set [MotionLayout] properties.
 */
@BindingAdapter("mutableCurrentState", requireAll = false)
fun bindingAdapterMotionLayout(view: MotionLayout, @Nullable currentState: LiveData<Int?>?) {
    view.getParentActivity()?.let { parentActivity ->
        currentState?.let { currentState ->
            currentState.observe(
                parentActivity
            ) { value -> value?.let { view.transitionToState(value) } }
        }
    }
}

@BindingAdapter(
    "mutableText",
    requireAll = false
)
fun bindingAdapterTextView(
    view: CheckBox,
    @Nullable text: LiveData<String?>?,
) {
    view.getParentActivity()?.let { parentActivity ->
        text?.let { text ->
            text.observe(parentActivity) { value -> value?.let { view.text = it } }
        }
    }

}

//endregion

//region Private methods
/**
 * Load image with coil.
 */
private fun loadImageWithCoil(
    view: ImageView,
    imageUrl: String,
    imagePlaceholder: Drawable?
) {
    //TODO uncomment when commit changes
    view.getParentActivity()?.let { parentActivity ->
        view.load(imageUrl) {
            apply {
                val circularProgressDrawable = CircularProgressDrawable(parentActivity)
                circularProgressDrawable.strokeWidth = 10f
                circularProgressDrawable.setColorSchemeColors(
                    ContextCompat.getColor(
                        parentActivity,
                        R.color.orange
                    )
                )
                circularProgressDrawable.centerRadius = 40f
                circularProgressDrawable.start()
                when (imagePlaceholder) {
                    null -> {
                        placeholder(circularProgressDrawable)
                        // error(R.drawable.ic_logo)
                    }
                    else -> {
                        placeholder(imagePlaceholder)
                        error(imagePlaceholder)
                    }
                }
            }
            listener(onError = { _, throwable ->
                Timber.w(throwable, "Failed to load image: $imageUrl")
            })
        }
    }
}

@BindingAdapter("app:layout_constraintWidth_percent")
fun setLayoutConstraintWidthPercent(view: View, width: Float) {
    (view.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
        this.matchConstraintPercentWidth = width
    }
}
//endregion