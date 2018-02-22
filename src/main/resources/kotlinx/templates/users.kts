import io.spring.demo.template
import io.spring.demo.users
import kotlinx.html.ul

template {
    ul {
        for (user in users) {
            include("user", mapOf("user" to user))
        }
    }
}
