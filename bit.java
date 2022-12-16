public class bit implements bitInterface{
    //bit representation
    private boolean status;

    /**
     * Constructor
     * @param result
     */

    public bit(){
        this.status=false;
     }
    public bit(boolean result){
        this.status = result;
    }

    /**
     * Sets the value of the bit
     * @param value
     */
    public void set(boolean value){
        this.status=value;
    }

    /**
     * Toggles the value of the bit
     */
    public void toggle(){
        //if bit is true then set it to false
        if(status==true){
            this.status=false;
        }
        //else set bit to false
        else{
            this.status=true;
        }
    }
    /**
     * Sets the value of the bit to false
     */
    public void clear(){
        this.status=false;
    }
    /**
     * Sets the value of the bit to true
     */
    public void set(){
        this.status=true;
    }
    /**
     * @returns the value of the bit
     */
    public boolean getValue(){
        //if bit is true then return true
        if(status==true){
            return true;
        }
        //else return false
        else{
            return false;
        }
    }

    /**
     * Performs the "and" operation on 2 bits
     * @param other
     * @returns the result of the "and" operation in a new bit
     */
    public bit and(bit other){
        //if bit and other are both equal to true then return true
        if(other.status){
            if(status){
               other= new bit(true);
            }
            else{
                other= new bit(false);
            }
        }
        //else return false
        else{
            other= new bit(false);
        }
        return other;
        
         
    }

    /**
     * performs the "or" operation on two bits
     * @returns the result of the "or" operation in a new bit
     */
    public bit or(bit other){
        //if one of the bits are equal to true then the new bit will be true
        if(other.status==true){
            return new bit(true);
        }
        //if bit and other are both equal to false then return true
        else if(status==true){
            return new bit(true);
        }
        //else return false
        else return new bit(false);
        
    }
    /**
     * Performs the exclusice or operation on 2 bits
     * @param other
     * @returns the "exclusive or" result of the operation in a new bit
     */
    public bit xor(bit other){
        //if bit is not equal to other then return true
        if(other.status!=status){
            return new bit(true);
        }
        //else return false
        else return new bit(false);
    
    }
    /**
     * Performs the not/negation operation on a bit
     * @returns the result of the negation in a new bit
     */
    public bit not(){
        //if bit is true
        if(status==true){
            //return a new bit as false
            return new bit(false);
        }
        //else return true
        else return new bit(true);
    }

    /**
     * toString method
     * @returns "t" if bit is true or "f" if bit is false
     */
    @Override
    public String toString(){
        //if bit is true
        if(status==true){
            //return "t"
            return "t";
        }
        //else return "f"
        else{
            return "f";
        }
    }
}