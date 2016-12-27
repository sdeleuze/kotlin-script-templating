import javax.script.*

fun render(template: String, model: Map<String, Any>, url: String): String {
    val engine = ScriptEngineManager().getEngineByName("kotlin")
    val bindings: Bindings = SimpleBindings()
    bindings.putAll(model)
    bindings.put("url", url)
    engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE)
    // TODO Use engine.eval(String, Bindings) when it will work
    return engine.eval(template) as String
}