
public class ALU {

    public static longword doOP(bit[] operation, longword firstLongword, longword secondLongword) throws Exception {
        if (operation.length != 4) {
            throw new Exception("Operation Length is not 4");
        }
        if (operation[0].getValue()) {
            if (operation[1].getValue()) {
                if (operation[2].getValue()) {
                    if (operation[3].getValue()) {
                        return rippleAdder.subtract(firstLongword, secondLongword);
                    } else {
                        return rippleAdder.add(firstLongword, secondLongword);
                    }
                } else { // 110x
                    if (operation[3].getValue()) {

                        longword temp = new longword();
                        temp.copy(secondLongword);
                        for (int i = 26; i > -1; i--) {
                            temp.setBit(i, new bit());
                        }

                        return firstLongword.rightShift(secondLongword.getSigned());
                    } else {
                        longword temp = new longword();
                        temp.copy(secondLongword);
                        for (int i = 26; i > -1; i--) {
                            temp.setBit(i, new bit());
                        }
                        return firstLongword.leftShift(secondLongword.getSigned());
                    }
                }

            } else {
                if (operation[2].getValue()) {
                    if (operation[3].getValue()) {
                        return firstLongword.not();
                    } else {
                        return firstLongword.xor(secondLongword);
                    }
                } else {
                    if (operation[3].getValue()) {
                        return firstLongword.or(secondLongword);
                    } else {
                        return firstLongword.and(secondLongword);
                    }
                }
            }

        } else {
            if (operation[1].getValue()) {
                if (operation[2].getValue()) {
                    if (operation[3].getValue()) {
                        return multiplier.multiply(firstLongword, secondLongword);
                    }
                } else {
                    if (operation[3].getValue()) {
                    } // branch
                    else {
                        if (operation[3].getValue()) {
                            // Branch
                            return rippleAdder.add(firstLongword, secondLongword);
                        } else {
                            longword result = new longword(); // 00
                            longword temp = new longword();
                            // comparable
                            // set the result
                            temp.copy(rippleAdder.subtract(firstLongword, secondLongword));

                            // result
                            if (temp.getSigned() == 0) { // 01
                                result.setBit(1, new bit(true));
                            } else if (temp.getSigned() > 0) { // 10
                                result.setBit(0, new bit(true));
                            } else if (temp.getSigned() < 0) { //
                                result.setBit(0, new bit());
                            }
                            return result;

                        }

                    }
                }
            } else {
                if (operation[2].getValue()) {
                    if (operation[3].getValue()) {
                        longword two = new longword();
                        two.set(2);
                        return multiplier.multiply(firstLongword, two);
                    }
                }
            }
        }
        return new longword();
    }
}
