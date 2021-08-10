fun main(args: Array<String>) {
    val url = if (args.size > 0) args[0] else "https://jsonplaceholder.typicode.com/posts"
    val dir = if (args.size > 1) args[1] else "."

    val fileWriter = FileWriterFacade()
    val postParser = PostParser()

    val content = ApiReader.fetchBodyFrom(url)
    val posts = postParser.listFromString(content)
    posts.forEach { post ->
        fileWriter.writeToFile(
            filePathFrom(dir, post),
            postParser.toString(post)
        )
    }
}

fun filePathFrom(dir: String, post: Post): String {
    return dir + "/" + post.id.toString() + ".json"
}
