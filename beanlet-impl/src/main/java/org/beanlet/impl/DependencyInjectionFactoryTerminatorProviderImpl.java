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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.beanlet.Inject;
import org.beanlet.StaticFactory;
import org.beanlet.annotation.Element;
import org.beanlet.annotation.ElementAnnotation;
import org.beanlet.common.AbstractProvider;
import org.beanlet.plugin.BeanletConfiguration;
import org.beanlet.plugin.DependencyInjectionFactory;
import org.beanlet.common.DependencyInjectionFactoryTerminator;
import org.beanlet.plugin.spi.DependencyInjectionFactoryProvider;
import org.jargo.deploy.SequentialDeployable;

/**
 *
 * @author Leon van Zantvoort
 */
public final class DependencyInjectionFactoryTerminatorProviderImpl extends 
        AbstractProvider implements DependencyInjectionFactoryProvider {
    
    public Sequence sequence(SequentialDeployable deployable) {
        return Sequence.AFTER;
    }

    public List<DependencyInjectionFactory> getDependencyInjectionFactories(
            BeanletConfiguration<?> configuration) {
        List<DependencyInjectionFactory> factories = new ArrayList<DependencyInjectionFactory>();
        factories.add(new DependencyInjectionFactoryTerminator<Inject>(configuration,
                Inject.class) {
            @Override 
            public boolean isOptional(
                ElementAnnotation<? extends Element, Inject> ea) {
                return ea.getAnnotation().optional();
            }
        });
        return Collections.unmodifiableList(factories);
    }
}
