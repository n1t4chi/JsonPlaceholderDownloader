import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


object ApiReader {
    private val client = OkHttpClient()

    fun fetchBodyFrom(url: String): String {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute().use {
            response -> if (response.isSuccessful)
                response.body?.string().orEmpty()
            else
                throw fail(response)
        }
    }

    private fun fail(response: Response) = RuntimeException(
        "Request failed." +
                "\nError code:" + response.code +
                "\nContent code:" + response.body
    )

}