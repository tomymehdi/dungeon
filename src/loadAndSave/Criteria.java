package loadAndSave;

public interface Criteria<T> {
	boolean satisfies(T obj);
}
