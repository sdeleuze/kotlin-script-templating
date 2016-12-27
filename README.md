Kotlin script based type safe template rendering example with Spring Boot.

Templates look like:

```kotlin
import io.spring.demo.User
import io.spring.demo.line

"""
${include("header")}
<h1>Title : $title</h1>
<ul>
	${(users as List<User>).line{ "<li>User ${it.firstname} ${it.lastname}</li>" }}
</ul>
${include("footer")}
"""
```