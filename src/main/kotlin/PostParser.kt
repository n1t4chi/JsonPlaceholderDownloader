import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.Exception
import java.lang.RuntimeException

class PostParser {
    private val mapper= ObjectMapper()
    private val listOfPostsType = mapper.typeFactory
        .constructCollectionType(List::class.java, Post::class.java)

    fun listFromString(content: String): List<Post> {
        try{
            return mapper.readValue(content, listOfPostsType )
        } catch ( ex: Exception ) {
            throw RuntimeException( "Could not parse content", ex )
        }
    }
    fun toString(post: Post): String {
        return mapper.writeValueAsString( post )
    }
}