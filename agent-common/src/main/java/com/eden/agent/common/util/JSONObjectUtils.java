package com.eden.agent.common.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Create by zhaoxianghui on 2017/8/16.
 */
public class JSONObjectUtils {

    public static JSONObject getJsonObject(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static JSONObject append(JSONObject json, String key, Object data) {
        try {
            json.put(key, data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static JSONObject getJsonObject(JSONObject json, String key) {
        try {
            return json.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JSONObject getJsonObject(JSONArray jsonArray, int index) {
        try {
            return jsonArray.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getInt(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static String getString(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JSONArray getJsonArray(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
