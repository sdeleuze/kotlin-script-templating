import io.spring.demo.i18n
import io.spring.demo.template
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.html

template {
    html {
        include("header")
        body {
            include("locale")
            h1 {
                + "Kotlinx"
                + " "
                +i18n("title")
            }
            include("users")
        }
    }
}
