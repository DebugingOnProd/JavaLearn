package org.lhq.design.filter;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriaSingle implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return persons.stream()
                .filter(item -> item.getMaritalStatus().equalsIgnoreCase("Single"))
                .collect(Collectors.toList());
    }
}
