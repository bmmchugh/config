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
package com.frdna.config.context.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.frdna.config.context.AbstractContext;
import com.frdna.config.context.ContextException;
import com.frdna.loginator.Log;

public class SpringContext extends AbstractContext {

    private ApplicationContext applicationContext = null;

    public SpringContext(Object environment, String name) {
        String fileName = name;
        if (fileName == null) {
            fileName = "config.xml";
        }

        GenericXmlApplicationContext applicationContext
            = new GenericXmlApplicationContext();
        Log.info(this, "Loading SpringContext from [classpath:%s]", fileName);
        applicationContext.load(new ClassPathResource(fileName));
        if (environment != null) {
            Log.info(this, "Loading SpringContext from [classpath:%s/%s]",
                    environment,
                    fileName);
            ClassPathResource resource = new ClassPathResource(
                    String.valueOf(environment) + "/" + fileName);
            if (resource.exists()) {
                applicationContext.load(resource);
            } else {
                Log.warn(this,
                        "Unable to find environment resource [%s]",
                        resource);
            }
        }
        applicationContext.refresh();
        this.applicationContext = applicationContext;
    }

    public SpringContext(String name) {
        this(null, name);
    }

    public SpringContext() {
        this(null);
    }

    public Object get(String name) {
        try {
            return this.applicationContext.getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            throw new ContextException(
                    "No configuration was found with the name [" + name + "]",
                    e);
        } catch (BeansException e) {
            throw new ContextException(
                    "Configuration could not be created for [" + name + "]",
                    e);
        }
    }
}
