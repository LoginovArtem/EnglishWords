package ru.timon.englishwords

import android.app.Application
import android.content.Context

class App : Application() {
    lateinit var viewModel: PracticeViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("EnglishWords", Context.MODE_PRIVATE)
        viewModel = PracticeViewModel(PracticeRepository.Base(IntCache.Base(sharedPreferences, "indexKey", 0)))
    }
}