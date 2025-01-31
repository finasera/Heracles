package earth.terrarium.heracles.client.screens.quest.rewards;

import com.teamresourceful.resourcefullib.client.scissor.ScissorBoxStack;
import earth.terrarium.heracles.api.client.DisplayWidget;
import earth.terrarium.heracles.common.constants.ConstantComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public record RewardListHeadingWidget(boolean complete, int rewards, int claimed) implements DisplayWidget {
    private static final String DESC_SINGULAR = "reward.heracles.progress.desc.incomplete.singular";
    private static final String DESC_PLURAL = "reward.heracles.progress.desc.incomplete.plural";
    private static final String DESC_COMPLETE = "reward.heracles.progress.desc.complete";
    private static final String DESC_LOCKED = "reward.heracles.progress.desc.locked";

    @Override
    public void render(GuiGraphics graphics, ScissorBoxStack scissor, int x, int y, int width, int mouseX, int mouseY, boolean hovered, float partialTicks) {
        graphics.fill(x, y, x + width, y + 30, 0xD0000000);
        graphics.renderOutline(x, y, width, 30, 0xFFFFFFFF);

        String desc = rewards == claimed ? DESC_COMPLETE : (!complete ? DESC_LOCKED : (rewards - claimed > 1 ? DESC_PLURAL : DESC_SINGULAR));
        String completion = String.format("%.0f%%", this.claimed * 100 / (double) rewards);

        graphics.drawString(
            Minecraft.getInstance().font,
            ConstantComponents.Rewards.STATUS, x + 5, y + 5, 0xFFFFFFFF,
            false
        );
        graphics.drawString(
            Minecraft.getInstance().font,
            completion, x + width - 5 - Minecraft.getInstance().font.width(completion), y + 5, 0xFFFFFFFF,
            false
        );
        graphics.drawString(
            Minecraft.getInstance().font,
            Component.translatable(desc, rewards - claimed), x + 5, y + 25 - Minecraft.getInstance().font.lineHeight, 0xFF696969,
            false
        );
    }

    @Override
    public int getHeight(int width) {
        return 30;
    }
}
