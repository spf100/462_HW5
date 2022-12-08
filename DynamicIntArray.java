import java.util.NoSuchElementException;

class DynamicIntArray  {
  private int[] array;
  private int front;
  private int length;
  
  public DynamicIntArray() {
	array = new int[16];
  }

  public int length() {
    return length;
  }

  public boolean isEmpty() {
    return length == 0;
  }

  public void prepend(int x) {
	if (length == array.length)
	  resize();
	front = getIndex(front - 1);
	array[front] = x;
	length++;
  }

  public void append(int x) {
	if (length == array.length)
		  resize();
	int end = getIndex(front + length);
	array[end] = x;
	length++;
  }

  public int getFirst() {
    if (length == 0) {
      throw new NoSuchElementException();
    }
    return array[front];
  }
  
  public int getLast() {
    if (length == 0) {
      throw new NoSuchElementException();
    }
    return array[getIndex(front + length -1)];
  }

  public void removeFirst() {
    if (length == 0) {
      throw new NoSuchElementException();
    }
    front = getIndex(front + 1);
    length--;
  }
  
  public void removeLast() {
    if (length == 0) {
      throw new NoSuchElementException();
    }
    length--;
  }
  
  private void resize() {
	int[] biggerArray = new int[array.length * 2];
	
	for (int i = 0; i < length; i++) {
		biggerArray[i] = array[getIndex(front + i)];
	}
	array = biggerArray;
	front = 0;
  }
  
  private int getIndex(int x) {
	  x = x % array.length;
	  
	  if (x < 0) {
		  x += array.length;
	  }
	  return x;
  }
}