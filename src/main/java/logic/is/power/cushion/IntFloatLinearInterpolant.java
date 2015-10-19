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

/** Linear interpolation of finite discreet mappings from <code>int</code>
 *  to <code>float</code>; also represents a simple extrapolant:
 *  all values smaller than the smallest <code>x</code> in 
 *  the discreet mapping are mapped to the same value as that <code>x</code>;
 *  likewise, all values greater than the greatest <code>x</code> in 
 *  the discreet mapping are mapped to the same value as that <code>x</code>.
 */
public class IntFloatLinearInterpolant
    implements UnaryFunctionObject<Integer,Float> {
    
    /** There is no discreet mapping at all; the constructed 
     *  interpolant maps everything to 0. 
     */
    public IntFloatLinearInterpolant() {
	_discreetMap = new TreeMap<Integer,Float>();
    }

    /** <b>post:</b> there is no discreet mapping at all;
     *          the object maps everything to 0. 
     */
    public final void clear() {
	_discreetMap.clear();
    }

    /** Adds the pair <code>x -> y</code> to the underlying discreet
     *  mapping; if <code>x</code> already has an associated value,
     *  that value is overriden.
     */
    public final void put(Integer x,Float y) {
	_discreetMap.put(x,y);
    }

    /** Adds the pair <code>x -> y</code> to the underlying discreet
     *  mapping; if <code>x</code> already has an associated value,
     *  that value is overriden.
     */
    public final void put(int x,float y) {
	put(new Integer(x),new Float(y));
    } 

    /** Computes the interpolant on <code>x</code>. */
    public final float map(int x) {
	return map(new Integer(x)).floatValue();
    }

    /** Computes the interpolant on <code>x</code>. */
    public final Float map(Integer x) {
	
	if (_discreetMap.isEmpty()) 
		return new Float(0);
		
	Map.Entry<Integer,Float> ceiling = 
	    _discreetMap.ceilingEntry(x);

	// Extrapolation:
	if (ceiling == null) 
	    return _discreetMap.lastEntry().getValue();

	if (ceiling.getKey().equals(x))
	    return ceiling.getValue();

	Map.Entry<Integer,Float> floor = 
	    _discreetMap.floorEntry(x);
	
	// Extrapolation:
	if (floor == null)
	    return _discreetMap.firstEntry().getValue();
	    
	if (floor.getKey().equals(x))
	    return floor.getValue();
	
	assert ceiling.getKey() > floor.getKey();
	
	Float result =
	    floor.getValue() + 
	    (((x - floor.getKey()) * 
	      (ceiling.getValue() - floor.getValue()))/
	     (ceiling.getKey() - floor.getKey()));


	return result;
	
    } // map(Integer x)


    /** Computes the interpolant on <code>x</code>. */
    public final Float evaluate(Integer x) {
	return map(x);
    }

    public final IntFloatLinearInterpolant clone() {
	IntFloatLinearInterpolant result =
	    new IntFloatLinearInterpolant();
	
	result._discreetMap = 
	    (TreeMap<Integer,Float>)_discreetMap.clone();

	assert _discreetMap.isEmpty() == result._discreetMap.isEmpty();


	return result; 
    }

    //                 Data:
    
    private TreeMap<Integer,Float> _discreetMap;

} // class IntFloatLinearInterpolant