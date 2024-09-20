package ru.timon.englishwords.views.visibilityButton

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class VisibilityButton : AppCompatButton, UpdateVisibility {
    private lateinit var state: VisibilityUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

     override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = VisibilitySavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as VisibilitySavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(uiState: VisibilityUiState) {
        state = uiState
        state.update(this)
    }

    override fun update(visibility: Int) {
        this.visibility = visibility
    }
}

interface UpdateVisibility {
    fun update(uiState: VisibilityUiState)
    fun update(visibility: Int)
}