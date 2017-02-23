package org.xing.update;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;

/**
 * Created by sangbo on 16-5-19.
 */
public class UpdateEntity {

    public int versionCode = 0;
    public int isForceUpdate = 0;
    public int preBaselineCode = 0;
    public String versionName = "";
    public String downUrl = "";
    public String updateLog = "";
    public BigInteger md5 = null;
    public int fileSize = 0;


    public UpdateEntity(String json) throws JSONException {

        JSONObject jsonObject = new JSONObject(json);
        this.versionCode = jsonObject.getInt("versionCode");
        this.versionName = jsonObject.getString("versionName");
        this.isForceUpdate = jsonObject.getInt("isForceUpdate");
        this.downUrl = jsonObject.getString("downUrl");
        this.preBaselineCode = jsonObject.getInt("preBaselineCode");
        this.updateLog = jsonObject.getString("updateLog");
        String md5String = jsonObject.getString("md5");
        if(md5String != null) {
            this.md5 = new BigInteger(md5String, 16);
        }
        this.fileSize = jsonObject.getInt("fileSize");
    }
}