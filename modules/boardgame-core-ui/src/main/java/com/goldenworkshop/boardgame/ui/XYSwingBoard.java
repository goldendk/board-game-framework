package com.goldenworkshop.boardgame.ui;

import com.goldenworkshop.boardgame.Board;
import com.goldenworkshop.boardgame.Player;
import com.goldenworkshop.boardgame.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class XYSwingBoard {
    private final static Logger logger = LoggerFactory.getLogger(XYSwingBoard.class);

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] boardSquares;
    private JPanel tileContainer;
    private final JLabel message;
    private Map<Player, Image> playerIcons = new HashMap<>();
    private final Image[][] chessPieceImages = new Image[2][6];
    private static final String COLS = "ABCDEFGH";
    public static final int QUEEN = 0, KING = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    public static final int[] STARTING_ROW = {
            ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
    };
    final Insets buttonMargin = new Insets(0, 0, 0, 0);
    public static final int BLACK = 0, WHITE = 1;

    public XYSwingBoard(String title, Board board) {
        message = new JLabel(title);
        initializeGui(board.getMaxY() + 1, board.getMaxX() + 1);
    }

    private void initializeGui(final int boardColumns, final int boardRows) {

        boardSquares = new JButton[boardColumns][boardRows];
        // create the images for the chess pieces
        createImages();

        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("New") {

            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("New game action clicked.");
                // setupNewGame();
            }
        };
        tools.add(newGameAction);
//        tools.add(new JButton("Save")); // TODO - add functionality!
//        tools.add(new JButton("Restore")); // TODO - add functionality!
//        tools.addSeparator();
//        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);


        this.tileContainer = new ScalingJPanel(new GridLayout(boardColumns, boardRows));

        this.tileContainer.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8),
                new LineBorder(Color.BLACK)
        ));
        // Set the BG to be ochre
        Color ochre = new Color(204, 119, 34);
        this.tileContainer.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(this.tileContainer);
        gui.add(boardConstrain);

        // create the board squares
        for (int xCoord = 0; xCoord < boardSquares.length; xCoord++) {
            for (int yCoord = 0; yCoord < boardSquares[xCoord].length; yCoord++) {
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                createTile(xCoord, yCoord);
            }
        }

        /*
         * fill the chess board
         */
        //  this.board.add(new JLabel(""));
//        // fill the top row
//        for (int ii = 0; ii < 8; ii++) {
//            this.board.add(
//                    new JLabel(COLS.substring(ii, ii + 1),
//                            SwingConstants.CENTER));
//        }
        // fill the black non-pawn piece row
        addTilesToTileContainer(boardColumns, boardRows);
    }

    public void updateTile(Tile tile){

    }

    private void addTilesToTileContainer(int boardColumns, int boardRows) {
        for (int yCoordinate = 0; yCoordinate < boardColumns; yCoordinate++) {
            for (int xCoordinate = 0; xCoordinate < boardRows; xCoordinate++) {
                switch (xCoordinate) {
//                    case 0:
//                        this.board.add(new JLabel("" + (9-(ii + 1)),
//                                SwingConstants.CENTER));
                    default:
                        this.tileContainer.add(boardSquares[yCoordinate][xCoordinate]);
                }
            }
        }
    }

    private void createTile(int xCoord, int yCoord) {
        JButton b = new JButton();
        b.setMargin(buttonMargin);
        ImageIcon icon = new ImageIcon(
                new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        b.setIcon(icon);
        b.setText(xCoord + "_" + yCoord);
        if ((yCoord % 2 == 1 && xCoord % 2 == 1)
                || (yCoord % 2 == 0 && xCoord % 2 == 0)) {
            b.setBackground(Color.WHITE);
        } else {
            b.setBackground(Color.BLACK);
        }
        boardSquares[xCoord][yCoord] = b;
    }

    /**
     * Registers the player on the board. Will do several things such as assign an icon for the player.
     * @param player
     */
    public void registerPlayer(Player player) {
        Image image = chessPieceImages[playerIcons.size()][KING];
        playerIcons.put(player, image);
    }

    public final JComponent getGui() {
        return gui;
    }

    private final void createImages() {
        try {
            URL url = XYSwingBoard.class.getResource("/images/chess-images.png");
            BufferedImage bi = ImageIO.read(url);
            for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    chessPieceImages[ii][jj] = bi.getSubimage(
                            jj * 64, ii * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Initializes the icons of the initial chess board piece places
     */
//    private final void setupNewGame() {
//        message.setText("Make your move!");
//        // set up the black pieces
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][0].setIcon(new ImageIcon(
//                    chessPieceImages[BLACK][STARTING_ROW[ii]]));
//        }
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][1].setIcon(new ImageIcon(
//                    chessPieceImages[BLACK][PAWN]));
//        }
//        // set up the white pieces
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][6].setIcon(new ImageIcon(
//                    chessPieceImages[WHITE][PAWN]));
//        }
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][7].setIcon(new ImageIcon(
//                    chessPieceImages[WHITE][STARTING_ROW[ii]]));
//        }
//    }


}
