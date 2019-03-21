
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TableroGUI {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[10][10];
    private Image[][] chessPieceImages = new Image[2][14];
    private JPanel chessBoard;
    private final JLabel message = new JLabel(
            "Serpiente y Escaleras");
    private static final String COLS = "ABCDEFGHIJ";
    public static final int KING = 0, QUEEN = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    public static final int[] STARTING_ROW = {
        ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
    };
    public static final int BLACK = 0, WHITE = 1;
    
    TableroGUI() {
        initializeGui();
    }
    public final void initializeGui() {
    	gui.setBorder(new EmptyBorder(50, 50, 50, 50));
    	JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("Comenzar") {

            @Override
            public void actionPerformed(ActionEvent e) {
                setupNewGame();
            }
        };
        tools.add(newGameAction);
        tools.add(new JButton("Salvar")); // TODO - add functionality!
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Guardar")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);
        gui.add(new JLabel("?"), BorderLayout.LINE_START);

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
        gui.add(boardConstrain);
        Insets buttonMargin = new Insets(-10, -10, -10, -10);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    //b.setBackground(Color.white);
                    b.setText("algo");
                } else {
                    b.setBackground(Color.WHITE);
                    b.setText("algo");
                }
                chessBoardSquares[jj][ii] = b;
            }
        }
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
    public final JComponent getGui() {
        return gui;
    }
    
    public escalerasYSer[] CrearFichas(escalerasYSer[] pFichas) {
    	pFichas[0]=new escalerasYSer(null, null,"Lujuria", 37, 13);
    	pFichas[1]=new escalerasYSer(null, null,"Pereza", 65, 50);
    	pFichas[2]=new escalerasYSer(null, null,"Ira", 80, 2);
    	pFichas[3]=new escalerasYSer(null, null,"Envidia", 87, 51);
    	pFichas[4]=new escalerasYSer(null, null,"Soberbia", 99, 15);
    	pFichas[5]=new escalerasYSer(null, null,"Gula", 71, 34);
    	pFichas[6]=new escalerasYSer(null, null,"Avaricia", 91, 66);
    	pFichas[7]=new escalerasYSer(null, null,"Humildad", 4, 79);
    	pFichas[8]=new escalerasYSer(null, null,"Generosidad", 10, 28);
    	pFichas[9]=new escalerasYSer(null, null,"Abstinencia", 20, 98);
    	pFichas[10]=new escalerasYSer(null, null,"Paciencia", 25, 67);
    	pFichas[11]=new escalerasYSer(null, null,"Templanza", 47, 76);
    	pFichas[12]=new escalerasYSer(null, null,"Caridad", 77, 95);
    	pFichas[13]=new escalerasYSer(null, null,"Diligencia", 82, 93);
    	return pFichas;
    	
    }
    public static void main(String[] args) {
    	//escalerasYSer[] Fichas = new escalerasYSer[14];
    	//Fichas=CrearFichas(Fichas);
    	
    	
        Runnable r = new Runnable() {

            @Override
            public void run() {
            	TableroGUI cg = new TableroGUI();

                JFrame f = new JFrame("Serpientes Y escaleras");
                f.add(cg.getGui());
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // See https://stackoverflow.com/a/7143398/418556 for demo.
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

}
