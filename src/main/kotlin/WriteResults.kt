import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class WriteResults {
    var scannerInput = Scanner(System.`in`)
    var thanks = "Tack för att du använde valkompassen"
    var i = 0


    fun writeResults(name: String, results: List<Quiz.PartyScores>){
        val earlierResults = File("src/main/kotlin/results.txt").readText()
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatted = current.format(formatter)
        while(i == 0) {
            var saveOrNotStr = scannerInput.nextLine()
            if (!saveOrNotStr.matches(Regex("^[1-2]$"))) {
                println("Vänligen skriv in 1 eller 2.   ")
            } else {
                var saveOrNot = Integer.parseInt(saveOrNotStr)
                when(saveOrNot){
                    1 -> {File("src/main/kotlin/results.txt").printWriter().use { out ->
                        out.println(earlierResults)
                        out.println(name)
                        out.println(formatted)
                        results.forEach {
                            out.println(
                                "${it.party}: ${
                                    BigDecimal((it.score / 120.0) * 100).setScale(
                                        0,
                                        RoundingMode.HALF_EVEN
                                    )
                                }%"
                            )
                        }
                    }
                        println(thanks)
                        i++}
                    2 -> {println(thanks)
                        i++}
                }

            }
        }
    }

    fun deleteResults(){
        File("src/main/kotlin/results.txt").printWriter().use { out -> ""}

    }
}