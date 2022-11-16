import java.io.File

class ReadResults {


    fun readResults(){
        val results = File("src/main/kotlin/results.txt").readText()
        println(results)
        if(results == ""){
            println("Inga tidigare resultat funna")
        }
    }
}