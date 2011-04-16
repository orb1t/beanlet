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
package org.beanlet.web.impl;

import org.beanlet.RetentionPolicy;
import org.beanlet.annotation.AnnotationDeclaration;
import org.beanlet.annotation.TypeElement;
import org.beanlet.common.AbstractProvider;
import org.beanlet.plugin.BeanletConfiguration;
import org.beanlet.common.Beanlets;
import org.beanlet.web.Request;
import org.jargo.ComponentObjectPool;
import org.jargo.spi.ComponentObjectFactoryProvider;
import org.jargo.ComponentConfiguration;

/**
 * @author Leon van Zantvoort
 */
public final class RequestBeanletObjectPoolProviderImpl extends 
        AbstractProvider implements ComponentObjectFactoryProvider {
    
    public <T> ComponentObjectPool<T> getComponentObjectFactory(
            ComponentConfiguration<T> configuration) {
        final ComponentObjectPool<T> pool;
        if (configuration instanceof BeanletConfiguration) {
            Beanlets beanlets = Beanlets.getInstance(configuration);
            AnnotationDeclaration<Request> declaration = 
                    ((BeanletConfiguration) configuration).getAnnotationDomain().
                    getDeclaration(Request.class);
            Request request = declaration.getAnnotation(
                    TypeElement.instance(configuration.getType()));
            if (request != null) {
                pool = new RequestBeanletObjectPoolImpl<T>(
                        configuration.getComponentName(), beanlets.isLazy(),
                        beanlets.getRetentionPolicy() == RetentionPolicy.INVALIDATE);
            } else {
                pool = null;
            }
        } else {
            pool = null;
        }
        return pool;
    }
}
