public class rippleAdder {
    public static longword add(longword a, longword b){
        bit carry = new bit(false);
        longword sum=new longword();
    

        for(int i=31;i>-1;i--){
            //finding the sum value
            sum.setBit(i, (a.getBit(i).xor(b.getBit(i))).xor(carry));
            //ripple the carry
            carry = (a.getBit(i).and(b.getBit(i))).or((a.getBit(i).xor(b.getBit(i))).and(carry));
        }
        return sum;
    }
    
    public static longword subtract(longword a, longword b){
        //create new output longword object and set it equal to b
        longword diff=b;
        //create temporary longword
        longword temp=new longword();
        //set super implementation to 1
        //negate it
        temp.set(1);
        diff=diff.not();

        //add the negated value to find the difference
        return add(a, add(diff, temp));
    }

}
