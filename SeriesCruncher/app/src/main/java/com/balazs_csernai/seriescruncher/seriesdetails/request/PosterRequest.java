package com.balazs_csernai.seriescruncher.seriesdetails.request;

import android.graphics.Bitmap;

import com.balazs_csernai.seriescruncher.image.ImageLoader;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterEntity;
import com.balazs_csernai.seriescruncher.utils.bitmap.BlurStrategy;
import com.balazs_csernai.seriescruncher.utils.color.ColorProvider;
import com.octo.android.robospice.request.SpiceRequest;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.31..
 */
public class PosterRequest extends SpiceRequest<PosterEntity> {

    private final ImageLoader imageLoader;
    private final ColorProvider colorProvider;
    private final BlurStrategy blurStrategy;
    private String url;

    @Inject
    public PosterRequest(ImageLoader imageLoader, ColorProvider colorProvider, BlurStrategy blurStrategy) {
        super(PosterEntity.class);
        this.imageLoader = imageLoader;
        this.colorProvider = colorProvider;
        this.blurStrategy = blurStrategy;
    }

    public void setRequestParam(String url) {
        this.url = url;
    }

    @Override
    public PosterEntity loadDataFromNetwork() throws Exception {
        Bitmap poster = imageLoader.load(url);
        Bitmap posterBackground = blurStrategy.blur(poster);
        colorProvider.generateColorPalette(poster);

        return new PosterEntity()
                .setPoster(poster)
                .setPosterBackground(posterBackground)
                .setBackgroundColor(colorProvider.getBackgroundColor())
                .setTextColor(colorProvider.getTextColor());
    }
}
