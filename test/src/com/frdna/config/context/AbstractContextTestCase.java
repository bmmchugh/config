/* Copyright (c) 2011 Free Range Data, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.frdna.config.context;

import org.junit.Before;
import org.junit.Test;

import com.frdna.config.test.AbstractTestCase;
import com.frdna.config.test.Assert;

public abstract class AbstractContextTestCase<T extends Context>
    extends AbstractTestCase {

    public static final String TEST_NAME = "testObject";
    public static final String TEST_NAME_OVERRIDE = "overrideTestObject";
    private T context = null;

    @Before
    public void before() {
        this.context = this.initialize();
    }

    @Test
    public void shouldCastTheObject() {
        Assert.assertEquals(
                TestObject.class,
                this.getContext().get(
                    TestObject.class,
                    AbstractContextTestCase.TEST_NAME).getClass());
    }

    @Test
    public void shouldAssignTheProperties() {
        TestObject testObject = this.getContext().get(
                TestObject.class, AbstractContextTestCase.TEST_NAME);
        Assert.assertEquals(
                "This is a test",
                testObject.getTestField1());
        Assert.assertEquals(
                "This is another test",
                testObject.getTestField2());
    }

    @Test
    public void shouldAssignTheOverrideProperties() {
        TestObject testObject = this.getContext().get(
                TestObject.class, AbstractContextTestCase.TEST_NAME_OVERRIDE);
        Assert.assertEquals(
                "This is an override test",
                testObject.getTestField1());
        Assert.assertEquals(
                "This is another override test",
                testObject.getTestField2());
    }

    @Test
    public void shouldOverrideEnvironmentSpecificDefinitions() {
        T context = this.initialize("Environment");
        TestObject testObject = context.get(
                TestObject.class, AbstractContextTestCase.TEST_NAME_OVERRIDE);
        Assert.assertEquals(
                "This is an overridden override test!",
                testObject.getTestField1());
        Assert.assertEquals(
                "This is another overridden override test!",
                testObject.getTestField2());
    }

    public T getContext() {
        return this.context;
    }

    protected abstract T initialize();

    protected abstract T initialize(Object environment);
}
