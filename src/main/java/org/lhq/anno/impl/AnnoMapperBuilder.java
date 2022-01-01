package org.lhq.anno.impl;

import org.lhq.anno.Select;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnnoMapperBuilder {
    private static final Set<Class<? extends Annotation>> annotationTypes = Stream.of(Select.class, Select.List.class).collect(Collectors.toSet());

}
