package lexer;

public class Token
{
  // Lab 1, exercise 1: read the MiniJava specification
  // carefully, and answer these two questions:
  //   1. whether or not one should add other token kinds?
  //   2. which tokens come with an extra "lexeme", and
  //      which don't?
  // It's highly recommended that these token names are
  // alphabetically ordered, if you add new ones.
  public enum Kind {
    TOKEN_ADD, // "+" y
    TOKEN_AND, // "&&" y
    TOKEN_ASSIGN, // "="
    TOKEN_BOOLEAN, // "boolean"
    TOKEN_CLASS, // "class"
    TOKEN_COMMER, // ","
    TOKEN_DOT, // "."
    TOKEN_ELSE, // "else"
    TOKEN_EOF, // EOF
    TOKEN_EXTENDS, // "extends"
    TOKEN_FALSE, // "false"
    TOKEN_ID, // Identifier
    TOKEN_IF, // "if"
    TOKEN_INT, // "int"
    TOKEN_LBRACE, // "{"
    TOKEN_LBRACK, // "["
    TOKEN_LENGTH, // "length"
    TOKEN_LPAREN, // "("
    TOKEN_LT, // "<"
    TOKEN_MAIN, // "main"
    TOKEN_NEW, // "new"
    TOKEN_NOT, // "!"
    TOKEN_NUM, // IntegerLiteral
    // "out" is not a Java key word, but we treat it as
    // a MiniJava keyword, which will make the
    // compilation a little easier. Similar cases apply
    // for "println", "System" and "String".
    TOKEN_OUT, // "out"
    TOKEN_PRINTLN, // "println"
    TOKEN_PUBLIC, // "public"
    TOKEN_RBRACE, // "}"
    TOKEN_RBRACK, // "]"
    TOKEN_RETURN, // "return"
    TOKEN_RPAREN, // ")"
    TOKEN_SEMI, // ";"
    TOKEN_STATIC, // "static"
    TOKEN_STRING, // "String"
    TOKEN_SUB, // "-"
    TOKEN_SYSTEM, // "System"
    TOKEN_THIS, // "this"
    TOKEN_TIMES, // "*"
    TOKEN_TRUE, // "true"
    TOKEN_VOID, // "void"
    TOKEN_WHILE, // "while"
    
    // add some new tokens below
    TOKEN_BITAND, // '&'
    TOKEN_BITOR, // '|'
    TOKEN_ONE_NOTE,  // "//"add new 1
    TOKEN_LSTAR_NOTE, // "/*"
    TOEKN_RSTAR_NOTE, // "*/" 
    TOKEN_DELETE, // "delete"
    TOKEN_EQ, // "=="
    TOKEN_NOTEQ, //"!="
    TOKEN_IMPLEMENTS, // "implements"
    TOKEN_GT,  // ">"
    TOKEN_OR,  //"||"
    TOKEN_PROTECTED, // "protected"
    TOKEN_PRIVATE,  // "private"
    TOKEN_REAL,  // "real"
  }

  public Kind kind; // kind of the token
  public String lexeme; // extra lexeme for this token, if any
  public Integer lineNum; // on which line of the source file this token appears
  public Integer columnNum; 
  
  // Some tokens don't come with lexeme but 
  // others do.
  public Token(Kind kind, Integer lineNum)
  {
    this.kind = kind;
    this.lineNum = lineNum;
  }

  public Token(Kind kind, Integer lineNum, Integer columnNum)
  {
	  this.kind = kind;
	  this.lineNum = lineNum;
	  this.columnNum = columnNum;
  }
  
  public Token(Kind kind, Integer lineNum, String lexeme)
  {
    this(kind, lineNum);
    this.lexeme = lexeme;
  }

  public Token(Kind kind, Integer lineNum, String lexeme, Integer columnNum)
  {
    this(kind, lineNum);
    this.lexeme = lexeme;
    this.columnNum = columnNum;
  }
  @Override
  public String toString()
  {
    String s;

    // to check that the "lineNum" field has been properly set.
    if (this.lineNum == null)
      new util.Todo();

    s = ": " + ((this.lexeme == null) ? "\t<>" : "\t"+this.lexeme) + " \t : at line "
        + this.lineNum.toString() + "\t : at column " + this.columnNum.toString();
    return this.kind.toString() + s;
  }
}
