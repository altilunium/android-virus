package p007io.reactivex.internal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: io.reactivex.internal.util.VolatileSizeArrayList */
public final class VolatileSizeArrayList extends AtomicInteger implements List, RandomAccess {
    private static final long serialVersionUID = 3972397474470203923L;
    final ArrayList list;

    public VolatileSizeArrayList() {
        this.list = new ArrayList();
    }

    public VolatileSizeArrayList(int initialCapacity) {
        this.list = new ArrayList(initialCapacity);
    }

    public int size() {
        return get();
    }

    public boolean isEmpty() {
        return get() == 0;
    }

    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return this.list.iterator();
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        return this.list.toArray(objArr);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        boolean b = this.list.add(obj);
        lazySet(this.list.size());
        return b;
    }

    @Override // java.util.List
    public boolean remove(Object o) {
        boolean b = this.list.remove(o);
        lazySet(this.list.size());
        return b;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection collection) {
        return this.list.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection collection) {
        boolean b = this.list.addAll(collection);
        lazySet(this.list.size());
        return b;
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection collection) {
        boolean b = this.list.addAll(index, collection);
        lazySet(this.list.size());
        return b;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection collection) {
        boolean b = this.list.removeAll(collection);
        lazySet(this.list.size());
        return b;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection collection) {
        boolean b = this.list.retainAll(collection);
        lazySet(this.list.size());
        return b;
    }

    public void clear() {
        this.list.clear();
        lazySet(0);
    }

    @Override // java.util.List
    public Object get(int index) {
        return this.list.get(index);
    }

    @Override // java.util.List
    public Object set(int index, Object obj) {
        return this.list.set(index, obj);
    }

    @Override // java.util.List
    public void add(int index, Object obj) {
        this.list.add(index, obj);
        lazySet(this.list.size());
    }

    @Override // java.util.List
    public Object remove(int index) {
        Object remove = this.list.remove(index);
        lazySet(this.list.size());
        return remove;
    }

    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return this.list.listIterator();
    }

    @Override // java.util.List
    public ListIterator listIterator(int index) {
        return this.list.listIterator(index);
    }

    @Override // java.util.List
    public List subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }

    public boolean equals(Object obj) {
        if (obj instanceof VolatileSizeArrayList) {
            return this.list.equals(((VolatileSizeArrayList) obj).list);
        }
        return this.list.equals(obj);
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    public String toString() {
        return this.list.toString();
    }
}
