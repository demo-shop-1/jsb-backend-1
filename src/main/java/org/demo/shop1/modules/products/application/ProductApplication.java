package org.demo.shop1.modules.products.application;

import java.util.logging.Logger;

public class ProductApplication {

    protected static final Logger logger = Logger.getLogger(ProductApplication.class.getName());
    protected String nameClass;
    protected String nameMethod;

    protected void startMethod() {
        logger.info(String.format("Start > %s > %s", nameClass, nameMethod));
    }

    protected void endMethod() {
        logger.info(String.format("End > %s > %s", nameClass, nameMethod));
    }
}
