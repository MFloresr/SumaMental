import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringJoiner;


public class Calculator {
    private JTextField introducerespuesta;
    private JButton enviarRespuesta;
    private JLabel mensaje;
    private JLabel problema;
    private JLabel pregunta;
    private JLabel tiempo;
    private JPanel Calculatorview;
    private String[] operadores={"+","-","*"};
    private ArrayList operacionresult=new ArrayList();
    private  Timer timer;
    private ActionListener controltime;
    private int contadortime=0;
    private int contador=3;
    private int hora=0;
    private int minuto=0;
    private int segundo=0;


    private void createUIComponents() {
        Calculatorview = new JPanel();
    }
    public Calculator(){
        introducerespuesta.setEnabled(false);
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
    private boolean comprovar_resultado(){
        String resultado=String.valueOf(operacionresult.get(1));
        System.out.println(resultado);
        System.out.println(introducerespuesta.getText());
        if(introducerespuesta.getText().equals(resultado)){
            return true;
        }else{
            return false;
        }
    }
    private boolean comprovarboton(){
        boolean botoninicial=enviarRespuesta.getText().contains("Start");
        if(botoninicial){
            return true;
        }else{
            return false;
        }
    }
    private void controltiempo(){
        controltime = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                contadortime++;
                if(contadortime==61){
                    contadortime=0;
                    segundo++;
                }if(segundo==61){
                    minuto++;
                    segundo=0;
                }if(minuto==61){
                    hora++;
                    minuto=0;
                }
                System.out.println(hora+":"+minuto+":"+segundo+":"+contadortime);
                tiempo.setText(String.valueOf(hora+":"+minuto+":"+segundo));
            }
        };
    }
    private boolean contadorresultados(){
        if(contador==0){
            return true;
        }else{
            return false;
        }

    }
    private class EnviarRespuesta implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(comprovarboton()){
                enviarRespuesta.setText("Enviar");
                introducerespuesta.setEnabled(true);
                generarproblemas();
                problema.setText(operacionresult.get(0).toString());
                controltiempo();
                timer = new javax.swing.Timer(1, controltime);
                timer.start();
            }else{
                if(comprovar_resultado()){
                    contador--;
                    if(contadorresultados()){
                        timer.stop();
                        mensaje.setText("Correcto");
                        enviarRespuesta.setEnabled(false);
                        introducerespuesta.setEnabled(false);
                    }else{
                        generarproblemas();
                        problema.setText(operacionresult.get(0).toString());
                        mensaje.setText("Correcto");
                        introducerespuesta.setText("");
                    }
                }else{
                    mensaje.setText("Incorrecto");
                    introducerespuesta.setText("");
                }
            }
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
