data class Post(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
) {
    constructor() : this(-1,-1,"","")
}
