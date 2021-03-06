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

/**
 * Thrown if the specified beanlet has not yet been activated.
 *
 * @author Leon van Zantvoort
 */
public class BeanletNotActiveException extends BeanletCreationException {
    
    private static final long serialVersionUID = 1934972363486535312L;
    
    public BeanletNotActiveException(String beanletName) {
        super(beanletName, "Beanlet has not yet been activated. " +
                "Beanlet activation order can be altered by specifying " +
                "the correct dependencies.");
    }

    public BeanletNotActiveException(String beanletName, Throwable cause) {
        super(beanletName, "Beanlet has not yet been activated. " +
                "Beanlet activation order can be altered by specifying " +
                "the correct dependencies.", cause);
    }
}
