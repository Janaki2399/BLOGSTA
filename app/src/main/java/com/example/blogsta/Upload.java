package com.example.blogsta;

public class Upload {
    private String title;
    private String image;
    private String desc;

    public Upload() {
        //empty constructor needed
    }
    public Upload(String title,String desc,String image)
    {
        this.title=title;
        this.desc=desc;
        this.image=image;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image=image;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc=desc;
    }
}
