package top.rongxiaoli.plugins.PicturesPlugin;

import java.util.List;

public class PictureAPIDataStruct {
    public PictureAPIDataStruct(String error, List<Data> data) {
        this.error = error;
        this.data = data;
    }
    public String error;
    public List<Data> data;

    public String getError() {
        return this.error;
    }

    public List<Data> getData() {
        return this.data;
    }
    public static class Urls {
        public String regular;
        public String original;
    }
    public static class Data {
        public int pid;
        public int p;
        public int uid;
        public String title;
        public String author;
        public boolean r18;
        public int width;
        public int height;
        public List<String> tags;
        public String ext;
        public long uploadDate;
        public Urls urls;
    }
}