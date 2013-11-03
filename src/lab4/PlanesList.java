package lab4;

import lab3.Plane;

import java.util.*;

class PlanesList implements List<Plane> {

    /**
     * Pointer to first node.
     */
    Node first;

    /**
     * Pointer to last node.
     */
    Node last;

    /**
     * Size of this PlanesList object
     */
    int size;


    /**
     * Constructs an empty list.
     */
    public PlanesList() {

    }

    /**
     * Construct this list initialized with one element
     *
     * @param plane Single element contained in this list
     */
    public PlanesList(Plane plane) {
        add(plane);
    }

    /**
     * Initialize this PlaneList object with a copy of elements
     * of another collection
     *
     * @param c Collection-initializer of this PlaneList
     */
    public PlanesList(Collection<? extends Plane> c) {
        this();
        addAll(c);
    }

    @Override
    public boolean add(Plane plane) {
        linkLast(plane);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Plane> c) {
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
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object[] toArray() {
        return toArray(new Plane[size]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node x = first; x != null; x = x.next) {
            result[i] = x.item;
            i++;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
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
    public boolean retainAll(Collection<?> c) {
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
        for (Node x = first; x != null; ) {
            Node next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public Iterator<Plane> iterator() {
        return listIterator();
    }

    @Override
    public boolean addAll(int index, Collection<? extends Plane> c) {
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
            pred = succ.prev;
        }

        for (Plane plane : a) {
            Node newNode = new Node(pred, plane, null);
            if (pred == null) {
                first = newNode;
            } else {
                pred.next = newNode;
            }
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        return true;
    }

    @Override
    public Plane get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public Plane set(int index, Plane element) {
        checkElementIndex(index);
        Node x = node(index);
        Plane oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    @Override
    public void add(int index, Plane element) {
        checkPositionIndex(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    @Override
    public Plane remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null) {
                    return index;
                }
            }
        } else {
            for (Node x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item)) {
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
    public ListIterator<Plane> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

    @Override
    public PlanesList subList(int fromIndex, int toIndex) {
        PlanesList subList = new PlanesList();
        for (int index = fromIndex; index < toIndex; ++index) {
            subList.add(get(index));
        }
        return subList;
    }

    /**
     * Throws exception if {@code index} falls out of element index range
     * @param index Index to check bounds for
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * Throws exception if {@code index} falls out of element position range
     * @param index Index to check bounds for
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * Inserts element plane before non-null Node succ.
     */
    void linkBefore(Plane plane, Node succ) {
        // assert succ != null;
        final Node pred = succ.prev;
        final Node newNode = new Node(pred, plane, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    /**
     * Links plane as last element.
     */
    void linkLast(Plane plane) {
        final Node l = last;
        final Node newNode = new Node(l, plane, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node node(int index) {
        Node x = last;
        for (int i = size - 1; i > index; i--) {
            x = x.prev;
        }
        return x;
    }

    /**
     * Unlinks non-null node x.
     */
    Plane unlink(Node x) {
        // assert x != null;
        final Plane element = x.item;
        final Node next = x.next;
        final Node prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }


    /**
     * Node class represents a node in a doubly-linked list data structure
     * used by PlanesList
     */
    private static class Node {
        /**
         * Plane stored in this Node
         */
        Plane item;

        /**
         * Next node
         */
        Node next;

        /**
         * Previous node
         */
        Node prev;

        /**
         * Construct node with a link to previous Node,
         * element stored in this Node and a link to next node
         * @param prev Previous Node
         * @param element Item stored in this node
         * @param next Next Node
         */
        Node(Node prev, Plane element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * ListItr implements ListIterator for PlanesList
     */
    private class ListItr implements ListIterator<Plane> {
        /**
         * Last returned Node
         */
        private Node lastReturned = null;

        /**
         * Next node to return
         */
        private Node next;

        /**
         * Index of the next node
         */
        private int nextIndex;


        /**
         * Initialize this ListItr iterator with element starting at @{code index}
         * @param index Index of element iteration starts with
         */
        ListItr(int index) {
            next = (index == size) ? null : node(index);
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
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            Node lastNext = lastReturned.next;
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

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
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
        public void set(Plane plane) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.item = plane;
        }

        @Override
        public void add(Plane plane) {
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
