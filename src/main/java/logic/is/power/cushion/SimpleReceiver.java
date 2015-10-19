
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
   * that can, in some sense, receive values of the type <code>T</code>.\n
   *
   * In particular, this abstraction can be useful for communicating results
   * of functions that are intended to compute (enumerate) sequences of
   * values of indefinite size. For example, suppose we need a function
   * that enumerates all even numbers between 2 and <code>n > 0</code>. 
   * The function can be written as follows:\n\n
   * <code>
   * void foo(int n, SimpleReceiver<Integer> output) {\n
   *   for (int i = 2; i <= n; i = i + 2)\n
   *     output.receive(i);\n
   * }\n\n
   * </code>
   * Client codes must define implementations of 
   * <code>SimpleReceiver<Integer></code>
   * that do what is necessary with the elements of the computed sequences.\n
   * 
   * In general, the <code>SimpleReceiver</code> abstraction can be useful 
   * for defining nterfaces for classes and functions that have to communicate
   * by sending or receiving data.\n
   *
   * The applications of <code>SimpleReceiver</code> naturally overlap with 
   * the applications of output iterators. However, in many cases the use
   * of <code>SimpleReceiver</code> can easily cope with tasks that are 
   * difficult for output iterators.
   * 
   * See also {@link cushion_je.SimpleSender}.
   */
public interface SimpleReceiver<T> {


    /** Must try to somehow accomodate <code>val</code>; the specific 
     *  semantics is up to the implementations.
     *  @return <code>false</code> if the value cannot be accomodated.
     */
    public boolean receive(T val);

}; // interface SimpleReceiver<T>

