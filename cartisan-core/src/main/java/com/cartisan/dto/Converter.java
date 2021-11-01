package com.cartisan.dto;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author colin
 */
public interface Converter<Source, Target> {
    @Mappings({})
    @InheritConfiguration
    Target convert(Source source);

    @InheritConfiguration
    List<Target> convert(List<Source> sources);
}
