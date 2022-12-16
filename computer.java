public class computer {

    longword SP = new longword();
    bit status = new bit(false); // Add bit to indicate if the computer is halted or not- have it default to not
    bit halt = new bit(false);// Halt bit
    bit[] temp = new bit[4]; // temp array
    private static bit[] comparable;
    private memory member = new memory(); // add a memory member
    private longword pc = new longword();
    public longword registers[] = new longword[16];
    longword op1 = new longword();
    longword op2 = new longword();
    longword currentInstruction = new longword();
    longword result = new longword();

    public void run() throws Exception {
        for (int i = 0; i < 16; i++) {
            registers[i] = new longword();

        }

        while (!halt.getValue()) {
            fetch();
            decode();
            execute();
            store();
        }
    }

    void fetch() {
        longword incrementValue = new longword();
        incrementValue.set(2);
        currentInstruction.copy(member.read(pc));
        pc = rippleAdder.add(pc, incrementValue); // Increments by 2 using ripple adder

        for (int i = 0; i < 4; i++) {
            temp[i] = currentInstruction.getBit(i);
        }
    }

    void decode() {
        // Temporary longword objects
        longword tempWordOne = new longword();
        longword tempWordTwo = new longword();
        longword tempWordThree = new longword();
        longword tempWordFour = new longword();

        // Set values to each object
        tempWordOne.set(24);
        tempWordTwo.set(15);
        tempWordThree.set(20);
        tempWordFour.set(255);
        // Part 1: Halt
        // ignores decode execution if the halt instruction is 0000
        if (temp[0].getValue() == false && temp[1].getValue() == false && temp[2].getValue() == false
                && temp[3].getValue() == false) {
        }
        // part 2 move
        else if (temp[0].getValue() == false && temp[1].getValue() == false && temp[2].getValue() == false
                && temp[3].getValue() == true) {
            // move
            // right shift the values to isolate the desired 4 bits

            op1.copy(currentInstruction.rightShift(tempWordOne.getSigned()).and(tempWordTwo));
            op2.copy(currentInstruction.rightShift(16).and(tempWordFour));
        }
        // jump
        else if ((this.temp[3].getValue() == false)) {
            return;
        } else if (this.temp[3].getValue() == true) {
            // JUMP JUMP
            longword FouryNinetyFive = new longword();
            FouryNinetyFive.set(4095);
            this.op1.copy(this.currentInstruction.rightShift(16).and(FouryNinetyFive));
            this.op2.copy(op1); // temporarily sets op2 as op1
            return;
        } else if (this.temp[2].getValue() == false) {
            longword sixteen = new longword();
            longword fifteen = new longword();
            fifteen.set(15);
            sixteen.set(16);
            // compare the registers
            this.op1.copy(this.registers[this.currentInstruction.rightShift(fifteen.getSigned()).and(fifteen)
                    .getSigned()]); // isolates xxxx
            this.op2.copy(this.registers[this.currentInstruction.rightShift(sixteen.getSigned()).and(fifteen)
                    .getSigned()]); // isolates yyyy
            return;
        } else {// branch
            longword tentwothree = new longword();
            tentwothree.set(1023);
            this.op1.copy(this.currentInstruction.rightShift(16).and(tentwothree)); // Masks
            this.op2.copy(pc); // To not directly touch the program counter
            longword number = new longword();
            number.set(this.currentInstruction.rightShift(16).getSigned());

            // check if the number is a negative
            if (this.op1.getBit(22).getValue() == true) { // tests the sign negative
                longword conditional = new longword();
                conditional = conditional.not();
                for (int i = 31; i > 22; i--) {
                    conditional.setBit(i, number.getBit(i));
                }
                this.op1.copy(conditional);
            }

            // push/pop x0110
            else if (this.temp[0].getValue() == false && this.temp[1].getValue() && this.temp[2].getValue()
                    && this.temp[3].getValue() == false) {
                // stack
                if ((this.currentInstruction.getBit(4).getValue() == false
                        && this.currentInstruction.getBit(5).getValue() == false)
                        || (this.currentInstruction.getBit(4).getValue() == false)
                                && this.currentInstruction.getBit(5).getValue()) {
                    // push pop
                    longword fifteen = new longword();
                    fifteen.set(15);
                    this.op1.copy(this.currentInstruction.rightShift(16).and(fifteen));
                    return;
                } else if (this.currentInstruction.getBit(4).getValue()
                        && this.currentInstruction.getBit(5).getValue() == false) {
                    // call
                    this.op1.copy(this.currentInstruction.rightShift(16).and(tentwothree));
                    return;
                } else if (this.currentInstruction.getBit(4).getValue()
                        && this.currentInstruction.getBit(5).getValue()) {
                    return;
                }
            }
            return;
        }
        // right shift then mask
        op1.copy(registers[currentInstruction.rightShift(tempWordOne.getSigned()).and(tempWordTwo).getSigned()]);
        op2.copy(registers[currentInstruction.rightShift(tempWordThree.getSigned()).and(tempWordTwo).getSigned()]);
    }

    void execute() throws Exception {
        // temp longwords
        longword tentwothree = new longword();
        tentwothree.set(1023);
        // Part 1: Halt
        // when we encounter the halt instruction (0000), the computer should stop
        // looping
        if (temp[0].getValue() == false && temp[1].getValue() == false && temp[2].getValue() == false
                && temp[3].getValue() == false) {
            halt = new bit(true);
        }
        // normal case
        else if (temp[0].getValue() == false && temp[1].getValue() == false && temp[2].getValue() == false
                && temp[3].getValue() == true) {
            if (op2.getSigned() < 0) {
                // temporary longword placeholder object
                longword temp = new longword();
                temp = temp.not();
                // fill last 8 bits of op2 into temp
                // temporty longword object to store the last 8 bits
                longword lastEight = new longword();
                for (int i = 0; i < 8; i++) {
                    lastEight.setBit(i, op2.getBit(8 + i));
                }
                temp.copy(lastEight);
                result = temp;
            } else {
                result = op2;
            }
        }
        // part 3: Interrupt
        // check for interrupt(The first 4 bits are 0010)
        else if (temp[0].getValue() == false && temp[1].getValue() == false && temp[2].getValue() == true
                && temp[3].getValue() == false) {
            // If the 15th bit is 0 then it prints the whole register
            if (!currentInstruction.getBit(16).getValue()) {
                for (int i = 0; i < 16; i++) {
                    System.out.println(registers[i]);
                }
            }
            // If the 15th bit is 1 then it prints all 1024 bytes of memory
            else if (currentInstruction.getBit(15).getValue()) {
                System.out.print(member.toString());
            }
        } else if (currentInstruction.getBit(15).getValue() == false) {
            for (int i = 0; i < 4; i++) {
                temp[i] = new bit(currentInstruction.getBit(i).getValue());
            }
            result.copy(ALU.doOP(temp, op1, op2));
            // compare
            // CCSA AAAA AAAA
        } else if (this.temp[2].getValue() == false && this.temp[3].getValue() == false) {
            // compare
            /*
             * Decided to use ALU to make execute less cramped
             */
        } else if (this.temp[0].getValue() == false && this.temp[1].getValue() && this.temp[2].getValue() == false
                && this.temp[3].getValue()) { // 0101
            // CCSA AAAA AAAA
            // If Less than
            if (this.currentInstruction.getBit(4).getValue() == false
                    && this.currentInstruction.getBit(5).getValue() == false) {
                // Checks agasint the comparable bits
                if (comparable[1].getValue() == false) { // the comparable bits to compare
                    this.result.copy(ALU.doOP(this.temp, this.op1, this.op2));
                    return;
                }
                return;
                // If Greater
            } else if (this.currentInstruction.getBit(4).getValue()
                    && this.currentInstruction.getBit(5).getValue() == false) {
                if (comparable[0].getValue()) { // the comparable bits to compare
                    this.result.copy(ALU.doOP(this.temp, this.op1, this.op2));
                    return;
                }
                return;
                // if equal
            } else if (this.currentInstruction.getBit(4).getValue() == false
                    && this.currentInstruction.getBit(5).getValue()) {
                if (comparable[1].getValue()) { // the comparable bits to compare
                    this.result.copy(ALU.doOP(this.temp, this.op1, this.op2));
                    return;
                }
                return;
                // if greater thann or equal
            } else if (this.currentInstruction.getBit(4).getValue()
                    && this.currentInstruction.getBit(5).getValue()) {
                if (comparable[0].getValue() || comparable[1].getValue()) { // the comparable bits to
                                                                            // compare
                    this.result.copy(ALU.doOP(this.temp, this.op1, this.op2));
                    return;
                }
                return;
            }
        } else {
            if (this.temp[0].getValue() == false && this.temp[1].getValue() && this.temp[2].getValue()
                    && this.temp[3].getValue() == false) {// 0110
                // stack
                longword four = new longword();
                four.set(4);
                if (this.currentInstruction.getBit(4).getValue() == false
                        && this.currentInstruction.getBit(5).getValue() == false) {
                    SP.set(1019); // push
                    this.member.write(this.SP, registers[this.op1.getSigned()]);
                    this.SP.copy(rippleAdder.subtract(this.SP, four));
                    return;
                } else if ((this.currentInstruction.getBit(4).getValue() == false
                        && this.currentInstruction.getBit(5).getValue())
                        || (this.currentInstruction.getBit(4).getValue()
                                && this.currentInstruction.getBit(5).getValue())) {
                    // pop -> return
                    this.SP.copy(rippleAdder.add(this.SP, four));
                    this.result.copy(this.member.read(this.SP));
                    return;
                } else if (this.currentInstruction.getBit(4).getValue()
                        && this.currentInstruction.getBit(5).getValue() == false) {
                    // call
                    this.member.write(this.SP, this.pc);
                    this.SP.copy(rippleAdder.subtract(this.SP, four));
                    this.result.copy(this.op1);
                    return;
                }
            }
            return;
        }
        this.result.copy(ALU.doOP(this.temp, this.op1, this.op2));
    }

    void store() {
        // ignores store() execution if the halt instrution is 0000
        if (temp[0].getValue() == false && temp[1].getValue() == false && temp[2].getValue() == false
                && temp[3].getValue() == false) {
        } else if (temp[0].getValue() == false && temp[1].getValue() == false && temp[2].getValue() == false
                && temp[3].getValue() == true) {
            registers[op1.getSigned()].copy(result);
        } else if (this.temp[3].getValue() == false) {
            // interrupt
        } else if (this.temp[3].getValue()) {
            // jump
            this.pc.copy(this.result);
            return;
        }

        else {
            if (this.temp[2].getValue() == false && this.temp[3].getValue() == false) {
                // compare
                computer.comparable[0] = new bit(result.getBit(0).getValue());
                computer.comparable[1] = new bit(result.getBit(1).getValue());
                return;
            } else if (this.currentInstruction.getBit(4).getValue() == false
                    && this.currentInstruction.getBit(5).getValue() == false) {
                // branch
                // !=
                if (this.currentInstruction.getBit(4).getValue() == false
                        && this.currentInstruction.getBit(5).getValue() == false) {
                    if (comparable[1].getValue() == false) { // compare
                        this.pc.copy(result);
                    }
                    return;
                    // <
                } else if (this.currentInstruction.getBit(4).getValue()
                        && this.currentInstruction.getBit(5).getValue() == false) {
                    if (comparable[0].getValue() == true
                            && comparable[1].getValue() == false) { // compare
                        this.pc.copy(result);
                    }
                    return;
                    // ==
                } else if (this.currentInstruction.getBit(4).getValue() == false
                        && this.currentInstruction.getBit(5).getValue()) {
                    if (comparable[1].getValue()) {// compare
                        this.pc.copy(result);
                    }
                    return;
                    // <=
                } else if (this.currentInstruction.getBit(4).getValue()
                        && this.currentInstruction.getBit(5).getValue()) {
                    if (comparable[0].getValue() || comparable[1].getValue()) { // compare
                        this.pc.copy(result);
                    }
                    return;
                }
            } else {
                if ((this.temp[0].getValue() == false && this.temp[1].getValue() && this.temp[2].getValue()
                        && this.temp[3].getValue() == false)) {
                    if (this.currentInstruction.getBit(4).getValue() == false
                            && this.currentInstruction.getBit(5).getValue() == false) {
                        // push
                        return;
                    } else if (this.currentInstruction.getBit(4).getValue() == false
                            && this.currentInstruction.getBit(5).getValue()) {
                        // pop
                        registers[this.op1.getSigned()].copy(this.result);
                        return;
                    } else if ((this.currentInstruction.getBit(4).getValue()
                            && this.currentInstruction.getBit(5).getValue() == false)
                            || (this.currentInstruction.getBit(4).getValue()
                                    && this.currentInstruction.getBit(5).getValue())) {
                        // call & return
                        this.pc.copy(this.result);
                        return;
                    }
                    return;
                }
            }
        }
        // right shift to register
        // Mask the registered input
        longword tempWord = new longword();
        longword tempWordTwo = new longword();
        tempWordTwo.set(15);
        tempWord.set(16);
        registers[currentInstruction.rightShift(tempWord.getSigned()).and(tempWordTwo).getSigned()].copy(result);
    }

    // part 4: Initializtion
    public void preload(String[] s) {

        // temp longword to hold the 2 instructions
        longword temp = new longword();
        // counter variables
        int counter = 0;
        longword count = new longword();
        // longword to increment the count longword by
        longword two = new longword();
        two.set(2);

        // nested for loop to store the 2 instruction codes
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length(); j++) {
                if (s[i].charAt(j) == '1') {
                    temp.setBit(counter, new bit(true));
                    counter++;
                } else if (s[i].charAt(j) == '0') {
                    temp.setBit(counter, new bit());
                    counter++;
                }
            }
            counter = 0;
            member.write(count, temp);
            count = rippleAdder.add(two, count);
        }
    }

    /*
     * helper function to test compare
     * prints the registers
     */public static String printCompare() throws Exception {
        String result = "";
        for (int i = 0; i < 2; i++) {
            if (comparable[i].getValue()) {
                result += "1";
            } else {
                result += "0";
            }
        }
        return result;
    }
     /**
     * Helper function to print counter
     * @throws Exception
     */
    public int printProgramCounter() throws Exception {
        return this.pc.getSigned() - 2; 
    }
}