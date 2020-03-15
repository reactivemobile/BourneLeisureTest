import com.google.gson.Gson
import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph
import java.net.URL

fun main() {
    println("Creating Campsite..\n")

    val campsite = createCampsite()

    println("Adding Roads..\n")

    addRoads(campsite)

    println("Loading data from URL..\n")

    val jsonResponse = URL("https://haven-tech-test.s3-eu-west-1.amazonaws.com/tech+test+json.json").readText()

    val groups = parse(jsonResponse)

    println("Transporting groups..")

    transportGroups(campsite, groups)

    println("\nDone")
}

fun transportGroups(campsite: Graph<String, DefaultEdge>, groups: Array<Group>) {
    var waitingTimeSoFar = 0

    for (group in groups) {
        val route = DijkstraShortestPath.findPathBetween(campsite, "Caravan 1", "Caravan ${group.caravan}")
        val pathLength = route.length
        val routeSteps = route.vertexList
        println("Group ${group.familyid}\t\tWaiting time = $waitingTimeSoFar minutes\t\t Path to Caravan ${group.caravan} is $routeSteps")
        waitingTimeSoFar += pathLength
    }
}

/**
 * Create the campsite (graph) and add the caravans (nodes)
 */
fun createCampsite(): Graph<String, DefaultEdge> {
    val graph: Graph<String, DefaultEdge> = SimpleGraph<String, DefaultEdge>(DefaultEdge::class.java)

    val caravans = Array(16) { i: Int -> "Caravan ${i + 1}" }

    for (caravan in caravans) {
        graph.addVertex(caravan)
    }

    return graph
}

/**
 * Add the roads to the map (edges to the graph)
 */
fun addRoads(graph: Graph<String, DefaultEdge>) {

    // Main circuit
    graph.addEdge("Caravan 1", "Caravan 2")
    graph.addEdge("Caravan 2", "Caravan 3")
    graph.addEdge("Caravan 3", "Caravan 4")
    graph.addEdge("Caravan 4", "Caravan 5")
    graph.addEdge("Caravan 5", "Caravan 6")
    graph.addEdge("Caravan 6", "Caravan 7")
    graph.addEdge("Caravan 7", "Caravan 8")
    graph.addEdge("Caravan 8", "Caravan 9")
    graph.addEdge("Caravan 9", "Caravan 10")
    graph.addEdge("Caravan 10", "Caravan 11")
    graph.addEdge("Caravan 11", "Caravan 12")
    graph.addEdge("Caravan 12", "Caravan 13")
    graph.addEdge("Caravan 13", "Caravan 1")

    // Shortcut through the middle
    graph.addEdge("Caravan 13", "Caravan 16")
    graph.addEdge("Caravan 16", "Caravan 15")
    graph.addEdge("Caravan 15", "Caravan 14")
    graph.addEdge("Caravan 14", "Caravan 8")

}

data class Group(val familyid: Int, val groupsize: Int, val caravan: Int)

fun parse(json: String): Array<Group> = Gson().fromJson(json, Array<Group>::class.java)
