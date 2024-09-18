package ru.timon.englishwords

import android.view.View
import androidx.appcompat.widget.AppCompatButton

interface CheckUiState {

    fun update(checkButton: AppCompatButton)
    abstract class Abstract(
        private val visibility: Int,
        private val enabled: Boolean
        ) : CheckUiState {
        override fun update(checkButton: AppCompatButton) {
            checkButton.visibility = visibility
            checkButton.isEnabled = enabled
        }
    }
    object Initial : Abstract(View.VISIBLE, false)
    object Checkable : Abstract(View.VISIBLE, true)
    object Invisible : Abstract(View.INVISIBLE, false)
}