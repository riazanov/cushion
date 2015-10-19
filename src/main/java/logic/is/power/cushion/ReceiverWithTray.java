
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
   * Communication abstraction based on the following protocol:
   * a sender requests a tray by calling getTray(); then it attaches
   * a data item to the tray by Tray.put(); then it can "close" the tray
   * by Tray.close(), in which case the data item is completely accomodated
   * by the receiver, or "release" it by Tray.cancel(), making the tray 
   * unusable in both cases.
   */
public interface ReceiverWithTray<T> {

    public interface Tray<T> {

	public void put(T val); 

	public void close();
	
	public void cancel();
	
    }; // interface Tray

    /** @return null if the receiver is not ready to accept 
     *          another data item
     */
    public Tray getTray();
     

}; // interface ReceiverWithTray<T>

