package semester3.lab4;

import semester3.lab3.Plane;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Colection which represents list of planes.
 */
public class PlanesList implements List<Plane> {

    /**
     * Pointer to first node.
     */
    private Node first;

    /**
     * Pointer to last node.
     */
    private Node last;

    /**
     * Size of this PlanesList object.
     */
    private int size;


    /**
     * Constructs an empty list.
     */
    public PlanesList() {

    }

    /**
     * Construct this list initialized with one element.
     *
     * @param plane Single element contained in this list
     */
    public PlanesList(final Plane plane) {
        add(plane);
    }

    /**
     * Initialize this PlaneList object with a copy of elements.
     * of another collection
     *
     * @param c Collection-initializer of this PlaneList
     */
    public PlanesList(final Collection<? extends Plane> c) {
        this();
        addAll(c);
    }

    @Override
    public boolean add(final Plane plane) {
        linkLast(plane);
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends Plane> c) {
        return addAll(size, c);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object[] toArray() {
        return toArray(new Plane[size]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(final T[] a) {
        Object[] result = a;
        if (a.length < size) {
            result = (Object[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;
        for (Node x = first; x != null; x = x.getNext()) {
            result[i] = x.getItem();
            i++;
        }

        if (a.length > size) {
            result[size] = null;
        }

        return (T[]) result;
    }

    @Override
    public boolean remove(final Object o) {
        if (o == null) {
            for (Node x = first; x != null; x = x.getNext()) {
                if (x.getItem() == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getItem())) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean modified = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        boolean modified = false;
        Iterator<Plane> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        Node x = first;
        while (x != null) {
            Node next = x.getNext();
            x.setItem(null);
            x.setNext(null);
            x.setPrev(null);
            x = next;
        }
        last = null;
        first = null;
        size = 0;
    }

    @Override
    public Iterator<Plane> iterator() {
        return listIterator();
    }

    @Override
    public boolean addAll(final int index,
                          final Collection<? extends Plane> c) {
        checkPositionIndex(index);

        Plane[] a = (Plane[]) c.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        }

        Node pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.getPrev();
        }

        for (Plane plane : a) {
            Node newNode = new Node(pred, plane, null);
            if (pred == null) {
                first = newNode;
            } else {
                pred.setNext(newNode);
            }
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.setNext(succ);
            succ.setPrev(pred);
        }

        size += numNew;
        return true;
    }

    @Override
    public Plane get(final int index) {
        checkElementIndex(index);
        return node(index).getItem();
    }

    @Override
    public Plane set(final int index, final Plane element) {
        checkElementIndex(index);
        Node x = node(index);
        Plane oldVal = x.getItem();
        x.setItem(element);
        return oldVal;
    }

    @Override
    public void add(final int index, final Plane element) {
        checkPositionIndex(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    @Override
    public Plane remove(final int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public int indexOf(final Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = first; x != null; x = x.getNext()) {
                if (x.getItem() == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getItem())) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(final Object o) {
        int index = size;
        if (o == null) {
            for (Node x = last; x != null; x = x.getPrev()) {
                index--;
                if (x.getItem() == null) {
                    return index;
                }
            }
        } else {
            for (Node x = last; x != null; x = x.getPrev()) {
                index--;
                if (o.equals(x.getItem())) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<Plane> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<Plane> listIterator(final int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

    @Override
    public PlanesList subList(final int fromIndex, final int toIndex) {
        PlanesList subList = new PlanesList();
        for (int index = fromIndex; index < toIndex; ++index) {
            subList.add(get(index));
        }
        return subList;
    }

    /**
     * Throws exception if {@code index} falls out of element index range.
     * @param index Index to check bounds for
     */
    private void checkElementIndex(final int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Tells if the argument is an index of existing element.
     * @param index Index to test
     * @return True if index is valid
     */
    private boolean isElementIndex(final int index) {
        return index >= 0 && index < size;
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * @param index Index of element
     * @return Constructed error message
     */
    private String outOfBoundsMsg(final int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * Throws exception if {@code index} falls out of element position range.
     * @param index Index to check bounds for
     */
    private void checkPositionIndex(final int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     * @param index Index to test
     * @return True if index is valid
     */
    private boolean isPositionIndex(final int index) {
        return index >= 0 && index <= size;
    }

    /**
     * Inserts element plane before non-null Node succ.
     * @param plane Plane to link at the end.
     * @param succ Successor plane
     */
    void linkBefore(final Plane plane, final Node succ) {
        final Node pred = succ.getPrev();
        final Node newNode = new Node(pred, plane, succ);
        succ.setPrev(newNode);
        if (pred == null) {
            first = newNode;
        } else {
            pred.setNext(newNode);
        }
        size++;
    }

    /**
     * Links plane as last element.
     * @param plane Plane to link at the and
     */
    void linkLast(final Plane plane) {
        final Node l = last;
        final Node newNode = new Node(l, plane, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.setNext(newNode);
        }
        size++;
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     * @param index Specifies Node index.
     * @return Node with index {@code index}
     */
    Node node(final int index) {
        Node x = first;
        for (int i = 0; i < index; i++) {
            x = x.getNext();
        }
        return x;
    }

    /**
     * Unlinks non-null node x.
     * @param x Node to unlink.
     * @return Item stored in unlinked Node
     */
    Plane unlink(final Node x) {
        final Plane element = x.getItem();
        final Node next = x.getNext();
        final Node prev = x.getPrev();

        if (prev == null) {
            first = next;
        } else {
            prev.setNext(next);
            x.setPrev(null);
        }

        if (next == null) {
            last = prev;
        } else {
            next.setPrev(prev);
            x.setNext(null);
        }

        x.setItem(null);
        size--;
        return element;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof List)) {
            return false;
        }

        ListIterator e1 = listIterator();
        ListIterator e2 = ((List) o).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            Object o1 = e1.next();
            Object o2 = e2.next();
            if (o1 == null) {
                if (!(o2 == null)) {
                    return false;
                }
            } else {
                if (!o1.equals(o2)) {
                    return false;
                }
            }
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Object e : this) {
            final int HASH_MULTIPLIER = 31;
            if (e == null) {
                hashCode = HASH_MULTIPLIER * hashCode;
            } else {
                hashCode = HASH_MULTIPLIER * hashCode + e.hashCode();
            }
        }
        return hashCode;
    }

    /**
     * Node class represents a node in a doubly-linked list data structure
     * used by PlanesList.
     */
    private static class Node {
        /**
         * Item stored in this Node.
         */
        private Plane item;

        /**
         * Next node.
         */
        private Node next;

        /**
         * Previous Node.
         */
        private Node prev;

        /**
         * Construct node with a link to previous Node,
         * element stored in this Node and a link to next node.
         * @param prev Previous Node
         * @param element Item stored in this node
         * @param next Next Node
         */
        Node(final Node prev, final Plane element, final Node next) {
            this.setItem(element);
            this.setNext(next);
            this.setPrev(prev);
        }

        /**
         *
         * @return Plane stored in this Node
         */
        public Plane getItem() {
            return item;
        }

        /**
         * Set plane stored in this Node.
         * @param item New plane stored in this Node
         */
        public void setItem(final Plane item) {
            this.item = item;
        }

        /**
         * @return Next node getter
         */
        public Node getNext() {
            return next;
        }

        /**
         * Next node setter.
         * @param next New next node
         */
        public void setNext(final Node next) {
            this.next = next;
        }

        /**
         * @return Previous node getter
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * Set previous node.
         * @param prev New previous node
         */
        public void setPrev(final Node prev) {
            this.prev = prev;
        }
    }

    /**
     * ListItr implements ListIterator for PlanesList.
     */
    private class ListItr implements ListIterator<Plane> {
        /**
         * Last returned Node.
         */
        private Node lastReturned = null;

        /**
         * Next node to return.
         */
        private Node next;

        /**
         * Index of the next node.
         */
        private int nextIndex;


        /**
         * Initialize this ListItr iterator with element
         * starting at @{code index}.
         * @param index Index of element iteration starts with
         */
        ListItr(final int index) {
            if (index == size) {
                next = null;
            } else {
                next = node(index);
            }
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Plane next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.getNext();
            nextIndex++;
            return lastReturned.getItem();
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            Node lastNext = lastReturned.getNext();
            unlink(lastReturned);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public Plane previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            if (next == null) {
                next = last;
                lastReturned = next;
            } else {
                next = next.getPrev();
                lastReturned = next;
            }
            nextIndex--;
            return lastReturned.getItem();
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void set(final Plane plane) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.setItem(plane);
        }

        @Override
        public void add(final Plane plane) {
            lastReturned = null;
            if (next == null) {
                linkLast(plane);
            } else {
                linkBefore(plane, next);
            }
            nextIndex++;
        }
    }
}
