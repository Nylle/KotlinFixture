package com.github.nylle.kotlinfixture

import com.github.nylle.javafixture.Configuration
import com.github.nylle.javafixture.SpecimenType
import java.time.Clock
import kotlin.streams.asSequence

class Fixture(configuration: Configuration = Configuration()) {
    var configuration = configuration

    inline fun <reified T> construct(): T = com.github.nylle.javafixture.Fixture(configuration).construct(object : SpecimenType<T>() {})
    inline fun <reified T> create(): T = com.github.nylle.javafixture.Fixture(configuration).create(object : SpecimenType<T>() {})
    inline fun <reified T> createMany(): Sequence<T> = com.github.nylle.javafixture.Fixture(configuration).createMany(object : SpecimenType<T>() {}).asSequence()
    inline fun <reified T> createMany(size: Int): Sequence<T> = com.github.nylle.javafixture.Fixture(configuration).build(object : SpecimenType<T>() {}).createMany(size).asSequence()
    inline fun <reified T> addManyTo(result: Collection<T>) = com.github.nylle.javafixture.Fixture(configuration).addManyTo(result, object : SpecimenType<T>() {})
    inline fun <reified T> build(): SpecimenBuilder<T> = SpecimenBuilder(com.github.nylle.javafixture.Fixture(configuration).build(object : SpecimenType<T>() {}))

    fun streamSize(streamSize: Int): Fixture {
        configuration = configuration.streamSize(streamSize)
        return this
    }

    fun collectionSizeRange(min: Int, max: Int): Fixture {
        configuration = configuration.collectionSizeRange(min, max)
        return this
    }

    fun clock(clock: Clock): Fixture {
        configuration = configuration.clock(clock)
        return this
    }

    fun usePositiveNumbersOnly(usePositiveNumbersOnly: Boolean): Fixture {
        configuration = configuration.usePositiveNumbersOnly(usePositiveNumbersOnly)
        return this
    }
}