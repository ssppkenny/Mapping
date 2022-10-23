package org.example.mapping.filter;


import ma.glasnost.orika.Filter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Property;
import ma.glasnost.orika.metadata.Type;
import ognl.Ognl;
import ognl.OgnlException;

import java.util.Map;

public class MyFilter implements Filter {

    private final String expression;
    private final Object left;

    public MyFilter(Object left, String expression) {
        this.expression = expression;
        this.left = left;
    }

    @Override
    public boolean appliesTo(Property property, Property property1) {
        return true;
    }

    @Override
    public boolean filtersSource() {
        return true;
    }

    @Override
    public boolean filtersDestination() {
        return false;
    }

    @Override
    public Object filterSource(Object o, Type type, String s1, Type type1, String s2, MappingContext mappingContext) {
        return o;
    }

    @Override
    public Object filterDestination(Object o, Type type, String s, Type type1, String s1, MappingContext mappingContext) {
        return o;
    }

    @Override
    public boolean shouldMap(Type type, String s, Object s1, Type type1, String s2, Object o, MappingContext mappingContext) {
        try {

            Object exp = Ognl.parseExpression(expression);
            Map defaultContext = Ognl.createDefaultContext(left);
            return (Boolean) Ognl.getValue(exp, defaultContext, left);

        } catch (OgnlException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Type getAType() {
        return null;
    }

    @Override
    public Type getBType() {
        return null;
    }
}
