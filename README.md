# Bourne Leisure Test

## Assumptions

I'm assuming there's no distance from the park gate to Caravan 1 because it's not listed in the instructions (They state it takes 1 minute to travel between each _caravan_ on the map). If necessary we could modify the graph setup to make a new start node.

It also wasn't clear from the instructions whether or not the json should be used directly from the URL or copied to a local file. I've just gone with the former but I'm not handling network errors.

## The App

I'm using Kotlin to build a command-line app which

* Creates a graph of the campsite  
* Downloads the group data from the URL
* Uses Dijkstra's shortest path algorithm to find the shortest path from Caravan 1 to the target caravan

I'm using the Java graphing library [jgrapht](https://jgrapht.org/) for graph creation and searching to save reinventing the wheel.

## Running the code

Navigate to the project directory and build the jar using `./gradlew clean build jar`

Then run the app using `java -jar build/libs/BourneLeisureTest-1.0-SNAPSHOT.jar`

This (should) produce the output

```
Creating Campsite..

Adding Roads..

Loading data from URL..

Transporting groups..
Group   1	Waiting time = 0 minutes	Path to Caravan 5 is [Caravan 1, Caravan 2, Caravan 3, Caravan 4, Caravan 5]
Group   2	Waiting time = 4 minutes	Path to Caravan 12 is [Caravan 1, Caravan 13, Caravan 12]
Group   3	Waiting time = 6 minutes	Path to Caravan 9 is [Caravan 1, Caravan 13, Caravan 12, Caravan 11, Caravan 10, Caravan 9]
Group   4	Waiting time = 11 minutes	Path to Caravan 15 is [Caravan 1, Caravan 13, Caravan 16, Caravan 15]
Group   5	Waiting time = 14 minutes	Path to Caravan 10 is [Caravan 1, Caravan 13, Caravan 12, Caravan 11, Caravan 10]
Group   6	Waiting time = 18 minutes	Path to Caravan 3 is [Caravan 1, Caravan 2, Caravan 3]
Group   7	Waiting time = 20 minutes	Path to Caravan 1 is [Caravan 1]
Group   8	Waiting time = 20 minutes	Path to Caravan 3 is [Caravan 1, Caravan 2, Caravan 3]
Group   9	Waiting time = 22 minutes	Path to Caravan 7 is [Caravan 1, Caravan 2, Caravan 3, Caravan 4, Caravan 5, Caravan 6, Caravan 7]
Group  10	Waiting time = 28 minutes	Path to Caravan 6 is [Caravan 1, Caravan 2, Caravan 3, Caravan 4, Caravan 5, Caravan 6]
Group  11	Waiting time = 33 minutes	Path to Caravan 14 is [Caravan 1, Caravan 13, Caravan 16, Caravan 15, Caravan 14]
Group  12	Waiting time = 37 minutes	Path to Caravan 11 is [Caravan 1, Caravan 13, Caravan 12, Caravan 11]
Group  13	Waiting time = 40 minutes	Path to Caravan 13 is [Caravan 1, Caravan 13]
Group  14	Waiting time = 41 minutes	Path to Caravan 8 is [Caravan 1, Caravan 13, Caravan 16, Caravan 15, Caravan 14, Caravan 8]
Group  15	Waiting time = 46 minutes	Path to Caravan 16 is [Caravan 1, Caravan 13, Caravan 16]
Group  16	Waiting time = 48 minutes	Path to Caravan 2 is [Caravan 1, Caravan 2]

Done

```
