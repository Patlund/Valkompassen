
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.math.abs

class Quiz {
    var scannerInput = Scanner(System.`in`)
    @Serializable
    data class Opinions(val party: String, val opinion: Int)

    @Serializable
    data class PartyScores(val party: String, var score: Int)

    @Serializable
    data class Questions(val id: Int,val question: String, val opinion: List<Opinions>)

    val writeResults = WriteResults()

    var n = 0
    var i = 0
    var questionInterval = 0


    //Serializing and decoding the questions JSON
    val questionsJson = File("src/main/kotlin/questions.json").readText()
    val readQuestionsFile = Json.decodeFromString<List<Questions>>(questionsJson)

    //Serializing and decoding the parties with scores
    val partyScoreJson = File("src/main/kotlin/partyScores.json").readText()
    var readPartyScoreFile = Json.decodeFromString<List<PartyScores>>(partyScoreJson)



    fun startQuiz(name: String){
        println("Fyll i numret på det du tycker i frågan")
        while(questionInterval < readQuestionsFile.size){
            println("Fråga "+ readQuestionsFile[questionInterval].id + ")  " + readQuestionsFile[questionInterval].question)
            println("1. Instämmer helt")
            println("2. Instämmer delvis")
            println("3. Delvis emot")
            println("4. Helt emot")
            var choiceStr = scannerInput.nextLine()
            if(!choiceStr.matches(Regex("^[1-4]$"))){
                println("Vänligen skriv in ett nummer mellan 1 till 4")
            }
            else {
                var choice = Integer.parseInt(choiceStr);
                when (choice) {
                    1 -> choice = 2
                    2 -> choice = 1
                    3 -> choice = -1
                    4 -> choice = -2
                    else -> {}
                }
                for (opinion in readQuestionsFile[questionInterval].opinion) {

                    val score = 4 - abs(opinion.opinion - choice)
                    for (partyScore in readPartyScoreFile) {
                        if (partyScore.party == opinion.party) {
                            readPartyScoreFile[n].score += score
                            n++
                        }
                    }
                }
                n = 0
                questionInterval++
            }
            }


        val finalResult = readPartyScoreFile.sortedByDescending { it.score }
        println("Dina resultat är:")
       while (i <= finalResult.lastIndex) {
           println(
               finalResult[i].party + ": " + BigDecimal((finalResult[i].score / 120.0) * 100).setScale(
                   0,
                   RoundingMode.HALF_EVEN
               ) + "%"
           )
           i++
       }
        println("Vill du spara dina resultat?")
        println("1. Ja")
        println("2. Nej")
        writeResults.writeResults(name,finalResult)
        questionInterval = 0
        i = 0
        n = 0
        for(score in readPartyScoreFile){
            score.score = 0
        }
        }

}
