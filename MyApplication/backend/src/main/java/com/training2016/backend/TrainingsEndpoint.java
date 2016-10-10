/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.training2016.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.training2016.android.http.HttpClient;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "trainingApi",
        version = "2016",
        namespace = @ApiNamespace(
                ownerDomain = "backend.training2016.com",
                ownerName = "backend.training2016.com",
                packagePath = ""
        )
)
public class TrainingsEndpoint {

    public static final String STATS_URL = "https://dl.dropboxusercontent.com/u/20755008/response.json";

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "getStats")
    public DataBean getStats() {
        DataBean response = new DataBean();
        String data = new HttpClient().get(STATS_URL);
        response.setData(data);
        return response;
    }

}
