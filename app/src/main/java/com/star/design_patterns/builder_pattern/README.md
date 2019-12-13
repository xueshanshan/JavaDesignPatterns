## 构建者模式

#### 实用场景
    如果有一个类有好多参数，并且每次new该对象可能需要传入不同的参数，所以可能就需要很多的构造方法，这无疑是不合理的
    一般构建者类都以静态内部类的形式写在需要被创建的类里面，因为一般情况下该构建者类都是为该外部类服务的
    比如我有一个分享页面，而这个分享页面根据不同情况需要各种不同参数，这个时候我就需要该模式来实现
    
```java
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
    
    //使用方式
    private void use() {
        new ShareBuilder(this, "id", "kindId")
             .picturePath("picture_path")
             .shareCount(ShareBuilder.shareCount4)
             .buildShareActivity();
    }
}
```

#### 用到Builder模式的例子

    - Android中的AlertDialog.Builder
    - OkHttp中OkHttpClient的创建
    - Retrofit中Retrofit对象的创建

