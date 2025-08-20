package org.demo.shop1.modules.categories.application;

import java.util.logging.Logger;

public class CategoryApplication {

    protected static final Logger logger = Logger.getLogger(CategoryApplication.class.getName());
    protected String nameClass;

    protected void infoMethod(String nameMethod, String message) {
        logger.info(String.format("Info > %s > %s: %s", nameClass, nameMethod, message));
    }

    protected void startMethod(String nameMethod) {
        logger.info(String.format("Start > %s > %s", nameClass, nameMethod));
    }

    protected void endMethod(String nameMethod) {
        logger.info(String.format("End > %s > %s", nameClass, nameMethod));
    }
}
