package ru.timon.englishwords

import ru.timon.englishwords.views.UpdateText
import ru.timon.englishwords.views.translation.UpdateTranslation
import ru.timon.englishwords.views.check.CheckUiState
import ru.timon.englishwords.views.check.UpdateCheckButton
import ru.timon.englishwords.views.input.InputUiState
import ru.timon.englishwords.views.input.UpdateInput
import ru.timon.englishwords.views.translation.TranslationUiState
import ru.timon.englishwords.views.visibilityButton.UpdateVisibility
import ru.timon.englishwords.views.visibilityButton.VisibilityUiState

interface PracticeUiState {

    fun update(
        wordView: UpdateText,
        translationView: UpdateTranslation,
        inputView: UpdateInput,
        check: UpdateCheckButton,
        tryVisibility: UpdateVisibility,
        nextVisibility: UpdateVisibility,
        showVisibility: UpdateVisibility
    )

    object Empty : PracticeUiState {
        override fun update(
            wordView: UpdateText,
            translationView: UpdateTranslation,
            inputView: UpdateInput,
            check: UpdateCheckButton,
            tryVisibility: UpdateVisibility,
            nextVisibility: UpdateVisibility,
            showVisibility: UpdateVisibility
        ) = Unit
    }

    abstract class Abstract(
        private val inputUiState: InputUiState,
        private val checkUiState: CheckUiState,
        private val tryVisibilityUiState: VisibilityUiState,
        private val nextVisibilityUiState: VisibilityUiState,
        private val showVisibilityUiState: VisibilityUiState
    ) : PracticeUiState {
        override fun update(
            wordView: UpdateText,
            translationView: UpdateTranslation,
            inputView: UpdateInput,
            check: UpdateCheckButton,
            tryVisibility: UpdateVisibility,
            nextVisibility: UpdateVisibility,
            showVisibility: UpdateVisibility
        ) {
            inputView.update(inputUiState)
            check.update(checkUiState)
            tryVisibility.update(tryVisibilityUiState)
            nextVisibility.update(nextVisibilityUiState)
            showVisibility.update(showVisibilityUiState)
        }
    }

    data class Initial(
        private val word: String,
        private val translation: String
    ) : Abstract(
        InputUiState.Initial,
        CheckUiState.Disabled,
        VisibilityUiState.Invisible,
        VisibilityUiState.Invisible,
        VisibilityUiState.Invisible
    ) {
        override fun update(
            wordView: UpdateText,
            translationView: UpdateTranslation,
            inputView: UpdateInput,
            check: UpdateCheckButton,
            tryVisibility: UpdateVisibility,
            nextVisibility: UpdateVisibility,
            showVisibility: UpdateVisibility
        ) {
            super.update(wordView, translationView, inputView, check, tryVisibility, nextVisibility, showVisibility)
            wordView.update(word)
            translationView.update(translation)
            translationView.update(TranslationUiState.Invisible)
        }
    }

    object NoText : Abstract(
        InputUiState.Initial,
        CheckUiState.Disabled,
        VisibilityUiState.Invisible,
        VisibilityUiState.Invisible,
        VisibilityUiState.Invisible
    )

    object Checkable : Abstract(
        InputUiState.Enabled,
        CheckUiState.Checkable,
        VisibilityUiState.Invisible,
        VisibilityUiState.Invisible,
        VisibilityUiState.Invisible
    )

    object Correct : Abstract(
        InputUiState.Disabled,
        CheckUiState.Invisible,
        VisibilityUiState.Invisible,
        VisibilityUiState.Visible,
        VisibilityUiState.Invisible
    )

    object Incorrect : Abstract(
        InputUiState.DisabledError,
        CheckUiState.Invisible,
        VisibilityUiState.Visible,
        VisibilityUiState.Invisible,
        VisibilityUiState.Visible
    )

    object Fail : Abstract(
        InputUiState.Invisible,
        CheckUiState.Invisible,
        VisibilityUiState.Invisible,
        VisibilityUiState.Visible,
        VisibilityUiState.Invisible
    ) {
        override fun update(
            wordView: UpdateText,
            translationView: UpdateTranslation,
            inputView: UpdateInput,
            check: UpdateCheckButton,
            tryVisibility: UpdateVisibility,
            nextVisibility: UpdateVisibility,
            showVisibility: UpdateVisibility
        ) {
            super.update(wordView, translationView, inputView, check, tryVisibility, nextVisibility, showVisibility)
            translationView.update(TranslationUiState.Visible)
        }
    }
}

