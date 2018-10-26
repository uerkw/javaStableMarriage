package ArrayStack;
/**
 * 
 */

/**
 * @author gheurin
 *
 */
public class ArrayStack implements StackInterface {

	protected int top;
	protected Object stack[];
	
	/**
	 * Constructor -- size indicates the size of stack that is allocated.  
	 * Once completed the stack has been allocated and is empty.  
	 * 
	 * @param size The size of stack to allocate for this object. 
	 */
	public ArrayStack(int size) {
		stack = new Object[size];
		top = -1;
	}
	/**
	 * Default constructor.  Uses a default size of 10 for the stack.
	 */
	public ArrayStack() {
		// TODO Auto-generated constructor stub
		this(15000);
	}

	/* (non-Javadoc)
	 * @see StackInterface#pop()
	 */
	@Override
	public void pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException("User tried to pop an empty stack.");
		}
		stack[top] = null;
		top--;
	}

	/* (non-Javadoc)
	 * @see StackInterface#top()
	 */
	@Override
	public Object top() throws StackUnderflowException {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new StackUnderflowException("User tried to examine the top of an empty stack.");
		}
		
		return stack[top];
	}

	/* (non-Javadoc)
	 * @see StackInterface#push(java.lang.Object)
	 */
	@Override
	public void push(Object element) throws StackOverflowException {
		// TODO Auto-generated method stub
		if (isFull()) {
			throw new StackOverflowException("User tried to push an element onto a full stack.");
		}
		top++;
		stack[top] = element;
		return;
	}

	/* (non-Javadoc)
	 * @see StackInterface#isFull()
	 */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		if (top == stack.length-1) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see StackInterface#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (top == -1) {
			return true;
		}
		return false;
	}

}
