package org.lhq.design.factory;

import org.lhq.entity.enums.FactoryEnum;

public class FactoryProducer {
    public static AbstractFactory getFactory(FactoryEnum factoryEnum) {
        AbstractFactory factory = null;
        switch (factoryEnum){
            case DbType ->  factory = new DBDriverFactory();
            case DataSource ->  factory = new DataSourceFactory();
        }
        return factory;

    }

}
