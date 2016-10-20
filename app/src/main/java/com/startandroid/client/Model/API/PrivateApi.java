package com.startandroid.client.Model.API;

import com.loopj.android.http.RequestParams;
import com.startandroid.client.Model.API.BaseApi.BaseApi;
import com.startandroid.client.Model.App;

/**
 * Created by Денис on 20.09.2016.
 */
public class PrivateApi extends BaseApi {
    protected RequestParams prepareParams(RequestParams params) {
        params = super.prepareParams(params);
        params.put("accessToken", App.getInstance().getAppUser().getAccessToken());
        return params;
    }
}
