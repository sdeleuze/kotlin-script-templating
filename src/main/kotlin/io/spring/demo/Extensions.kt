package io.spring.demo

fun  <T> Iterable<T>.line(function: (foo: T) -> String): String { return joinToString(separator = "\n") { foo -> function.invoke(foo) } }