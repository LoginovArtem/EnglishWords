package ru.timon.englishwords

interface PracticeRepository {

    fun word(): String
    fun translation(): String
    fun next()

    class Base(
        private var index: IntCache,
        private val list: List<Pair<String, String>> = listOf(
            "sky" to "небо",
            "tree" to "дерево",
            "run" to "бежать"
        )
    ) : PracticeRepository {

        override fun word(): String = list[index.read()].first

        override fun translation(): String = list[index.read()].second

        override fun next() {
            index.save(index.read() + 1)
            if (index.read() == list.size) {
                index.save(0)
            }
        }

    }
}
