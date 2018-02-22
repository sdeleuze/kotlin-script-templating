import io.spring.demo.template
import kotlinx.html.a
import kotlinx.html.p

template {
    p {
        +"Locale:"
        +" "
        a("/?locale=fr") { +"FR" }
        +" | "
        a("/?locale=en") { +"EN" }
    }
}
