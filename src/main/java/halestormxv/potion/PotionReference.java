package halestormxv.potion;

import halestormxv.potion.potions.PotionDispel;
import halestormxv.potion.potions.PotionBlast;
import halestormxv.potion.potions.PotionLearning;
import halestormxv.potion.potions.PotionRecall;

public class PotionReference
{

    public static final PotionReference INSTANCE = new PotionReference();

    public PotionBase FUSE = new PotionBlast(true, 16711680);
    public PotionTypeBase TYPE_FUSE_NORMAL = new PotionTypeBase(FUSE, 20 * 20, 0, "fuse_normal");
    public PotionTypeBase TYPE_FUSE_STRONG = new PotionTypeBase(FUSE, 20 * 30, 1, "fuse_strong");
    public PotionTypeBase TYPE_FUSE_QUICK = new PotionTypeBase(FUSE, 20 * 10, 0, "fuse_quick");


    public PotionBase RECALL = new PotionRecall(false, 16773632);
    public PotionTypeBase TYPE_RECALL_NORMAL = new PotionTypeBase(RECALL, 20 * 40, 0, "recall_normal");
    public PotionTypeBase TYPE_RECALL_STRONG = new PotionTypeBase(RECALL, 20 * 30, 1, "recall_strong");
    public PotionTypeBase TYPE_RECALL_LONG = new PotionTypeBase(RECALL, 20 * 80, 0, "recall_long");

    public PotionBase LEARNING = new PotionLearning(false, 14024505);
    public PotionTypeBase TYPE_LEARNING_NORMAL = new PotionTypeBase(LEARNING, 20*60*4, 0, "learning_normal");
    public PotionTypeBase TYPE_LEARNING_STRONG = new PotionTypeBase(LEARNING, 20*60*2, 1, "learning_strong");
    public PotionTypeBase TYPE_LEARNING_LONG = new PotionTypeBase(LEARNING, 20*60*6, 0, "learning_long");

    public PotionBase DISPEL = new PotionDispel(true, 0x105955);
    public PotionTypeBase TYPE_DISPEL = new PotionTypeBase(DISPEL, 0, 0, "dispel_normal");



    public PotionBase CHARGED = new PotionBase(false, 7667860, "charged")
    {
        @Override
        public boolean isInstant() {
            return true;
        }
    };
    public PotionTypeBase TYPE_CHARGED = new PotionTypeBase(CHARGED, 0, 0, "charged_normal");

    public PotionBase CHARGED2 = new PotionBase(false, 16711899, "charged2")
    {
        @Override
        public boolean isInstant() {
            return true;
        }
    };
    public PotionTypeBase TYPE_CHARGED2 = new PotionTypeBase(CHARGED2, 0, 0, "charged2_normal");

}