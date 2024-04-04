package exam.base;

public class ToIterate<T> { // implements Iterable so that ToIterate can be iterated
    private final T[] internal;

    public static final <T> ToIterate<T> of(T ... ts) {
        T[] array = (T[]) new Object[ts.length];
        System.arraycopy(ts, 0, array, 0, ts.length);
        return new ToIterate(array);
    }

    private ToIterate(T[] ts) { internal = ts; }

}
