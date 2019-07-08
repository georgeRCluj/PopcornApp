package com.movie.popcornapp.extensions;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author george.radu on 2019-07-08.
 */
public class GetErrorMessage {

    public String processErrorMessage(String jsonString) {
        String errorMessage = "";
        try {
            JSONObject objJson = new JSONObject(jsonString);
            if (objJson.has("errors")) {
                JSONArray detailsObject = objJson.getJSONArray("errors");
                for (int i = 0; i < detailsObject.length(); i++) {
                    String detailObject = detailsObject.get(i).toString();
                    errorMessage = errorMessage + detailObject;
                }
            }
        } catch (Exception ex) {
            return "";
        }

        return  errorMessage;
    }

}
