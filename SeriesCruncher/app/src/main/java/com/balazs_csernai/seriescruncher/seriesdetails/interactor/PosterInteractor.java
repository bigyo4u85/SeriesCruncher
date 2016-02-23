package com.balazs_csernai.seriescruncher.seriesdetails.interactor;

import android.graphics.Bitmap;

import com.balazs_csernai.seriescruncher.rest.interactor.Interactor;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterEntity;
import com.balazs_csernai.seriescruncher.utils.image.BlurStrategy;
import com.balazs_csernai.seriescruncher.utils.image.ImageLoader;
import com.balazs_csernai.seriescruncher.utils.ui.color.ColorProvider;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.31..
 */
public class PosterInteractor implements Interactor<PosterEntity> {

    private final ImageLoader imageLoader;
    private final ColorProvider colorProvider;
    private final BlurStrategy blurStrategy;
    private String url;

    @Inject
    public PosterInteractor(ImageLoader imageLoader, ColorProvider colorProvider, BlurStrategy blurStrategy) {
        this.imageLoader = imageLoader;
        this.colorProvider = colorProvider;
        this.blurStrategy = blurStrategy;
    }

    public void setRequestParam(String url) {
        this.url = url;
    }

    @Override
    public Class<PosterEntity> getResultType() {
        return PosterEntity.class;
    }

    @Override
    public PosterEntity execute() throws Exception {
        Bitmap poster = imageLoader.load(url);
        Bitmap posterBackground = blurStrategy.blur(poster);
        colorProvider.generateColorPalette(poster);

        return new PosterEntity()
                .setPoster(poster)
                .setPosterBackground(posterBackground)
                .setPrimaryColor(colorProvider.getPrimaryColor())
                .setSecondaryColor(colorProvider.getSecondaryColor())
                .setAccentColor(colorProvider.getAccentColor());
    }

    @Override
    public void cancel() {
        imageLoader.cancel();
    }
}
