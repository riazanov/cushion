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
public class Quadruple<C1,C2,C3,C4> {

    /** @param x1 may be null
     *  @param x2 may be null
     *  @param x3 may be null
     *  @param x4 may be null
     */
    public Quadruple(C1 x1,C2 x2,C3 x3,C4 x4) {
	first = x1;
	second = x2;
	third = x3;
	fourth = x4;
    }
    
    public Quadruple() {
	first = null;
	second = null;
	third = null;
	fourth = null;
    }

    /** @param o != null */
    public boolean equals(Object o) {
	
	if (!(o instanceof Quadruple)) return false; 
	
	if (first == null)
	    {
		// 0
		if (second == null)
		    {	       
			// 0,0
			if (third == null)
			    {
				// 0,0,0
				if (fourth == null)
				    {
					// 0,0,0,0
					return
					    ((Quadruple)o).first == null &&
					    ((Quadruple)o).second == null &&
					    ((Quadruple)o).third == null &&
					    ((Quadruple)o).fourth == null;
				    }
				else
				    // 0,0,0,1
				    return
					((Quadruple)o).first == null &&
					((Quadruple)o).second == null &&
					((Quadruple)o).third == null &&
					((Quadruple)o).fourth != null &&
					fourth.equals(((Quadruple)o).fourth);


			    }
			else if (fourth == null)
			    {
				// 0,0,1,0
				return
				    ((Quadruple)o).first == null &&
				    ((Quadruple)o).second == null &&
				    ((Quadruple)o).third != null &&
				    ((Quadruple)o).fourth == null &&
				    third.equals(((Quadruple)o).third);
				
			    }
			else	 
			    // 0,0,1,1

			    return
				((Quadruple)o).first == null &&
				((Quadruple)o).second == null &&
				((Quadruple)o).third != null &&
				((Quadruple)o).fourth != null &&
				third.equals(((Quadruple)o).third) &&
				fourth.equals(((Quadruple)o).fourth);
			
		    }
		else if (third == null)				       
		    {       
			// 0,1,0
			if (fourth == null)
			    {
				// 0,1,0,0
				return
				    ((Quadruple)o).first == null &&
				    ((Quadruple)o).second != null &&
				    ((Quadruple)o).third == null &&
				    ((Quadruple)o).fourth == null &&
				    second.equals(((Quadruple)o).second);	
			    }
			else
			    // 0,1,0,1
			    return
				((Quadruple)o).first == null &&
				((Quadruple)o).second != null &&
				((Quadruple)o).third == null &&
				((Quadruple)o).fourth != null &&
				second.equals(((Quadruple)o).second) &&
				fourth.equals(((Quadruple)o).fourth);

		    }
		else if (fourth == null)
		    {
			// 0,1,1,0
			return
			    ((Quadruple)o).first == null &&
			    ((Quadruple)o).second != null &&
			    ((Quadruple)o).third != null &&
			    ((Quadruple)o).fourth == null &&
			    second.equals(((Quadruple)o).second) &&
			    third.equals(((Quadruple)o).third);
			
		    }
		else		       
		    // 0,1,1,1
		    return
			((Quadruple)o).first == null &&
			((Quadruple)o).second != null &&
			((Quadruple)o).third != null &&
			((Quadruple)o).fourth != null &&
			second.equals(((Quadruple)o).second) &&
			third.equals(((Quadruple)o).third) &&
			fourth.equals(((Quadruple)o).fourth);
		
	    }
	else if (second == null)
	    {
		// 1,0

		if (third == null)				       
		    {
			// 1,0,0
			if (fourth == null)
			    {
				// 1,0,0,0
				return
				    ((Quadruple)o).first != null &&
				    ((Quadruple)o).second == null &&
				    ((Quadruple)o).third == null &&
				    ((Quadruple)o).fourth == null &&
				    first.equals(((Quadruple)o).first);
				
			    }
			else
			    // 1,0,0,1
			    return
				((Quadruple)o).first != null &&
				((Quadruple)o).second == null &&
				((Quadruple)o).third == null &&
				((Quadruple)o).fourth != null &&
				first.equals(((Quadruple)o).first) &&
				fourth.equals(((Quadruple)o).fourth);

		    }
		else if (fourth == null)
		    {
			// 1,0,1,0
			return
			    ((Quadruple)o).first != null &&
			    ((Quadruple)o).second == null &&
			    ((Quadruple)o).third != null &&
			    ((Quadruple)o).fourth == null &&
			    first.equals(((Quadruple)o).first) &&
			    third.equals(((Quadruple)o).third);
			
		    }
		else	       
		    // 1,0,1,1
		    return
			((Quadruple)o).first != null &&
			((Quadruple)o).second == null &&
			((Quadruple)o).third != null &&
			((Quadruple)o).fourth != null &&
			first.equals(((Quadruple)o).first) &&
			third.equals(((Quadruple)o).third) &&
			fourth.equals(((Quadruple)o).fourth);

	    }
	else if (third == null)				       
	    {	      
		// 1,1,0

		if (fourth == null)
		    {
			// 1,1,0,0
			return
			    ((Quadruple)o).first != null &&
			    ((Quadruple)o).second != null &&
			    ((Quadruple)o).third == null &&
			    ((Quadruple)o).fourth == null &&
			    first.equals(((Quadruple)o).first) &&
			    second.equals(((Quadruple)o).second);	
			
		    }
		else
		    // 1,1,0,1
		    return
			((Quadruple)o).first != null &&
			((Quadruple)o).second != null &&
			((Quadruple)o).third == null &&
			((Quadruple)o).fourth != null &&
			first.equals(((Quadruple)o).first) &&
			second.equals(((Quadruple)o).second) &&
			fourth.equals(((Quadruple)o).fourth);

	    }
	else if (fourth == null)
	    {
		// 1,1,1,0
		return
		    ((Quadruple)o).first != null &&
		    ((Quadruple)o).second != null &&
		    ((Quadruple)o).third != null &&
		    ((Quadruple)o).fourth == null &&
		    first.equals(((Quadruple)o).first) &&
		    second.equals(((Quadruple)o).second) &&
		    third.equals(((Quadruple)o).third);
	    }
	else
	    // 1,1,1,1
	    return
		((Quadruple)o).first != null &&
		((Quadruple)o).second != null &&
		((Quadruple)o).third != null &&
		((Quadruple)o).fourth != null &&
		first.equals(((Quadruple)o).first) &&
		second.equals(((Quadruple)o).second) &&
		third.equals(((Quadruple)o).third) &&
		fourth.equals(((Quadruple)o).fourth);

    } // equals(Object o)




    public int hashCode() {
	

	if (first == null)
	    {
		// 0
		if (second == null)
		    {	       
			// 0,0
			if (third == null)
			    {
				// 0,0,0
				if (fourth == null)
				    {
					// 0,0,0,0
					return 75739;
				    }
				else
				    // 0,0,0,1
				    return 74782 + fourth.hashCode();


			    }
			else if (fourth == null)
			    {
				// 0,0,1,0
				return 957272 + third.hashCode() * 3;
			    }
			else	 
			    // 0,0,1,1

			    return
				963365 +
				third.hashCode() * 3 + 
				fourth.hashCode();
			
		    }
		else if (third == null)				       
		    {       
			// 0,1,0
			if (fourth == null)
			    {
				// 0,1,0,0
				return	
				    5652801 + 
				    second.hashCode() * 9;
			    }
			else
			    // 0,1,0,1
			    return
				662945 +
				second.hashCode() * 9 + 
				fourth.hashCode();

		    }
		else if (fourth == null)
		    {
			// 0,1,1,0
			return
			    673850 +
			    second.hashCode() * 9 + 
			    third.hashCode() * 3;
			
		    }
		else		       
		    // 0,1,1,1
		    return
			728506 +
			second.hashCode() * 9 + 
			third.hashCode() * 3 + 
			fourth.hashCode();
	    }
	else if (second == null)
	    {
		// 1,0

		if (third == null)				       
		    {
			// 1,0,0
			if (fourth == null)
			    {
				// 1,0,0,0
				return 759923 + first.hashCode() * 18;
				
			    }
			else
			    // 1,0,0,1
			    return
				789305 +
				first.hashCode() * 18 + 
				fourth.hashCode();

		    }
		else if (fourth == null)
		    {
			// 1,0,1,0
			return
			    9903524 +
			    first.hashCode() * 18 + 
			    third.hashCode() * 3;
			
		    }
		else	       
		    // 1,0,1,1
		    return
			6284956 +
			first.hashCode() * 18 + 
			third.hashCode() * 3 + 
			fourth.hashCode();

	    }
	else if (third == null)				       
	    {	      
		// 1,1,0

		if (fourth == null)
		    {
			// 1,1,0,0
			return	
			    9936385 +
			    first.hashCode() * 18 + 
			    second.hashCode() * 9;
		    }
		else
		    // 1,1,0,1
		    return
			628953 +
			first.hashCode() * 18 + 
			second.hashCode() * 9 + 
			fourth.hashCode();

	    }
	else if (fourth == null)
	    {
		// 1,1,1,0
		return
		    284950 +
		    first.hashCode() * 18 + 
		    second.hashCode() * 9 + 
		    third.hashCode() * 3;
	    }
	else
	    // 1,1,1,1
	    return
		first.hashCode() * 18 + 
		second.hashCode() * 9 + 
		third.hashCode() * 3 + 
		fourth.hashCode();

    } // hashCode()


    //               Data:

    public C1 first;
    
    public C2 second;

    public C3 third;

    public C4 fourth;

} // class Quadruple<C1,C2,C3,C4>
