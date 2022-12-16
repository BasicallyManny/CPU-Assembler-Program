public class assembler{
	public static String[] Assemble(String[] args) throws Exception {
        String[] cpuInstructions = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            Lexer lex = new Lexer();
            Token[] temp = lex.lex(args[i]);
            Parser state = new Parser(temp);
            cpuInstructions[i] = state.getCurrentInstruction();
        }
        return cpuInstructions;
    }
}
 
 class Lexer {
	private int count;
	private String arg;
 
	public Lexer() {
		this.count = 0;
		this.arg = "";
	}
 
	//lexical analyzer 
	public Token[] lex(String arg) throws Exception { 
		this.arg = arg; 
		Token[] temptokens = new Token[arg.length()];
 
		Token temp = nextToken();
		int i = 0;
		while (temp != null) {
			temptokens[i] = temp;
			temp = nextToken();
			i++;
		}
 
		Token[] tokens = new Token[i];
		int j = 0;
		while (j < i) {
			tokens[j] = temptokens[j];
			j++;
		}
		return tokens;
	}
 
	private Token nextToken() throws Exception {
		getNonBlank();
		char c = currentChar();
		if (isEOF()) {
			return null;
		} else if (c == '+' || c == '-' || c == '*' || c == '-' || c == '/') {
			Token temp;
 
			// check for - (to seeif the number is negative)
			if (c == '-') {
				StringBuilder tempSTR = new StringBuilder();
				tempSTR.append(next());
				if (Character.isDigit(currentChar())) {
					tempSTR.append(next());
					while (!isEOF() && Character.isDigit(currentChar())) {
						tempSTR.append(next());
					}
					temp = new Token(tempSTR.toString());
					return temp;
				} else {
					return new Token(tempSTR.toString());
				}
			} else {
				temp = new Token(Character.toString(next()));
			}
			return temp;
		} else if (Character.isDigit(c)) { //else the numberis positive
			StringBuilder tempSTR = new StringBuilder();
			tempSTR.append(next());
			while (!isEOF() && Character.isDigit(currentChar())) {
				tempSTR.append(next());
			}
			Token temp = new Token(tempSTR.toString());
			return temp;
		} else if (Character.isAlphabetic(c)) {
			StringBuilder tempSTR = new StringBuilder();
			tempSTR.append(next());
			while (!isEOF() && (Character.isAlphabetic(currentChar()) || Character.isDigit(currentChar()))) {
				tempSTR.append(next());
			}
			Token temp2 = new Token(tempSTR.toString());
			return temp2;
		} else {
			throw new Exception("Token Error");
		}
	}
 
	//checks if its the end of the string
	private boolean isEOF() {
		return this.arg.length() <= this.count;
	}
 
	private char currentChar() throws Exception {
		if (isEOF()) {
			// return Character.MIN_VALUE;
			return 'c'; 
		}
		return arg.charAt(count);
	}
 
	//helper function to get the next character
	private char next() throws Exception {
		char next = currentChar();
		count++;
		return next;
	}
 
	//helper function to see if a word is a space.
	private void getNonBlank() throws Exception {
		while (!isEOF() && Character.isWhitespace(currentChar())) {
			next();
		}
	}
}