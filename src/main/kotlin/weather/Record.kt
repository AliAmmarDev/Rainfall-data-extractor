package weather

/**
 * This class represents an individual weather record containing the
 * [year], [month] and [rain]
 */
data class Record(val year: Int, val month: Int, val rain: Double) {
    init {
        require(month in 1..12) {"invalid month"}
        require(year > 1929) {"invalid year"}
        require(rain > 0.0) {"invalid rain"}
    }
}