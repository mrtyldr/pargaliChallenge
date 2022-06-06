package li.parga.pargalichallenge.core.utilities.results;

import lombok.EqualsAndHashCode;

public class SuccessDataResult<T> extends DataResult<T>{

    public SuccessDataResult(T data, String message) {
        super(data, true, message);
    }

    public SuccessDataResult( T data) {
        super(true, data);
    }

    public SuccessDataResult(String message){
        super(true,message);
    }
}
