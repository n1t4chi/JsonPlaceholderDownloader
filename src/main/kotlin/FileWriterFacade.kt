import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class FileWriterFacade {
    fun writeToFile( filename: String, content: String )
    {
        val file = File(filename)
        //make parent dir if necessary
        file.parentFile?.mkdirs()
        val bw = BufferedWriter( FileWriter(file) )
        bw.use {
            bw.write( content )
        }
    }
}