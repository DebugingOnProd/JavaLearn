package org.lhq.design.filter;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;


    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}
