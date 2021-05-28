package com.colemichaels.notes.dummy

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month
import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ThreadLocalRandom

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): DummyItem {
        return DummyItem(
            position.toString(),
            "Title " + position,
            makeDetails(position),
            dateBetween(LocalDate.of(1970, Month.JANUARY, 1), LocalDate.now().minusDays(10)),
            dateBetween(LocalDate.now().minusDays(9), LocalDate.now().minusDays(1))
        )
    }

    private fun dateBetween(start: LocalDate, end: LocalDate): LocalDateTime {
        val startEpochDay = start.toEpochDay()
        val endEpochDay = end.toEpochDay()
        val randomDay = ThreadLocalRandom
            .current()
            .nextLong(startEpochDay, endEpochDay)

        return LocalDateTime.of(LocalDate.ofEpochDay(randomDay), LocalTime.MIDNIGHT)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val title: String, val content: String, val createdAt: LocalDateTime, val updatedAt: LocalDateTime) {
        override fun toString(): String = "$content $createdAt $updatedAt"
        operator fun get(position: Int): DummyItem {
            return ITEMS[position]
        }
    }
}