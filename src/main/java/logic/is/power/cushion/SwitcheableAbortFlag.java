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
 * Flags (local, but switcheable and accessible as global objects) that 
 * can be used to indicate that the compliant execution should be
 * aborted by throwing a special exception.
 */
public class SwitcheableAbortFlag {

    public class Exception extends Throwable { // Not static!
	
	public SwitcheableAbortFlag flagObject() {
	    return outerObject();
	}

	private Exception() {
	}
		
    }; // class Exception



    
    /** @param info user-provided data that is associated with the object
     *         and is later accessible with the {@link #info()} method
     *  <b>post:</b> the flag is clear 
     */
    public SwitcheableAbortFlag(Object info) {
	_abortFlag = false;
	_info = info;
    }
    

    /** Same as SwitcheableAbortFlag(null). */
    public SwitcheableAbortFlag() {
	_abortFlag = false;
	_info = null;
    }
    

    
    /** <b>post:</b> the flag is clear */
    public final void clear() { _abortFlag = false; }

    /** <b>post:</b> the flag is set */
    public final void set() { _abortFlag = true; }

    /** Checks the flag and throws a 
     *  {@link cushion_je.SwitcheableAbortFlag.Exception}
     *  if the flag is set.
     */
    public final void check() throws Exception {
      if (_abortFlag) throw new Exception();
    }

    /** User-provided info associated with the object. */
    public final Object info() { return _info; }

    //      Making it switcheable:

    public static SwitcheableAbortFlag current() {
      return _current;
    }
    
    public static void makeCurrent(SwitcheableAbortFlag f) {
      _current = f;
    }


    //              Private methods:

    private final SwitcheableAbortFlag outerObject() {
	return this;
    }


    //              Data:

    private static SwitcheableAbortFlag _current = null;

    private boolean _abortFlag;

    private Object _info;

}; // class SwitcheableAbortFlag
