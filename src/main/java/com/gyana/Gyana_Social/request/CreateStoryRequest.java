package com.gyana.Gyana_Social.request;

public class CreateStoryRequest {

    private String image;
    private String caption;

    public CreateStoryRequest() {}

    public CreateStoryRequest(String image, String caption) {
        this.image = image;
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "CreateStoryRequest{" +
                "image='" + image + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }
}
