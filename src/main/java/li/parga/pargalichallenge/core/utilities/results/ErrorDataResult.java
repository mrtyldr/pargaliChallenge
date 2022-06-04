package li.parga.pargalichallenge.core.utilities.results;

public class ErrorDataResult <T> extends DataResult<T>{
    public ErrorDataResult(T data, String message) {
        super(data, false, message);
    }

    public ErrorDataResult(String message){
        super(null,false,message);
    }

    public ErrorDataResult(boolean success, T data) {
        super(false, data);
    }

    public ErrorDataResult(){
        super(false,null);
    }
}
