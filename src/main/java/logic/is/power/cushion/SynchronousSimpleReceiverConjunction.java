/* Copyright (C) 2011 Alexandre Riazanov (Alexander Ryazanov)
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
 *  2-hub for simple receivers that combines the boolean results
 *  of receiving by conjunction; it differs from SimpleReceiverConjunction
 *  as follows: if the first receiver accepts an item, the conjunction 
 *  will keep sending the item until it succeds (yielding between such attempts).
 */
public class SynchronousSimpleReceiverConjunction<T> implements SimpleReceiver<T> {

    /** Creates a receiver that sends received data first to <code>rec1</code>,
     *  and then to <code>rec2</code>; if <code>rec2</code> does not accept
     *  the item, this object will keep trying until it succeeds. 
     *  <b>pre:</b> <code>rec1</code> != null && rec2 != null</code>
     */
    public SynchronousSimpleReceiverConjunction(SimpleReceiver<T> rec1,
						SimpleReceiver<T> rec2) {
	assert rec1 != null;
	assert rec2 != null;
	_rec1 = rec1;
	_rec2 = rec2;
    }

    /** Calls the <code>receive</code> method on <code>rec1</code>;
     *  then, if it succeeds, it calls <code>receive</code> on 
     *  <code>rec2</code>; if this fails, it will keep trying and
     *  calling Thread.yield() between the attempts.
     */
    public boolean receive(T val) {
	if (!_rec1.receive(val)) return false;
	while (!_rec2.receive(val)) Thread.yield();
	return true;
    }

    
    //                       Data:
    
    private SimpleReceiver<T> _rec1;
      
    private SimpleReceiver<T> _rec2;

  }; // class SynchronousSimpleReceiverConjunction<T>
