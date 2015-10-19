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

public class IteratorComposition<C> implements Iterator<C> {

    public IteratorComposition(Iterator<C> iter1,Iterator<C> iter2) {
	_currentIter = iter1;
	_nextIter = iter2;
    }

    public final boolean hasNext() {
	if (_currentIter.hasNext()) return true;
	if (_nextIter == null) return false;
	_currentIter = _nextIter;
	_nextIter = null;
	return _currentIter.hasNext();
    }

    public final C next() {
	try
	    {
		return _currentIter.next();
	    }
	catch (NoSuchElementException ex)
	    {
		if (_nextIter == null) 
		    throw new NoSuchElementException("No more elements: " + ex);
		_currentIter = _nextIter;
		_nextIter = null;
		return _currentIter.next();
	    }
    }

    public final void remove() {
	_currentIter.remove();
    }


    //               Data:

    private Iterator<C> _currentIter;

    private Iterator<C> _nextIter;
    
} // class IteratorComposition<C>