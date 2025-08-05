package org.demo.shop1.modules.products.application;

import java.util.logging.Logger;

public class ProductApplication {
    protected static final Logger logger = Logger.getLogger(ProductApplication.class.getName());

    protected void startMethod(String kindOfClass, String kindOfMethod) {
        logger.info(String.format("Start > %s > %s", kindOfClass, kindOfMethod));
    }

    protected void endMethod(String kindOfClass, String kindOfMethod) {
        logger.info(String.format("End > %s > %s", kindOfClass, kindOfMethod));
    }
}
