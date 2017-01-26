Spring Boot + Kotlin type safe template rendering with i18n and nested template support.

Requires Spring Framework 5.x and Kotlin 1.1+.

These templates look like:

```kotlin
import io.spring.demo.*

"""
${include("header")}
<h1>${i18n("title")}</h1>
<ul>
	${users.joinToLine{ "<li>${i18n("user")} ${it.firstname} ${it.lastname}</li>" }}
</ul>
${include("footer")}
"""
```

To enable variable resolution in `.kts` files in IDEA, go to menu preferences -> Build, Execution, Deployement -> Compiler -> Kotlin Compiler and set:
 - Script templates class: `kotlin.script.templates.standard.ScriptTemplateWithBindings`
 - Script templates classpath: `/path/to/kotlin-script-runtime.jar`
 
This will be configured automatically in future version of IDEA Kotlin plugin.

Feel free to send pull requests to improve it!
