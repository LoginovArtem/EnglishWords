package ru.timon.englishwords.views.translation

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.os.Build


class TranslationSavedState : View.BaseSavedState {

    private lateinit var state: TranslationUiState

    constructor(superState: Parcelable) : super(superState)

      private constructor(parcelIn: Parcel) : super(parcelIn) {
       state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             parcelIn.readSerializable(TranslationUiState::class.java.classLoader, TranslationUiState::class.java) as TranslationUiState
        } else {
           parcelIn.readSerializable() as TranslationUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): TranslationUiState = state

    fun save(uiState: TranslationUiState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<TranslationSavedState> {
        override fun createFromParcel(parcel: Parcel): TranslationSavedState =
            TranslationSavedState(parcel)

        override fun newArray(size: Int): Array<TranslationSavedState?> =
            arrayOfNulls(size)
    }
}