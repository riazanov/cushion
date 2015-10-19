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



import java.lang.reflect.*;

/**
 * Implements a simple queue of restricted capacity, which acts as
 * a {@link cushion_je.SimpleReceiver}
 * to push data, and as a {@link cushion_je.SimpleSender} to pop data.
 * @param T type of objects stored in the queue
 */
public class SimpleRestrictedQueue<T> 
    implements SimpleReceiver<T>, SimpleSender<T>
{
    /** @throws java.lang.IllegalArgumentException if 
     *  <code>capacity < 0</code> 
     */
    public SimpleRestrictedQueue(int capacity) {
	if (capacity < 0)
	    throw new IllegalArgumentException("Negative queue capacity"); 
	_contents = new java.lang.Object[capacity];
	_first = -1;
	if (capacity == 0)
	    {
		_end = -1; 
	    }
	else
	    _end = 0;
    }
    
    
    public final void clear() {
	for (int i = 0; i < _contents.length; ++i)
	    _contents[i] = null;
	_first = -1;
	if (_contents.length == 0)
	    {
		_end = -1; 
	    }
	else
	    _end = 0;	
    } // clear()
    
    
    public final boolean isEmpty() { 
	return _first == -1;	    
    }

    /** Pushes <code>val</code> on the back of the queue. 
     *  @return false if no space left for new elements
     */
    public boolean receive(T val) { 

	if (_end == _first)
	    // covers the case of zero capacity
	    return false;
	
	assert _end < _contents.length;

	_contents[_end] = val;
	if (_first == -1) _first = _end; // the only item
	++_end;
	
	if (_end == _contents.length)		
	    _end = 0;

	return true;
    } // receive(T val)


    /** Pops from the front of the queue after copying the popped
     *  value in <code>var</code>.
     *  @return false if the queue is empty 
     */
    public boolean send(Ref<T> var) {
	if (isEmpty()) return false;
	var.content = (T)_contents[_first];
	++_first;				

	if (_first == _contents.length)
	    _first = 0;

	if (_first == _end) 
	    _first = -1; // now empty

	return true;
    }


    public int size() {
	if (_end == _first || // covers the case of zero capacity
	    _first == -1)     // empty
	    return 0;

	if (_end > _first)
	    {
		return _end - _first;
	    }
	else
	    {
		return _end + (_contents.length - _first);
	    }
    }

    public String toString() {
	return _contents.toString();
    }
    
    //                     Data:

    // We cannot have just T[] because Java doesn't support arrays of generics
    private final java.lang.Object[] _contents;

    private int _first;				
    
    private int _end;

}; // class SimpleRestrictedQueue<T> 
