import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class Calculator {
    private JTextField introducerespuesta;
    private JButton enviarRespuesta;
    private JLabel mensaje;
    private JLabel problema;
    private JLabel pregunta;
    private JPanel Calculatorview;
    private String[] operadores={"+","-","*"};
    private ArrayList operacionresult=new ArrayList();
    private int clicks=0;

    private void createUIComponents() {
        Calculatorview = new JPanel();
    }
    public Calculator(){
        enviarRespuesta.addActionListener(new EnviarRespuesta());
    }
    
    private ArrayList generarproblemas() {
        Random rnd = new Random();
        int primerOperador = rnd.nextInt(12)+1;
        int segundoOperador = rnd.nextInt(12)+1;
        int posicionOperador = rnd.nextInt(operadores.length);
        int resultado=calcularoperacion(primerOperador,operadores[posicionOperador],segundoOperador);
        String operacion= String.valueOf(primerOperador+operadores[posicionOperador]+segundoOperador);
        System.out.println(primerOperador+operadores[posicionOperador]+segundoOperador+"="+resultado);
        operacionresult=new ArrayList();
        operacionresult.add(operacion);
        operacionresult.add(resultado);
        return operacionresult;
    }
    private int calcularoperacion(int operador1,String operador,int operador2){
        int resultado = 0;
        if(operador.contains("+")){
            resultado=operador1+operador2;
        }if(operador.contains("-")){
            resultado=operador1-operador2;
        }if(operador.contains("*"))
            resultado=operador1*operador2;
        return resultado;
    }
    private void cambiar_start_comprovar(int clicks){
        if(clicks>0){
            enviarRespuesta.setText("Comprovar");
        }
    }
    private boolean comprovar_resultado(){
        if(Integer.valueOf(introducerespuesta.getText())==(operacionresult.get(1))){
            return true;
        }else{
            return false;
        }
    }

    private class EnviarRespuesta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            generarproblemas();
            problema.setText(operacionresult.get(0).toString());
            if (comprovar_resultado()){
                mensaje.setText("Correcto");
            }else{
                mensaje.setText("Incorrecto");
            }
            clicks=clicks+1;
            cambiar_start_comprovar(clicks);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora Mental");
        frame.setContentPane(new Calculator().Calculatorview);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
        Toolkit miPantalla=Toolkit.getDefaultToolkit();
        Dimension tamanopantalla=miPantalla.getScreenSize();
        int alturapantalla=tamanopantalla.height;
        int anchurapantalla= tamanopantalla.width;
        frame.setSize(anchurapantalla/2,alturapantalla/2);
        frame.setLocation(anchurapantalla/4,alturapantalla/4);
    }
}
