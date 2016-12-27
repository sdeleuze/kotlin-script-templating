
import javax.script.Invocable
import javax.script.ScriptEngineManager

fun render(template: String, model: Map<String, Object>, url: String): String {
    val engine = ScriptEngineManager().getEngineByName("kotlin")
    // TODO Use engine.eval(String, Bindings) when it will work
    engine.eval("fun template(model: Map<String, Object>) = \"\"\"" + template + "\"\"\"")
    return (engine as Invocable).invokeFunction("template", model) as String
}