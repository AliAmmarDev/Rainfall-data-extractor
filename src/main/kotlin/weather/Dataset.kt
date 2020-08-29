package weather

/**
 * This class represents an entire weather record dataset for a 
 * station [station], [lat], [long], [elevation]
 */

class Dataset(val station: String, val lat: Double, val long: Double, val elevation: Int) {

    // list of indivdual records
    val records : MutableList<Record> = arrayListOf()

    /**
    * Adds an individual record [record].
    */
    fun addRecord(record: Record) {
        this.records.add(record)
    }

    /**
    * Returns the Record containing the most rainfall in a month.
    */
    fun getWettestMonth(): Record? {
        val wettestMonth = this.records.maxBy { record -> record.rain } 
        return wettestMonth
    }
    
    /**
    * Returns the Record containing the least rainfall in a month.
    */
    fun getDriestMonth(): Record? {
        val driestMonth = this.records.minBy { record -> record.rain } 
        return driestMonth
    }

    /**
    * Returns the year of the earliest record in the dataset.
    */
    fun getEarliestYear(): Int? {
        val record = this.records.minBy { record -> record.year } 
        return record?.year
    }

    /**
    * Returns the year of the most recent record in the dataset.
    */
    fun getLatestYear(): Int? {
        val record = this.records.maxBy { record -> record.year } 
        return record?.year
    }

    /**
    * Returns the year and amount of rainfall for the year that 
    * has the most rain.
    */
    fun getWettestYear(): Pair<Int?, Double?> {
        val rain = getRainAllYears()
        var maxRain = rain.maxBy { it.value }
        return Pair(maxRain?.key, maxRain?.value)
    }

    /**
    * Returns the year and amount of rainfall for the year that 
    * has the least rain.
    */
    fun getDriestYear(): Pair<Int?, Double?> {
        val rain = getRainAllYears()
        var minRain = rain.minBy { it.value }
        return Pair(minRain?.key, minRain?.value)
    }

    /**
    * Returns the amount of rain for each year in the dataset
    */
    fun getRainAllYears(): MutableMap<Int, Double> {
        val rainPerYear = mutableMapOf<Int, Double>()
        this.records.forEach {
            val record = rainPerYear.get(it.year)
            if (record == null) {
                rainPerYear.put(it.year, it.rain)
            } else {
                var rain = rainPerYear.getValue(it.year)
                rain += it.rain 
                rainPerYear.put(it.year, rain)
            }
        }
        return rainPerYear
    }

    /**
    * Returns the record containing the amount of rainfall for a
    * year [year].
    */
    fun getRainForYear(year: Int): List<Record> {
        val rain = this.records.filter { it.year == year }
        if (rain.size == 0) {
            throw Exception("Year ${year} not found in dataset.")
        }
        return rain
    }
}