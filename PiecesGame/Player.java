import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private String name;
    private byte numberOfPieces;
    private Piece[] pieces;
    private boolean alive;
    private byte alivePieces;

    Player(String name){
        setName(name);
        alive = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumberOfPieces(byte numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }

    public byte getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setPieces(Piece[] pieces) {
        this.pieces = pieces;
    }

    public Piece[] getPieces() {
        return pieces;
    }

    public void setPiece(Piece piece, byte index){
        this.pieces[index] = piece;
    }

    public Piece getPiece(byte index){
        return pieces[index];
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlivePieces(byte alivePieces) {
        this.alivePieces = alivePieces;
    }

    public byte getAlivePieces() {
        return alivePieces;
    }

    Piece getPieceWithAttackArrow(byte attackArrow){
        for (byte i = 0; i < this.getPieces().length; i++) {
            if (this.getPiece(i).getAttackArrow() == attackArrow) return this.getPiece(i);
        }
        return null;
    }


    byte selectPiece(){ // متد انتخاب مهره توسط یک بازیکن
        Scanner input = new Scanner(System.in);
        byte select = 0;
        boolean trueSelection;
        for (byte i = 0; i < this.getPieces().length; i++) {
            if (this.getPiece(i).isAlive() && (this.getPiece(i).getAttackArrow() != 5)) {
                System.out.print(this.getPiece(i).getAttackArrow() + ") " + this.getPiece(i).getAbsoluteIcon() + "  ");
            }
        }
        trueSelection = false;
        while (!trueSelection){
            try {
                select = input.nextByte();
                if (select == 5) {
                    System.out.println("این مهره ثابت است، لطفا مهره دیگری انتخاب کنید:");
                    trueSelection = false;
                } else if (this.getPieceWithAttackArrow(select) == null){
                    System.out.println("این مهره وجود ندارد، لطفا مهره دیگری انتخاب کنید:");
                    trueSelection = false;
                } else if (!this.getPieceWithAttackArrow(select).isAlive()){
                    System.out.println("این مهره وجود ندارد، لطفا مهره دیگری انتخاب کنید:");
                    trueSelection = false;
                }else if (this.getPieceWithAttackArrow(select).isAlive()){
                    trueSelection = true;
                }
            } catch (InputMismatchException e){
                System.out.println("ورودی نامعتبر، دوباره تلاش کنید:");
                input.nextLine();
                trueSelection = false;
            }
        }
        return select;
    }
}
