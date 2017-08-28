import org.springframework.web.servlet.view.script.RenderingContext
import org.springframework.context.support.ResourceBundleMessageSource
import javax.script.*
import org.springframework.beans.factory.getBean

fun render(template: String, model: Map<String, Any>, renderingContext: RenderingContext): String {
	val engine = ScriptEngineManager().getEngineByName("kotlin")
	val bindings = SimpleBindings(model)
	var messageSource = renderingContext.applicationContext.getBean<ResourceBundleMessageSource>()
	bindings.put("i18n", { code: String -> messageSource.getMessage(code, null, renderingContext.locale) })
	bindings.put("include", { path: String -> renderingContext.templateLoader.apply("templates/$path.kts") })
	return engine.eval(template, bindings) as String
}