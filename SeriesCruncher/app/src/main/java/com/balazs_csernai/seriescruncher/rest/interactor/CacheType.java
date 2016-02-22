package com.balazs_csernai.seriescruncher.rest.interactor;

import android.support.annotation.IntDef;

import com.octo.android.robospice.persistence.DurationInMillis;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Erik_Markus_Kramli on 2016-02-22.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({CacheType.DEFAULT, CacheType.SKIP_CACHE, CacheType.CACHE_ONLY})
public @interface CacheType {
    long DEFAULT = DurationInMillis.ONE_DAY;
    long SKIP_CACHE = DurationInMillis.ALWAYS_EXPIRED;
    long CACHE_ONLY = DurationInMillis.ALWAYS_RETURNED;
}
