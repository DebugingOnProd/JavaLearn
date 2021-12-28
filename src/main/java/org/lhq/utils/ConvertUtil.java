package org.lhq.utils;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


/**
 * @author Hades
 */
public class ConvertUtil {


	private ConvertUtil(){}

    public static <T,R> List<R> resultToList(List<T> originList, Function<T,R> mapper){
        if (originList==null||originList.isEmpty()){
            return new ArrayList<>();
        }
        List<R> newList = new ArrayList<>(originList.size());
        for (T originElement : originList) {
            R newElement = mapper.apply(originElement);
            if (newElement==null){
                continue;
            }
            newList.add(newElement);
        }
        return newList;
    }
}
