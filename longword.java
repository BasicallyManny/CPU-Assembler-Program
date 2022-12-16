public class longword implements ILongword {

    private bit arrayValue[]=new bit[32];

   public longword(bit[] result){
       for(int i=0; i<32; i++){
           arrayValue[i]= result[i];
       }
   }

   public longword(){
       for(int i=0; i<32; i++){
           arrayValue[i]= new bit(false);
       }
   }

   public bit getBit(int value){
       return new bit(arrayValue[value].getValue());    
   }

   
   public void setBit(int i, bit value) { 
       arrayValue[i] = value;
   }

   public longword and(longword other) {
       longword result=new longword(arrayValue);
       for(int i=0; i<32; i++){
         result.arrayValue[i]= this.arrayValue[i].and(other.arrayValue[i]);
       }

       return result;
   }

   
   public longword or(longword other) {

       longword result=new longword(arrayValue);
       for(int i=0; i<32; i++){
         result.arrayValue[i]= this.arrayValue[i].or(other.arrayValue[i]);
       }

       return result;
       
   }

   
   public longword xor(longword other) {

       longword result=new longword(arrayValue);
       for(int i=0; i<32; i++){
         result.arrayValue[i]= this.arrayValue[i].xor(other.arrayValue[i]);
       }

       return result;
   }

   
   public longword not() {

       longword result=new longword(arrayValue);
       for(int i=0; i<32; i++) {
           result.arrayValue[i]=result.arrayValue[i].not();
       }
       return result;
   }

   
   public longword rightShift(int amount) {
       longword tempArray=new longword();

       for(int i=amount, j = 0; i<32; i++, j++) {
           tempArray.arrayValue[i]= arrayValue[j];
       }

       return tempArray;
   }


   public longword leftShift(int amount) {
   
       longword tempArray=new longword();


       for(int i=amount, j = 0; i<32; i++, j++) {
           tempArray.arrayValue[j]= arrayValue[i];
       }

       return tempArray;
   
   }

   
   public String toString(){

       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < 32; i++) {
           sb.append(this.arrayValue[i]+",");
       }

       //removing the last comma.
       sb.deleteCharAt(sb.length()-1);

       return sb.toString();
   }

   
   public long getUnsigned() {

       long unSignedVar=0;
   
       for (int i = 0, j=32; i < 32; i++,j--) {
           if(arrayValue[i].getValue()){
               unSignedVar += Math.pow(2, j);
           }
       }
       return unSignedVar/2;
   }

   
   public int getSigned() {
       int signedVar=0;
       bit signedBit=getBit(0);

       if(!signedBit.getValue()){
           return signedVar=(int)getUnsigned();
       }else{
           longword tempArray=this.not();
           signedVar=(int)tempArray.getUnsigned();
       }
       return (signedVar+1)*-1;
   }

   
   public void copy(longword other) {

       for(int i = 0; i<32; i++) {
           arrayValue[i] = other.getBit(i);
       }
       
   }


   public void set(int value) {
       if(value<0){
           arrayValue[0].set();
           value*=-1;
           value--;
       }
       for(int i = 31; i>=0; i--) {
           if(value%2==1){
               if(getBit(0).getValue()){
                   arrayValue[i] = new bit(false);
               }
               else{
                   arrayValue[i] = new bit(true);
               }
           }
           else{
               if(getBit(0).getValue()){
                   arrayValue[i] = new bit(true);
               }
               else{
                   arrayValue[i] = new bit(false);
               }
           }
           value/=2;    
       }
   }
}