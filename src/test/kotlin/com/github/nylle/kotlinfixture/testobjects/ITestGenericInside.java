package com.github.nylle.kotlinfixture.testobjects;

import java.util.Optional;

public interface ITestGenericInside<T> {

    Optional<Boolean> getOptionalBoolean();

    Optional<T> getOptionalT();

    TestObjectGeneric<String, T> getTestGeneric();

}
