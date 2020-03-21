package com.github.nylle.kotlinfixture.testobjects;

public class TestObjectWithNestedGenericInterfaces {

    private ITestGeneric<String, ITestGenericInside<Integer>> testGeneric;

    public ITestGeneric<String, ITestGenericInside<Integer>> getTestGeneric() {
        return testGeneric;
    }
}
