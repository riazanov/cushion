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

public class MultipleIteratorComposition<C> implements Iterator<C> {

    
    public MultipleIteratorComposition(Iterable<Iterator<C>> iterators) {

	_iteratorOverIterators = iterators.iterator();

	_currentIter = null;
	

    } // MultipleIteratorComposition(Iterable<Iterator<C>> iterators)
    



    public 
	MultipleIteratorComposition(Iterator<Iterator<C>> iteratorOverIterators) {
	_iteratorOverIterators = iteratorOverIterators;

	_currentIter = null;

    } // MultipleIteratorComposition(Iterator<Iterator<C>> iteratorOverIterators)

    public final boolean hasNext() {
	
	if (_currentIter == null)
	    if (_iteratorOverIterators.hasNext())
		{
		    _currentIter = _iteratorOverIterators.next();
		}
	    else return false;

	
	while (!_currentIter.hasNext())
	    {
		if (_iteratorOverIterators.hasNext())
		    {
			_currentIter = _iteratorOverIterators.next();
		    }
		else return false;		
	    };
	
	return true;

    } // hasNext()
    


    public final C next() {

	if (_currentIter == null)
	    if (_iteratorOverIterators.hasNext())
		{
		    _currentIter = _iteratorOverIterators.next();
		}
	    else 
		throw new NoSuchElementException("No elements at all.");		

	while (true)
	    {
		try
		    {
			return _currentIter.next();
		    }
		catch (NoSuchElementException ex)
		    {
			if (_iteratorOverIterators.hasNext())
			    {
				_currentIter = _iteratorOverIterators.next();
			    }
			else 
			    throw new NoSuchElementException("No more elements: " + ex);			
		    }
	    }

    } // next() 


    public final void remove() {

	if (_currentIter == null)
	    throw new IllegalStateException("next() has not been called.");	    
	_currentIter.remove();
    }




    //               Data:
    
    
    private final Iterator<Iterator<C>> _iteratorOverIterators;

    private Iterator<C> _currentIter;

} // class MultipleIteratorComposition<C>