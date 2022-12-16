public class memory {

    bit[] memoryBits;

    public memory() {
        this.memoryBits = new bit[8192];
        for (int i = 0; i < 8192; i++) {
            memoryBits[i] = new bit();
        }
    }
    public longword read(longword address){
        if (address.getUnsigned() < 0) {
            throw new IndexOutOfBoundsException();
        }
        if(address.getUnsigned() > 1020){
            throw new IndexOutOfBoundsException();
        }

        longword result = new longword();

        for (int i = 0; i < 32; i++) {
            result.setBit(i, memoryBits[(int) ((address.getUnsigned() * 8) + i)]);
        }
        return result;
    }  

    public void write(longword address, longword value){
        if (address.getUnsigned() < 0) {
            throw new IndexOutOfBoundsException();
        }
        if(address.getUnsigned() > 1020){
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < 32; i++) {
            memoryBits[(int) ((address.getUnsigned() * 8) + i)] = value.getBit(i);
        }
    }

    //toString method to test
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Dumping All Memory...");
        for (int i = 0; i < 8192; i++) {
            sb.append(i+1+": "+memoryBits[i]);
        }
        return sb.toString();
    }
}
