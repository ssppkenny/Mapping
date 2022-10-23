package org.example.mapping;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.example.mapping.filter.MyFilter;
import org.example.mapping.left.Left;
import org.example.mapping.left.Line;
import org.example.mapping.left.Product;
import org.example.mapping.left.Status;
import org.example.mapping.right.Right;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Left left = getLeft();
        Right right = new Right();

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Left.class, Right.class)
                .fieldAToB("version", "version")
                .fieldAToB("name", "name")
                .register();

        MyFilter filter = new MyFilter(left, "getStatus().getType().equals(\"E\")");
        mapperFactory.registerFilter(filter);

        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        mapperFacade.map(left, right);

        mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Left.class, Right.class)
//                .fieldAToB("lines", "lines")
                .fieldAToB("lines{product}", "lines{product}")
                .fieldAToB("lines{lineNumber}", "lines{lineNumber}")
                .fieldAToB("lines{quantity}", "lines{quantity}")
                .register();

        mapperFacade = mapperFactory.getMapperFacade();
        mapperFacade.map(left, right);
        System.out.println(right.getName());
        System.out.println(right.getVersion());
        System.out.println(right.getLines().size());


    }

    private void f(String ttt, String a, String b) {

    }

    private static Left getLeft() {
        Left left = new Left();
        left.setName("left");
        left.setVersion(1);

        left.setType("S");

        Status status = new Status();
        status.setType("S");

        left.setStatus(status);

        Product p = new Product();
        p.setName("product1");
        p.setManufacturer("Manufacturer1");

        Line line = new Line();
        line.setLineNumber(1);
        line.setProduct(p);
        line.setQuantity(10);

        Product p1 = new Product();
        p1.setName("product2");
        p1.setManufacturer("Manufacturer2");

        Line line1 = new Line();
        line1.setLineNumber(2);
        line1.setProduct(p1);
        line1.setQuantity(11);

        left.setLines(List.of(line, line1));
        return left;
    }

}
