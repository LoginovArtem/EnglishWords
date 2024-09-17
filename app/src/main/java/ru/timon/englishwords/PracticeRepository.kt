package ru.timon.englishwords

interface PracticeRepository {

    fun word(): String
    fun translation(): String
    fun next()
}
