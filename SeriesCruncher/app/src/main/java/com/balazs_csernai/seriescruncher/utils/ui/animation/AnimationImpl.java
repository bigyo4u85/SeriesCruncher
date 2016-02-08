package com.balazs_csernai.seriescruncher.utils.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
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

    private final int longDuration, mediumDuration;
    private final Interpolator overshot, anticipateOvershot;
    private List<Animator> sequentialAnimations, parallelAnimations;

    @Inject
    public AnimationImpl(Resources resources) {
        longDuration = resources.getInteger(android.R.integer.config_longAnimTime);
        mediumDuration = resources.getInteger(android.R.integer.config_mediumAnimTime);
        overshot = new OvershootInterpolator();
        anticipateOvershot = new AnticipateOvershootInterpolator();
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
        fade.setDuration(longDuration);
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
        reveal.setInterpolator(overshot);
        reveal.setDuration(mediumDuration);
        List<Animator> revealAnimators = new ArrayList<>(views.length * 3);
        for (View v : views) {
            revealAnimators.add(ObjectAnimator.ofFloat(v, View.ALPHA, from, to));
            revealAnimators.add(ObjectAnimator.ofFloat(v, View.SCALE_X, from, to));
            revealAnimators.add(ObjectAnimator.ofFloat(v, View.SCALE_Y, from, to));
        }
        reveal.playTogether(revealAnimators);
        return reveal;
    }

    @Override
    public Animation rotate(float degree, View... views) {
        AnimatorSet rotate = new AnimatorSet();
        rotate.setInterpolator(anticipateOvershot);
        rotate.setDuration(mediumDuration);
        List<Animator> rotateAnimators = new ArrayList<>(views.length);
        for (View v : views) {
            float start = v.getRotation();
            float end = start + degree;
            rotateAnimators.add(ObjectAnimator.ofFloat(v, View.ROTATION, start, end));
        }
        rotate.playTogether(rotateAnimators);
        parallelAnimations.add(rotate);
        return this;
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
        fullAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                parallelAnimations.clear();
                sequentialAnimations.clear();
            }
        });
        fullAnimation.start();
    }
}
