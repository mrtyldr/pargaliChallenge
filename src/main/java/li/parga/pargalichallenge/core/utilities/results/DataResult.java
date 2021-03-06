package li.parga.pargalichallenge.core.utilities.results;

import lombok.EqualsAndHashCode;


public class DataResult<T> extends Result{
    private T data;

    public DataResult(T data, boolean success, String message) {
        super(success, message);
        this.data = data;
    }

    public DataResult(boolean success, T data) {
        super(success);
        this.data = data;
    }
    public DataResult(boolean success,String message){
        super(success,message);
    }

    public T getData() {
        return data;
    }
}
