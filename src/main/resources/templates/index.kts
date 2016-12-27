import io.spring.demo.User
import io.spring.demo.line

"""
<html>
<body>
<h1>Title : ${bindings["title"]}</h1>
<ul>
	${(bindings["users"] as List<User>).line{ "<li>User ${it.firstname} ${it.lastname}</li>" }}
</ul>
</html>
"""
