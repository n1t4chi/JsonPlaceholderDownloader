import java.io.File

fun main(args: Array<String>) {
    val (url, dir) = parseArguments(args)
    val fileWriter = FileWriterFacade()
    val postParser = PostParser()

    println( "Fetching posts from " + url )
    val content = ApiReader.fetchBodyFrom(url)
    val posts = postParser.listFromString(content)
    println( "Fetched " + posts.size + " posts" )
    println( "Saving files to directory: " + dir )
    posts.forEach { post ->
        fileWriter.writeToFile(
            filePathFrom(dir, post),
            postParser.toString(post)
        )
    }
    println( "Finished" )
}

fun filePathFrom(dir: String, post: Post): String {
    return dir + "/" + post.id.toString() + ".json"
}

private fun parseArguments(args: Array<String>): Pair<String, String> {
    var url = "https://jsonplaceholder.typicode.com/posts"
    var dir = File("").absolutePath
    if (args.size % 2 != 0) {
        printHelp()
        throw IllegalArgumentException("Invalid amount of arguments. See help above")
    }
    for (i in 0 until args.size step 2) {
        when (args[i]) {
            "-url" -> url = args[i + 1]
            "-dir" -> dir = args[i + 1]
            else -> {
                printHelp()
                throw IllegalArgumentException("Unknown argument: " + args[i] )
            }
        }
    }
    return Pair(url, dir)
}

fun printHelp() {
    println( """
        Optional parameters:
            -url <url> - program will fetch posts from <url>, default: https://jsonplaceholder.typicode.com/posts
            -dir <path> - program will save posts to <path> directory, default is current directory.
    """.trimIndent()
    )
}
