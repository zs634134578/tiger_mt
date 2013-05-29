package lexer;

import java.io.InputStream;

import util.Todo;

import lexer.Token.Kind;

public class Lexer
{
  public static int lineNum = 1;
  public static int columnNum = 0;
  
  String fname; // the input file name to be compiled
  InputStream fstream; // input stream for the above file

  public Lexer(String fname, InputStream fstream)
  {
    this.fname = fname;
    this.fstream = fstream;
  }

  // When called, return the next token (refer to the code "Token.java")
  // from the input stream.
  // Return TOKEN_EOF when reaching the end of the input stream.
  private Token nextTokenInternal() throws Exception
  {
	  
    int c = this.fstream.read();
    columnNum++;
    if (-1 == c)
      // The value for "lineNum" is now "null",
      // you should modify this to an appropriate
      // line number for the "EOF" token.
      return new Token(Kind.TOKEN_EOF, null);

    // skip all kinds of "blanks"
    while (' ' == c || '\t' == c || '\n' == c) {
       if('\n' == c )
      	  {
    	   lineNum += 1; 
    	   columnNum = 0;
      	  }
       // deal with '\t'
       if('\t' == c)
    	   columnNum += 3;
       columnNum++;
       c = this.fstream.read();

    }
    if (-1 == c)
      return new Token(Kind.TOKEN_EOF, lineNum);
    
    char ctemp = (char)c;
    
    switch (ctemp) {
    case '+':
    	return new Token(Kind.TOKEN_ADD, lineNum);
    case '&':   // not deal with &&
    {
    	this.fstream.mark(99);
    	columnNum++;
    	c = this.fstream.read();
    	if('&' == c)
    		return new Token(Kind.TOKEN_BITAND, lineNum, columnNum);
    	else 
    	{
    		columnNum--;
    		this.fstream.reset();
    		return new Token(Kind.TOKEN_AND, lineNum, columnNum);
    	}
    }
    case '|':
    {
    	this.fstream.mark(99);
    	columnNum++;
    	c = this.fstream.read();
    	if('|' == c)
    		return new Token(Kind.TOKEN_BITOR, lineNum, columnNum);
    	else 
    	{
    		columnNum--;
    		this.fstream.reset();
    		return new Token(Kind.TOKEN_OR, lineNum, columnNum);
    	}
    }
    case '=': 
    {
    	this.fstream.mark(99);
    	columnNum++;
    	c = this.fstream.read();
    	if('=' == c)
    		return new Token(Kind.TOKEN_ASSIGN, lineNum, columnNum);
    	else
    	{
    		columnNum--;
    		this.fstream.reset();
    		return new Token(Kind.TOKEN_EQ, lineNum, columnNum);
    	}
    }
    case ',':
    	return new Token(Kind.TOKEN_COMMER, lineNum, columnNum);
    case '.':
    	return new Token(Kind.TOKEN_DOT, lineNum, columnNum);
    case '{':
    	return new Token(Kind.TOKEN_LBRACE, lineNum, columnNum);
    case '[':
    	return new Token(Kind.TOKEN_LBRACK, lineNum, columnNum);
    case '(':
    	return new Token(Kind.TOKEN_LPAREN, lineNum, columnNum);
    case '<':
    	return new Token(Kind.TOKEN_LT, lineNum, columnNum);
    case '!':
    	return new Token(Kind.TOKEN_NOT, lineNum, columnNum);
    case '}':
    	return new Token(Kind.TOKEN_RBRACE, lineNum, columnNum);
    case ']':
    	return new Token(Kind.TOKEN_RBRACK, lineNum, columnNum);
    case ')':
    	return new Token(Kind.TOKEN_RPAREN, lineNum, columnNum);
    case '-':
    	return new Token(Kind.TOKEN_SUB, lineNum, columnNum);
    case ';':
    	return new Token(Kind.TOKEN_SEMI, lineNum, columnNum);
    case '*':
    	return new Token(Kind.TOKEN_TIMES, lineNum, columnNum);
    case '>':
    	return new Token(Kind.TOKEN_GT, lineNum, columnNum);
    	
 //   default:
      // Lab 1, exercise 2: supply missing code to
      // lex other kinds of tokens.
      // Hint: think carefully about the basic
      // data structure and algorithms. The code
      // is not that much and may be less than 50 lines. If you
      // find you are writing a lot of code, you
      // are on the wrong way.
 //     new Todo();
 //     return null;
    }
    if(Character.isLetter(c) || '_' == c)
    {
    	int coltemp = columnNum;
    	StringBuffer temp = new StringBuffer();
    	do {
    		temp.append((char)c);
    		this.fstream.mark(99);
    		columnNum++;
    		c = this.fstream.read();
    	}while(Character.isLetterOrDigit(c) || '_' == c );
    	
    	columnNum--;
    	this.fstream.reset();
    	String s = temp.toString();
    	return new Token(Kind.TOKEN_ID, lineNum, s, coltemp);
    };
    
    if(Character.isDigit(c))
    {
    	int v = 0;
    	int vclotemp = columnNum;
    	do {
    	v = 10 * v + Character.digit(c, 10);
    	this.fstream.mark(99);
    	columnNum++;
    	c = this.fstream.read();
    	}while(Character.isDigit(c));
    	if('.' != c)
    		{
    		columnNum--;
    		this.fstream.reset();
    		return new Token(Kind.TOKEN_INT, lineNum, String.valueOf(v), vclotemp);
    		}
    	float f = v; float d = 10;
    	do {
    		columnNum++;
    		c = this.fstream.read();
    		f = v + Character.digit(c, 10) / d;
    		d = d * 10;
    	}while(Character.isDigit(c));
    	String temp = String.valueOf(f);
    	columnNum--;
    	this.fstream.reset();
    	return new Token(Kind.TOKEN_REAL, lineNum, temp, vclotemp);
    }
    else
    {
  //  	new Todo();
        Token t=new Token(Kind.TOKEN_ID,c);
        c=' ';
        return t;
    }
   
  }
  
  public Token nextToken()
  {
    Token t = null;

    try {
      t = this.nextTokenInternal();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
    if (control.Control.lex)
      System.out.println(t.toString());
    return t;
  }
}
