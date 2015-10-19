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


public class ListUtil {
    
    
    public static <T> LinkedList<T> linkedList(T... elements) {
	LinkedList<T> result = new LinkedList<T>();
	
	for (T el : elements)
	    result.add(el);

	return result;
    }
    
    public static <T> LinkedList<T> linkedList(Iterable<T> elements) {
	LinkedList<T> result = new LinkedList<T>();

	for (T el : elements)
	    result.add(el);

	return result;
    }
   
    
    public 
	static 
	<T> 
	LinkedList<T> linkedList(Iterable<T> elements,T... moreElements) {

	LinkedList<T> result = new LinkedList<T>();
	
	for (T el : elements)
	    result.add(el);
	for (T el : moreElements)
	    result.add(el);

	return result;
    }

    public 
	static 
	<T> 
	LinkedList<T> linkedList(Iterable<T> elements1,
				 Iterable<T> elements2) {

	LinkedList<T> result = new LinkedList<T>();

	for (T el : elements1)
	    result.add(el);
	for (T el : elements2)
	    result.add(el);

	return result;
    }
   
   
    public 
	static 
	<T> 
	LinkedList<T> linkedList(Iterable<T> elements1,
				 Iterable<T> elements2,
				 T... moreElements) {

	LinkedList<T> result = new LinkedList<T>();

	for (T el : elements1)
	    result.add(el);
	for (T el : elements2)
	    result.add(el);
	for (T el : moreElements)
	    result.add(el);

	return result;
    }
   


} // class ListUtil