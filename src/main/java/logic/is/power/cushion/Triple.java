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
 *  Template for triples.
 */
public class Triple<C1,C2,C3> {

    /** @param x1 may be null
     *  @param x2 may be null
     *  @param x3 may be null
     */
    public Triple(C1 x1,C2 x2,C3 x3) {
	first = x1;
	second = x2;
	third = x3;
    }
    
    public Triple() {
	first = null;
	second = null;
	third = null;
    }

    /** @param o != null */
    public boolean equals(Object o) {
	
	if (!(o instanceof Triple)) return false; 
	
	if (first == null)
	    {
		if (second == null)
		    {	       
			if (third == null)
			    {
				return
				    ((Triple)o).first == null &&
				    ((Triple)o).second == null &&
				    ((Triple)o).third == null;
			    }
			else	       
			    return
				((Triple)o).first == null &&
				((Triple)o).second == null &&
				((Triple)o).third != null &&
				third.equals(((Triple)o).third);
			
		    }
		else if (third == null)				       
		    {       
			return
			    ((Triple)o).first == null &&
			    ((Triple)o).second != null &&
			    ((Triple)o).third == null &&
			    second.equals(((Triple)o).second);			
		    }
		else 		       
		    return
			((Triple)o).first == null &&
			((Triple)o).second != null &&
			((Triple)o).third != null &&
			second.equals(((Triple)o).second) &&
			third.equals(((Triple)o).third);
		
	    }
	else if (second == null)
	    {
		if (third == null)				       
		    {
			return
			    ((Triple)o).first != null &&
			    ((Triple)o).second == null &&
			    ((Triple)o).third == null &&
			    first.equals(((Triple)o).first);
		    }
		else		       
		    return
			((Triple)o).first != null &&
			((Triple)o).second == null &&
			((Triple)o).third != null &&
			first.equals(((Triple)o).first) &&
			third.equals(((Triple)o).third);
	    }
	else if (third == null)				       
	    {	       
		return
		    ((Triple)o).first != null &&
		    ((Triple)o).second != null &&
		    ((Triple)o).third == null &&
		    first.equals(((Triple)o).first) &&
		    second.equals(((Triple)o).second);
	    }
	else 			       
	    return
		((Triple)o).first != null &&
		((Triple)o).second != null &&
		((Triple)o).third != null &&
		first.equals(((Triple)o).first) &&
		second.equals(((Triple)o).second) &&
		third.equals(((Triple)o).third);

    } // equals(Object o)




    public int hashCode() {
	
	if (first == null)
	    {
		if (second == null)
		    {	       
			if (third == null)
			    {
				return 763850;
			    }
			else	       
			    return 7593002 + third.hashCode();
		    }
		else if (third == null)				       
		    {      
			return 7733994 + second.hashCode();
		    }
		else 	
		    return
			6794926 + 
			second.hashCode() * 5 + 
			third.hashCode();
	    }
	else if (second == null)
	    {
		if (third == null)				       
		    {
			return
			    first.hashCode() * 25 + 6378052;
		    }
		else	
		    return
			first.hashCode() * 25 + 
			7374894 +
			third.hashCode();
	    }
	else if (third == null)				       
	    {	       
		return
		    first.hashCode() * 25 + 
		    second.hashCode() * 5 + 
		    76362004;
	    }
	else 			       
	    return
		first.hashCode() * 25 + 
		second.hashCode() * 5 + 
		third.hashCode();

    } // hashCode()


    //               Data:

    public C1 first;
    
    public C2 second;

    public C3 third;

} // class Triple<C1,C2,C3>
