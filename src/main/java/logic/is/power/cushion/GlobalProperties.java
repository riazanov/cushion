/* Copyright (C) 2010 Alexandre Riazanov (Alexander Ryazanov)
 *
 * The copyright owner licenses this file to You under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package logic.is.power.cushion;

import java.util.*;


/** Debugging-oriented facility; Allows to maintain
 *  a globally visible dynamic set of polymorphic variables
 *  (a map from <code>String</code> to <code>Object</code>);
 *  Can be used for communicating ad hoc debugging information
 *  between modules;
 *  The class cannot have instances.
 */
public class GlobalProperties {


    public static boolean isEmpty() { return _map.isEmpty(); }

    /** Registers a property with the name <code>var</code> 
     *  and the given value.
     *  <b>pre:</b> <code>value</code> can be null
     */
    public static void register(String var,Object value) {
	_map.put(var,value);
    }


    /** Same as <code>register(var,null)</code>. */
    public static void register(String var) {
	_map.put(var,null);
    }

    /** Checks if <code>var</code> is registered. */
    public static boolean isRegistered(String var) {
	return _map.containsKey(var);
    }

    /** Checks if <code>var</code> is registered and, if so.
     *  returns the value throgh the reference.
     *  <b>pre:</b> <code>value != null</code>
     */
    public static boolean get(String var,Ref<Object> value) {
	if (_map.containsKey(var))
	    {
		value.content = _map.get(var);
		return true;
	    };
	return false;
    }
    
    /** Removes the property. */
    public static void remove(String var) {
	_map.remove(var);
    }


    private static HashMap<String,Object> _map = 
	new HashMap<String,Object>();

} // class GlobalProperties 