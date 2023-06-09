public class Parameter<T, S> {
    private T field1;
    private S field2;

    public Parameter(T field1, S field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public Parameter(){

    }

    public T getField1() {
        return field1;
    }
    public void setField1(T field1) {
        this.field1 = field1;
    }
    public S getField2() {
        return field2;
    }
    public void setField2(S field2) {
        this.field2 = field2;
    }
    
}
