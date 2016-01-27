package com.balazs_csernai.seriescruncher.utils.converter;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public interface ModelConverter<TARGET, SOURCE> {

    TARGET convert(SOURCE source);
}
