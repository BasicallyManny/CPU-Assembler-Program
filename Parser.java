public class Parser {
    private String bitCode = "";
 
    public Parser(Token[] args) throws Exception {
        command(args);
    }
 
    //get the current instruction codes
    public String getCurrentInstruction() {
        return this.bitCode;
    }
 
    private void command(Token[] args) throws Exception {
        int state = 1;
        String temp = args[0].getString();
        char c;
        for (int i = 0; i < temp.length(); i++) {
            c = temp.charAt(i);
 
            //Letter Parrser
            switch (state) {
                case 1:
                    switch (c) {
                        case 'h':
                            state = 2;
                            break;
                        case 'i':
                            state = 3;
                            break;
                        case 'n':
                            state = 4;
                            break;
                        case 'a':
                            state = 5;
                            break;
                        case 'o':
                            state = 6;
                            break;
                        case 'x':
                            state = 7;
                            break;
                        case 's':
                            state = 8;
                            break;
                        case 'm':
                            state = 9;
                            break;
                        default:
                            throw new Exception("State 1 Failed");
                    }
                    break;
                case 2:
                    switch (c) {
                        case 'a':
                            state = 10;
                            break;
                        default:
                            throw new Exception("State 2 Failed");
                    }
                    break;
                case 3:
                    switch (c) {
                        case 'n':
                            state = 11;
                            break;
                        default:
                            throw new Exception("State 3 Failed");
                    }
                    break;
                case 4:
                    switch (c) {
                        case 'o':
                            state = 12;
                            break;
                        default:
                            throw new Exception("State 4 Failed");
                    }
                    break;
                case 5:
                    switch (c) {
                        case 'n':
                            state = 13;
                            break;
                        case 'd':
                            state = 14;
                            break;
                        default:
                            throw new Exception("State 5 Failed");
                    }
                    break;
                case 6:
                    switch (c) {
                        case 'r':
                            state = 15;
                            break;
                        default:
                            throw new Exception("State 6 Failed");
                    }
                    break;
                case 7:
                    switch (c) {
                        case 'o':
                            state = 16;
                            break;
                        default:
                            throw new Exception("State 7 Failed");
                    }
                    break;
                case 8:
                    switch (c) {
                        case 'h':
                            state = 17;
                            break;
                        case 'u':
                            state = 18;
                            break;
                        default:
                            throw new Exception("State 8 Failed");
                    }
                    break;
                case 9:
                    switch (c) {
                        case 'o':
                            state = 19;
                            break;
                        case 'u':
                            state = 20;
                            break;
                        default:
                            throw new Exception("State 9 Failed");
                    }
                    break;
                case 10:
                    switch (c) {
                        case 'l':
                            state = 21;
                            break;
                        default:
                            throw new Exception("State 10 Failed");
                    }
                    break;
                case 11:
                    switch (c) {
                        case 't':
                            state = 22;
                            break;
                        default:
                            throw new Exception("State 11 Failed");
                    }
                    break;
                case 12:
                    switch (c) {
                        case 't':
                            state = 23;
                            break;
                        default:
                            throw new Exception("State 12 Failed");
                    }
                    break;
                case 13:
                    switch (c) {
                        case 'd':
                            state = 24;
                            break;
                        default:
                            throw new Exception("State 13 Failed");
                    }
                    break;
                case 14:
                    switch (c) {
                        case 'd':
                            state = 25;
                            break;
                        default:
                            throw new Exception("State 14 Failed");
                    }
                    break;
                case 16:
                    switch (c) {
                        case 'r':
                            state = 26;
                            break;
                        default:
                            throw new Exception("State 16 Failed");
                    }
                    break;
                case 17:
                    switch (c) {
                        case 'l':
                            state = 27;
                            break;
                        case 'r':
                            state = 28;
                            break;
                        default:
                            throw new Exception("State 17 Failed");
                    }
                    break;
                case 18:
                    switch (c) {
                        case 'b':
                            state = 29;
                            break;
                        default:
                            throw new Exception("State 18 Failed");
                    }
                    break;
                case 19:
                    switch (c) {
                        case 'v':
                            state = 30;
                            break;
 
                        default:
                            throw new Exception("State 19 Failed");
                    }
                    break;
                case 20:
                    switch (c) {
                        case 'l':
                            state = 31;
                            break;
                        default:
                            throw new Exception("State 20 Failed");
                    }
                    break;
                case 21:
                    switch (c) {
                        case 't':
                            state = 34;
                            break;
                        default:
                            throw new Exception("State 21 Failed");
                    }
                    break;
                case 22:
                    switch (c) {
                        case 'r':
                            state = 35;
                            break;
                        case 'm':
                            state = 36;
                            break;
                        default:
                            throw new Exception("State 22 Failed");
                    }
                    break;
                case 30:
                    switch (c) {
                        case 'e':
                            state = 32;
                            break;
                        default:
                            throw new Exception("State 30 Failed");
                    }
                    break;
                case 31:
                    switch (c) {
                        case 't':
                            state = 33;
                            break;
                        default:
                            throw new Exception("State 31 Failed");
                    }
                    break;
                default:
                    throw new Exception("Too many characters");
            }
        }
 
        //assigns the code 
        switch (state) {
            case 15:
                this.bitCode += "1001";
                register(args[1].getString());
                register(args[2].getString());
                register(args[3].getString());
                break; // or
            case 23:
                this.bitCode += "1011";
                register(args[1].getString());
                register(args[2].getString()); // This argument won't be use in the ALU
                register(args[2].getString());
                break; // not
            case 24:
                this.bitCode += "1000";
                register(args[1].getString());
                register(args[2].getString());
                register(args[3].getString());
                break; // and
            case 25:
                this.bitCode += "1110";
                register(args[1].getString());
                register(args[2].getString());
                register(args[3].getString());
                break; // add
            case 26:
                this.bitCode += "1010";
                register(args[1].getString());
                register(args[2].getString());
                register(args[3].getString());
                break; // xor
            case 27:
                this.bitCode += "1100";
                register(args[1].getString());
                register(args[2].getString());
                register(args[3].getString());
                break; // shl
            case 28:
                this.bitCode += "1101";
                register(args[1].getString());
                register(args[2].getString());
                register(args[3].getString());
                break; // shr
            case 29:
                this.bitCode += "1111";
                register(args[1].getString());
                register(args[2].getString());
                register(args[3].getString());
                break; // sub
            case 32:
                this.bitCode += "0001";
                register(args[1].getString());
                number(args[2].getString());
                // number(args, 2);
                break; // move
            case 33:
                this.bitCode += "0111";
                register(args[1].getString());
                register(args[2].getString());
                register(args[3].getString());
                break; // mult
            case 34:
                this.bitCode += "0000 0000 0000 0000";
                break; // halt
            case 35:
                this.bitCode += "0010 0000 0000 0000";
                break; // intr
            case 36:
                this.bitCode += "0010 0000 0000 0001";
                break; // intm
            default:
                throw new Exception("Unknown Command");
        }
    }
 
    //Parser for all the integer values
    private void number(String string) throws Exception {
        int state = 1;
        char c;
        for (int i = 0; i < string.length(); i++) {
            c = string.charAt(i);
            switch (state) {
                case 1:
                    switch (c) {
                        case '-':
                            state = 6;
                            break;
                        case '0':
                            state = 2;
                            break;
                        case '1':
                            state = 2;
                            break;
                        case '2':
                            state = 2;
                            break;
                        case '3':
                            state = 2;
                            break;
                        case '4':
                            state = 2;
                            break;
                        case '5':
                            state = 2;
                            break;
                        case '6':
                            state = 2;
                            break;
                        case '7':
                            state = 2;
                            break;
                        case '8':
                            state = 2;
                            break;
                        case '9':
                            state = 2;
                            break;
                        default:
                            throw new Exception("SYNTAX ERROR");
                    }
                    break;
                case 2:
                    switch (c) {
                        case '0':
                            state = 3;
                            break;
                        case '1':
                            state = 3;
                            break;
                        case '2':
                            state = 3;
                            break;
                        case '3':
                            state = 3;
                            break;
                        case '4':
                            state = 3;
                            break;
                        case '5':
                            state = 3;
                            break;
                        case '6':
                            state = 3;
                            break;
                        case '7':
                            state = 3;
                            break;
                        case '8':
                            state = 3;
                            break;
                        case '9':
                            state = 3;
                            break;
                        default:
                            throw new Exception("Wrong syntax for number");
                    }
                    break;
                case 3:
                    switch (c) {
                        case '0':
                            state = 4;
                            break;
                        case '1':
                            state = 4;
                            break;
                        case '2':
                            state = 4;
                            break;
                        case '3':
                            state = 4;
                            break;
                        case '4':
                            state = 4;
                            break;
                        case '5':
                            state = 4;
                            break;
                        case '6':
                            state = 4;
                            break;
                        case '7':
                            state = 4;
                            break;
                        case '8':
                            state = 4;
                            break;
                        case '9':
                            state = 4;
                            break;
                        default:
                            throw new Exception("Wrong syntax for number");
                    }
                    break;
                case 6:
                    switch (c) {
                        case '0':
                            state = 7;
                            break;
                        case '1':
                            state = 7;
                            break;
                        case '2':
                            state = 7;
                            break;
                        case '3':
                            state = 7;
                            break;
                        case '4':
                            state = 7;
                            break;
                        case '5':
                            state = 7;
                            break;
                        case '6':
                            state = 7;
                            break;
                        case '7':
                            state = 7;
                            break;
                        case '8':
                            state = 7;
                            break;
                        case '9':
                            state = 7;
                            break;
                        default:
                            throw new Exception("Wrong syntax for number");
                    }
                    break;
                case 7:
                    switch (c) {
                        case '0':
                            state = 8;
                            break;
                        case '1':
                            state = 8;
                            break;
                        case '2':
                            state = 8;
                            break;
                        case '3':
                            state = 8;
                            break;
                        case '4':
                            state = 8;
                            break;
                        case '5':
                            state = 8;
                            break;
                        case '6':
                            state = 8;
                            break;
                        case '7':
                            state = 8;
                            break;
                        case '8':
                            state = 8;
                            break;
                        case '9':
                            state = 8;
                            break;
                        default:
                            throw new Exception("Wrong syntax for number");
                    }
                    break;
                case 8:
                    switch (c) {
                        case '0':
                            state = 9;
                            break;
                        case '1':
                            state = 9;
                            break;
                        case '2':
                            state = 9;
                            break;
                        case '3':
                            state = 9;
                            break;
                        case '4':
                            state = 9;
                            break;
                        case '5':
                            state = 9;
                            break;
                        case '6':
                            state = 9;
                            break;
                        case '7':
                            state = 9;
                            break;
                        case '8':
                            state = 9;
                            break;
                        case '9':
                            state = 9;
                            break;
                        default:
                            throw new Exception("Wrong syntax for number");
                    }
                    break;
                default:
                    throw new Exception("Invalid Input");
            }
        }
        switch (state) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            default:
                throw new Exception("Incomplete number");
        }
        int i = 0;
        char[] temp = new char[8];
        for (int j = 0; j < 8; j++) {
            temp[j] = '0';
        }
        int value = Integer.parseInt(string);
        if (value <= 127 || value >= -127) {
            boolean isNegative = false;
            if (value < 0) {
                isNegative = true;
                value *= -1;
            }
            while (value != 0) {
                if (value % 2 == 1) {
                    temp[i] = '1';
                } else {
                    temp[i] = '0';
                }
                value /= 2;
                i++;
            }
            if (isNegative) {
                // for negative number in move
                for (int j = 0; j < 8; j++) { // One's compliment
                    if (temp[j] == '1') {
                        temp[j] = '0';
                    } else {
                        temp[j] = '1';
                    }
                }
 
                if (temp[7] != '1') {
                    temp[7] = '1';
                } else {
                    i = 0;
                    while (temp[i] == '1') {
                        temp[i] = '0';
                        i++;
                    }
                    temp[i] = '1'; // complete two's compliment
                }
            }
            this.bitCode += " " + temp[7] + temp[6] + temp[5] + temp[4] + " " + temp[3] + temp[2]
                    + temp[1]
                    + temp[0];
        }
    }
 
    //Parser for all the Registers 0-15
 
    private void register(String string) throws Exception {
        int state = 1;
        char c;
        for (int i = 0; i < string.length(); i++) {
            c = string.charAt(i);
            switch (state) {
                case 1:
                    switch (c) {
                        case 'R':
                            state = 2;
                            break;
                        default:
                            throw new Exception("Invalid register input");
                    }
                    break;
                case 2:
                    switch (c) {
                        case '0':
                            state = 3;
                            break;
                        case '1':
                            state = 4;
                            break;
                        case '2':
                            state = 5;
                            break;
                        case '3':
                            state = 6;
                            break;
                        case '4':
                            state = 7;
                            break;
                        case '5':
                            state = 8;
                            break;
                        case '6':
                            state = 9;
                            break;
                        case '7':
                            state = 10;
                            break;
                        case '8':
                            state = 11;
                            break;
                        case '9':
                            state = 12;
                            break;
                        default:
                            throw new Exception("Invalid register number");
                    }
                    break;
                case 4:
                    switch (c) {
                        case '0':
                            state = 13;
                            break;
                        case '1':
                            state = 14;
                            break;
                        case '2':
                            state = 15;
                            break;
                        case '3':
                            state = 16;
                            break;
                        case '4':
                            state = 17;
                            break;
                        case '5':
                            state = 18;
                            break;
                        default:
                            throw new Exception("Invalid Register");
                    }
                    break;
                default:
                    throw new Exception("Invalid Input");
            }
        }
        switch (state) {
            case 3:
                this.bitCode += " 0000";
                break;
            case 4:
                this.bitCode += " 0001";
                break;
            case 5:
                this.bitCode += " 0010";
                break;
            case 6:
                this.bitCode += " 0011";
                break;
            case 7:
                this.bitCode += " 0100";
                break;
            case 8:
                this.bitCode += " 0101";
                break;
            case 9:
                this.bitCode += " 0110";
                break;
            case 10:
                this.bitCode += " 0111";
                break;
            case 11:
                this.bitCode += " 1000";
                break;
            case 12:
                this.bitCode += " 1001";
                break;
            case 13:
                this.bitCode += " 1010";
                break;
            case 14:
                this.bitCode += " 1011";
                break;
            case 15:
                this.bitCode += " 1100";
                break;
            case 16:
                this.bitCode += " 1101";
                break;
            case 17:
                this.bitCode += " 1110";
                break;
            case 18:
                this.bitCode += " 1111";
                break;
            default:
                throw new Exception("Error in Register");
        }
    }
 
    @Override
    public String toString() {
        return this.bitCode;
    }
}