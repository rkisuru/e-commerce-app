package com.rkisuru.product.category;

import lombok.Getter;

@Getter
public enum Category {

    ELECTRONICS(new SubCategory[]{SubCategory.SMARTPHONES, SubCategory.LAPTOPS}),
    FASHION(new SubCategory[]{SubCategory.MENS_WEAR, SubCategory.WOMENS_WEAR}),
    BOOKS(new SubCategory[]{SubCategory.FICTION, SubCategory.NON_FICTION});

    private final SubCategory[] subCategories;

    Category(SubCategory[] subCategories) {
        this.subCategories = subCategories;
    }

    public enum SubCategory {
        SMARTPHONES,
        LAPTOPS,
        MENS_WEAR,
        WOMENS_WEAR,
        FICTION,
        NON_FICTION
    }
}
