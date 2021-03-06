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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.beanlet.BeanletValidationException;
import org.beanlet.ScopeAnnotation;
import org.beanlet.annotation.AnnotationDomain;
import org.beanlet.annotation.ElementAnnotation;
import org.beanlet.annotation.TypeElement;
import org.beanlet.plugin.BeanletConfiguration;
import org.beanlet.plugin.BeanletConfigurationValidator;

/**
 *
 * @author Leon van Zantvoort
 */
public final class BeanletScopeConfigurationValidatorImpl implements 
        BeanletConfigurationValidator {
    
    public void validate(BeanletConfiguration configuration) throws
            BeanletValidationException {
        Class<?> type = configuration.getType();
        AnnotationDomain domain = configuration.getAnnotationDomain();
        List<Object> list = new ArrayList<Object>();
        for (ElementAnnotation<TypeElement, ? extends Annotation> ea :
                domain.getTypedElements(TypeElement.class)) {
            if (ea.getAnnotation().annotationType().
                    isAnnotationPresent(ScopeAnnotation.class)) {
                if (!ea.getElement().isElementOf(type)) {
                    throw new BeanletValidationException(configuration.getComponentName(),
                            "Scope annotations MAY only be applied to " +
                            "beanlet type: '" + ea.getAnnotation() + "'.");
                }
                if (ea.getElement().getType().equals(type)) {
                    list.add(ea);
                }
            }
        }
        if (list.size() > 1) {
            throw new BeanletValidationException(configuration.getComponentName(),
                    "Beanlet MAY only define one scope annotation: " + list + ".");
        }
    }
}
