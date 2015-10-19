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



/** Debugging-oriented facility; Allows to maintain
 *  a globally visible counter for approximate timestamping;
 *  The class cannot have instances.
 */
public class GlobalEventCounter {

    /** Increments the counter; always returns true - this 
     *  allows to use it in asserts so that such call do not
     *  affect performance in the non-debugging mode.
     */
    public static boolean inc() {
	++_value;
	return true;
    }

    /** The current value of the counter. */
    public static int value() {
	return _value;
    }



    private static int _value = 0; 

} // class GlobalEventCounter