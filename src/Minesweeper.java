import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;


public class Minesweeper implements ActionListener {

    JFrame frame = new JFrame("Minesweeper");
    JButton reset = new JButton("reset");
    JButton[][] buttons = new JButton[20][20];
    int[][] count = new int[20][20];
    Container grid = new Container();
    final int MINE = 10;
    int noofmines = 0;

    public Minesweeper(){
        frame.setSize(400,400);
        frame.setLayout(new BorderLayout());
        frame.add(reset, BorderLayout.NORTH);
        reset.addActionListener(this);

        grid.setLayout(new GridLayout(20,20));

        for(int a=0;a <buttons.length; a++) {
            for (int b = 0; b < buttons.length; b++) {
                buttons[a][b] = new JButton();
                buttons[a][b].addActionListener(this);
                grid.add(buttons[a][b]);
            }
        }
        frame.add(grid,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        createRandomMines();
    }


    public static void main(String[] args){

        new Minesweeper();
    }

    public void createRandomMines() {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < count.length; i++)
            for (int j = 0; j < count[0].length; j++) {
                list.add(100 * j + i);
            }
            count = new int[20][20];

            //for 30 mines

        for(int a = 0; a < 50; a++){

                int choice = (int)(Math.random() * list.size());
                count[list.get(choice) / 100][list.get(choice) % 100] = MINE;
                list.remove(choice);
        }



        for(int a=0;a<count.length;a++){
            for(int b=0;b<count[0].length;b++){
                if (count[a][b]!=MINE){
                    noofmines=0;
                    if (a > 0 && b > 0 && count[a - 1][b - 1] == MINE)//up left
                        noofmines++;
                    if (a > 0 && count[a-1][b] == MINE) // up
                        noofmines++;
                    if (a < count.length - 1 && b < count.length - 1 && count[a + 1][b + 1] == MINE) // bottom right
                        noofmines++;
                    if (a<count.length-1&&count[a+1][b]==MINE)//  down
                        noofmines++;
                    if (a>0&&b<count.length-1&&count[a-1][b+1]==MINE) //up right
                    noofmines++;
                    if (a<count.length-1&&b>0&&count[a+1][b-1]==MINE)//down left
                        noofmines++;
                    if (b>0&&count[a][b-1]==MINE)// left
                        noofmines++;
                    if(b<count.length-1&&count[a][b+1]==MINE)// right
                        noofmines++;
                        count[a][b]=noofmines;
                }
            }
        }
    }

    public void checkWin(){

        boolean win = true;
        for(int i=0;i<count.length;i++){
            for (int j = 0; j < count.length; j++) {
                if(count[i][j]!=MINE&&buttons[i][j].isEnabled()==true)
                    win = false;
            }
        }
        if(win == true)
            JOptionPane.showMessageDialog(frame,"you win!");
    }

    public void GameOver(){
        System.out.print("game over");
        for(int i=0;i<count.length;i++){
            for(int j=0;j<count[0].length;j++){
                if(count[i][j]!=MINE){
                    buttons[i][j].setText(count[i][j]+"");
                    buttons[i][j].setEnabled(false);

                }
                else{
                    buttons[i][j].setText("X");
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }

    public void CallZeros(ArrayList<Integer> toClear){
        if(toClear.size()==0)
        {
            return ;
        }

        else{

            int x = toClear.get(0) /100;
            int y = toClear.get(0)%100;
            toClear.remove(0);
            if(count[x][y]==0){
                if(x>0&&y>0&&buttons[x-1][y-1].isEnabled()){ //top left
                    buttons[x-1][y-1].setText(count[x-1][y-1]+"");
                    buttons[x-1][y-1].setEnabled(false);
                    if(count[x-1][y-1]==0){
                        toClear.add((x-1)*100+(y-1));
                    }
                }
                if(y>0&&buttons[x][y-1].isEnabled()){// top
                    buttons[x][y-1].setText(count[x][y-1]+"");
                    buttons[x][y-1].setEnabled(false);
                    if(count[x][y-1]==0){
                        toClear.add((x)*100+y-1);
                    }
                }
                if(y<count.length-1&&buttons[x][y+1].isEnabled()){//bottom
                    buttons[x][y+1].setText(count[x][y+1]+"");
                    buttons[x][y+1].setEnabled(false);
                    if(count[x][y+1]==0){
                        toClear.add(x*100+y+1);
                    }
                }
                if(x>0&&buttons[x-1][y].isEnabled()){//left
                    buttons[x-1][y].setText(count[x-1][y]+"");
                    buttons[x-1][y].setEnabled(false);
                    if(count[x-1][y]==0){
                        toClear.add((x-1)*100+y);
                    }
                }
                if(x<count.length-1&&buttons[x+1][y].isEnabled()){//right
                    buttons[x+1][y].setText(count[x+1][y]+"");
                    buttons[x+1][y].setEnabled(false);
                    if(count[x+1][y]==0){
                        toClear.add((x+1)*100+y);
                    }
                }
                if(x<count.length-1&&y<count.length-1&&buttons[x+1][y+1].isEnabled()){//bottom right
                    buttons[x+1][y+1].setText(count[x+1][y+1]+"");
                    buttons[x+1][y+1].setEnabled(false);
                    if(count[x+1][y+1]==0){
                        toClear.add((x+1)*100+y+1);
                    }
                }
                if(x<count.length-1&&y>0&&buttons[x+1][y-1].isEnabled()){//top right
                    buttons[x+1][y-1].setText(count[x+1][y-1]+"");
                    buttons[x+1][y-1].setEnabled(false);
                    if(count[x+1][y-1]==0){
                        toClear.add((x+1)*100+y-1);
                    }
                }
                if(x>0-1&&y<count.length-1&&buttons[x-1][y+1].isEnabled()){//bottom left
                    buttons[x-1][y+1].setText(count[x-1][y+1]+"");
                    buttons[x-1][y+1].setEnabled(false);
                    if(count[x-1][y+1]==0){
                        toClear.add((x-1)*100+y+1);
                    }
                }
            }
        }
        CallZeros(toClear);
    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource().equals(reset)){

            for(int i=0;i<count.length;i++){
                for(int j=0;j<count[0].length;j++){
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setText("");
                }
                createRandomMines();
            }
        }
        else { // a button was hit
            for(int x=0;x<buttons.length;x++){
                for(int y=0;y<buttons[0].length;y++){

                    if(event.getSource().equals(buttons[x][y])){ // Checking if button has been pressed

                        if(count[x][y]==MINE){
                            GameOver();
                        }
                        else if(count[x][y]==0){
                            //System.out.print("call recursive call zero func");
                            buttons[x][y].setText(count[x][y]+"");
                            buttons[x][y].setEnabled(false);
                            ArrayList<Integer> toClear = new ArrayList<Integer>();
                            toClear.add(x*100+y);
                            CallZeros(toClear);
                            checkWin();
                        }
                        else {
                            buttons[x][y].setText(count[x][y] + "");
                            buttons[x][y].setEnabled(false);
                            checkWin();
                        }
                    }
                }
            }
        }
    }
}
