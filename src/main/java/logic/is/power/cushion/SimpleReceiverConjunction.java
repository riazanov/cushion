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
 *  2-hub for simple receivers that combines the boolean results
 *  of receiving by conjunction.
 */
public class SimpleReceiverConjunction<T> implements SimpleReceiver<T> {

    /** Creates a receiver that sends received data first to <code>rec1</code>,
     *  and then to <code>rec2</code>.
     *  <b>pre:</b> <code>rec1</code> != null && rec2 != null</code>
     */
    public SimpleReceiverConjunction(SimpleReceiver<T> rec1,
				     SimpleReceiver<T> rec2) {
	assert rec1 != null;
	assert rec2 != null;
	_rec1 = rec1;
	_rec2 = rec2;
    }

    /** Calls the <code>receive</code> method  on both receivers and combines
     *  the boolean results with conjunction.
     *  Note that even if the first receiver fails to receive <code>val</code>,
     *  <code>receive(val)</code> is called on the second receiver.
     */
    public boolean receive(T val) {
	boolean res1 = _rec1.receive(val);
	boolean res2 = _rec2.receive(val);
	return res1 && res2;
    }

    
    //                       Data:
    
    private SimpleReceiver<T> _rec1;
      
    private SimpleReceiver<T> _rec2;

  }; // class SimpleReceiverConjunction<T>
