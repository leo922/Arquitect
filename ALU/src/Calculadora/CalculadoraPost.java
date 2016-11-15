package Calculadora;

import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculadoraPost {
  public static void main(String[] args) {

    //Entrada de datos
    System.out.println("*Escribe una expresión algebraica: ");
 
    Scanner leer = new Scanner(System.in);

    //Obtener expresion algebraica
    String expr = depurar(leer.nextLine());
    String[] arrayInfix = expr.split(" ");
    

    //Declaración de las pilas
    Stack < String > E = new Stack < String > (); //Pila entrada
    Stack < String > P = new Stack < String > (); //Pila temporal para operadores
    Stack < String > S = new Stack < String > (); //Pila salida
  


    //Añadir la array a la Pila de entrada (E)
    for (int i = arrayInfix.length - 1; i >= 0; i--) {
      E.push(arrayInfix[i]);
    }

    try {
      //Algoritmo Infijo a Postfijo
      while (!E.isEmpty()) {
        switch (pref(E.peek())){
          case 1:
            P.push(E.pop());
            break;
          case 3:
          case 4:
            while(pref(P.peek()) >= pref(E.peek())) {
              S.push(P.pop());
            }
            P.push(E.pop());
            break; 
          case 2:
            while(!P.peek().equals("(")) {
              S.push(P.pop());
            }
            P.pop();
            E.pop();
            break; 
          default:
            S.push(E.pop()); 
        } 
      } 

      //Eliminar cosas que no son de las expresiones algebraicas
      String infix = expr.replace(" ", "");
      String postfix = S.toString().replaceAll("[\\]\\[,]", "");

      //Mostrar resultados:
      
      
      System.out.println("Expresion Infija: " + infix);
      System.out.println("Expresion Postfija: " + postfix);
      ScriptEngineManager mgr = new ScriptEngineManager();    
      
      ScriptEngine engine = mgr.getEngineByName("JavaScript");    
      try {
          String formula = infix;
          System.out.println(formula + " = " + engine.eval(formula));
      } catch (ScriptException ex) {}
    
      
      
      int i;
      String H;
      for(i=0;i<E.size();i++){
     H= E.get(i);
      
      System.out.println(" Salida"+ H);
      }
    }catch(Exception ex){ 
      System.out.println("Error en la expresión algebraica");
      System.err.println(ex);
    }
  } 

  // expresión algebraica
  private static String depurar(String s) {
    s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
    s = "(" + s + ")";
    String simbols = "+-*/()";
    String str = "";
  
    //Deja espacios entre operadores
    for (int i = 0; i < s.length(); i++) {
      if (simbols.contains("" + s.charAt(i))) {
        str += " " + s.charAt(i) + " ";
      }else str += s.charAt(i);
    }
    return str.replaceAll("\\s+", " ").trim();
  } 

  //Jerarquia de los operadores
  private static int pref(String op) {
    int prf = 99;
    if (op.equals("^")) prf = 5;
    if (op.equals("*") || op.equals("/")) prf = 4;
    if (op.equals("+") || op.equals("-")) prf = 3;
    if (op.equals(")")) prf = 2;
    if (op.equals("(")) prf = 1;
    return prf;
  }
 // private static int evaluar(String op, String n2, String n1) {
	//    int num1 = Integer.parseInt(n1);
	  //  int num2 = Integer.parseInt(n2);
	    //if (op.equals("+")) return (num1 + num2);
	    //if (op.equals("-")) return (num1 - num2);
	    //if (op.equals("*")) return (num1 * num2);
	    //if (op.equals("/")) return (num1 / num2);
	    //if (op.equals("%")) return (num1 % num2);
	    //return 0;
	  //}
  public static  double resuelve (Queue posfija)
  {
    Stack Pila=new Stack();
    double resultado, a , b;
     while(posfija.peek()!=null)
        {
            char dato=(Character)posfija.poll();
            if (Character.isDigit(dato))
                Pila.push(dato);
            else
               {
                String Str =Pila.pop().toString();
                b=Double.valueOf(Str).doubleValue();
               //b=(double)Pila.pop();
                Str =Pila.pop().toString();
                a=Double.valueOf(Str).doubleValue();
                if (dato == '+')
                    Pila.push(a+b);
                else if (dato == '-')
                    Pila.push(a-b);
                else if (dato == '*')
                     Pila.push(a*b);
                 else if (dato == '/')
                     Pila.push(a/b);
                else if (dato == '^')
                   Pila.push( Math.pow(a,b));
                }
        }
                String Str =Pila.pop().toString();
                resultado=Double.valueOf(Str).doubleValue();
                System.out.println(resultado);
                 return resultado;
                 
  }
  
  
}
