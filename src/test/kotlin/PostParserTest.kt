import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.stream.Collectors
import java.util.stream.IntStream

internal class PostParserTest {
    private val postParser = PostParser()

    @Test
    internal fun givenInvalidContentThrowsNeatException() {
        //prepare
        val content = "some thrash made by cosmic rays "

        //execute
        try{
            postParser.listFromString(content)
            Assertions.fail<Any>("no error")
        } catch ( ex: RuntimeException ) {
            //verify
            Assertions.assertEquals(
                "Could not parse content",
                ex.message
            )
        }
    }

    @Test
    internal fun givenProperStringReturnsArrayOfPosts() {
        //prepare
        val content = """
        [
            {
                "userId": 1,
                "id": 1,
                "title": "title1",
                "body": "postBody1"
            },
            {
                "userId": 2,
                "id": 2,
                "title": "title2",
                "body": "postBody2"
            }
        ]
        """

        //execute
        val parsedPosts = postParser.listFromString(content)

        //verify
        Assertions.assertEquals(
            IntStream.of( 1, 2 )
                .mapToObj{ i -> Post( i, i, "title" + i, "postBody" + i ) }
                .collect( Collectors.toList() )
            ,
            parsedPosts
        )
    }

    @Test
    internal fun parsesPostToStringProperly() {
        //prepare
        val post = Post( 1, 2, "myTitle", "myBody" )

        //execute
        val content = postParser.toString(post)

        //verify
        Assertions.assertEquals(
            """{"userId":1,"id":2,"title":"myTitle","body":"myBody"}""",
            content
        )
    }
}