public class multiplier {

    public static longword multiply(longword a, longword b) {
        //new longword object
         longword product = new longword();
         //iteratre backwards throught the longword
         for(int i=31;i>-1;i--){
            //add to product when the iteration is true and left shift
            if(b.getBit(i).getValue()==true){
                product=rippleAdder.add(product,a.leftShift(31-i));
            }
         }
         return product;
    }
       
}
