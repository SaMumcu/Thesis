package com.sabihamumcu.tez.model;

import java.util.List;

/**
 * Created by sabis on 1/14/2018.
 */

public class CategoriesModel {
    private String categoryTitle;

    private List<SubCategories> subCategories;

    public CategoriesModel(String categoryTitle, List<SubCategories> subCategories) {
        this.categoryTitle = categoryTitle;
        this.subCategories = subCategories;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<SubCategories> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategories> subCategories) {
        this.subCategories = subCategories;
    }
}
