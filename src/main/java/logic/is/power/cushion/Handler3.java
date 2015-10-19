
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
   * Common interface for various handlers of triples of data items.
   * See also {@link cushion_je.SimpleReceiver}.
   */
public interface Handler3<T1,T2,T3> {

    /** Handles the data item pair <code>(val1,val2,val3)</code>; the specific 
     *  semantics is up to the implementations.
     */
    public void handle(T1 val1,T2 val2,T3 val3);

}; // interface Handler3<T1,T2,T3> 