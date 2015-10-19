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



/**
 *  Template for pairs.
 */
public class Pair<C1,C2> {
    
    /** @param x1 may be null
     *  @param x2 may be null
     */
    public Pair(C1 x1,C2 x2) {
	first = x1;
	second = x2;
    }
    
    public Pair() {
	first = null;
	second = null;
    }

    /** @param o != null */
    public boolean equals(Object o) {

	if (!(o instanceof Pair)) return false; 
	
	if (first == null)
	    {
		if (second == null)
		    {				       
			return
			    ((Pair)o).first == null &&
			    ((Pair)o).second == null;
		    }
		else			       
		    return
			((Pair)o).first == null &&
			((Pair)o).second != null &&
			second.equals(((Pair)o).second);	
	    }
	else if (second == null)
	    {		       
		return
		    ((Pair)o).first != null &&
		    ((Pair)o).second == null &&
		    first.equals(((Pair)o).first);	
	    }
	else				       
	    return
		((Pair)o).first != null &&
		((Pair)o).second != null &&
		first.equals(((Pair)o).first) &&
		second.equals(((Pair)o).second);
    } // equals(Object o)

    
    public int hashCode() {

	if (first == null)
	    {
		if (second == null)
		    {
			return 847293;
		    }
		else	
		    return 937254 + second.hashCode();
	    }
	else if (second == null)
	    {
		return first.hashCode() * 5 + 9524;
	    }
	else				       
	    return first.hashCode() * 5 + second.hashCode();

    } // hashCode()


    /** Shallow copy of the object. */
    public Object clone() {
	return new Pair(first,second);
    }


    //               Data:

    public C1 first;
    
    public C2 second;

} // class Pair<C1,C2>
