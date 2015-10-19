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


/** Represents a unary predicate that checks equality ("equals", not "==")
 *  to a predefined object of the class <code>Domain</code>.  
 */
public class Equals<Domain>
    implements UnaryPredicateObject<Domain> {

    public Equals(Domain obj) {
	_obj = obj;
    }
    
    public boolean evaluate(Domain x) {
	return _obj.equals(x);
    }

    private Domain _obj;

}; // class Equals<Domain>
