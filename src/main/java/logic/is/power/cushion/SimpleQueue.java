
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


import java.util.LinkedList;

/**
 * Implements a simple queue which acts as a {@link cushion_je.SimpleReceiver}
 * to push data, and as a {@link cushion_je.SimpleSender} to pop data.
 * The underlying container is <code>java.util.LinkedList<T></code> and it is 
 * directly accessible.
 * @param T type of objects stored in the queue
 */
public class SimpleQueue<T> 
    implements SimpleReceiver<T>, SimpleSender<T>
{
    public SimpleQueue() {
	_contents = new LinkedList<T>();
    }
    
    /** The underlying <code>LinkedList<T></code> object. */
    public final LinkedList<T> contents() { return _contents; }
    
    public final boolean isEmpty() { return _contents.isEmpty(); }

    /** Pushes <code>val</code> on the back of the queue. 
     *  @return true (always)
     */
    public boolean receive(T val) { 
	_contents.addLast(val);
	return true;
    }

    /** Pops from the front of the queue after copying the popped
     *  value in <code>var</code>.
     *  @return false if the queue is empty 
     */
    public boolean send(Ref<T> var) {
	if (isEmpty()) return false;
	var.content = _contents.removeFirst();
	return true;
    }

    /** Empties the queue. */
    public void empty() {
	_contents = new LinkedList<T>();
    }

    public String toString() {
	return _contents.toString();
    }
    
    //                     Data:

    private LinkedList<T> _contents;

}; // class SimpleQueue<T>
