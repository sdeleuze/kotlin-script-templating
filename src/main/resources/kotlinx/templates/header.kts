import io.spring.demo.i18n
import io.spring.demo.template
import kotlinx.html.title
import kotlinx.html.head

template {
    head {
        title {
            +i18n("title")
        }
    }
}
