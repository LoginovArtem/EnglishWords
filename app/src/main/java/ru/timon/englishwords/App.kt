package ru.timon.englishwords

import android.app.Application

class App : Application() {
    lateinit var viewModel: PracticeViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = PracticeViewModel(PracticeRepository.Base())
    }
}