package com.balazs_csernai.seriescruncher.utils.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import com.balazs_csernai.seriescruncher.utils.ui.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.02.05..
 */
public class AnimationImpl implements Animation {

    private static final float MIN = 0F;
    private static final float MAX = 1F;

    private final int longAnimationTime, mediumAnimationTime;
    private final Interpolator overshotInterpolator;
    private List<Animator> sequentialAnimations, parallelAnimations;

    @Inject
    public AnimationImpl(Resources resources) {
        longAnimationTime = resources.getInteger(android.R.integer.config_longAnimTime);
        mediumAnimationTime = resources.getInteger(android.R.integer.config_mediumAnimTime);
        overshotInterpolator = new OvershootInterpolator();
    }

    @Override
    public AnimationImpl create() {
        sequentialAnimations = new ArrayList<>();
        parallelAnimations = new ArrayList<>();
        return this;
    }

    @Override
    public AnimationImpl fadeOut(final View... views) {
        AnimatorSet fadeOut =  createFadeAnimator(MAX, MIN, views);
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                ViewUtils.alpha(MAX, views);
                ViewUtils.visible(views);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewUtils.gone(views);
            }
        });
        parallelAnimations.add(fadeOut);
        return this;
    }

    @Override
    public AnimationImpl fadeIn(final View... views) {
        AnimatorSet fadeIn = createFadeAnimator(MIN, MAX, views);
        fadeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                ViewUtils.alpha(MIN, views);
                ViewUtils.visible(views);
            }
        });
        parallelAnimations.add(fadeIn);
        return this;
    }

    private AnimatorSet createFadeAnimator(float fromAlpha, float toAlpha, View... views) {
        AnimatorSet fade = new AnimatorSet();
        fade.setDuration(longAnimationTime);
        List<Animator> alphaAnimators = new ArrayList<>(views.length);
        for (View v : views) {
            alphaAnimators.add(ObjectAnimator.ofFloat(v, View.ALPHA, fromAlpha, toAlpha));
        }
        fade.playTogether(alphaAnimators);
        return fade;
    }

    @Override
    public Animation reveal(final View... views) {
        AnimatorSet reveal = createRevealAnimator(MIN, MAX, views);
        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                ViewUtils.visible(views);
            }
        });
        parallelAnimations.add(reveal);
        return this;
    }

    private AnimatorSet createRevealAnimator(float from, float to, View... views) {
        AnimatorSet reveal = new AnimatorSet();
        reveal.setInterpolator(overshotInterpolator);
        reveal.setDuration(mediumAnimationTime);
        List<Animator> scaleAnimators = new ArrayList<>(views.length);
        for (View v : views) {
            scaleAnimators.add(ObjectAnimator.ofFloat(v, View.ALPHA, from, to));
            scaleAnimators.add(ObjectAnimator.ofFloat(v, View.SCALE_X, from, to));
            scaleAnimators.add(ObjectAnimator.ofFloat(v, View.SCALE_Y, from, to));
        }
        reveal.playTogether(scaleAnimators);
        return reveal;
    }

    @Override
    public AnimationImpl then() {
        addParallelAnimationsToSequentialAnimations();
        parallelAnimations = new ArrayList<>();
        return this;
    }

    private void addParallelAnimationsToSequentialAnimations() {
        if (!parallelAnimations.isEmpty()) {
            AnimatorSet animationStep = new AnimatorSet();
            animationStep.playTogether(new ArrayList<>(parallelAnimations));
            sequentialAnimations.add(animationStep);
        }
    }

    @Override
    public void play() {
        addParallelAnimationsToSequentialAnimations();

        AnimatorSet fullAnimation = new AnimatorSet();
        fullAnimation.playSequentially(sequentialAnimations);
        fullAnimation.start();
    }
}
