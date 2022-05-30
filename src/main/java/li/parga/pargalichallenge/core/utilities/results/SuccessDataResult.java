package li.parga.pargalichallenge.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T>{

    public SuccessDataResult(T data, String message) {
        super(data, true, message);
    }

    public SuccessDataResult( T data) {
        super(true, data);
    }

    public SuccessDataResult(){
        super(true,null);
    }
}
