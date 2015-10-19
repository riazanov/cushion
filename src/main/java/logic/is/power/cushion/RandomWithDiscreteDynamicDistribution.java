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


import java.util.Random;

/** Generator of random numbers according to a discrete distribution
 *  that can be adjusted dynamically.
 */
public class RandomWithDiscreteDynamicDistribution {

    /** <b>post:</b> all potentially generated numbers have (unnormalised 
     *  relative) probability 1 and are unavailable for selection by
     *  {@link #generate()}.
     *  @param maxValue > 0 maximal value that can be generated with this
     *         object, ie, the range of the values will be <code>[0,maxValue]</code>
     */
    public RandomWithDiscreteDynamicDistribution(int maxValue,
						 long seed) {
	_maxValue = maxValue;
	_uniformGenerator = new Random(seed);
	_numberOfAvailableValues = 0;

	_leaves = new Leaf[maxValue + 1];
	
	_tree = createTree(0,maxValue,null);
	
    } // RandomWithDiscreteDynamicDistribution(long maxValue,long seed)

    
    /** Selects one of the available values from the range; the chances of 
     *  a particular value <code>n</code> to be selected are proportional 
     *  to <code>relativeProbabilityOf(n)</code>.
     *  @return -1 if no values are available
     */
    public int generate() {
	
	if (_numberOfAvailableValues == 0) return -1;

	TreeNode currentNode = _tree;

	long randomCut = _uniformGenerator.nextLong();
	// Has to be normalised.
	
	long upperBound = _tree.probabilitySum();
	assert upperBound > 0;
	if (randomCut < 0) randomCut = -1 * randomCut;
	randomCut = randomCut % upperBound;

	// Now 0 <= randomCut < upperBound.
	assert 0 <= randomCut;
	assert randomCut < upperBound;

	assert !currentNode.isLeaf();

	do
	{
	    if (randomCut < 
		((IntermediateNode)currentNode).leftProbability())
	    {
		// Look for the required value in the left subtree:
		
		currentNode = ((IntermediateNode)currentNode).leftSubtree();

		// Here randomCut already cuts through the sum of 
		// probabilities in the left subtree, so no adjustment
		// to  randomCut is needed.
	    }
	    else
	    {
		// Look for the required value in the right subtree:

		// Now randomCut has to cut through the sum of 
		// probabilities in the right subtree:
		randomCut = 
		    randomCut - 
		    ((IntermediateNode)currentNode).leftProbability();

		assert 
		    randomCut < 
		    ((IntermediateNode)currentNode).rightProbability();

		currentNode = ((IntermediateNode)currentNode).rightSubtree();
	    };
	}
	while (!currentNode.isLeaf());
	       
	// This is because only probabilities of available values
	// contribute to the sums of probabilities in the intermediate nodes.
	assert ((Leaf)currentNode).isAvailable();
	
	return ((Leaf)currentNode).value();

    } // generate()


    //                  Current distribution:

    /** Checks if <code>n</code> is available for selection by 
     *  {@link #generate()}.
     *  <b>pre:<b> <code>n</code> is in the range
     */
    public boolean isAvailable(int n) {
	assert n >= 0;
	assert n <= _maxValue;
	return _leaves[n].isAvailable();
    }

    /** The relative probability of <code>n</code> in the current distribution. 
     *  <b>pre:<b> <code>n</code> is in the range
     */
    public long relativeProbabilityOf(int n) {
	assert n >= 0;
	assert n <= _maxValue;
	return _leaves[n].probability();
    }
  
    //                  Adjusting the distribution:

    /** Makes the value <code>n</code> available for selection by 
     *  {@link #generate()}.
     *  <b>pre:<b> <code>n</code> is in the range
     */
    public void makeAvailable(int n) {
	assert n >= 0;
	assert n <= _maxValue;
	
	Leaf leaf = _leaves[n];

	if (!leaf.isAvailable())
	{
	    ++_numberOfAvailableValues;
	    assert _numberOfAvailableValues <= _maxValue + 1;
	    leaf.makeAvailable();
	    recomputeProbabilitiesInBranch(leaf);
	};

    } // makeAvailable(int n) 

    /** Makes the value <code>n</code> unavailable for selection by 
     *  {@link #generate()}.
     *  <b>pre:<b> <code>n</code> is in the range
     */
    public void makeUnavailable(int n) {
	assert n >= 0;
	assert n <= _maxValue;
	
	Leaf leaf = _leaves[n];

	if (leaf.isAvailable())
	{
	    assert _numberOfAvailableValues > 0;
	    --_numberOfAvailableValues;
	    leaf.makeUnavailable();
	    recomputeProbabilitiesInBranch(leaf);
	};
    } // makeUnavailable(int n)



    /** Adjusts the current ditribution so that 
     *  <code>relativeProbabilityOf(n) == prob</code>;
     *  does not change availability of any elements unless <code>prob == 0</code>,
     *  in which case <code>n</code> becomes unavailable.
     *  <b>pre:<b> <code>n</code> is in the range of generated values
     *  <b>pre:<b> <code>prob >= 0</code> 
     */
    public void setRelativeProbability(int n,long prob) {
	assert n >= 0;
	assert n <= _maxValue;
	assert prob >= 0;

	Leaf leaf = _leaves[n];
	
	leaf.setProbability(prob);
	
	if (prob == 0)
	{
	    makeUnavailable(n);
	}
	else if (leaf.isAvailable())
	{
	    recomputeProbabilitiesInBranch(leaf);
	};

	
    } // setRelativeProbability(int n,long probability)




    //                                Private types:

    
    /** Common base for leaves and intermediate nodes of the binary
     *  tree intended to facilitate fast execution of {@link #generate()}.
     */
    private static class TreeNode {
	
	/** @param leaf
	 *  @param parentNode should be the parent node of <code>this</code>
	 *         or <code>null</code> if this node is a root
	 */
	public TreeNode(boolean leaf,IntermediateNode parentNode) {
	    _isLeaf = leaf;
	    _parentNode = parentNode;
	}

	public boolean isLeaf() { return _isLeaf; }

	/** Parent of this node.
	 *  @return <code>null</code> if this node is a root
	 */
	public IntermediateNode parentNode() { return _parentNode; }


	/** The sum of probabilities of all leaves underneath this node 
	 *  with values available for selection by {@link #generate()}. 
	 */
	public long probabilitySum() {
	    if (_isLeaf) 
	    {
		if (((Leaf)this).isAvailable())
		{
		    return ((Leaf)this).probability();
		}
		else
		    return 0;
	    }
	    else
		return ((IntermediateNode)this).probabilitySum();
	} // probabilitySum()


	//                               Data:
	
	private boolean _isLeaf;

	private IntermediateNode _parentNode;

    }; // class TreeNode



    /** Intermediate nodes in the binary
     *  tree intended to facilitate fast execution of {@link #generate()}.
     */
    private static class IntermediateNode extends TreeNode {
	
	public IntermediateNode(IntermediateNode parentNode) {
	    super(false,parentNode);
	    _probabilitySum = 0;
	    _leftProbability = 0;
	    _rightProbability = 0;
	}
	
	public long probabilitySum() { return _probabilitySum; }

	public TreeNode leftSubtree() { return _leftSubtree; }

	public TreeNode rightSubtree() { return _rightSubtree; }
	
	public long leftProbability() { return _leftProbability; }
	
	public long rightProbability() { return _rightProbability; }

	public void attachLeftSubtree(TreeNode subtree) {
	    _leftSubtree = subtree;
	}

	public void attachRightSubtree(TreeNode subtree) {
	    _rightSubtree = subtree;
	}
	
	public void setLeftProbability(long leftProb) {
	    _leftProbability = leftProb;
	    _probabilitySum = _leftProbability + _rightProbability;
	}

	public void setRightProbability(long rightProb) {
	    _rightProbability = rightProb;
	    _probabilitySum = _leftProbability + _rightProbability;
	}


	//                               Data:
	
	private TreeNode _leftSubtree;
	
	private TreeNode _rightSubtree;
	
	/** Keeps the sum <code> _leftProbability + _rightProbability</code>.*/
	private long _probabilitySum;

	/** Keeps the value <code>_leftSubtree.probabilitySum()</code>
	 *  for fast access.
	 */
	private long _leftProbability;
	
	/** Keeps the value <code>_rightSubtree.probabilitySum()</code>
	 *  for fast access.
	 */
	private long _rightProbability;
	
	
    }; // class IntermediateNode
    

    private static class Leaf extends TreeNode {
	
	/** <b>post:</b> <code>!isAvailable() && 
	 *   value() == value && probability() == 1</code>.
	 */
	public Leaf(int value,IntermediateNode parentNode) {
	    super(true,parentNode);
	    _isAvailable = false;
	    _value = value;
	    _probability = 1;
	}

	public boolean isAvailable() { return _isAvailable; }
	
	public void makeAvailable() { _isAvailable = true; }

	public void makeUnavailable() { _isAvailable = false; }
	
	public int value() { return _value; }

	public long probability() { return _probability; }
	
	/** <b>pre:</b> <code>prob >= 0</code>. */
	public void setProbability(long prob) {
	    assert prob >= 0;
	    _probability = prob;
	}

	//                         Data:
	
	private boolean _isAvailable;

	private int _value;

	private long _probability;

    }; // class Leaf
    

    //                                Private methods:

    /** Creates a binary tree to cover the specified range of values. 
     *  <b>pre:</b> <code> minVal <= maxVal</code>.
     */
    private TreeNode createTree(int minVal,int maxVal,IntermediateNode parent) {
    
	assert 0 <= minVal;
	assert minVal <= maxVal;
	assert maxVal <= _maxValue;

	if (minVal == maxVal) 
	{
	    Leaf result = new Leaf(minVal,parent);
	    _leaves[minVal] = result;	    
	    return result;
	};
	
	// Intermediate node:

	int middle = minVal + (maxVal - minVal)/2;

	IntermediateNode result = new IntermediateNode(parent);

	TreeNode leftSubtree = createTree(minVal,middle,result);
	
	TreeNode rightSubtree = createTree(middle + 1,maxVal,result);

	result.attachLeftSubtree(leftSubtree);

	result.attachRightSubtree(rightSubtree);
	
	return result;

    } // createTree(int minVal,int maxVal,IntermediateNode parent)

    /** Adjusts the probability values in all nodes of the branch
     *  ending in the specified leaf (in which the probability is already OK).
     */
    private static void recomputeProbabilitiesInBranch(Leaf branchEnd) {
	
	assert branchEnd.isAvailable() || branchEnd.probabilitySum() == 0;
	
	TreeNode previousNode = branchEnd;

	assert branchEnd.parentNode() != null;

	for (IntermediateNode currentNode = branchEnd.parentNode();
	     currentNode != null;
	     currentNode = currentNode.parentNode())
	{
	    if (currentNode.leftSubtree() == previousNode)
	    {
		currentNode.setLeftProbability(previousNode.probabilitySum());
	    }
	    else
	    {
		assert currentNode.rightSubtree() == previousNode;
		currentNode.setRightProbability(previousNode.probabilitySum());
	    };
  
	    previousNode = currentNode;
	};
	
    } // recomputeProbabilitiesInBranch(Leaf branchEnd)


    //                                Data:

    private int _maxValue;
    
    private Random _uniformGenerator;

    private int _numberOfAvailableValues;

    /** Binary tree intended to facilitate fast execution of 
     *  {@link #generate()}.
     */
    private TreeNode _tree;

    /** Maps integer values to the corresponding leaves. */
    private Leaf[] _leaves;

}; // class RandomWithDiscreteDynamicDistribution
