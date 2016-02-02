package com.balazs_csernai.seriescruncher.seriesdetails.request;

import android.graphics.Bitmap;

import com.balazs_csernai.seriescruncher.image.ImageLoader;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterEntity;
import com.balazs_csernai.seriescruncher.utils.color.ColorProvider;
import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by ErikKramli on 2016.01.31..
 */
public class PosterRequest extends SpiceRequest<PosterEntity> {

    private final ImageLoader imageLoader;
    private final ColorProvider colorProvider;
    private String url;
    
    public PosterRequest(ImageLoader imageLoader, ColorProvider colorProvider) {
        super(PosterEntity.class);
        this.imageLoader = imageLoader;
        this.colorProvider = colorProvider;
    }

    public void setRequestParam(String url) {
        this.url = url;
    }

    @Override
    public PosterEntity loadDataFromNetwork() throws Exception {
        Bitmap poster = imageLoader.load(url);
        colorProvider.generateColorPalette(poster);

        return new PosterEntity()
                .setPoster(poster)
                .setBackgroundColor(colorProvider.getBackgroundColor())
                .setTextColor(colorProvider.getTextColor());
    }
}
