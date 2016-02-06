package com.balazs_csernai.seriescruncher.utils.ui.animation;

import android.view.View;

/**
 * Created by ErikKramli on 2016.02.06..
 */
public interface Animation {

    /**
     * Creates a new animation builder object.
     * @return The animation builder object.
     */
    Animation create();

    /**
     * Animates the alpha property from 1 to 0.
     * @param views Views to animate.
     * @return The animation builder object.
     */
    Animation fadeOut(View... views);

    /**
     * Animates the alpha property from 0 to 1.
     * @param views Views to animate.
     * @return The animation builder object.
     */
    Animation fadeIn(View... views);

    /**
     * Animates the alpha property from 0 to 1 and scales the view form 0 to 1.
     * @param views Views to animate.
     * @return The animation builder object.
     */
    Animation reveal(View... views);

    /**
     * Wraps the current animations of the builder object into an animation step and creates the
     * next step. The wrapped animations will be played parallel.
     * @return The animation builder object.
     */
    Animation then();

    /**
     * Executes the animation based on the builder object.
     */
    void play();
}
