package halestormxv;

import halestormxv.utils.Reference;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings
{

    public static KeyBinding runeBag, getRuneCraftLevel;

    public static void init()
    {
        runeBag = new KeyBinding("key.runebag", Keyboard.KEY_T, Reference.KEY_BINDINGS_CATEGORY);
        ClientRegistry.registerKeyBinding(runeBag);

        getRuneCraftLevel = new KeyBinding("key.runecraftlevel", Keyboard.KEY_O, Reference.KEY_BINDINGS_CATEGORY);
        ClientRegistry.registerKeyBinding(getRuneCraftLevel);
    }
}