package com.balazs_csernai.seriescruncher.seriesdetails.request;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

import com.balazs_csernai.seriescruncher.image.ImageLoader;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterEntity;
import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by ErikKramli on 2016.01.31..
 */
public class PosterRequest extends SpiceRequest<PosterEntity> {

    private final ImageLoader imageLoader;
    private final String url;
    
    public PosterRequest(String url, ImageLoader imageLoader) {
        super(PosterEntity.class);
        this.url = url;
        this.imageLoader = imageLoader;
    }

    @Override
    public PosterEntity loadDataFromNetwork() throws Exception {
        Bitmap poster = imageLoader.load(url);

        Palette palette = Palette.from(poster).generate();
        Palette.Swatch swatch = palette.getSwatches().get(0);

        return new PosterEntity()
                .setPoster(poster)
                .setBackgroundColor(swatch.getRgb())
                .setTextColor(swatch.getTitleTextColor());
    }
}
