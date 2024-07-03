module com.tuandev.excelhandle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires java.desktop;

    exports com.tuandev.excelhandle.views;
    exports com.tuandev.excelhandle.models;

    exports com.tuandev.excelhandle.controllers;
    opens com.tuandev.excelhandle.controllers to javafx.fxml;
}