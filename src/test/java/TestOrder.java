import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestOrder {
	public static void main(String[] args) {
        Foo foo1 = new Foo("1", "zhangzhang");
        Foo foo2 = new Foo("1", "liuliu");
        Foo foo3 = new Foo("2", "hello");
        Foo foo4 = new Foo("3", "jie");
        Foo foo5 = new Foo("3", "san");
        Foo foo6 = new Foo("3", "ww");
        List<Foo> list = new ArrayList<>();
        list.add(foo1);
        list.add(foo2);
        list.add(foo3);
        list.add(foo4);
        list.add(foo5);
        list.add(foo6);
        //Map<Integer, IntSummaryStatistics> collect = list.stream().collect(Collectors.groupingBy(Foo::getCode, Collectors.groupingBy(classifier)));
        Map<String, Long> countMap = list.stream().collect(Collectors.groupingBy(Foo::getState, Collectors.counting()));
        System.out.println(countMap.get("1"));
        System.out.println(countMap.get("2"));
        System.out.println(countMap.get("3"));
       /* System.out.println(statistics1.getSum());
        System.out.println(statistics1.getAverage());
        System.out.println(statistics1.getMax());
        System.out.println(statistics1.getMin());
        System.out.println(statistics1.getCount());

        System.out.println(statistics2.getSum());
        System.out.println(statistics2.getAverage());
        System.out.println(statistics2.getMax());
        System.out.println(statistics2.getMin());
        System.out.println(statistics2.getCount());*/
    }

}
