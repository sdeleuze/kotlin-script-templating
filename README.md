Spring Boot + Kotlin type safe template rendering

These templates look like:

```kotlin
import io.spring.demo.User
import io.spring.demo.joinToLine

"""
${include("header", bindings)}
<h1>Title : $title</h1>
<ul>
	${(users as List<User>).joinToLine{ "<li>User ${it.firstname} ${it.lastname}</li>" }}
</ul>
${include("footer")}
"""
```

Feel free to send pull requests to improve it!