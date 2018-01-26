package halestormxv;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings
{

    public static KeyBinding runeBag;

    public static void init()
    {
        runeBag = new KeyBinding("key.runicsorcery", Keyboard.KEY_T, "key.categories.runebag");
        ClientRegistry.registerKeyBinding(runeBag);
    }
}