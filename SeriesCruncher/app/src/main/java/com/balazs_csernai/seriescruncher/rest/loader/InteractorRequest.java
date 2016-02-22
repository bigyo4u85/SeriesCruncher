package com.balazs_csernai.seriescruncher.rest.loader;

import com.balazs_csernai.seriescruncher.rest.interactor.Interactor;
import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by Erik_Markus_Kramli on 2016-02-22.
 */
final class InteractorRequest<RESULT> extends SpiceRequest<RESULT> {

    private final Interactor<RESULT> interactor;

    public InteractorRequest(Interactor<RESULT> interactor) {
        super(interactor.getResultType());
        this.interactor = interactor;
    }

    @Override
    public RESULT loadDataFromNetwork() throws Exception {
        return interactor.execute();
    }

    @Override
    public void cancel() {
        super.cancel();
        interactor.cancel();
    }
}
