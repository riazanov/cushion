
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

/** A SimpleReceiver that always accomodates the element bein passed. */
public abstract class FaultlessSimpleReceiver<T> implements SimpleReceiver<T> {

    
    /**  @return <code>true</code> always. */
    public boolean receive(T val) {
	accommodate(val);
	return true;
    }

    protected abstract void accommodate(T val);

} // class FaultlessSimpleReceiver<T>