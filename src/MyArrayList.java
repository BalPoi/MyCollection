import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class MyArrayList<E> {
    private Object[] elements;
    private int arraySize;

    public MyArrayList() {
        this.elements = new Object[]{};
        this.arraySize = 0;
    }

    public MyArrayList(MyArrayList<? extends E> c) {
        this.elements = c.getElements();
        this.arraySize = c.size();
    }

    private Object[] getElements() {
        return elements;
    }

    private void resizeArray(int minCapacity) {
        int oldCapacity = elements.length;

        int newCapacity = (oldCapacity == 0) ? 1 : oldCapacity;
        if (minCapacity > oldCapacity) {
            while (newCapacity < minCapacity) {
                newCapacity = newCapacity << 1;
            }
        } else if (minCapacity < oldCapacity) {
            while (newCapacity > minCapacity) {
                newCapacity = newCapacity >> 1;
            }
        }

        Object[] temp = new Object[newCapacity];
        System.arraycopy(elements, 0, temp, 0, arraySize);
        elements = temp;
    }

    public boolean add(E e) {
        add(arraySize, e);
        return true;
    }
    public void add(int index, E element) {
        Objects.checkIndex(index, arraySize+1);
        if (arraySize == elements.length) resizeArray(arraySize + 1);
        if (index < arraySize)
            System.arraycopy(elements, index, elements, index+1, arraySize-index);
        elements[index] = element;
        arraySize++;
    }

    public boolean addAll(MyArrayList<? extends E> c) {
        return addAll(arraySize, c);
    }
    public boolean addAll(int index, MyArrayList<? extends E> c) {
        Objects.checkIndex(index, arraySize+1);
        Object[] arr = c.toArray();
        int sizeDelta = c.size();
        if (sizeDelta == 0) return false;
        if (arraySize + sizeDelta > elements.length) resizeArray(arraySize + sizeDelta);
        int numMoved = arraySize - index;
        if (arraySize != 0)
            System.arraycopy(elements, arraySize-numMoved, elements, arraySize+sizeDelta, sizeDelta);
        System.arraycopy(arr, 0, elements, arraySize, sizeDelta);
        arraySize += sizeDelta;
        return true;
    }

    public E get(int index) {
        Objects.checkIndex(index, arraySize);
        return (E) elements[index];
    }

    public E remove(int index) {
        Objects.checkIndex(index, arraySize);
        Object deletedObj = elements[index];
        for (int i = index+1; i < arraySize; i++) {
            elements[i-1] = elements[i];
        }
        elements[arraySize-1] = null;
        resizeArray(--arraySize);
        return (E) deletedObj;
    }
    public boolean remove(Object o) {
        for (int i = 0; i < arraySize; i++) {
            if (elements[i].equals(o))
                remove(i);
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < arraySize; i++) {
            elements[i] = null;
        }
        arraySize = 0;
        resizeArray(0);
    }

    public int size() {
        return arraySize;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements, arraySize);
    }

    public void bubbleSort(Comparator<? super E> comparator) {
        if (arraySize < 2) return;
        E temp;
        boolean isSorted;
        for (int i = 0; i < arraySize; i++){
            isSorted = true;
            for (int j = 1; j < (arraySize - i); j++){
                if (comparator.compare( (E)elements[j-1], (E)elements[j] ) > 0){
                    isSorted = false;
                    temp = (E)elements[j-1];
                    elements[j-1] = elements[j];
                    elements[j] = temp;
                }
            }
            if (isSorted) return;
        }
    }

    @Override
    public String toString() {
        return "MyArrayList(Size:%d; Capacity:%d) %s".formatted(arraySize, elements.length, Arrays.toString(elements));
    }
}
