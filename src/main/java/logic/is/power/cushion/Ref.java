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
 *  Template for reference-like objects that facilitate 
 *  pass-by-reference in Java.
 */
public class Ref<C> {

    /** Creates a "reference" whose content is <code>c</code>. */
    public Ref(C c) { content = c; }
    
    /** Same as <code>Ref(null)</code>. */
    public Ref() { content = null; }

    /** Current content of the reference; read it or assign it. */
    public C content;

} // class Ref<C>
