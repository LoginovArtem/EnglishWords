package ru.timon.englishwords.views.translation

import android.view.View
import java.io.Serializable

interface TranslationUiState : Serializable {

    fun update(updateTranslation: UpdateTranslation)

    abstract class Abstract(
        private val visibility: Int
    ): TranslationUiState {
        override fun update(updateTranslation: UpdateTranslation) {
            updateTranslation.update(visibility)
        }
    }

    object Visible: Abstract(View.VISIBLE)
    object Invisible: Abstract(View.INVISIBLE)
}