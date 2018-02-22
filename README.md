Spring Boot + Kotlin type safe template rendering with i18n and nested template support.
**This feature is considered experimental** since there is a lot of things to wire manually,
no cross-site scripting protection out of the box, the caching mechanism need to be improved,
etc.

It requires Spring Framework 5.x and Kotlin 1.1+.

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

To enable variable resolution in `.kts` files in IDEA, go to menu preferences -> Build, Execution, Deployment -> Compiler -> Kotlin Compiler and set:
 - Script templates class: `kotlin.script.templates.standard.ScriptTemplateWithBindings`
 - Script templates classpath: `/path/to/kotlin-script-runtime.jar`
 
This may be configured automatically in future version of IDEA Kotlin plugin.

Feel free to send pull requests to improve it!
