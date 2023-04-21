package lh.test;

import lh.annotations.Getter;
import lh.annotations.Setter;

/**
 * @author lh
 */
@Setter
@Getter
public class Apple {
    private String age;
    private String color;

    public Apple(String age, String color) {
        this.age = age;
        this.color = color;
    }
}
