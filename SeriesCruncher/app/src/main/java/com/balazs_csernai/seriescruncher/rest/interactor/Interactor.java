package com.balazs_csernai.seriescruncher.rest.interactor;

/**
 * Created by Erik_Markus_Kramli on 2016-02-22.
 */
public interface Interactor<RESULT> {

    Class<RESULT> getResultType();

    RESULT execute() throws Exception;

    void cancel();
}
