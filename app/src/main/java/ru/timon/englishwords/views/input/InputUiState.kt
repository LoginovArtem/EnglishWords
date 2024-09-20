package ru.timon.englishwords.views.input

import android.view.View
import java.io.Serializable

interface InputUiState  : Serializable {
    fun update(updateInput: UpdateInput)

    abstract class Abstract(
        private val visibility: Int,
        private val enabled: Boolean,
        private val error: Boolean,
        private val clearText: Boolean
    ) : InputUiState {
        override fun update(updateInput: UpdateInput) {
            updateInput.update(visibility, error, enabled, clearText)
        }
    }
    object Initial : Abstract(View.VISIBLE, true, false, true)
    object Enabled : Abstract(View.VISIBLE, true, false, false)
    object Disabled : Abstract(View.VISIBLE, false, false, false)
    object DisabledError : Abstract(View.VISIBLE, false, true, false)
    object Invisible : Abstract(View.INVISIBLE, false, false, false)
}