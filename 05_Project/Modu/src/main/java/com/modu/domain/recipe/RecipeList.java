package com.modu.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data 
public class RecipeList {
    private long id;
    private String foodPhoto;
    private String title;
    private String food;
    private String profileImg;
    private String mNickname;
    private String mEmail;
    private double star;
    private int stars;
    private long hits;
    private String sort;
}
