fun main() {
    val user = User.Builder().firstName("David").lastName("Corrado").id(1).build()
    println(user)

    val user2 = User(firstName = "David", lastName = "Corrado", 2)
    println(user2)

    runCatching {
        User.Builder().firstName("David").lastName("Corrado").build()
    }.onFailure {
        println(it)
    }
}

data class User(
    val firstName: String,
    val lastName: String,
    val id: Int
) {
    init {
        if(id == -1) throw Exception("id is required")
    }
    data class Builder(
        private var firstName: String = "",
        private var lastName: String = "",
        private var id: Int = -1
    ) {
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun id(id: Int) = apply { this.id = id }
        fun build() = User(firstName, lastName, id)
    }
}