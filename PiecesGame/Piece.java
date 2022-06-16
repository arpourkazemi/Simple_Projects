public class Piece {
    private Player player;
    private String icon;  // icon in board
    private byte attackArrow;
    private boolean alive = true;
    private String absoluteIcon; // icon in menu

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setAttackArrow(byte attackArrow) {
        this.attackArrow = attackArrow;
    }

    public byte getAttackArrow() {
        return attackArrow;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAbsoluteIcon(String absoluteIcon) {
        this.absoluteIcon = absoluteIcon;
    }

    public String getAbsoluteIcon() {
        return absoluteIcon;
    }

    public void setAttackArrowAndIcon(byte attackArrow, String icon,String absoluteIcon){
        this.setAttackArrow(attackArrow);
        this.setIcon(icon);
        this.setAbsoluteIcon(absoluteIcon);
    }
}
