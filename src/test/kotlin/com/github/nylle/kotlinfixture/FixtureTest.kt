package com.github.nylle.kotlinfixture

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.nylle.kotlinfixture.testobjects.TestObjectGeneric
import com.github.nylle.kotlinfixture.testobjects.TestObjectWithGenericConstructor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Clock
import java.util.ArrayList
import java.util.Optional


class FixtureTest {

    @Test
    fun construct() {
        val result = Fixture().construct<TestObjectWithGenericConstructor>()

        assertThat(result).isInstanceOf(TestObjectWithGenericConstructor::class.java)
        assertThat(result.value).isInstanceOf(String::class.java)
        assertThat(result.value).isNotBlank()
        assertThat(result.integer).isInstanceOf(Optional::class.java)
        assertThat(result.integer).isPresent
        assertThat(result.integer.get()).isInstanceOf(Integer::class.java)
        assertThat(result.privateField).isNull()
    }

    @Test
    fun create() {
        val result = Fixture().create<TestObjectGeneric<String, Optional<Int>>>()

        assertThat(result).isInstanceOf(TestObjectGeneric::class.java)
        assertThat(result.t).isInstanceOf(String::class.java)
        assertThat(result.u).isInstanceOf(Optional::class.java)
        assertThat(result.u).isPresent
        assertThat(result.u.get()).isInstanceOf(Integer::class.java)
    }

    @TestWithFixture(minCollectionSize = 11, maxCollectionSize = 11, positiveNumbersOnly = true)
    fun create(result: TestObjectGeneric<String, List<Int>>) {
        assertThat(result).isInstanceOf(TestObjectGeneric::class.java)
        assertThat(result.t).isInstanceOf(String::class.java)
        assertThat(result.u).isInstanceOf(List::class.java)
        assertThat(result.u).hasSize(11)
        assertThat(result.u).allMatch { it > 0 }
    }

    @Test
    fun createMany() {
        val results = Fixture().createMany<TestObjectGeneric<String, Optional<Int>>>()
        assertThat(results).isInstanceOf(Sequence::class.java)

        val resultList = results.toList()
        assertThat(resultList.toList()).hasSize(3);

        val result = resultList[0]
        assertThat(result).isInstanceOf(TestObjectGeneric::class.java)
        assertThat(result.t).isInstanceOf(String::class.java)
        assertThat(result.u).isInstanceOf(Optional::class.java)
        assertThat(result.u).isPresent()
        assertThat(result.u.get()).isInstanceOf(Integer::class.java)
    }

    @Test
    fun createTwo() {
        val results = Fixture().createMany<String>(2)
        assertThat(results).isInstanceOf(Sequence::class.java)

        val resultList = results.toList()
        assertThat(resultList.toList()).hasSize(2);
        assertThat(resultList[0]).isInstanceOf(String::class.java)
        assertThat(resultList[0]).isNotBlank()
    }

    @Test
    fun addManyTo() {
        val collection = mutableListOf("foo")

        Fixture().addManyTo(collection)

        assertThat(collection).hasSize(4)
        assertThat(collection[0]).isEqualTo("foo")
        assertThat(collection[1]).isNotBlank()
        assertThat(collection[2]).isNotBlank()
        assertThat(collection[3]).isNotBlank()
    }

    @Test
    fun build() {
        val result = Fixture().build<String>()

        assertThat(result).isInstanceOf(SpecimenBuilder::class.java)
        assertThat(result.create()).isInstanceOf(String::class.java)
        assertThat(result.create()).isNotBlank()
    }

    @Test
    fun canBeFullyConfigured() {
        val result = Fixture()
                .usePositiveNumbersOnly(true)
                .collectionSizeRange(11, 11)
                .clock(Clock.systemUTC())
                .streamSize(1)
                .createMany<List<Int>>()
                .toList()

        assertThat(result).hasSize(1)
        assertThat(result[0]).hasSize(11)
        assertThat(result[0]).allMatch { x -> x > -1 }
    }
}