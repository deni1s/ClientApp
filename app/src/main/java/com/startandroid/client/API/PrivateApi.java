package com.startandroid.client.API;

import com.loopj.android.http.RequestParams;
import com.startandroid.client.API.BaseApi.BaseApi;

/**
 * Created by Денис on 20.09.2016.
 */
public class PrivateApi extends BaseApi {
    protected RequestParams prepareParams(RequestParams params) {
        params = super.prepareParams(params);
        params.put("accessToken", AppUser.getInstance().getAccessToken());
        return params;
    }
}
