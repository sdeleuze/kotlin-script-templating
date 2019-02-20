package io.spring.demo.scriptengine

import org.jetbrains.kotlin.cli.common.repl.KotlinJsr223JvmScriptEngineFactoryBase
import org.jetbrains.kotlin.cli.common.repl.ScriptArgsWithTypes
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import org.jetbrains.kotlin.script.jsr223.KotlinStandardJsr223ScriptTemplate
import org.slf4j.LoggerFactory
import javax.script.Bindings
import javax.script.ScriptContext
import javax.script.ScriptEngine

class KotlinScriptEngineFactory : KotlinJsr223JvmScriptEngineFactoryBase() {

    private val kotlinScriptEngine: ScriptEngine by lazy {
        LoggerFactory.getLogger(javaClass).info("Creating a new script engine with classpath: ${springBootClassPath}")
        KotlinJsr223JvmLocalScriptEngine(
            this,
            springBootClassPath,
            KotlinStandardJsr223ScriptTemplate::class.qualifiedName!!,
            { ctx, types ->
                ScriptArgsWithTypes(arrayOf(ctx.getBindings(ScriptContext.ENGINE_SCOPE)), types ?: emptyArray())
            },
            arrayOf(Bindings::class)
        )
    }

    override fun getScriptEngine(): ScriptEngine {
        return kotlinScriptEngine
    }
}