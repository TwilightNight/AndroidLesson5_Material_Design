package com.example.shana.androidlesson5_material_design.Utils;

import com.google.common.base.Optional;

/**
 * Created by shana on 2015/12/17.
 */
public class FailedDataRequest implements DataRequest {
    @Override
    public Optional<String> execute() {
        return Optional.absent();
    }
}
