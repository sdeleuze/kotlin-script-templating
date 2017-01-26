import io.spring.demo.*

"""${include("header")}
<p>Locale: <a href="/?locale=fr">FR</a> | <a href="/?locale=en">EN</a></p>
<h1>${i18n("title")}</h1>
${include("users", mapOf(Pair("users", users)))}
${include("footer")}"""
