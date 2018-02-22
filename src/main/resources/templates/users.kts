import io.spring.demo.*

"""<ul>
${users.joinToLine { "<li>${include("user", mapOf("user" to it))}</li>" }}
</ul>"""