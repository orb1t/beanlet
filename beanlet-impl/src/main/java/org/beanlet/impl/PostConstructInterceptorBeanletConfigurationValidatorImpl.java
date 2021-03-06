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
package org.beanlet.impl;

import java.lang.reflect.Modifier;
import org.beanlet.BeanletValidationException;
import org.beanlet.InvocationContext;
import org.beanlet.PostConstruct;
import org.beanlet.annotation.Element;
import org.beanlet.common.AbstractBeanletConfigurationValidator;
import org.beanlet.plugin.BeanletConfiguration;

/**
 *
 * @author Leon van Zantvoort
 */
public final class PostConstructInterceptorBeanletConfigurationValidatorImpl extends
        AbstractBeanletConfigurationValidator<PostConstruct> {
    
    public Class<PostConstruct> annotationType() {
        return PostConstruct.class;
    }

    public Class<?>[] getParameterTypes() {
        return new Class[]{InvocationContext.class};
    }

    public Class<?> getReturnType() {
        return Void.TYPE;
    }

    public Class<?>[] getExceptionTypes() {
        return new Class[]{Exception.class};
    }

    public int getRequiredModifiers() {
        return 0;
    }

    public int getInvalidModifiers() {
        return Modifier.STATIC;
    }

    public boolean isBeanletTypeRequired() {
        return false;
    }

    @Override
    public boolean validate(BeanletConfiguration configuration, 
            Element element) throws BeanletValidationException {
        return !element.isElementOf(configuration.getType());
    }
}
