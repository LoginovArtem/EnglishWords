package ru.timon.englishwords.views.input

import android.content.Context
import android.os.Parcelable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ru.timon.englishwords.databinding.InputBinding

class InputView : FrameLayout, UpdateInput {
    private lateinit var state: InputUiState
    private val binding = InputBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = InputSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as InputSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }

    override fun update(uiState: InputUiState) {
        state = uiState
        state.update(this)
    }

    override fun update(
        visibility: Int,
        error: Boolean,
        enabled: Boolean,
        clearText: Boolean
    ) = with(binding) {
        inputLayout.visibility = visibility
        inputLayout.isEnabled = enabled
        inputLayout.isErrorEnabled = error
        if (clearText)
            inputEditText.text?.clear()
    }

    fun addTextChangedListener(textWatcher: TextWatcher) {
        binding.inputEditText.addTextChangedListener(textWatcher)
    }

    fun removeTextChangedListener(textWatcher: TextWatcher) {
        binding.inputEditText.removeTextChangedListener(textWatcher)
    }

    fun text(): String {
        return binding.inputEditText.text.toString()
    }
}

interface UpdateInput {
    fun update(uiState: InputUiState)
    fun update(visibility: Int, error: Boolean, enabled: Boolean, clearText: Boolean)
}