import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTextArea;


public class Analizador {
    
     public int [] contadores = new int[ 28 ] ;
     
     public int [][] matrizL={
            //	l	L	N	.	_	"	/	*	{	}	=	<	>	+	-	(	)	[	]	,	;	:	/b	/t	/e      !=
		{1	,1	,3	,14	,500	,5	,6	,13	,9	,500	,10	,11	,12	,113	,114	,118	,119	,120	,121	,122	,124	,16	,0	,0	,0	,505}	,
		{1	,1	,1	,101	,2	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101}	,
		{1	,1	,1	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500}	,
		{103	,103	,3	,4	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103	,103}	,
		{504	,504	,15	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504	,504}	,
		{5	,5	,5	,5	,5	,105	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,501	,5}	,
		{117	,117	,117	,117	,117	,117	,117	,7	,117	,117	,117	,117	,117	,117	,117	,117	,117	,117	,117	,117	,117	,117	,117	,117	,117	,117}	,
		{7	,7	,7	,7	,7	,7	,7	,8	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,502	,7}	,
		{8	,8	,8	,8	,8	,8	,106	,8	,8	,8	,8	,8	,8	,8	,8	,8	,8	,8	,8	,8	,8	,8	,8	,8	,502	,8}	,
		{9	,9	,9	,9	,9	,9	,9	,9	,9	,127	,9	,9	,9	,9	,9	,9	,9	,9	,9	,9	,9	,9	,9	,9	,502	,9}	,
		{107	,107	,107	,107	,107	,107	,107	,107	,107	,107	,108	,107	,107	,107	,107	,107	,107	,107	,107	,107	,107	,107	,107	,107	,107	,107}	,
		{109	,109	,109	,109	,109	,109	,109	,109	,109	,109	,110	,109	,109	,109	,109	,109	,109	,109	,109	,109	,109	,109	,109	,109	,109	,109}	,
		{111	,111	,111	,111	,111	,111	,111	,111	,111	,111	,112	,111	,111	,111	,111	,111	,111	,111	,111	,111	,111	,111	,111	,111	,111	,111}	,
		{115	,115	,115	,115	,115	,115	,115	,116	,115	,115	,115	,115	,115	,115	,115	,115	,115	,115	,115	,115	,115	,115	,115	,115	,115	,115}	,
		{123	,123	,123	,126	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123	,123}	,
		{104	,104	,15	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104	,104}   ,
		{125	,125	,125	,125	,125	,125	,125	,125	,125	,125	,107	,125	,125	,125	,125	,125	,125	,125	,125	,125	,125	,125	,125	,125	,125	,125}

    };
     
     
     JTextArea areaCadena, areaLexica, areaSemantico;
     public Analizador(){
        areaCadena=  Interfaz.areaCadena;
        areaLexica=Interfaz.areaLexica;
        areaSemantico=Interfaz.areaSemantico;    
     }
    
    public String cadena="";
    char []cA; 
    int i, temp; 
    int columna, estado; 
    String conca="";
    //String comparar=conca.substring(0, conca.length()-1).trim();
    String mensaje ;
    char[] ae ={89,101,78}; 
    //String string=String.valueOf(ae);
    ArrayList<String> asignacion=new ArrayList<>(); 
    ArrayList<String> operadoresLogicos=new ArrayList<>(); 
    ArrayList<String> operadoresAritmeticos=new ArrayList<>(); 
    ArrayList<String> operadoresRelacionales=new ArrayList<>(); 
    ArrayList<String> palres=new ArrayList<>(); 
    ArrayList<String> comentarios=new ArrayList<>();
    ArrayList<String> identificadores=new ArrayList<>(); // _ident cal59 
        ArrayList<String> minusculas=new ArrayList(); 
        ArrayList<String> mayusculas=new ArrayList();
    ArrayList<String> numeros=new ArrayList(); //1 5.1 -5
        ArrayList<String> digitos=new ArrayList(); 
    ArrayList<String> delimitadores = new ArrayList() ;
    ArrayList<String> simbolos = new ArrayList() ;
    

    public void Analizar(String cadena){ //Array123
        cA=cadena.toCharArray();
        i=0; 
        while(i<cadena.length()){
            temp=i; 
            columna=Relaciona(cA[i]) ;
            conca=conca+cA[i]; 

            if(estado<99 ){
                
                estado=matrizL[estado][columna];
                if( Revision( conca ) ){
                    estado = 101 ;  
                    i++ ;
                }
                if( RevisionLogico( conca ) ){
                    estado = 102 ; 
                    i++ ;
                }
                if ((estado>=100)&&(estado<=200)){ 
                    mensaje="";
                    Token(estado) ;
                    areaLexica.setText(areaLexica.getText()+mensaje+"\n");
                    estado=0;
                    conca="";

                }else if(estado>=500){ 
                    mensaje="";
                    Error(estado);
                    areaLexica.setText(areaLexica.getText()+mensaje+"\n");
                    estado=0;
                    conca="";
                }
            }
            i++;
        }
    }

    private int Relaciona(char cAi){
        if(cAi>='A' && cAi<='Z'){
            columna=0;
        }
        else if(cAi>='a' && cAi<='z'){
            columna=1;
        }
        else if(cAi>='0' && cAi<='9' ) {
            columna=2; 
        }else{

            switch (cAi){
                case '.':{columna=3; break;}
                case '_':{columna=4;break;}
                case '"':{columna=5; break;}
                case '/':{columna=6; break;}
                case '*':{columna=7;break;}
                case '{':{columna=8;break;}
                case '}':{columna=9;break;}
                case '=':{columna=10;break;}
                case '<':{columna=11;break;}
                case '>':{columna=12;break;}
                case '+':{columna=13;break;}
                case '-':{columna=14;break;}
                case '(':{columna=15;break;}
                case ')':{columna=16; break;}
                case '[':{columna=17; break;}
                case ']':{columna=18; break;}
                case ',':{columna=19; break;}
                case ';':{columna=20; break;}
                case ':':{columna=21; break;}
                case ' ':{columna=22; break;} 
                case 0x09:{columna=23; break;}
                case '\n':{columna=24; break;}
                default: {columna=25; break;}

            }
        }

        return columna;
    }
    String st = String.valueOf(0x59+0x65+0x4E); 
    
    private boolean Revision( String comparar ){ 
                if( comparar.equalsIgnoreCase("Array")){
                    return true ; 
                }
                if(comparar.equalsIgnoreCase("Else")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("For")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Exit")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Repeat")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("To")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("True")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Char")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Begin")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Writeln")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("If")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Return")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Until")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("False")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("String")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Case")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Readln")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Loop")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Of")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Pocedure")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Var")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Div")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Byte")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Const")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("ElseIf")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Module")){ 
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Mod")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("By")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("While")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Integer")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Boolean")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Do")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("End")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Function")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Record")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Then")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("With")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("Real")){
                    return true ;
                }
                if(comparar.equalsIgnoreCase("String")){
                    return true ;
                }
                //if(comparar.equals(string)){
                //   
                //    return true ;
                //}
                return false ; 
    }
    
    private boolean RevisionLogico( String comparar ){
        if(comparar.equalsIgnoreCase("or")){
                    return true ;
        }
        if(comparar.equalsIgnoreCase("and")){
            return true ;
        }
        if(comparar.equalsIgnoreCase("not")){
            return true ;
        }
        return false ; 
    }

    private String Token(int token){ 
        String comparar=conca.substring(0, conca.length()-1).trim();
        if(token==101||token==103||token==104||token==107||token==109||token==111||token==115||token==117||token==123){
            i--;
        }
        
        
        switch(token){
            case 100:
                break;
            case 101:
                if( Revision( conca  ) ){ //arra
                    mensaje=conca;
                    palres.add(mensaje);
                    mensaje="";
                    contadores[ 1 ]++;
                    mensaje="Palabra reservada: "+ conca +  "\t\t" + " Codigo: 101-" + contadores[ 1 ] ;
                    
                    break ; 
                }
                else if( RevisionLogico( conca ) ){
                    operadoresLogicos.add( conca ) ;
                    contadores[ 1 ]++;
                    mensaje="Operador Logico: "+ conca + "\t\t" + " Codigo: 101-" + contadores[ 1 ] ;
                    break ; 
                }
                else{
                    estado=0;
                    Token(102); 
                }
                    break;
            case 102:
                identificadores.add( comparar ) ;
                estado=0;
                contadores[ 2 ]++;
                mensaje="Identificador: "+comparar + "\t\t" + " Codigo: 102-" + contadores[ 2 ] ;
                
                break;
            case 103: 
                numeros.add( comparar ) ;
                contadores[ 3 ]++;
                mensaje="Numero Entero: "+comparar + "\t\t" + " Codigo: 103-" + contadores[ 3 ];
                
                break;
            case 104: 
                numeros.add( comparar ) ;
                contadores[ 4 ]++;
                mensaje="Numero Real: "+comparar + "\t\t" + " Codigo: 104-" + contadores[ 4 ];
                
                break;
            case 105:
                comentarios.add( comparar ) ;
                String letrero=conca.substring(1,conca.length()-1);
                contadores[ 5 ]++;
                mensaje="Letrero "+letrero + "\t\t" + " Codigo: 105-" + contadores[ 5 ];
                
                break;
            case 106:
                comentarios.add( comparar ) ;
                String comentario=conca.substring(2, conca.length()-2).trim();
                contadores[ 6 ]++;
                mensaje="Comentario: "+comentario + "\t\t" + " Codigo: 106-" + contadores[ 6 ];
                
                break;
            case 107: 
                if( conca.contains(":") ){
                    asignacion.add( conca ) ;
                    contadores[ 7 ]++;
                    mensaje="Asignacion: "+ conca  + "\t\t" + " Codigo: 107-" + contadores[ 7 ];
                    
                    i++ ;
                    break;
                }  
                else{
                    asignacion.add( comparar ) ;
                    contadores[ 7 ]++;
                    mensaje="Asignacion: "+comparar + "\t\t" + " Codigo: 107-" + contadores[ 7 ];
                    
                    break;
                }
                
            case 108:
                operadoresRelacionales.add( conca ) ;
                contadores[ 8 ]++;
                mensaje="Comparacion: "+conca + "\t\t" + " Codigo: 108-" + contadores[ 8 ];
                
                break;
            case 109: 
                operadoresRelacionales.add( comparar ) ;
                contadores[ 9 ]++;
                mensaje="Menor que: "+comparar + "\t\t" + " Codigo: 109-" + contadores[ 9 ];
                
                break;
            case 110: 
                operadoresRelacionales.add( conca ) ;
                contadores[ 10 ]++;
                mensaje="Menor igual que: "+conca + "\t\t" + " Codigo: 110-" + contadores[ 10 ];
                
                break;
            case 111:
                operadoresRelacionales.add( comparar ) ;
                contadores[ 11 ]++;
                mensaje="Mayor que: "+comparar + "\t\t\t" + " Codigo: 111-" + contadores[ 11 ];
                
                break;
            case 112: 
                operadoresRelacionales.add( conca ) ;
                contadores[ 12 ]++;
                mensaje="Mayor igual que : "+ conca + "\t\t" + " Codigo: 112-" + contadores[ 12 ];
                
                break;
            case 113:
                operadoresAritmeticos.add( conca ) ;
                contadores[ 13 ]++;
                mensaje="Suma: "+conca + "\t\t\t" + " Codigo: 113-" + contadores[ 13 ];
                
                break;
            case 114: 
                operadoresAritmeticos.add( conca ) ;
                contadores[ 14 ]++;
                mensaje="Resta: "+conca + "\t\t" + " Codigo: 114-" + contadores[ 14 ];
                
                break;
            case 115: 
                operadoresAritmeticos.add( comparar ) ;
                contadores[ 15 ]++;
                mensaje="Multiplicacion: "+comparar + "\t\t" + " Codigo: 115-" + contadores[ 15 ];
                
                break;
            case 116: 
                operadoresAritmeticos.add( conca ) ;
                contadores[ 16 ]++;
                mensaje="Potencia: "+conca + "\t\t" + " Codigo: 116-" + contadores[ 16 ];
                
                break;
            case 117: 
                operadoresAritmeticos.add( comparar ) ;
                contadores[ 17 ]++;
                mensaje="Division: "+comparar + "\t\t" + " Codigo: 117-" + contadores[ 17 ];
                
                break;
            case 118: 
                delimitadores.add( conca ) ;
                contadores[ 18 ]++;
                mensaje="Delimitador: "+conca + "\t\t" + " Codigo: 118-" + contadores[ 18 ];
                
                break;
            case 119: 
                delimitadores.add( conca ) ;
                contadores[ 19 ]++;
                mensaje="Delimitador: "+conca + "\t\t" + " Codigo: 119-" + contadores[ 19 ];
                
                break;
            case 120: 
                delimitadores.add( conca ) ;
                contadores[ 20 ]++;
                mensaje="Delimitador: "+conca + "\t\t" + " Codigo: 120-" + contadores[ 20 ];
                
                break;
            case 121: 
                delimitadores.add( conca ) ;
                contadores[ 21 ]++;
                mensaje="Delimitador: "+conca + "\t\t" + " Codigo: 121-" + contadores[ 21 ];
                
                break;
            case 122: 
                delimitadores.add( conca ) ;
                contadores[ 22 ]++;
                mensaje=">Delimitador: "+conca + "\t\t" + " Codigo: 122-" + contadores[ 22 ];
                
                break;
            case 123: 
                delimitadores.add( comparar ) ;
                contadores[ 23 ]++;
                mensaje="Delimitador: "+ comparar  + "\t\t" + " Codigo: 123-" + contadores[ 23 ];
                
                break;
            case 124: 
                delimitadores.add( conca ) ;
                contadores[ 24 ]++;
                mensaje="Delimitador: "+conca + "\t\t" + " Codigo: 124-" + contadores[ 24 ];
                
                break;
            case 125: 
                delimitadores.add( conca ) ;
                contadores[ 25 ]++;
                mensaje="Delimitador: "+ conca + "\t\t" + " Codigo: 125-" + contadores[ 25 ];
                
                break;
            case 126: 
                delimitadores.add( conca ) ;
                contadores[ 26 ]++;
                mensaje="Delimitador: "+conca.trim() + "\t\t" + " Codigo: 126-" + contadores[ 26 ];
                
                break;
            case 127:
                comentarios.add( conca ) ;
                String comentario2=conca.substring(1, conca.length()-1).trim();
                contadores[ 27 ]++;
                mensaje="Comentario: "+comentario2 + "\t\t" + " Codigo: 127-" + contadores[ 27 ];
                
                break;
        }
        return mensaje;
    }

    private String Error(int error){
        String comet=conca.substring(1, conca.length()).trim();
        switch(error){
            case 500: mensaje="Identificador incorrecto";
                break;
            case 501: mensaje="Comentario incorrecto: "+comet;
                i--;
                break;
            case 502:
                i--;
                if(conca.contains("/*")){
                    mensaje="Comentario incorrecto: "+comet.substring(1,comet.length());

                }else{
                    mensaje="Comentario incorrecto: "+comet;
                }
                break;
            case 503: mensaje="Caracter incorrecto: "+conca; 
                break;
            case 504: mensaje="Numero incorrecto: ";
                i--;
                break;
            case 505: mensaje ="Simbolo incorrecto: "+conca;
        }

        return mensaje;
    }



    public void semantico(){
        
        String semantico = "" ;
        
        for( String x : identificadores ){
            
            char [] identificador  = x.toCharArray() ;
            
            for( char y : identificador ){
                if( Character.isLowerCase(y) )
                    minusculas.add( ""+y+"" ) ;
                    else if( Character.isUpperCase(y) )
                        mayusculas.add( ""+y+"" ) ;
                    else if( Character.isDigit(y) )
                        digitos.add( ""+y+"" ) ;
                else 
                    simbolos.add( ""+y+"" ) ;    
            }
        }
        
        for( String x : numeros ){
            char [] numero = x.toCharArray() ;
            for( char y : numero ){
                if( Character.isDigit(y) )
                    digitos.add( ""+y+"" ) ;
                else 
                    simbolos.add( ""+y+"" ) ;   
            }
        }
        
        Collections.sort( minusculas ) ; 
        Collections.sort( mayusculas ) ;
        Collections.sort( digitos ) ; // 123 ...
            Collections.reverse(digitos ) ; // 321...
        Collections.sort( palres ) ;
        
        for( String x : minusculas ){
            semantico += x ; 
        }
        for( String x : mayusculas ){
            semantico += x ; 
        }
        for( String x : asignacion ){
            semantico += x ; 
        }
        for( String x : operadoresRelacionales ){
            semantico += x ; 
        }
        for( String x : operadoresLogicos ){
            semantico += x ; 
        }
        for( String x : digitos ){
            semantico += x ; 
        }
        for( String x : palres ){
            semantico += x ; 
        }
        for( String x : operadoresAritmeticos ){
            semantico += x ; 
        }
        for( String x : comentarios ){
            semantico += x ; 
        }
        for( String x : simbolos ){
            semantico += x ; 
        }
    
        //Collections.replaceAll(palres,null,"");

        areaSemantico.setText( semantico ) ;
    }
   
    public void Analizar(){
                estado=0;
                areaLexica.setText("");
                areaSemantico.setText("");
                minusculas.removeAll(minusculas);
                mayusculas.removeAll(mayusculas);
                numeros.removeAll(numeros);
                palres.removeAll(palres);
                //operadores.removeAll(operadores);
                comentarios.removeAll(comentarios);
                Analizar(areaCadena.getText()+"\n");
                semantico() ;
    }

  
    
    public static void main(String[] args) { 
        Interfaz i =new Interfaz();
        Analizador l=new Analizador();
        i.setVisible(true);
    }
}