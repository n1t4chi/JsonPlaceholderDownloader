# JsonPlaceholderDownloader
Simple program written in Kotlin and Gradle as a training. The program downloads posts from https://jsonplaceholder.typicode.com/ site and writes them to separate JSON files.
\
\
To run the project use Main.kt file as a starting point. 
If it finishes successfully, individual posts should be located in the working directory. Target directory can be provided via `-dir <path_to_dir>` program argument.
Also, you can change endpoint via `-url <url>`.
\
\
Tested on JDK16