package com.github.nylle.kotlinfixture

import com.github.nylle.javafixture.Configuration
import com.github.nylle.javafixture.Fixture
import com.github.nylle.javafixture.SpecimenType
import kotlin.streams.asSequence

class Fixture(configuration: Configuration = Configuration()) {
    val javaFixture = Fixture(configuration)

    inline fun <reified T> construct(): T = javaFixture.construct(object : SpecimenType<T>(){})
    inline fun <reified T> create(): T = javaFixture.create(object : SpecimenType<T>(){})
    inline fun <reified T> createMany(): Sequence<T> = javaFixture.createMany(object : SpecimenType<T>(){}).asSequence()
    inline fun <reified T> createMany(size: Int): Sequence<T> = javaFixture.build(object : SpecimenType<T>(){}).createMany(size).asSequence()
    inline fun <reified T> addManyTo(result: Collection<T>) = javaFixture.addManyTo(result, object : SpecimenType<T>(){})
    inline fun <reified T> build(): SpecimenBuilder<T> = SpecimenBuilder(javaFixture.build(object : SpecimenType<T>(){}))

}