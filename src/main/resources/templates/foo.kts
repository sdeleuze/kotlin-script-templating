import io.spring.demo.User
import io.spring.demo.line

fun template(model: Map<String, Any>) =
"""
<html>
<body>
<h1>Title : ${model["title"]}</h1>
<ul>
	${(model["users"] as List<User>).line {"<li>User ${it.firstname} ${it.lastname}" }}
</ul>
</html>
"""
