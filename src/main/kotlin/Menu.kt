import java.util.*

class Menu {

    val valkompassen = Quiz()
    val readResults = ReadResults()
    val writeResults = WriteResults()
    var scannerInput = Scanner(System.`in`)
    var choice = 0
    fun startMenu() {

        println("Välkommen till Valkompassen!")
        startMenuText()
        while (choice != 4) {
            var choiceStr = scannerInput.nextLine()
            if (!choiceStr.matches(Regex("^[1-4]$"))) {
                println("Vänligen skriv in ett nummer mellan 1 till 4")
            } else {
                choice = Integer.parseInt(choiceStr)
                when (choice) {
                    1 -> {
                        println("Vänligen skriv in ditt namn för att starta valkompassen.")
                        var name = scannerInput.nextLine()
                        valkompassen.startQuiz(name)
                        startMenuText()
                    }
                    2 -> {
                        readResults.readResults()
                        startMenuText()

                    }
                    3 -> {
                        writeResults.deleteResults()
                        println("Resultathistoriken har rensats")
                        startMenuText()
                    }
                    4 -> println("Programmet stängs av.")
                }
            }
        }
    }

    fun startMenuText(){
        println("Välj alternativ, fyll i siffran och tryck ENTER för att starta")
        println("1. Starta Valkompassen")
        println("2. Se tidigare resultat")
        println("3. Rensa historik")
        println("4. Stäng av programmet")
    }
}