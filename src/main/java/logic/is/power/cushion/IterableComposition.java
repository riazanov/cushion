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

import java.util.*;

public class IterableComposition<C> implements Iterable<C> {

    public IterableComposition(Iterable<C> iterable1,Iterable<C> iterable2) {
	_iterable1 = iterable1;
	_iterable2 = iterable2;
    }

    public final Iterator<C> iterator() {
	return new IteratorComposition(_iterable1.iterator(),
				       _iterable2.iterator());
    }

    //               Data:

    private Iterable<C> _iterable1;

    private Iterable<C> _iterable2;
    
} // class IteratorComposition<C>