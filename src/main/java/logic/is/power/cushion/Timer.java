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


/** Simple timers based on {@link System#nanoTime()}. */
public class Timer {
    
    public Timer() {
	_isTicking = false;
	_elapsedMilliseconds = 0;
    }

    /** Checks if the timer is active, ie, {@link #start()} has been called
     *  and since then {@link #stop()} has not been called.
     */
    public boolean isTicking() { return _isTicking; }

    
    /** <b>post:</b> <code>!is_ticking() && elapsedMilliseconds() == 0</code> */
    public void reset() {
	_isTicking = false;
	_elapsedMilliseconds = 0;
    }

    /** <b>post:</b> <code>is_ticking()</code> */
    public void start() {
	if (!_isTicking) {
	    _isTicking = true;
	    _startTime = System.nanoTime()/1000000;
	};
    }

    /** <b>post:</b> <code>!is_ticking()</code> */
    public void stop() {
	
	if (_isTicking) {
	      
	    _isTicking = false;

	    long now = System.nanoTime()/1000000;
	      
	    assert now >= _startTime;

	    _elapsedMilliseconds += (now - _startTime);
		  
	};
    } // stop()

    /** Sum of lengths of time intervals during which 
     *  <code>is_ticking() == true</code>.
     *  The method can be called regardless of the current value of 
     *  <code>is_ticking()</code>.
     */
    public long elapsedMilliseconds() {

	if (_isTicking) {
	      
	    long now = System.nanoTime()/1000000;

	    assert now >= _startTime;

	    return 
		_elapsedMilliseconds + now - _startTime;
	}
	else
	    return _elapsedMilliseconds;

    } // elapsedMilliseconds()


    //                    Data:

    private boolean _isTicking;
      
    /** In milliseconds. */
    private long _startTime;

    private long _elapsedMilliseconds;
    
}; // class Timer 
