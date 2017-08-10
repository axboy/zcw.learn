package local.zcw.demo.java8;

import java.util.function.*;

/**
 * 作者 zcw
 * 时间 2017/8/10 14:43
 * 描述 测试java8语法
 */
public class Main {
    public static void main(String[] args) {
        //Function<T,R>，T输入，R输出
        Function<String, Integer> function = (str) -> {
            return Integer.parseInt(str);
        };
        System.out.println("function: " + function.apply("15"));

        //Predicate<T>，T输入，boolean输出
        Predicate<String> predicate = (str) -> {
            return str.equals(true);
        };
        System.out.println("predicate: " + predicate.test("false"));

        //Consumer<T>，T输入，执行动作，无返回值
        Consumer<String> consumer = (str) -> {
            System.out.println(str);
        };
        consumer.accept("consumer: todo");

        //Supplier<T>，无输入，返回T
        Supplier<String> supplier = () -> {
            return "getString";
        };
        System.out.println("supplier: " + supplier.get());

        //BinartOperator<T>，两个T作为输入，返回一个T，适用于reduce
        BinaryOperator<Integer> binaryOperator = (x, y) -> {
            return x + y;
        };
        System.out.println("binaryOperator: " + binaryOperator.apply(1, 5));
    }
}
