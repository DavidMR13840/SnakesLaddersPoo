package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TableroGUI {
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[10][10];
    private ImageIcon[][] chessPieceImages = new ImageIcon[3][14];
    private JPanel chessBoard;
    private JPanel interactJPanel = new JPanel(new GridBagLayout());
    protected Board_leftPanel players_panel = new Board_leftPanel();
    private  GridBagConstraints c = new GridBagConstraints();
    private final JLabel message = new JLabel(
            "Serpiente y Escaleras");
    private static final String COLS = "ABCDEFGHIJ";
    public static final int BLACK = 0, WHITE = 1;
    protected JButton startButton = new JButton("Start Game"), rollDiceButton = new JButton("Roll Dice");
    TableroGUI() {
        initializeGui();
    }
    public final void initializeGui() {
        createImages();
        gui.setBackground(Color.darkGray);
        interactJPanel.setBackground(Color.darkGray);
    	gui.setBorder(new EmptyBorder(50, 50, 50, 50));
        gui.add(interactJPanel, BorderLayout.PAGE_END);
        gui.add(players_panel, BorderLayout.LINE_START);
       
        c.insets = new Insets(1, 0, 1, 0);  
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        interactJPanel.add(startButton, c);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.gridx = 1;
        c.gridy = 0;
        interactJPanel.add(rollDiceButton, c);
        
        


        chessBoard = new JPanel(new GridLayout(0, 11)) {

            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstaint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(0,0,0,0),
                new LineBorder(Color.BLACK)
                ));
        Color ochre = new Color(0,119,34);
        chessBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.add(chessBoard);
        boardConstrain.setBackground(Color.darkGray);
        gui.add(boardConstrain);
        Insets buttonMargin = new Insets(-10, -10, -10, -10);
        String Bnumero;
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
        	Bnumero="";
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setBackground(Color.WHITE);
                
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setIcon(null);
                if (ii % 2 == 1){
                	if(jj%10==0) {
                		
                		Bnumero=Integer.toString(100-ii*10);
                		b.setText(Bnumero);
                	}else {
                		//b.setBackground(Color.white);
                    	Bnumero=Integer.toString(10-ii-1);
                    	Bnumero=Bnumero+Integer.toString(10-jj);
                        b.setText(Bnumero);
                	}
                    
                    
                } else {
                	if(jj==9) {
                		Bnumero=Integer.toString(100-ii*10);
                		b.setText(Bnumero);
                	}else {
                		Bnumero=Integer.toString(10-ii-1);
                    	 Bnumero=Bnumero+Integer.toString(jj+1);
                        b.setText(Bnumero);
                	}
                	 
                }
                chessBoardSquares[jj][ii] = b;
            }
        }
        pintarEscalerasYSerpientes();
        moverFicha(1,1,1,1,1);
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 10; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                    SwingConstants.CENTER));
        }
        for (int ii = 0; ii < 10; ii++) {
            for (int jj = 0; jj < 10; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (11-(ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }

    }
    
    public void setPlayersLabels(ArrayList<String> players_labels){
        switch(players_labels.size()){
            case 4:
                players_panel.player4_boardl.setText(players_labels.get(3));
                players_panel.player3_board.setText(players_labels.get(2));
                players_panel.player1_board.setText(players_labels.get(0));
                players_panel.player2_board.setText(players_labels.get(1));
                break;
            case 3:
                players_panel.player3_board.setText(players_labels.get(2));
                players_panel.player1_board.setText(players_labels.get(0));
                players_panel.player2_board.setText(players_labels.get(1));
                break;
            case 2:
                players_panel.player1_board.setText(players_labels.get(0));
                players_panel.player2_board.setText(players_labels.get(1));
                break;
            default:
                break;
        }
    }
    
    public  void setActiveP_label(String name){
        players_panel.playerTurn_label.setText(name + "'s turn");
    }
    public void disableStart(){
        startButton.setEnabled(false);
    }
    public final JComponent getGui() {
        return gui;
    }
    public void moverFicha(int nuevo,int Ant) {
        int ii=Ant/10;
        int jj=Ant%10;
        JButton b = chessBoardSquares[jj][ii];
        if (ii % 2 == 1){
            if(jj%10==0) {
                if(nuevo==100-ii*10){
                    b.setIcon(null);
                }
            }else {
                if(nuevo==(10-jj)*10+10-ii-1){
                    b.setIcon(null);
                }
            }
        } else {
            if(jj==9) {
                if(nuevo==100-ii*10){
                    b.setIcon(null);
                }else {
                    if(nuevo==(10-ii-1)*10+jj+1){
                        b.setIcon(null);
                    }
                }
            }
        }
        chessBoardSquares[jj][ii] = b;
    }
    
 public void displayDice(int dice_value){
        players_panel.displayDice(dice_value);
    }
            
public void pintarEscalerasYSerpientes(){
        //65, 50);
        JButton b=chessBoardSquares[2][9-1];
        b.setText("");
        ImageIcon icon=chessPieceImages[1][1];
        b.setIcon(icon);
        b=chessBoardSquares[6][9-3];
        b.setText("");
        icon=chessPieceImages[1][0];
        b.setIcon(icon);
        b=chessBoardSquares[5][9-6];
        b.setText("");
        icon=chessPieceImages[1][2];
        b.setIcon(icon);
        b=chessBoardSquares[0][9-4];
        b.setText("");
        icon=chessPieceImages[1][3];
        b.setIcon(icon);
        b=chessBoardSquares[9][9-7];
        b.setText("");
        icon=chessPieceImages[1][4];
        b.setIcon(icon);
        b=chessBoardSquares[10-2][9-0];
        b.setText("");
        icon=chessPieceImages[1][5];
        b.setIcon(icon);
        //87, 51
        b=chessBoardSquares[3][9-8];
        b.setText("");
        icon=chessPieceImages[1][6];
        b.setIcon(icon);
        b=chessBoardSquares[0][9-5];
        b.setText("");
        icon=chessPieceImages[1][7];
        b.setIcon(icon);
        //99, 15
        b=chessBoardSquares[8][9-9];
        b.setText("");
        icon=chessPieceImages[1][8];
        b.setIcon(icon);
        b=chessBoardSquares[4][9-1];
        b.setText("");
        icon=chessPieceImages[1][9];
        b.setIcon(icon);
        //71, 34
        b=chessBoardSquares[0][9-7];
        b.setText("");
        icon=chessPieceImages[1][10];
        b.setIcon(icon);
        b=chessBoardSquares[3][9-3];
        b.setText("");
        icon=chessPieceImages[1][11];
        b.setIcon(icon);
        //91, 66
        b=chessBoardSquares[0][9-9];
        b.setText("");
        icon=chessPieceImages[1][12];
        b.setIcon(icon);
        b=chessBoardSquares[4][9-6];
        b.setText("");
        icon=chessPieceImages[1][13];
        b.setIcon(icon);
        //4, 79
        b=chessBoardSquares[6][9-0];
        b.setText("");
        icon=chessPieceImages[2][0];
        b.setIcon(icon);
        b=chessBoardSquares[8][9-7];
        b.setText("");
        icon=chessPieceImages[2][1];
        b.setIcon(icon);
        //10, 28
        b=chessBoardSquares[0][9-0];
        b.setText("");
        icon=chessPieceImages[2][2];
        b.setIcon(icon);
        b=chessBoardSquares[10-8][9-2];
        b.setText("");
        icon=chessPieceImages[2][3];
        b.setIcon(icon);
        //20, 98
        b=chessBoardSquares[9][9-1];
        b.setText("");
        icon=chessPieceImages[2][4];
        b.setIcon(icon);
        b=chessBoardSquares[7][9-9];
        b.setText("");
        icon=chessPieceImages[2][5];
        b.setIcon(icon);
        //25, 67
        b=chessBoardSquares[5][9-2];
        b.setText("");
        icon=chessPieceImages[2][6];
        b.setIcon(icon);
        b=chessBoardSquares[3][9-6];
        b.setText("");
        icon=chessPieceImages[2][7];
        b.setIcon(icon);
        //47, 76
        b=chessBoardSquares[3][9-4];
        b.setText("");
        icon=chessPieceImages[2][8];
        b.setIcon(icon);
        b=chessBoardSquares[5][9-7];
        b.setText("");
        icon=chessPieceImages[2][9];
        b.setIcon(icon);
        //77, 95
        b=chessBoardSquares[6][9-7];
        b.setText("");
        icon=chessPieceImages[2][10];
        b.setIcon(icon);
        b=chessBoardSquares[4][9-9];
        b.setText("");
        icon=chessPieceImages[2][11];
        b.setIcon(icon);
        //82, 93
        b=chessBoardSquares[10-2][9-8];
        b.setText("");
        icon=chessPieceImages[2][12];
        b.setIcon(icon);
        b=chessBoardSquares[2][9-9];
        b.setText("");
        icon=chessPieceImages[2][13];
        b.setIcon(icon);
    }
    /*
    este metodo carga las imagenes en la lista de iconos
    */
    private void createImages() {
        ImageIcon imagen= new  ImageIcon(getClass().getResource("/imagenes/i0j0.png"));
        Image img = imagen.getImage() ;  
        Image newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[0][0]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i0j1.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[0][1]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i0j2.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[0][2]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i0j3.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[0][3]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j0.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][0]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j1.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][1]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j2.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][2]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j3.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][3]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j4.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][4]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j5.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][5]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j6.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][6]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j7.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][7]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j8.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][8]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j9.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][9]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j10.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][10]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j12.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][12]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j13.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][13]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i1j11.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[1][11]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j0.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][0]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j1.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][1]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j2.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][2]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j3.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][3]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j4.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][4]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j5.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][5]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j6.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][6]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j7.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][7]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j8.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][8]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j9.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][9]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j10.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][10]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j11.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][11]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j12.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][12]=imagen;
        imagen= new  ImageIcon(getClass().getResource("/imagenes/i2j13.png"));
        img = imagen.getImage() ;  
        newimg = img.getScaledInstance( 64, 64,  java.awt.Image.SCALE_SMOOTH ) ;  
        imagen = new ImageIcon( newimg );
        chessPieceImages[2][13]=imagen;
    }
    
    public void pintarJugadores(int jug1,int jug2,int jug3,int jug4){
        JButton b;
        int ii;
        int jj;
        ii=jug1/10;
        jj=jug1%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                b=chessBoardSquares[0][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[0][10-ii]=b;
            }else {
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setText("");
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                b=chessBoardSquares[9][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[9][10-ii]=b;
            }else {
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        
        ii=jug2/10;
        jj=jug2%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                b=chessBoardSquares[0][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[0][10-ii]=b;
            }else {
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                b=chessBoardSquares[9][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[9][10-ii]=b;
            }else {
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        ii=jug3/10;
        jj=jug3%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                b=chessBoardSquares[0][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][2]);
                chessBoardSquares[0][10-ii]=b;
            }else {
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setIcon(chessPieceImages[0][2]);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                b=chessBoardSquares[9][10-ii];
                b.setIcon(chessPieceImages[0][2]);
                b.setText("");
                chessBoardSquares[9][10-ii]=b;
            }else {
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setText("");
                b.setIcon(chessPieceImages[0][2]);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        ii=jug4/10;
        jj=jug4%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                b=chessBoardSquares[0][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][3]);
                chessBoardSquares[0][10-ii]=b;
            }else {
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setIcon(chessPieceImages[0][3]);
                b.setText("");
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                b=chessBoardSquares[9][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][3]);
                chessBoardSquares[9][10-ii]=b;
            }else {
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setText("");
                b.setIcon(chessPieceImages[0][3]);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
    }
    public void pintarJugadores(int jug1,int jug2,int jug3){
        JButton b;
        int ii;
        int jj;
        ii=jug1/10;
        jj=jug1%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                b=chessBoardSquares[0][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[0][10-ii]=b;
            }else {
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setText("");
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                b=chessBoardSquares[9][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[9][10-ii]=b;
            }else {
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        
        ii=jug2/10;
        jj=jug2%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                b=chessBoardSquares[0][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[0][10-ii]=b;
            }else {
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                b=chessBoardSquares[9][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[9][10-ii]=b;
            }else {
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        ii=jug3/10;
        jj=jug3%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                b=chessBoardSquares[0][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][2]);
                chessBoardSquares[0][10-ii]=b;
            }else {
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setIcon(chessPieceImages[0][2]);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                b=chessBoardSquares[9][10-ii];
                b.setIcon(chessPieceImages[0][2]);
                b.setText("");
                chessBoardSquares[9][10-ii]=b;
            }else {
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setText("");
                b.setIcon(chessPieceImages[0][2]);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
    }
    
    public void pintarJugadores(int jug1,int jug2){
        JButton b;
        int ii;
        int jj;
        ii=jug1/10;
        jj=jug1%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                b=chessBoardSquares[0][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[0][10-ii]=b;
            }else {
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setText("");
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                b=chessBoardSquares[9][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[9][10-ii]=b;
            }else {
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setIcon(chessPieceImages[0][0]);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        
        ii=jug2/10;
        jj=jug2%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                b=chessBoardSquares[0][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[0][10-ii]=b;
            }else {
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                b=chessBoardSquares[9][10-ii];
                b.setText("");
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[9][10-ii]=b;
            }else {
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setIcon(chessPieceImages[0][1]);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        
    }
    
    public void moverFicha(int Ant,int jug1,int jug2,int jug3,int jug4){
        JButton b;
        
        String Bnumero;
        int ii=Ant/10;
        int jj=Ant%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                Bnumero=Integer.toString(ii*10);
                b=chessBoardSquares[0][10-ii];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[0][10-ii]=b;
            }else {
                Bnumero=Integer.toString(ii);
                Bnumero=Bnumero+Integer.toString(jj);
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                Bnumero=Integer.toString(ii*10);
                b=chessBoardSquares[9][10-ii];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[9][10-ii]=b;
            }else {
                Bnumero=Integer.toString(ii);
                Bnumero=Bnumero+Integer.toString(jj);
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        pintarEscalerasYSerpientes();
        pintarJugadores(jug1,jug2,jug3,jug4);
        

                        
    }
    public void moverFicha(int Ant,int jug1,int jug2){
        JButton b;
        
        String Bnumero;
        int ii=Ant/10;
        int jj=Ant%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                Bnumero=Integer.toString(ii*10);
                b=chessBoardSquares[0][10-ii];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[0][10-ii]=b;
            }else {
                Bnumero=Integer.toString(ii);
                Bnumero=Bnumero+Integer.toString(jj);
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                Bnumero=Integer.toString(ii*10);
                b=chessBoardSquares[9][10-ii];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[9][10-ii]=b;
            }else {
                Bnumero=Integer.toString(ii);
                Bnumero=Bnumero+Integer.toString(jj);
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        pintarEscalerasYSerpientes();
        pintarJugadores(jug1,jug2);
        

                        
    }
    
  public void displayActive_pos(int pos){
        players_panel.displayActive_pos(pos);
    }
    public void moverFicha(int Ant,int jug1,int jug2,int jug3){
        JButton b;
        
        String Bnumero;
        int ii=Ant/10;
        int jj=Ant%10;
        if (ii % 2 == 1){
            if(jj%10==0) {
                Bnumero=Integer.toString(ii*10);
                b=chessBoardSquares[0][10-ii];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[0][10-ii]=b;
            }else {
                Bnumero=Integer.toString(ii);
                Bnumero=Bnumero+Integer.toString(jj);
                b=chessBoardSquares[jj-1][10-ii-1];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[jj-1][10-ii-1]=b;
            }
        } else {
            if(jj==0) {
                Bnumero=Integer.toString(ii*10);
                b=chessBoardSquares[9][10-ii];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[9][10-ii]=b;
            }else {
                Bnumero=Integer.toString(ii);
                Bnumero=Bnumero+Integer.toString(jj);
                b=chessBoardSquares[10-jj][10-ii-1];
                b.setIcon(null);
                b.setText(Bnumero);
                chessBoardSquares[10-jj][10-ii-1]=b;
            }
        }
        pintarEscalerasYSerpientes();
        pintarJugadores(jug1,jug2,jug3);
        

                        
    }

}
