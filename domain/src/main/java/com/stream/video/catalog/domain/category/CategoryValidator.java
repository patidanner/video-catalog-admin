package com.stream.video.catalog.domain.category;

import com.stream.video.catalog.domain.validation.Error;
import com.stream.video.catalog.domain.validation.ValidationHandler;
import com.stream.video.catalog.domain.validation.Validator;

/*
* Validators can change during time, that's why it is kept
* separated from the Entity itself.
* However, to follow DDD, this CategoryValidator is called from
* the Entity itself, from a "Validate" method available in Category entity.
* */
public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    protected void validate() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
