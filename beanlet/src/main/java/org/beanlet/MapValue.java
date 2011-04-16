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
package org.beanlet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;


/**
 * Represents a map of key/value entries.
 *
 * @see org.beanlet.Entry
 * @see org.beanlet.Inject
 * @author Leon van Zantvoort
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface MapValue {

    /**
     * Specifies the map type that is to be constructed. This value must
     * be set to a concrete class, or to the {@code Map} interface, in
     * which case the map type is inferred from the member to be 
     * injected.
     */
    Class<? extends Map> type() default Map.class;
    
    /**
     * Contains all map entries.
     */
    Entry[] value() default {};

    /**
     * {@code true} if annotation represents an empty map.
     */
    boolean empty() default false;

    /**
     * {@code true} if the map is unmodifiable after injection.
     */
    boolean unmodifiable() default false;
    
    /**
     * {@code true} if the map is synchronized.
     */
    boolean synced() default false;
}
