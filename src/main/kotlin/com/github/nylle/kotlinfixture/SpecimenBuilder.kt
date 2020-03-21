package com.github.nylle.kotlinfixture

import com.github.nylle.javafixture.ISpecimenBuilder
import java.util.function.Consumer
import kotlin.streams.asSequence

class SpecimenBuilder<T> (private val builder: ISpecimenBuilder<T>) {

    fun create(): T = builder.create()

    fun createMany(): Sequence<T> = builder.createMany().asSequence()

    fun createMany(size: Int): Sequence<T> = builder.createMany(size).asSequence()

    fun with(f: (t: T) -> Unit): SpecimenBuilder<T> = builder.with(f).let { this }

    fun with(name: String, value: Any): SpecimenBuilder<T> = builder.with(name, value).let { this }

    fun without(name: String): SpecimenBuilder<T> = builder.without(name).let { this }
}
