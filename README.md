Kotlin script based template rendering example with Spring Boot.

These type safe templates look like:

```kotlin
import io.spring.demo.User
import io.spring.demo.jointToLine

"""
${include("header", bindings)}
<h1>Title : $title</h1>
<ul>
	${(users as List<User>).jointToLine{ "<li>User ${it.firstname} ${it.lastname}</li>" }}
</ul>
${include("footer")}
"""
```