import io.spring.demo.*

"""<ul>
${users.joinToLine { "<li>${include("user", mapOf(Pair("user", it)))}</li>" }}
</ul>"""