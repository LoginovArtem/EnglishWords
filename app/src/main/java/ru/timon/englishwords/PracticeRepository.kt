package ru.timon.englishwords

interface PracticeRepository {

    class Base (private val list: List<Pair<String, String>> = listOf("sky" to "небо", "tree" to "дерево", "run" to "бежать")) : PracticeRepository {
        private var index = 0

        override fun word(): String = list[index].first

        override fun translation(): String = list[index].second

        override fun next() {
            if (index == list.size ) {
                index = 0
            }
            index++
        }

    }

    fun word(): String
    fun translation(): String
    fun next()
}
