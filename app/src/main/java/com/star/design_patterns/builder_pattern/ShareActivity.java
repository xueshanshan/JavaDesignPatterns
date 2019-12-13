package com.star.design_patterns.builder_pattern;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.IntDef;

import org.json.JSONArray;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xueshanshan
 * @date 2019-12-13
 */
public class ShareActivity extends Activity {
    public static void showActivity(ShareBuilder shareBuilder) {
        //TODO 根据shareBuilder里面的字段进行相应处理
    }

    public static class ShareBuilder {
        public static final int shareCount4 = 4;
        public static final int shareCount8 = 8;

        @IntDef({shareCount4, shareCount8})
        @Retention(RetentionPolicy.SOURCE)
        public @interface ShareCount {}

        private Context context;
        private String id;
        private String kind;
        private String dm_trace_id;
        private String dm_scene_id;
        private String dm_item_id;
        private String picture_path;
        private boolean newTask;
        private JSONArray channelArray;
        private @ShareCount
        int shareCount = shareCount8;

        public ShareBuilder(Context context, String id, String kind) {
            this.context = context;
            this.id = id;
            this.kind = kind;
        }

        public ShareBuilder dmId(String dm_trace_id, String dm_scene_id, String dm_item_id) {
            this.dm_trace_id = dm_trace_id;
            this.dm_scene_id = dm_scene_id;
            this.dm_item_id = dm_item_id;
            return this;
        }

        public ShareBuilder picturePath(String picture_path) {
            this.picture_path = picture_path;
            return this;
        }

        public ShareBuilder newTask(boolean newTask) {
            this.newTask = newTask;
            return this;
        }

        public ShareBuilder channelArray(JSONArray channelArray) {
            this.channelArray = channelArray;
            return this;
        }

        public ShareBuilder shareCount(@ShareCount int shareCount) {
            this.shareCount = shareCount;
            return this;
        }

        public void buildShareActivity() {
            ShareActivity.showActivity(this);
        }
    }


    private void use() {
        new ShareBuilder(this, "id", "kindId")
                .picturePath("picture_path")
                .shareCount(ShareBuilder.shareCount4)
                .buildShareActivity();
    }
}
