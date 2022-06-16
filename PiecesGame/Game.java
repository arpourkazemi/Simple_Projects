import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private byte numberOfPlayers;
    private byte alivePlayers;
    private Player[] players;
    private Board gameBoard;
    private int numberOfMoves;

    Game(byte numberOfPlayers){
        setNumberOfPlayers(numberOfPlayers);
        players = new Player[this.getNumberOfPlayers()];
        setAlivePlayers(this.getNumberOfPlayers());
    }

    public void setNumberOfPlayers(byte numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public byte getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setAlivePlayers(byte alivePlayers) {
        this.alivePlayers = alivePlayers;
    }

    public byte getAlivePlayers() {
        return alivePlayers;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    void defaultSettings(byte horizontalLength, byte verticalLength){ // بارگزاری تنظیمات پیش‌فرض بازی
        for (int i = 0; i < 2; i++) {
            this.players[i] = new Player("بازیکن" + (i+1));
            this.players[i].setNumberOfPieces((byte) 6);
            this.players[i].setPieces(new Piece[6]);
            this.players[i].setAlivePieces((byte) 6);
            for (byte j = 0; j < 6; j++) {
                this.players[i].setPiece(new Piece(),j);
                this.players[i].getPiece(j).setPlayer(this.players[i]);
            }
        }
        this.players[0].getPiece((byte) 0).setAttackArrowAndIcon((byte) 5,"\u2004"+"\u2004"+"\u2006"+"⚫"+"\u2004"+"\u2005"+"\u2004","⚫");
        this.players[0].getPiece((byte) 1).setAttackArrowAndIcon((byte) 6,"\u2005"+"\u2006"+"\u2005"+"\uD83E\uDC82"+"\u2005"+"\u2006"+"\u2005","\uD83E\uDC82");
        this.players[0].getPiece((byte) 2).setAttackArrowAndIcon((byte) 4,"\u2005"+"\u2006"+"\u2005"+"\uD83E\uDC80"+"\u2005"+"\u2006"+"\u2005","\uD83E\uDC80");
        this.players[0].getPiece((byte) 3).setAttackArrowAndIcon((byte) 1,"\u2005"+"\u2004"+"\u2005"+"\uD83E\uDC87"+"\u2006"+"\u2004"+"\u2005","\uD83E\uDC87");
        this.players[0].getPiece((byte) 4).setAttackArrowAndIcon((byte) 2,"\u2005"+"\u2004"+"\u2005"+"\uD83E\uDC83"+"\u2005"+"\u2004"+"\u2005","\uD83E\uDC83");
        this.players[0].getPiece((byte) 5).setAttackArrowAndIcon((byte) 3,"\u2005"+"\u2004"+"\u2005"+"\uD83E\uDC86"+"\u2006"+"\u2004"+"\u2005","\uD83E\uDC86");


        this.players[1].getPiece((byte) 0).setAttackArrowAndIcon((byte) 5,"\u2004"+"\u2004"+"\u2006"+"⚪"+"\u2004"+"\u2005"+"\u2004","⚪");
        this.players[1].getPiece((byte) 1).setAttackArrowAndIcon((byte) 6,"\u2005"+"\u2006"+"\u2004"+"\uD83E\uDC62"+"\u2004"+"\u2006"+"\u2004","\uD83E\uDC62");
        this.players[1].getPiece((byte) 2).setAttackArrowAndIcon((byte) 4,"\u2005"+"\u2006"+"\u2004"+"\uD83E\uDC60"+"\u2004"+"\u2006"+"\u2004","\uD83E\uDC60");
        this.players[1].getPiece((byte) 3).setAttackArrowAndIcon((byte) 7,"\u2005"+"\u2005"+"\u2004"+"\uD83E\uDC64"+"\u2004"+"\u2004"+"\u2004","\uD83E\uDC64");
        this.players[1].getPiece((byte) 4).setAttackArrowAndIcon((byte) 8,"\u2005"+"\u2006"+"\u2004"+"\uD83E\uDC61"+"\u2004"+"\u2005"+"\u2004","\uD83E\uDC61");
        this.players[1].getPiece((byte) 5).setAttackArrowAndIcon((byte) 9,"\u2005"+"\u2005"+"\u2004"+"\uD83E\uDC65"+"\u2004"+"\u2004"+"\u2004","\uD83E\uDC65");


        this.gameBoard = new Board(horizontalLength,verticalLength);
        byte vertical;
        byte horizontal;

        for (byte i = 0; i < 2; i++) {
            for (byte j = 0; j < 6; j++) {
                if ( i == 0) vertical = 0;
                else vertical = (byte) (this.gameBoard.getVerticalLength()-1);
                vertical += getYChanges(this.players[i].getPiece(j).getAttackArrow());
                horizontal = (byte) (this.gameBoard.getHorizontalLength()/2);
                horizontal += getXChanges(this.players[i].getPiece(j).getAttackArrow());
                this.gameBoard.addPiece(horizontal,vertical,this.players[i].getPiece(j));
            }
        }

    }

    void run(){
        byte turn = -1;
        boolean turnCheck;
        gameMainLoop : // حلقه اصلی بازی
        while (this.alivePlayers > 1){
            this.gameBoard.printBoard();
            System.out.println("تعداد حرکات: "+this.getNumberOfMoves()+" مهره‌های بازیکن اول: "+this.players[0].getAlivePieces()+" مهره‌های بازیکن دوم: "+this.players[1].getAlivePieces());
            for (Player player : players) {  // حالتی که همه مهره ها بجز مهره ثابت زده شود
                if (player.getAlivePieces() == 1) {
                    player.setAlive(false);
                    break gameMainLoop;
                }
            }
            turnCheck = false;
            while (!turnCheck){
                turn++;
                if (turn == this.players.length) turn = 0;
                if (this.players[turn] == null) continue;
                if (!this.players[turn].isAlive()) turn++;
                if (this.players[turn].isAlive() && (turn != this.players.length)) turnCheck = true;
            }
            System.out.println("نوبت ("+players[turn].getName()+") است، عددی از اعداد زیر را به عنوان مهره انتخاب کنید:");
            movePiece(players[turn],players[turn].selectPiece());
            this.setNumberOfMoves(this.getNumberOfMoves()+1);
        }
        System.out.println("\n\n");
        this.gameBoard.printBoard();
        System.out.println("تعداد حرکات: "+this.getNumberOfMoves()+" مهره‌های بازیکن اول: "+this.players[0].getAlivePieces()+" مهره‌های بازیکن دوم: "+this.players[1].getAlivePieces());
        System.out.println("\n");
        for (Player player : players) {
            if (player.isAlive()) {
                System.out.println("تبریک! (" + player.getName() + ") برنده شد!");
                break;
            }
        }
    }


    void movePiece(Player player, byte selectedPiece){ // متد حرکت مهره
        Scanner input = new Scanner(System.in);
        boolean trueSelection = false;
        System.out.println("لطفا جهتی برای حرکت مهره انتخاب کنید(اعداد بین ۱ تا ۹ بجز ۵) :");
        byte select = 0;
        while (!trueSelection){
            try{
                select = input.nextByte();
                if (select == 5){ // جهت مرکزی
                    System.out.println("نمیتوان در این جهت حرکت کرد، مقدار دیگری را انتخاب کنید:");
                    trueSelection = false;
                    continue;
                }
                if (select < 1 || select >9){
                    System.out.println("جهت مورد نظر وجود ندارد، مقدار دیگری را انتخاب کنید:");
                    trueSelection = false;
                    continue;
                }
                if (canMoveTo(select,player,selectedPiece)) {
                    trueSelection = true;
                }
            } catch (InputMismatchException e){
                System.out.println("ورودی نامعتبر، لطفا دوباره امتحان کنید:");
                input.nextLine();
                trueSelection = false;
            }
        }
        byte x = findPieceX(player,selectedPiece); // مختصات مهره انتخاب شده از بازیکن
        byte y = findPieceY(player,selectedPiece); // مختصات مهره انتخاب شده از بازیکن
        byte xChange = getXChanges(select); // محاسبه تغییرات مختصات جهت حرکت
        byte yChange = getYChanges(select); // محاسبه تغییرات مختصات جهت حرکت
        if (this.gameBoard.getPiece((byte) (x+xChange),(byte) (y+yChange)) == null){     // حالتی که مقصد خانه خالی باشد
            this.gameBoard.addPiece((byte) (x+xChange),(byte) (y+yChange),this.gameBoard.getPiece(x,y));
            this.gameBoard.addPiece(x,y,null);
        }
        else if (!this.gameBoard.getPiece((byte) (x+xChange),(byte) (y+yChange)).getPlayer().equals(this.gameBoard.getPiece(x,y).getPlayer())){ // حالتی که حریف در مقصد است
            if (this.gameBoard.getPiece((byte) (x+xChange),(byte) (y+yChange)).getAttackArrow()==5){ // حمله به مهره هدف
                this.setAlivePlayers((byte) (this.getAlivePlayers()-1)); // کم کردن یکی از بازیکنان
                this.gameBoard.getPiece((byte) (x+xChange),(byte) (y+yChange)).getPlayer().setAlive(false); // تغییر خصیصه alive
            } else { // حمله به مهره غیر هدف
                this.gameBoard.getPiece((byte) (x + xChange), (byte) (y + yChange)).setAlive(false); // تغییر خصیصه alive
                Player player1 = this.gameBoard.getPiece((byte) (x + xChange), (byte) (y + yChange)).getPlayer(); //ذخیره بازیکن مهره مقصد
                player1.setAlivePieces((byte)( player1.getAlivePieces()-1)); // تغییر خصیصه alive
            }
            this.gameBoard.addPiece((byte) (x+xChange),(byte) (y+yChange),this.gameBoard.getPiece(x,y)); // جابجایی مهره به مقصد
            this.gameBoard.addPiece(x,y,null); // نال شدن مبدا
        }

    }

    boolean canMoveTo (byte moveArrow,Player player,byte selectedPiece){ // متد بررسی خانه مقصد
        byte horizontalMax = this.gameBoard.getHorizontalLength();
        byte verticalMax = this.gameBoard.getVerticalLength();
        byte x = findPieceX(player,selectedPiece);
        byte y = findPieceY(player,selectedPiece);
        byte xChange = getXChanges(moveArrow);
        byte yChange = getYChanges(moveArrow);
        if ( (x+xChange) == horizontalMax || (x+xChange) == -1 || (y+yChange) == verticalMax || (y+yChange) == -1) { // حالت برخورد به گوشه صفحه
            System.out.println("این خانه وجود ندارد، جهت حرکت دیگری را انتخاب کنید:");
            return false;
        }
        else if (this.gameBoard.getPiece((byte) (x+xChange),(byte) (y+yChange)) == null) return  true;  // حالتی که مقصد خالی باشد
        else if (this.gameBoard.getPiece((byte) (x+xChange),(byte) (y+yChange)).getPlayer().equals(this.gameBoard.getPiece(x,y).getPlayer())){ //حالت مقصد هم تیمی
            System.out.println("این مهره در تیم شماست، نمی‌توانید به این خانه بروید! جهت حرکت دیگری را انتخاب کنید:");
            return false;
        }
        else if ((!this.gameBoard.getPiece((byte) (x+xChange),(byte) (y+yChange)).getPlayer().equals(this.gameBoard.getPiece(x,y).getPlayer())) && (this.gameBoard.getPiece(x,y).getAttackArrow() == moveArrow)){
            return true; //حالت مقصد تیم مخالف، حرکت در جهت حمله
        } else if ((!this.gameBoard.getPiece((byte) (x+xChange),(byte) (y+yChange)).getPlayer().equals(this.gameBoard.getPiece(x,y).getPlayer())) && (this.gameBoard.getPiece(x,y).getAttackArrow() != moveArrow)){
            System.out.println("نمیتوانید به این مهره حمله کنید، دوباره امتحان کنید: ");
            return false;  //حالت مقصد تیم مخالف، حرکت خلاف جهت حمله
        }
        return false;
    }

    byte findPieceX(Player player, byte selectedPiece){ // دریافت مختصات یک مهره
        byte x = 0;
        mainLoop :
        for (byte i = 0; i < this.gameBoard.getHorizontalLength(); i++) {
            for (byte j = 0; j < this.gameBoard.getVerticalLength(); j++) {
                if (this.gameBoard.getPiece(i,j) == null) continue;
                if (this.gameBoard.getPiece(i,j).getPlayer().equals(player) && (this.gameBoard.getPiece(i,j).getAttackArrow() == selectedPiece)){
                    x = i;
                    break mainLoop;
                }
            }
        }
        return x;
    }

    byte findPieceY(Player player, byte selectedPiece){ // دریافت مختصات یک مهره
        byte y = 0;
        mainLoop :
        for (byte i = 0; i < this.gameBoard.getHorizontalLength(); i++) {
            for (byte j = 0; j < this.gameBoard.getVerticalLength(); j++) {
                if (this.gameBoard.getPiece(i,j) == null) continue;
                if (this.gameBoard.getPiece(i,j).getPlayer().equals(player) && (this.gameBoard.getPiece(i,j).getAttackArrow() == selectedPiece)){
                    y = j;
                    break mainLoop;
                }
            }
        }
        return y;
    }

    //تغییرات مختصاتی جهات حرکت
    static byte getXChanges(byte attackArrow){
        if (attackArrow == 1 || attackArrow == 4 || attackArrow == 7) return -1;
        if (attackArrow == 2 || attackArrow == 5 || attackArrow == 8) return 0;
        if (attackArrow == 3 || attackArrow == 6 || attackArrow == 9) return 1;
        return 0;
    }

    static byte getYChanges(byte attackArrow){
        if (attackArrow == 1 || attackArrow == 2 || attackArrow == 3) return 1;
        if (attackArrow == 4 || attackArrow == 5 || attackArrow == 6) return 0;
        if (attackArrow == 7 || attackArrow == 8 || attackArrow == 9) return -1;
        return 0;
    }



}
