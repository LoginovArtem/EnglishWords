package ru.timon.englishwords.views.translation

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TranslationView : AppCompatTextView, UpdateTranslation {
    private lateinit var state: TranslationUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getFreezesText() = true

     override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = TranslationSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as TranslationSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(uiState: TranslationUiState) {
        state = uiState
        state.update(this)
    }

    override fun update(text: String) {
        this.text = text
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }
}

interface UpdateTranslation {
    fun update(uiState: TranslationUiState)
    fun update(text: String)
    fun update(visibility: Int)
}