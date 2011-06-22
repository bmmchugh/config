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
package com.frdna.config;

import com.frdna.config.context.Context;
import com.frdna.config.context.spring.SpringContext;

public class Config {

    private static final String ENVIRONMENT_KEY  = "config.environment";
    private static final String FILE_NAME_KEY    = "config.file";
    private static final String CONTEXT_TYPE_KEY = "config.context";

    private static Context context = null;

    static {
        String environment = System.getProperty(Config.ENVIRONMENT_KEY);

        if (environment == null) {
            environment = "development";
        }

        String fileName = System.getProperty(Config.FILE_NAME_KEY);

        String contextType = System.getProperty(Config.CONTEXT_TYPE_KEY);
        if (contextType == null) {
            contextType = "spring";
        }

        if ("spring".equalsIgnoreCase(contextType)) {
            Config.context = new SpringContext(environment, fileName);
        } else {
            throw new ConfigException(
                    "Unhandled context type [" + contextType + "]");
        }
    }

    private Config() { }

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
    public static Object get(String name) {
        return Config.context.get(name);
    }

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
    public static <T> T get(Class<T> type, String name) {
        return Config.context.get(type, name);
    }
}
