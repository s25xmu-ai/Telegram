package org.telegram.ui.Components.blur3.source;

import android.graphics.Canvas;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawableRenderNode;

import me.vkryl.core.reference.ReferenceList;

/** Crossfades the button and frosted menu sources, following Telegram 13.2.1. */
@RequiresApi(api = Build.VERSION_CODES.Q)
public final class BlurredBackgroundSourceCrossfade implements BlurredBackgroundSource {
    private final BlurredBackgroundSourceRenderNode from;
    private final BlurredBackgroundSourceRenderNode to;
    private final ReferenceList<BlurredBackgroundDrawableRenderNode> drawables = new ReferenceList<>();
    private float progress;

    public BlurredBackgroundSourceCrossfade(@NonNull BlurredBackgroundSourceRenderNode from,
                                             @NonNull BlurredBackgroundSourceRenderNode to) {
        this.from = from;
        this.to = to;
    }

    public void setProgress(float progress) {
        if (this.progress == progress) return;
        this.progress = progress;
        for (BlurredBackgroundDrawableRenderNode drawable : drawables) {
            drawable.invalidateDisplayList();
            drawable.invalidateSelf();
        }
    }

    @Override
    public void draw(Canvas canvas, float left, float top, float right, float bottom) {
        if (progress < 1f) from.draw(canvas, left, top, right, bottom);
        if (progress > 0f) {
            if (progress < 1f) canvas.saveLayerAlpha(left, top, right, bottom, (int) (255 * progress));
            to.draw(canvas, left, top, right, bottom);
            if (progress < 1f) canvas.restore();
        }
    }

    @Override
    public void dispatchOnDrawablesRelativePositionChange() {
        from.dispatchOnDrawablesRelativePositionChange();
        if (to != from) to.dispatchOnDrawablesRelativePositionChange();
    }

    @Override
    public BlurredBackgroundDrawable createDrawable() {
        final BlurredBackgroundDrawableRenderNode drawable = new BlurredBackgroundDrawableRenderNode(this);
        drawables.add(drawable);
        // Both sources must capture the whole menu region and invalidate this drawable on scroll.
        from.registerDrawable(drawable);
        if (to != from) to.registerDrawable(drawable);
        return drawable;
    }
}
