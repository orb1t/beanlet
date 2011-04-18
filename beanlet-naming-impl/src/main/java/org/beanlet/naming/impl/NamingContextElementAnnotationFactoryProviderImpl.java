/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Beanlet - JSE Application Container.
 * Copyright (C) 2006  Leon van Zantvoort
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Leon van Zantvoort
 * 243 Acalanes Drive #11
 * Sunnyvale, CA 94086
 * USA
 *
 * zantvoort@users.sourceforge.net
 * http://beanlet.org
 */
package org.beanlet.naming.impl;

import java.lang.reflect.Method;
import org.beanlet.annotation.AnnotationValueResolver;
import static org.beanlet.naming.impl.NamingConstants.*;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.beanlet.annotation.AnnotationProxy;
import org.beanlet.naming.NamingContext;
import org.beanlet.naming.NamingProperty;
import org.beanlet.common.AbstractElementAnnotationFactory;
import org.beanlet.common.AbstractProvider;
import org.beanlet.plugin.ElementAnnotationContext;
import org.beanlet.plugin.ElementAnnotationFactory;
import org.beanlet.plugin.spi.ElementAnnotationFactoryProvider;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Leon van Zantvoort
 */
public class NamingContextElementAnnotationFactoryProviderImpl extends AbstractProvider 
        implements ElementAnnotationFactoryProvider {
    
    public List<ElementAnnotationFactory> getElementAnnotationFactories() {
        final XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(NAMING_NAMESPACE_CONTEXT);
        
        ElementAnnotationFactory factory = new AbstractElementAnnotationFactory() {
            public String getNamespaceURI() {
                return NAMING_NAMESPACE_URI;
            }
            public String getNodeName() {
                return "naming-context";
            }
            public Class<? extends Annotation> annotationType() {
                return NamingContext.class;
            }
            public Object getValueFromNode(Node node, String elementName, 
                    Class type, final Object parentValue, 
                    ElementAnnotationContext ctx) throws Throwable {
                if (elementName.equals("value")) {
                    NodeList names = (NodeList) xpath.evaluate(
                            "./:property/@name", node, XPathConstants.NODESET);
                    NodeList values = (NodeList) xpath.evaluate(
                            "./:property/@value", node, XPathConstants.NODESET);

                    assert names.getLength() == values.getLength();

                    NamingProperty[] props = new NamingProperty[names.getLength()];
                    for (int i = 0; i < names.getLength(); i++) {
                        final String name = names.item(i).getNodeValue();
                        final String value = values.item(i).getNodeValue();
                        props[i] = AnnotationProxy.newProxyInstance(
                                NamingProperty.class, ctx.getClassLoader(), 
                                new AnnotationValueResolver() {
                            public Object getValue(Method method, ClassLoader loader) throws Throwable {
                                final Object o;
                                if (method.getName().equals("name")) {
                                    o = name;
                                } else if (method.getName().equals("value")) {
                                    o = value;
                                } else {
                                    o = method.getDefaultValue();
                                }
                                return o;
                            }
                        });
                    }
                    return props;
                } else {
                    return super.getValueFromNode(node, elementName, type, 
                            parentValue, ctx);
                }
            }
        };
        return Collections.singletonList(factory);
    }
}