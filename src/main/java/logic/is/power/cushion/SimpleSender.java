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
 * Common interface for various classes
 * that can supply some values of the type <code>T</code> on request; such
 * classes can also be considered as lazy computations of sequences
 * of values of the type <code>T</code>.\n
 *
 * In particular, this abstraction can be useful for supplying
 * functions with lazily computed indefinite size sequences of values.
 * For example, suppose we need a function that searches in a lazily
 * computed sequence of integers for a number greater than <code>n</code>.
 * The function can be defined as follows:\n
 * <code>
 * bool foo(int n, SimpleSender<Integer> seq) {\n
 *   Ref<Integer> x = new Ref<Integer>();\n
 *   while (seq.send(x))\n
 *     if (x.content > n) return true;\n
 *   return false;\n
 * }\n
 * </code>
 * It is up to client codes how to compute the sequences given to 
 * the function, as long as the objects computing the sequences 
 * inherit from <code>SimpleSender<Integer></code> and define its virtual
 * functions according to the required semantics.
 *
 * The applications of <code>SimpleSender</code> strongly overlap with
 * the applicatios of input iterators. However, in many cases the use
 * of <code>SimpleSender</code> allows much more succint codes.
 *
 * See also {@link cushion_je.SimpleReceiver}.
 */
public interface SimpleSender<T> {

    /** Must try to generate the next value in the current sequence
     *  and assign it to <code>var.content</code>. 
     *  @return false to indicate the end of the current sequence;
     *          this does not imply that all subsequent calls 
     *          to <code>send(Ref<T>)</code> will fail: other sequences 
     *          may be computed later with the same object
     */
    public boolean send(Ref<T> var);

}; // interface SimpleSender<T>
