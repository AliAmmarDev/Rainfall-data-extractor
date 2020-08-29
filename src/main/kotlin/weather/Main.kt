package weather

import kotlin.math.*
import java.io.File

/**
* Performs analysis for weather records.
*/
fun main(args: Array<String>) {
    if (args.size == 0) {
        println("Minimum of 1 argument")
        return
    }
    if (args.size > 2) {
        println("too many arguments")
        return
    }
    // read file
    val fileContent = readFile(args[0])

    // create Dataset objecs
    val weatherData = createDataset(fileContent[0], fileContent[1])

    // populate Dataset object with records
    for (counter in 4 .. (fileContent.size - 1)) {
        var record = createRecord(fileContent[counter])
        weatherData.addRecord(record)
    }
    if (weatherData.records.size == 0) {
        println("No records exist for ${weatherData.station}")
        return
    }

    // perform analysis
    if (args.size == 1) {
        outputResults(weatherData)
    } else  {
        printChart(weatherData, args[1].toInt())
    }
    
}

/**
* Returns the content of file [file].
*/
fun readFile(file: String): List<String> {
    val data = mutableListOf<String>()
    try {
        File(file).forEachLine { 
            data.add(it)
        }
    } catch (e: Exception) {
        println("Error reading file ")
    }
    if (data.size < 4) {
        throw Exception("Error reading file")
    }
    return data
}

/**
* Returns a Dataset object for a station [name], [data]
*/
fun createDataset(name: String, data: String): Dataset {
    val metaData = data.replace(",", "").split(" ")
    lateinit var dataset: Dataset
    try{
        dataset = Dataset(name, metaData[4].toDouble(), metaData[6].toDouble(), metaData[7].toInt())
    } catch (e: Exception) {
        throw Exception("file corrupt")
    }
    return dataset
}   

/**
* Returns an individual record from record data [input]
*/
fun createRecord(input: String): Record {
    val data = input.split("\\s".toRegex()).filter { it.isNotBlank() }
    val record = Record(data[0].toInt(), data[1].toInt(), data[5].toDouble())
    return record
}

/**
* Outputs the results of the analysis done on a dataset [data]
*/
fun outputResults(data: Dataset) {
    val driestYear = data.getDriestYear()
    val wettestYear = data.getWettestYear()
    val wettestMonth = data.getWettestMonth()
    val driestMonth = data.getDriestMonth()

    println("Station: ${data.station}")
    println("Latitude: ${data.lat}°")
    println("Longitude: ${data.long}°")
    println("Elevation: ${data.elevation} m")
    println("Number of records: ${data.records.size}")
    println("Years spanned: ${data.getEarliestYear()} to ${data.getLatestYear()}")
    println("Wettest year: ${wettestYear.first} (${wettestYear.second} mm)") 
    println("Driest year: ${driestYear.first} (${driestYear.second} mm)")
    println("Wettest month: ${getMonthName(wettestMonth?.month)} ${wettestMonth?.year} (${wettestMonth?.rain} mm)")
    println("Driest month: ${getMonthName(driestMonth?.month)} ${driestMonth?.year} (${driestMonth?.rain} mm)")
}

/**
* Outputs a bar chart representing the amount of rain each month
* for a year [year] in a dataset [data].
*/
fun printChart(data: Dataset, year: Int) {
    val rain = data.getRainForYear(year)
    val maxRain = rain.maxBy { it -> it.rain }  
    rain.forEach { 
        val bar = ((it.rain / maxRain!!.rain) * 50).toInt()
        val bars = "#".repeat(bar)
        System.out.printf("%9s (%5s): %s\n", getMonthName(it.month), it.rain.toString(), bars)
	}
}

/**
* Returns the name of the month corresponding to the month number [month].
*/
fun getMonthName(month: Int?): String? {
    val months = mapOf(
        1 to "January",
        2 to "Febrary",
        3 to "March",
        4 to "April",
        5 to "May",
        6 to "June",
        7 to "July",
        8 to "August",
        9 to "September",
        10 to "October",
        11 to "November",
        12 to "December"
    )
    val monthName: String? = months[month]
    return monthName
}
