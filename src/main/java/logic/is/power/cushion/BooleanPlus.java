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


/** Three-valued logic; instances of this class represent
 *  three values: <code>true</code>, <code>false</code> and
 *  <code>undefined</code>. Use this with care: in most cases
 *  it is better to use java.lang.Boolean for defined values 
 *  and null for undefined values. 
 */
public class BooleanPlus 
    implements Comparable<BooleanPlus> {

    /** Creates an object representing <code>undefined</code>.*/
    public BooleanPlus() {
	_isDefined = false;
    }

    /** Creates an object representing <code>true</code>
     *  or <code>false</code>, depending on <code>value</code>.
     */
    public BooleanPlus(boolean value) {
	_isDefined = true;
	_value = value;
    }

    /** Creates an object representing <code>true</code>
     *  or <code>false</code>, depending on <code>value</code>.
     */
    public BooleanPlus(Boolean value) {
	_isDefined = true;
	_value = value.booleanValue();
    }
    
    /** Creates an object representing <code>true</code>
     *  if <code>value.equalsIgnoreCase("true")</code>,
     *  <code>false</code>
     *  if <code>value.equalsIgnoreCase("false")</code>,
     *  and <code>undefined</code> in all other cases.
     */
    public BooleanPlus(String value) {
	if (value.equalsIgnoreCase("true")) {
	    _isDefined = true;
	    _value = true;
	}
	else if (value.equalsIgnoreCase("false")) {
	    _isDefined = true;
	    _value = false;
	}
	else _isDefined = false;
    }

    public final boolean isDefined() {
	return _isDefined;
    }

    public final boolean isUndefined() {
	return !_isDefined;
    }

    /** @throws java.lang.Error if the value is undefined. */
    public final boolean booleanValue() {
	if (_isDefined) return _value;
	throw new java.lang.Error("Undefined boolean value requested.");
    }

    public int compareTo(BooleanPlus b) {
	if (_isDefined == b._isDefined)
	    {
		if (_value == b._value) return 0;
		return (_value)? 1 : -1; 
	    }
	else 
	    return (_isDefined)? 1 : -1; 
    }

    public int hashCode() {
	if (_isDefined)
	    {
		return (_value)? 2 : 3;
	    }
	else return 1;
    }

    public String toString() {
	if (_isDefined)
	    {
		return (_value)? "true" : "false";
	    }
	else return "undefined";
    }


    public static final BooleanPlus TRUE = new BooleanPlus(true);

    public static final BooleanPlus FALSE = new BooleanPlus(false);

    public static final BooleanPlus UNDEFINED = new BooleanPlus();



    private boolean _isDefined;

    private boolean _value;


} // class BooleanPlus