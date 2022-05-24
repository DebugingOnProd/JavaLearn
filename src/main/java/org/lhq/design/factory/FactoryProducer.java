package org.lhq.design.factory;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("DataSource")) {
            return new DataSourceFactory();
        } else if (choice.equalsIgnoreCase("DBDriver")) {
            return new DBDriverFactory();
        }
        return null;
    }

}
