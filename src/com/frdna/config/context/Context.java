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

public interface Context {

    /**
     * Get an object from the configuration context identified by
     * <code>name</code>.
     *
     * @param name
     *     identifies the configured object in the context
     * @return
     *     the configured object from the configuration context
     * @throws ContextException
     *     if the configured object is not found
     */
    Object get(String name);

    /**
     * Get an object from the configuration context identified by
     * <code>name</code>.  The object is cast to the <code>class</code>
     * identified by <code>cls</code>
     *
     * @param cls
     *     the <code>class</code> of the configured object
     * @param name
     *     identifies the configured object in the context
     * @return
     *     the configured object from the configuration context
     * @throws ContextException
     *     if the configured object is not found
     */
    <T> T get(Class<T> cls, String name);
}
