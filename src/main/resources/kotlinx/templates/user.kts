import io.spring.demo.i18n
import io.spring.demo.user
import io.spring.demo.template
import kotlinx.html.*

template {
    li {
        +"${i18n("user")} ${user.firstname} ${user.lastname}"
    }
}
