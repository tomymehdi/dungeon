package saveLoadImplementation;

public interface Criteria<T> {
	boolean satisfies(T obj);
}
