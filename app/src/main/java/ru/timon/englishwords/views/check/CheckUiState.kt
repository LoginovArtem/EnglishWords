package ru.timon.englishwords.views.check

import android.view.View
import java.io.Serializable

interface CheckUiState : Serializable {

    fun update(updateCheckButton: UpdateCheckButton)

    abstract class Abstract(
        private val visibility: Int,
        private val enabled: Boolean
        ) : CheckUiState {
        override fun update(updateCheckButton: UpdateCheckButton) {
            updateCheckButton.update(visibility, enabled)
        }
    }
    object Disabled : Abstract(View.VISIBLE, false)
    object Checkable : Abstract(View.VISIBLE, true)
    object Invisible : Abstract(View.INVISIBLE, false)
}