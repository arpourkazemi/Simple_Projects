public class Board {
    private byte verticalLength;
    private byte horizontalLength;
    private Piece[][] pieceArray;

    Board(byte horizontalLength, byte verticalLength){
        this.setHorizontalLength(horizontalLength);
        this.setVerticalLength(verticalLength);
        setPieceArray(new Piece[horizontalLength][verticalLength]);
    }

    public void setVerticalLength(byte verticalLength) {
        this.verticalLength = verticalLength;
    }

    public byte getVerticalLength() {
        return verticalLength;
    }

    public void setHorizontalLength(byte horizontalLength) {
        this.horizontalLength = horizontalLength;
    }

    public byte getHorizontalLength() {
        return horizontalLength;
    }

    public void setPieceArray(Piece[][] pieceArray) {
        this.pieceArray = pieceArray;
    }

    public Piece[][] getPieceArray() {
        return pieceArray;
    }

    public void addPiece(byte indexX, byte indexY, Piece piece){
        this.pieceArray[indexX][indexY] = piece;
    }

    public Piece getPiece(byte indexX, byte indexY){
        return pieceArray[indexX][indexY];
    }

    void printBoard(){  // چاپ صفحه بازی
        byte vertical = this.getVerticalLength();
        byte horizontal = this.getHorizontalLength();
        for (int i = 0; i < vertical-1 ; i++) {
            for (byte j = 0; j < horizontal-1 ; j++) {
                if (this.pieceArray[j][i] == null) {
                    System.out.print("    ");
                } else if(!this.pieceArray[j][i].isAlive()){
                    System.out.print("    ");
                }else if(!this.pieceArray[j][i].getPlayer().isAlive()){
                    System.out.print("    ");
                } else {
                    System.out.print(this.pieceArray[j][i].getIcon());
                }
                System.out.print("│");
            }
            if (this.pieceArray[horizontal-1][i] == null){
                System.out.print("    ");
            }  else if(!this.pieceArray[horizontal-1][i].isAlive()){
                System.out.print("    ");
            }else if(!this.pieceArray[horizontal-1][i].getPlayer().isAlive()){
                System.out.print("    ");
            } else {
                System.out.print(this.pieceArray[horizontal-1][i].getIcon());
            }
            System.out.println();
            for (byte j = 0; j < horizontal-1 ; j++) {
                System.out.print("────");
                System.out.print("┼");
            }
            System.out.print("────");
            System.out.println();
        }
        for (byte j = 0; j < horizontal-1 ; j++) {
            if (this.pieceArray[j][vertical-1] == null){
                System.out.print("    ");
            }  else if(!this.pieceArray[j][vertical-1].isAlive()){
                System.out.print("    ");
            }else if(!this.pieceArray[j][vertical-1].getPlayer().isAlive()){
                System.out.print("    ");
            } else {
                System.out.print(this.pieceArray[j][vertical-1].getIcon());
            }
            System.out.print("│");
        }
        if (this.pieceArray[horizontal-1][vertical-1] == null){
            System.out.print("    ");
        }  else if(!this.pieceArray[horizontal-1][vertical-1].isAlive()){
            System.out.print("    ");
        }else if(!this.pieceArray[horizontal-1][vertical-1].getPlayer().isAlive()){
            System.out.print("    ");
        } else {
            System.out.print(this.pieceArray[horizontal-1][vertical-1].getIcon());
        }
        System.out.println();
    }
}
