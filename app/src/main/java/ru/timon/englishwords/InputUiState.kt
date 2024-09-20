package ru.timon.englishwords

import android.text.Editable
import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable

interface InputUiState  : Serializable {
    fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText)
    abstract class Abstract(
        private val visibility: Int,
        private val enabled: Boolean,
        private val error: Boolean,
        private val clearText: Boolean
    ) : InputUiState {
        override fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText) {
            inputLayout.visibility = visibility
            inputLayout.isEnabled = enabled
            inputLayout.isErrorEnabled = error
            if (clearText)
                inputEditText.text?.clear()
        }
    }
    object Initial : Abstract(View.VISIBLE, true, false, true)
    object Enabled : Abstract(View.VISIBLE, true, false, false)
    object Disabled : Abstract(View.VISIBLE, false, false, false)
    object DisabledError : Abstract(View.VISIBLE, false, true, false)
    object Invisible : Abstract(View.INVISIBLE, false, false, false)
}