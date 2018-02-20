package halestormxv.utils.interfaces;

public interface IRuneCraftLevel
{
    public void gainRuneLevel(int runeLevel);

    public void setRuneLevel(int runeLevel);

    public int getRuneLevel();

    public void syncToClient();
}
