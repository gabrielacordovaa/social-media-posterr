package br.com.social.media.posterr.application.enums;

import lombok.Getter;
@Getter
public enum PostType {

    PERSONAL(1, "PERSONAL"),
    QUOTE(2, "QUOTE"),
    REPOST(3, "REPOST");

    private  Integer code;
    private String value;

    PostType(Integer code, String value){
        this.code = code;
        this.value = value;
    }

    public static PostType getPostType(String value){
        for(PostType type : PostType.values()){
            if(type.value.equalsIgnoreCase(value)){
                return type;
            }
        }
        return null;
    }

    public static Integer getPostTypeCode(String value){
        PostType type = getPostType(value);
        return type != null ? type.getCode() : null;
    }
}
