package ArrayStack;
/**
 * 
 */

/**
 * @author Dale / Joyce / Weems
 * 
 * Interface for a class that implements a stack of Objects.
 * A stack is a last in, first out structure.
 *
 */
public interface StackInterface {
	/**
	 * Removes the top element from this stack.
	 * 
	 * @throws StackUnderflowException if this stack is empty.
	 */
	void pop() throws StackUnderflowException;
	/**
	 * Returns the top element from this stack.  Causes a StackUnderflowException if the stack is empty.
	 * @return top element from this stack.
	 * @throws StackUnderflowException if the stack is empty.
	 */
	Object top() throws StackUnderflowException;
	/**
	 * Push an element onto this stack.  If there is no room for the element on
	 * this stack a StackOverflowException is thrown.
	 * @param element the element to place on the top of this stack.
	 * @throws StackOverflowException  if the stack is full.
	 */
	void push(Object element) throws StackOverflowException;
	/**
	 * Test if this stack is full.
	 * @return true if the stack is full, false otherwise.
	 */
	boolean isFull();
	/**
	 * Test if this stack is empty.
	 * @return true if the stack is empty, false otherwise.
	 */
	boolean isEmpty();

}
