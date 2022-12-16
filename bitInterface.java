public interface bitInterface {
    public void set(boolean value);
    public void toggle();
    public void clear();
    public void set();
    public boolean getValue();
    public bit and(bit other);
    public bit or(bit other);
    public bit xor(bit other);
    public bit not();
    public String toString();
}